package com.exam.dao.manager.Impl;

import com.exam.dao.manager.DepartmentDao;
import com.exam.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
//    CM03
@Repository
public class DepartmentDaoImpl implements DepartmentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //显示所有信息
    @Override
    public List<Department> showDep() {
        String querysql="select * from sys_department";
        RowMapper<Department> rowMapper= new BeanPropertyRowMapper<>(Department.class);
        List<Department> departmentList = jdbcTemplate.query(querysql,rowMapper);
        return departmentList;
    }
    @Override
    public List<Department> findAllWithPagination(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String querysql = "select * from sys_department limit ? offset ?";
        BeanPropertyRowMapper<Department> depBeanPropertyRowMapper = new BeanPropertyRowMapper<>(Department.class);
        List<Department> departmentList =  jdbcTemplate.query(querysql, depBeanPropertyRowMapper, pageSize, offset);
        return departmentList;
    }

    @Override
    public int getTotalCount() {
        String countQuery = "SELECT COUNT(*) FROM sys_department";
        return jdbcTemplate.queryForObject(countQuery, Integer.class);
    }


    //    CM03-01
//    功能名称： 添加二级学院信息模块
    @Override
    public int addDepinfo(Department department) {
        String addsql="insert into sys_department(depID,depName) values(?,?)";
//        String addsql="insert into TABLE(depID,depName) select 'depID' from DUAL where NOT exists(select sys_department from TABLE where sys_department=?)";


        Object[] acc= {department.getDepID(), department.getDepName()};
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int add = jdbcTemplate.update(addsql, acc);
        return add;
    }

//    CM03-02
//    功能名称： 删除二级学院信息模块
    @Override
    public int deleteDep(String id) {
        String addsql="delete from sys_department where depID = ?";
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int delete = jdbcTemplate.update(addsql, id);
        return delete;

    }

//    CM03-03
//    功能名称： 修改二级学院信息模块
    @Override
    public int updataDep(Department department) {
        String addsql="update sys_department set depName=? where depID=?";
        Object[] acc= {department.getDepName(), department.getDepID()};
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int updata = jdbcTemplate.update(addsql, acc);
        return updata;
    }



//    CM03-04
//    功能名称： 查询二级学院信息模块
    @Override
    public List<Department> findByName(String depName) {
        String findByName="select * from sys_department where depName like concat('%',?,'%')";

        RowMapper<Department> rowMapper= new BeanPropertyRowMapper<>(Department.class);

        List<Department> departmentList = jdbcTemplate.query(findByName,rowMapper,depName);
        return departmentList;
    }




}
