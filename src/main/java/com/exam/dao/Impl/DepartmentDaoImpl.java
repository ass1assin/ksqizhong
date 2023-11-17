package com.exam.dao.Impl;

import com.exam.dao.DepartmentDao;
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
public class DepartmentDaoImpl implements DepartmentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //显示所有信息
    @Override
    public List<Department> showDep() {
        String querysql="select * from sys_department";
        RowMapper<Department> rowMapper= new BeanPropertyRowMapper<>(Department.class);
        List<Department> departmentList = jdbcTemplate.query(querysql,rowMapper);
        return departmentList;   }
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
        String addsql="update sys_department set  depID=?,depName=? where depID=?";
        Object[] acc= {department.getDepID(),department.getDepName(), department.getDepID()};
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int updata = jdbcTemplate.update(addsql, acc);
        return updata;
    }



//    CM03-04
//    功能名称： 查询二级学院信息模块
@Override
public List<Department> findByName(String depID,String depName, int page, int pageSize) {
    int offset = (page - 1) * pageSize;

    // 构建基本的 SQL 查询
    StringBuilder sql = new StringBuilder("SELECT * FROM sys_department WHERE 1 = 1");

    // 使用 ArrayList 来保存占位符对应的参数值
    List<Object> params = new ArrayList<>();

    // 仅在 depName 不为空时添加条件
    if (depName != null && !depName.isEmpty()) {
        sql.append(" AND depName LIKE ?");
        params.add("%" + depName + "%");
    }

    // 仅在 depID不为空时添加条件
    if (depID != null && !depID.isEmpty()) {
        sql.append(" AND depID LIKE ?");
        params.add("%" + depID + "%");
    }

    // 添加分页条件
    sql.append(" LIMIT ? OFFSET ?");


    // 添加分页参数值
    params.add(pageSize);
    params.add(offset);

    // 执行查询
    RowMapper<Department> rowMapper = new BeanPropertyRowMapper<>(Department.class);
    return jdbcTemplate.query(sql.toString(), rowMapper, params.toArray());
}


    @Override
    public int getTotalCountByName(String depName) {
        String countQuery = "SELECT COUNT(*) FROM sys_department where depName like concat('%',?,'%')";
        return jdbcTemplate.queryForObject(countQuery, Integer.class,depName);
    }


}
