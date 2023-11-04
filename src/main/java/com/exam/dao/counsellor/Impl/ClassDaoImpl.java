package com.exam.dao.counsellor.Impl;

import com.exam.dao.counsellor.ClassDao;
import com.exam.dao.counsellor.StuInfoDao;
import com.exam.entity.Classes;
import com.exam.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//    CM03
@Repository
public class ClassDaoImpl implements ClassDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //显示所有信息
    @Override
    public List<Classes> showClass() {
        String querysql="select * from sys_student" ;

        List<Classes> classesList = jdbcTemplate.query(querysql,new BeanPropertyRowMapper<Classes>(Classes.class));
        return classesList;
    }




    //    CM07-01
//    功能名称： 添加学生信息模块
    @Override
    public int addClass(Classes classes) {
        String addsql="insert into sys_student(classID,className,depID,major,grade) values(?,?,?,?,?)";
        Object[] acc= {classes.getClassID(),classes.getClassName(),classes.getDepID(),classes.getMajor(),classes.getGrade()};
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int add = jdbcTemplate.update(addsql, acc);
        return add;
    }

//    CM07-02
//    功能名称： 删除学生信息模块
    @Override
    public int deleteClass(List<Integer> ids) {
        String deletesql="delete from sys_student where classID in ?";
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int delete = jdbcTemplate.update(deletesql, ids);
        return delete;

    }

//    CM07-03
//    功能名称： 修改学生信息模块
    @Override
    public int updataClass(Classes classes) {
        String updataql="update sys_student set className=? ,depID =? , major=? , grade=? where classID=?";
        Object[] acc= {classes.getClassName(),classes.getDepID(),classes.getMajor(),classes.getGrade(),classes.getClassID()};
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int updata = jdbcTemplate.update(updataql, acc);
        return updata;
    }



//    CM07-04
//    功能名称： 查询学生信息模块
    @Override
    public List<Classes> findByName(String className) {
        String findByName="select * from sys_department where className like concat('%',?,'%')";

        RowMapper<Classes> rowMapper= new BeanPropertyRowMapper<>(Classes.class);

        List<Classes> ClassesList = jdbcTemplate.query(findByName,rowMapper);
        return ClassesList;
    }
}
