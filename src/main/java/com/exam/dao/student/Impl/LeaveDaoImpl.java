package com.exam.dao.student.Impl;

import com.exam.dao.student.LeaveDao;
import com.exam.entity.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

//    CM03
@Repository
public class LeaveDaoImpl implements LeaveDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //显示所有信息
    @Override
    public List<Leave> showLeave() {
        String querysql="select * from sys_leave" ;

        List<Leave> LeaveList = jdbcTemplate.query(querysql,new BeanPropertyRowMapper<>(Leave.class));
        return LeaveList;
    }



    @Override
    public List<Leave> findAllWithPagination(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
//        String querysql = "select * from sys_leave limit ? offset ?";
        String querysql ="select sl.leaveID,sc.courseName,sl.reason,sl.daynum,sl.stuNo,sl.applytime,sl.status,sl.audittime,sl.opinion\n" +
                         "FROM sys_leave sl JOIN sys_course sc on sl.courseID = sc.courseID  limit ? offset ?";
        BeanPropertyRowMapper<Leave> depBeanPropertyRowMapper = new BeanPropertyRowMapper<>(Leave.class);
        List<Leave> departmentList =  jdbcTemplate.query(querysql, depBeanPropertyRowMapper, pageSize, offset);
        return departmentList;
    }

    @Override
    public int getTotalCount() {
        String countQuery = "SELECT COUNT(*) FROM sys_leave";
        return jdbcTemplate.queryForObject(countQuery, Integer.class);
    }




    //    CM07-01
//    功能名称： 添加学生信息模块
    @Override
    public int addLeave(Leave leave) {
//        String addsql="insert IGNORE into sys_leave(leaveID,courseID,reason,daynum,applytime,status,stuNo) values(?,?,?,?,now(),?,?)";
        String addsql="INSERT INTO sys_leave (leaveID, reason, daynum, stuNo, applytime, status,courseID)\n" +
                      "SELECT ?,?,?,?,now(),?,courseID FROM sys_course WHERE courseName = ?;";
        Object[] acc = {leave.getLeaveID(), leave.getReason(), leave.getDaynum(), leave.getStuNo(), leave.getStatus(),leave.getCourseName()};
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int add = jdbcTemplate.update(addsql, acc);
        return add;
    }

//    CM07-02
//    功能名称： 删除学生信息模块
    @Override
    public int deleteLeave(String id) {
        String deletesql="delete from sys_leave where leaveID = ?";
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int delete = jdbcTemplate.update(deletesql, id);
        return delete;

    }

//

    @Override
    public int audit(Leave leave) {
        String audSql="update sys_leave set reason=?, status=?,audittime = now() where leaveID=?";
        Object[] acc= {leave.getReason(),leave.getStatus(),leave.getLeaveID()};
        int aud = jdbcTemplate.update(audSql, acc);
        return aud;
    }

    @Override
    public List<Leave> findAllWithPaginationBystuID(String stuID, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
//        String querysql = "select * from sys_leave where stuNo=? limit ? offset ?";
        String querysql ="select sl.leaveID,sc.courseName,sl.reason,sl.daynum,sl.stuNo,sl.applytime,sl.status,sl.audittime,sl.opinion\n" +
                         "FROM sys_leave sl JOIN sys_course sc on sl.courseID = sc.courseID WHERE sl.stuNo = ? limit ? offset ?";
        BeanPropertyRowMapper<Leave> depBeanPropertyRowMapper = new BeanPropertyRowMapper<>(Leave.class);
        List<Leave> departmentList =  jdbcTemplate.query(querysql, depBeanPropertyRowMapper,stuID, pageSize, offset);
        return departmentList;
    }

    @Override
    public int getTotalCountByID(String stuID) {
        String countQuery = "SELECT COUNT(*) FROM sys_leave  where stuNo=? ";
        return jdbcTemplate.queryForObject(countQuery, Integer.class,stuID);
    }
}
