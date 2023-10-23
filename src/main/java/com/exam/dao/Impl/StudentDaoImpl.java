package com.exam.dao.Impl;

import com.exam.dao.StudentDao;
import com.exam.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//    CM03
@Repository
public class StudentDaoImpl implements StudentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //显示所有信息
    @Override
    public List<Student> showStudent() {
        String querysql="select * from sys_department";
        RowMapper<Student> rowMapper= new BeanPropertyRowMapper<>(Student.class);
        List<Student> depList = jdbcTemplate.query(querysql,rowMapper);
        return depList;
    }




    //    CM07-01
//    功能名称： 添加学生信息模块
    @Override
    public int addStudentinfo(Student student) {
        String addsql="insert into sys_department(depID,depName) values(?,?)";
        Object[] acc= {student.getStuID(),student.getStuName()};
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int add = jdbcTemplate.update(addsql, acc);
        return add;
    }

//    CM07-02
//    功能名称： 删除学生信息模块
    @Override
    public int deleteStudent(int id) {
        String addsql="delete from sys_department where depID = ?";
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int delete = jdbcTemplate.update(addsql, id);
        return delete;

    }

//    CM07-03
//    功能名称： 修改学生信息模块
    @Override
    public int updataStudent(Student student) {
        String addsql="update sys_department set stuName=?where stuID=?";
        Object[] acc= {student.getStuName(),student.getStuID()};
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int updata = jdbcTemplate.update(addsql, acc);
        return updata;
    }



//    CM07-04
//    功能名称： 查询学生信息模块
    @Override
    public List<Student> findByName(String stuName) {
        String findByName="select * from sys_department where stuName like concat('%',?,'%')";

        RowMapper<Student> rowMapper= new BeanPropertyRowMapper<>(Student.class);

        List<Student> StudentList = jdbcTemplate.query(findByName,rowMapper);
        return StudentList;
    }
}
