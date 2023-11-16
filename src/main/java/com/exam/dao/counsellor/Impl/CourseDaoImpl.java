package com.exam.dao.counsellor.Impl;

import com.exam.dao.counsellor.CourseDao;
import com.exam.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
    @Override
    public List<Course> findAllWithPagination(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String querysql = "select * from sys_course limit ? offset ?";
        BeanPropertyRowMapper<Course> courseBeanPropertyRowMapper = new BeanPropertyRowMapper<>(Course.class);
        List<Course> courseListList =  jdbcTemplate.query(querysql, courseBeanPropertyRowMapper, pageSize, offset);
        return courseListList;
    }

    @Override
    public int getTotalCount() {
        String countQuery = "SELECT COUNT(*) FROM sys_course";
        return jdbcTemplate.queryForObject(countQuery, Integer.class);
    }





    //    CM07-01
//    功能名称： 添加学生信息模块
    @Override
    public int addCourse(Course course) {
        String addsql="insert IGNORE  into sys_course(courseID,classID,courseName,year,term,hour) values(?,?,?,?,?,?)";
        Object[] acc= {course.getCourseID(),course.getClassID(),course.getCourseName(),course.getYear(),course.getTerm(),course.getHour()};
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int add = jdbcTemplate.update(addsql, acc);
        return add;
    }

//    CM07-02
//    功能名称： 删除学生信息模块
    @Override
    public int deleteCourse(String id) {
        String deletesql="delete from sys_course where courseID = ?";
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int delete = jdbcTemplate.update(deletesql, id);
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
//    @Override
//    public List<Course> findByName(String className) {
//        String findByName="select * from sys_course where courseName like concat('%',?,'%')";
//
//        RowMapper<Course> rowMapper= new BeanPropertyRowMapper<>(Course.class);
//
//        List<Course> ClassesList = jdbcTemplate.query(findByName,rowMapper);
//        return ClassesList;
//    }
@Override
public List<Course> findByName(String courseID, String term, String courseName, int page, int pageSize) {
    int offset = (page - 1) * pageSize;

    // 构建基本的 SQL 查询
    StringBuilder sql = new StringBuilder("SELECT * FROM sys_course WHERE 1 = 1");

    // 使用 ArrayList 来保存占位符对应的参数值
    List<Object> params = new ArrayList<>();

    // 仅在 courseName 不为空时添加条件
    if (courseName != null && !courseName.isEmpty()) {
        sql.append(" AND courseName LIKE ?");
        params.add("%" + courseName + "%");
    }
    if (courseID != null && !courseID.isEmpty()) {
        sql.append(" AND courseID LIKE ?");
        params.add("%" + courseID + "%");
    }
    if (term != null && !term.isEmpty()) {
        sql.append(" AND term LIKE ?");
        params.add("%" + term + "%");
    }
    // 添加分页条件
    sql.append(" LIMIT ? OFFSET ?");

    // 添加分页参数值
    params.add(pageSize);
    params.add(offset);

    // 执行查询
    RowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
    return jdbcTemplate.query(sql.toString(), rowMapper, params.toArray());
}


    @Override
    public int getTotalCountByName(String courseName) {
        String countQuery = "SELECT COUNT(*) FROM sys_course where courseName like concat('%',?,'%')";
        return jdbcTemplate.queryForObject(countQuery, Integer.class,courseName);
    }
}
