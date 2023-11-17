package com.exam.dao.Impl;

import com.exam.dao.StuInfoDao;
import com.exam.entity.Classes;
import com.exam.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//    CM03
@Repository
public class StuInfoDaoImpl implements StuInfoDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //显示所有信息
    @Override
    public List<Classes> showClass() {
        String querysql="select * from sys_classes" ;

        List<Classes> classes = jdbcTemplate.query(querysql,new BeanPropertyRowMapper<>(Classes.class));
        return classes;
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
        String updataql="update sys_student set classID=? , stuName=? , address=?,stuTel=?,contact=?,contactTel=? where stuID=?";
        Object[] acc= {student.getClassID(),student.getStuName(),student.getAddress(),student.getStuTel(),student.getContact(),student.getContactTel(),student.getStuID()};
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int updata = jdbcTemplate.update(updataql, acc);
        return updata;
    }



//    CM07-04
//    功能名称： 查询学生信息模块
@Override
public List<Student> findByName(String studentID, String stuName, int page, int pageSize) {
    int offset = (page - 1) * pageSize;

    // 构建基本的 SQL 查询
    StringBuilder sql = new StringBuilder("SELECT * FROM sys_student WHERE 1 = 1");

    // 使用 ArrayList 来保存占位符对应的参数值
    List<Object> params = new ArrayList<>();

    // 仅在 stuName 不为空时添加条件与参数值
    if (stuName != null && !stuName.isEmpty()) {
        sql.append(" AND stuName LIKE ?");
        params.add("%" + stuName + "%");
    }
    // 仅在 stuID不为空时添加条件与参数值
    if (studentID != null && !studentID.isEmpty()) {
        sql.append(" AND stuID LIKE ?");
        params.add("%" + studentID+ "%");
    }
    // 添加分页条件
    sql.append(" LIMIT ? OFFSET ?");

    // 添加分页参数值
    params.add(pageSize);
    params.add(offset);

    // 执行查询
    RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);
    return jdbcTemplate.query(sql.toString(), rowMapper, params.toArray());
}

    @Override
    public int getTotalCountByName(String stuName) {
        String countQuery = "SELECT COUNT(*) FROM sys_student where stuName like concat('%',?,'%')";
        return jdbcTemplate.queryForObject(countQuery, Integer.class,stuName);
    }
}
