package com.exam.dao.Impl;

import com.exam.dao.LeaveDao;
import com.exam.entity.Course;
import com.exam.entity.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//    CM03
@Repository
public class LeaveDaoImpl implements LeaveDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Course> showCourse() {
        String querysql="select * from sys_course" ;

        List<Course> CourseList = jdbcTemplate.query(querysql,new BeanPropertyRowMapper<>(Course.class));
        return CourseList;
    }
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

        String querysql =
                "SELECT sl.leaveID, sc.courseName, sl.reason, sl.daynum, sl.stuNo, sl.applytime, sl.status, sl.audittime, sl.opinion " +
                "FROM sys_leave sl " +
                "JOIN sys_course sc ON sl.courseID = sc.courseID LIMIT ? OFFSET ?";

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
        String audSql="UPDATE sys_leave" +
                      "SET reason = ?, daynum = ?, stuNo = ?, courseID = (SELECT courseID FROM sys_course WHERE courseName = ?)" +
                      "WHERE leaveID = ?";
        Object[] acc= {leave.getReason(), leave.getDaynum(), leave.getStuNo(), leave.getCourseName(),leave.getLeaveID()};
        int aud = jdbcTemplate.update(audSql, acc);
        return aud;
    }

    @Override
    public List<Leave> findAllWithPaginationBystuID(String stuID, int page, int pageSize) {
        int offset = (page - 1) * pageSize;

        StringBuilder sql = new StringBuilder(
                "SELECT sl.leaveID, sc.courseName, sl.reason, sl.daynum, sl.stuNo, sl.applytime, sl.status, sl.audittime, sl.opinion " +
                "FROM sys_leave sl " +
                "JOIN sys_course sc ON sl.courseID = sc.courseID " +
                "WHERE 1 = 1 "
                );

        // 使用 ArrayList 来保存占位符对应的参数值
        List<Object> params = new ArrayList<>();


        // 仅在 instID 不为空时添加条件
        if (stuID!= null && !stuID.isEmpty()) {
            sql.append(" AND sl.stuNO LIKE ?");
            params.add("%" +stuID+ "%");
        }



        // 添加分页条件
        sql.append(" LIMIT ? OFFSET ?");


        // 添加分页参数值
        params.add(pageSize);
        params.add(offset);

        // 执行查询
        RowMapper<Leave> rowMapper = new BeanPropertyRowMapper<>(Leave.class);
        return jdbcTemplate.query(sql.toString(), rowMapper, params.toArray());
    }


    @Override
    public int getTotalCountByID(String stuID) {
        String countQuery = "SELECT COUNT(*) FROM sys_leave  where stuNo=? ";
        return jdbcTemplate.queryForObject(countQuery, Integer.class,stuID);
    }
}
