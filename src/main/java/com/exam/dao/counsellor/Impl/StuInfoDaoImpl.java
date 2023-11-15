package com.exam.dao.counsellor.Impl;

import com.exam.dao.counsellor.StuInfoDao;
import com.exam.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//    CM03
@Repository
public class StuInfoDaoImpl implements StuInfoDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //显示所有信息
    @Override
    public List<Student> showStudent() {
        String querysql="select * from sys_student" ;

        List<Student> students = jdbcTemplate.query(querysql,new BeanPropertyRowMapper<Student>(Student.class));
        return students;
    }
    @Override
    public List<Student> findAllWithPagination(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String querysql = "select * from sys_student limit ? offset ?";
        BeanPropertyRowMapper<Student> stuBeanPropertyRowMapper = new BeanPropertyRowMapper<>(Student.class);
        List<Student> studentList =  jdbcTemplate.query(querysql, stuBeanPropertyRowMapper, pageSize, offset);
        return studentList;
    }

    @Override
    public int getTotalCount() {
        String countQuery = "SELECT COUNT(*) FROM sys_student";
        return jdbcTemplate.queryForObject(countQuery, Integer.class);
    }



    //    CM07-01
//    功能名称： 添加学生信息模块
    @Override
    public int addStudentinfo(Student student) {
        String addsql="insert IGNORE into sys_student(stuID,classID,stuName,sex,address,stuTel,contact,contactTel) values(?,?,?,?,?,?,?,?)";
        Object[] acc= {student.getStuID(),student.getClassID(),student.getStuName(),student.getSex(),student.getAddress(),
                student.getStuTel(),student.getContact(),student.getContactTel()};
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int add = jdbcTemplate.update(addsql, acc);
        return add;
    }

//    CM07-02
//    功能名称： 删除学生信息模块
    @Override
    public int deleteStudent(String id) {
        String deletesql="delete from sys_student where stuID = ?";
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int delete = jdbcTemplate.update(deletesql, id);
        return delete;

    }

//    CM07-03
//    功能名称： 修改学生信息模块
    @Override
    public int updataStudent(Student student) {
        String updataql="update sys_student set classID=? , stuName=? , address=?,stuTel=? where stuID=?";
        Object[] acc= {student.getClassID(),student.getStuName(),student.getAddress(),student.getStuTel(),student.getStuID()};
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int updata = jdbcTemplate.update(updataql, acc);
        return updata;
    }



//    CM07-04
//    功能名称： 查询学生信息模块
    @Override
    public List<Student> findByName(String stuName,int page, int pageSize) {
        String findByName="select * from sys_student where stuName like concat('%',?,'%') limit ? offset ?";
        int offset = (page - 1) * pageSize;
        RowMapper<Student> rowMapper= new BeanPropertyRowMapper<>(Student.class);

        List<Student> studentList = jdbcTemplate.query(findByName,rowMapper,stuName, pageSize, offset);
        return studentList;
    }
    @Override
    public int getTotalCountByName(String stuName) {
        String countQuery = "SELECT COUNT(*) FROM sys_student where stuName like concat('%',?,'%')";
        return jdbcTemplate.queryForObject(countQuery, Integer.class,stuName);
    }
}
