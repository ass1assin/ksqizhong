package com.exam.dao.Impl;

import com.exam.dao.ClassDao;
import com.exam.entity.Classes;
import com.exam.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//    CM03
@Repository
public class ClassDaoImpl implements ClassDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //显示所有信息
    @Override
    public List<Department> showDepartment() {
        String querysql="select * from sys_department";
        RowMapper<Department> rowMapper= new BeanPropertyRowMapper<>(Department.class);
        List<Department> Department = jdbcTemplate.query(querysql,rowMapper);
        return Department;
    }


    @Override
    public List<Classes> findAllWithPagination(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String querysql = "select sc.classID,sc.className,sc.major,sc.grade,sd.depName" +
                          " FROM sys_classes sc JOIN sys_department sd on sc.depID = sd.depID limit ? offset ?";
        BeanPropertyRowMapper<Classes> depBeanPropertyRowMapper = new BeanPropertyRowMapper<>(Classes.class);
        List<Classes> classesListList =  jdbcTemplate.query(querysql, depBeanPropertyRowMapper, pageSize, offset);
        return classesListList;
    }

    @Override
    public int getTotalCount() {
        String countQuery = "SELECT COUNT(*) FROM sys_classes";
        return jdbcTemplate.queryForObject(countQuery, Integer.class);
    }
    //    CM07-01
//    功能名称： 添加学生信息模块
    @Override
    public int addClass(Classes classes) {
//        String addsql="insert IGNORE into sys_classes(classID,className,depID,major,grade) values(?,?,?,?,?)";
        String addsql="INSERT INTO sys_classes (classID, className, major, grade,depID)" +
                      "SELECT ?,?,?,?,depID" +
                      " FROM sys_department " +
                      "WHERE depName= ?";
        Object[] acc= {classes.getClassID(), classes.getClassName(), classes.getMajor(), classes.getGrade(), classes.getDepName()};
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int add = jdbcTemplate.update(addsql, acc);
        return add;
    }

//    CM07-02
//    功能名称： 删除学生信息模块
    @Override
    public int deleteClasses(String id) {
        String deletesql="delete from sys_classes where classID = ?";
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int delete = jdbcTemplate.update(deletesql, id);
        return delete;

    }

//    CM07-03
//    功能名称： 修改学生信息模块
    @Override
    public int updataClasses(Classes classes) {
//        String updataql="update sys_classes set className=? ,depID =? , major=? , grade=? where classID=?";
        String updataql="UPDATE sys_classes" +
                        " set className=?,major=?,grade=?,depID=(SELECT depID FROM sys_department WHERE depName=?) " +
                        "WHERE classID=?";
        Object[] acc= {classes.getClassName(),classes.getMajor(),classes.getGrade(),classes.getDepName(),classes.getClassID()};
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int updata = jdbcTemplate.update(updataql, acc);
        return updata;
    }



//    CM07-04
//    功能名称： 查询学生信息模块
@Override
public List<Classes> findByName(String classID, String className, int page, int pageSize) {
    int offset = (page - 1) * pageSize;

    // 构建基本的 SQL 查询
    StringBuilder sql = new StringBuilder(
            "SELECT sc.classID, sc.className, sc.major, sc.grade, sd.depName " +
            "FROM sys_classes sc " +
            "JOIN sys_department sd ON sc.depID = sd.depID " +
            "WHERE 1 = 1 ");

    // 使用 ArrayList 来保存占位符对应的参数值
    List<Object> params = new ArrayList<>();

    // 仅在 className 不为空时添加条件
    if (className != null && !className.isEmpty()) {
        sql.append(" AND className LIKE ?");
        params.add("%" + className + "%");
    }

    // 仅在 classID 不为空时添加条件
    if (classID != null && !classID.isEmpty()) {
        sql.append(" AND classID LIKE ?");
        params.add("%" + classID + "%");
    }
    // 添加分页条件
    sql.append(" LIMIT ? OFFSET ?");



    // 添加分页参数值
    params.add(pageSize);
    params.add(offset);

    // 执行查询
    RowMapper<Classes> rowMapper = new BeanPropertyRowMapper<>(Classes.class);
    return jdbcTemplate.query(sql.toString(), rowMapper, params.toArray());
}

    @Override
    public int getTotalCountByName(String classesName) {
        String countQuery = "SELECT COUNT(*) FROM sys_classes where className like concat('%',?,'%')";
        return jdbcTemplate.queryForObject(countQuery, Integer.class,classesName);
    }
}
