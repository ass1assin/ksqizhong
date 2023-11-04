package com.exam.dao.counsellor.Impl;

import com.exam.dao.counsellor.ClassDao;
import com.exam.dao.counsellor.CourseDao;
import com.exam.entity.Classes;
import com.exam.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//    CM03
@Repository
public class CourseDaoImpl implements CourseDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //显示所有信息
    @Override
    public List<Course> showCourse() {
        String querysql="select * from sys_course" ;

        List<Course> courseListList = jdbcTemplate.query(querysql,new BeanPropertyRowMapper<>(Course.class));
        return courseListList;
    }




    //    CM07-01
//    功能名称： 添加学生信息模块
    @Override
    public int addCourse(Course course) {
        String addsql="insert into sys_course(courseID,classID,courseName,year,term,hour) values(?,?,?,?,?,?)";
        Object[] acc= {course.getCourseID(),course.getClassID(),course.getCourseName(),course.getYear(),course.getTerm(),course.getHour()};
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int add = jdbcTemplate.update(addsql, acc);
        return add;
    }

//    CM07-02
//    功能名称： 删除学生信息模块
    @Override
    public int deleteCourse(List<Integer> ids) {
        String deletesql="delete from sys_course where classID in ?";
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int delete = jdbcTemplate.update(deletesql, ids);
        return delete;

    }

//    CM07-03
//    功能名称： 修改学生信息模块
    @Override
    public int updataCourse(Course course) {
        String updataql="update sys_course set classID=?,courseName=?,year=?,term=?,hour=? where courseID=?";
        Object[] acc= {course.getClassID(),course.getCourseName(),course.getYear(),course.getTerm(),course.getHour(),course.getCourseID()};
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int updata = jdbcTemplate.update(updataql, acc);
        return updata;
    }



//    CM07-04
//    功能名称： 查询学生信息模块
    @Override
    public List<Course> findByName(String className) {
        String findByName="select * from sys_course where courseName like concat('%',?,'%')";

        RowMapper<Course> rowMapper= new BeanPropertyRowMapper<>(Course.class);

        List<Course> ClassesList = jdbcTemplate.query(findByName,rowMapper);
        return ClassesList;
    }
}
