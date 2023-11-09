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
    public int deleteStudent(List<Integer> ids) {
        String deletesql="delete from sys_student where stuID in ?";
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int delete = jdbcTemplate.update(deletesql, ids);
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
    public List<Student> findByName(String stuName) {
        String findByName="select * from sys_department where stuName like concat('%',?,'%')";

        RowMapper<Student> rowMapper= new BeanPropertyRowMapper<>(Student.class);

        List<Student> StudentList = jdbcTemplate.query(findByName,rowMapper);
        return StudentList;
    }
}
