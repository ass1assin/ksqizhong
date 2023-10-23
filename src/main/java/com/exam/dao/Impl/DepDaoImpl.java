package com.exam.dao.Impl;

import com.exam.dao.DepDao;
import com.exam.entity.Dep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
//    CM03
@Repository
public class DepDaoImpl implements DepDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //显示所有信息
    @Override
    public List<Dep> showDep() {
        String querysql="select * from sys_department";
        RowMapper<Dep> rowMapper= new BeanPropertyRowMapper<>(Dep.class);
        List<Dep> depList = jdbcTemplate.query(querysql,rowMapper);
        return depList;
    }


//    CM03-01
//    功能名称： 添加二级学院信息模块
    @Override
    public int addDepinfo(Dep dep) {
        String addsql="insert into sys_department values(?,?,?)";
        Object[] acc= {dep.getDepID(),dep.getDepName()};
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int add = jdbcTemplate.update(addsql, acc);
        return add;
    }

//    CM03-02
//    功能名称： 删除二级学院信息模块

//    CM03-03
//    功能名称： 修改二级学院信息模块

//    CM03-04
//    功能名称： 查询二级学院信息模块

}
