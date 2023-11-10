package com.exam.dao.student.Impl;

import com.exam.dao.counsellor.CourseDao;
import com.exam.dao.student.LeaveDao;
import com.exam.entity.Course;
import com.exam.entity.Leave;
import com.exam.entity.LeaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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




    //    CM07-01
//    功能名称： 添加学生信息模块
    @Override
    public int addLeave(Leave leave) {
        String addsql="insert IGNORE into sys_leave(leaveID,courseID,reason,daynum,status) values(?,?,?,?,?)";
        Object[] acc= {leave.getLeaveID(),leave.getCourseID(),leave.getReason(),leave.getDayNum(),leave.getStatus()};
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int add = jdbcTemplate.update(addsql, acc);
        return add;
    }

//    CM07-02
//    功能名称： 删除学生信息模块
    @Override
    public int deleteLeave(List<Integer> ids) {
        String deletesql="delete from sys_leave where leaveID in ?";
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int delete = jdbcTemplate.update(deletesql, ids);
        return delete;

    }

    @Override
    public Leave findbyID(int id) {
        String IDsql="select * form sys_leave where leaveID = ? ";
        Leave leave = jdbcTemplate.queryForObject(IDsql,new BeanPropertyRowMapper<>(Leave.class),id);

        return leave;
    }

    @Override
    public int audit(Leave leave) {
        String audSql="update sys_leave set audittime=?, status=? where leaveID=?";
        Object[] acc= {leave.getAuditTime(),leave.getStatus(),leave.getLeaveID()};
        int aud = jdbcTemplate.update(audSql, acc);
        return aud;
    }


}
