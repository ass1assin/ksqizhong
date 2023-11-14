package com.exam.dao.manager.Impl;

import com.exam.dao.manager.InstInfoDao;
import com.exam.entity.Department;
import com.exam.entity.Inst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//    CM03
@Repository
public class InstInfoDaoImpl implements InstInfoDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //显示所有信息
    @Override
    public List<Inst> showInst() {
        String querysql="select * from sys_instructor";
        RowMapper<Inst> rowMapper= new BeanPropertyRowMapper<>(Inst.class);
        List<Inst> InstList = jdbcTemplate.query(querysql,rowMapper);
        return InstList;
    }

    @Override
    public List<Inst> findAllWithPagination(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String querysql = "select * from sys_instructor limit ? offset ?";
        BeanPropertyRowMapper<Inst> depBeanPropertyRowMapper = new BeanPropertyRowMapper<>(Inst.class);
        List<Inst> InstList =  jdbcTemplate.query(querysql, depBeanPropertyRowMapper, pageSize, offset);
        return InstList;
    }

    @Override
    public int getTotalCount() {
        String countQuery = "SELECT COUNT(*) FROM sys_department";
        return jdbcTemplate.queryForObject(countQuery, Integer.class);
    }



    //    CM03-01
//    功能名称： 添加辅导员信息模块
    @Override
    public int addInst(Inst inst) {
        String addsql="insert IGNORE into sys_instructor(instID,instName,depID,telephone) values(?,?,?,?)";
        Object[] acc= {inst.getInstID(),inst.getInstName(),inst.getDepID(),inst.getTelephone()};
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int add = jdbcTemplate.update(addsql, acc);
        return add;
    }

//    CM03-02
//    功能名称： 删除辅导员信息模块
    @Override
    public int deleteInst(String id) {
        String addsql="delete from sys_instructor where instID = ?";
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int delete = jdbcTemplate.update(addsql, id);
        return delete;

    }

//    CM03-03
//    功能名称： 修改辅导员信息模块
    @Override
    public int updataInst(Inst inst) {
        String addsql="update sys_instructor set instName=?,depID=?,telephone=? where instID=?";
        Object[] acc= {inst.getInstName(),inst.getDepID(),inst.getTelephone(),inst.getInstID()};
        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int updata = jdbcTemplate.update(addsql, acc);
        return updata;
    }



//    CM03-04
//    功能名称： 查询辅导员信息模块
    @Override
    public List<Inst> findByName(String instName,int page, int pageSize) {
        String findByName="select * from sys_instructor where instName like concat('%',?,'%') limit ? offset ?";
        int offset = (page - 1) * pageSize;
        RowMapper<Inst> rowMapper= new BeanPropertyRowMapper<>(Inst.class);

        List<Inst> instList = jdbcTemplate.query(findByName,rowMapper,instName,pageSize,offset);
        return instList;
    }
    @Override
    public int getTotalCountByName(String instName) {
        String countQuery = "SELECT COUNT(*) FROM sys_instructor where instName like concat('%',?,'%')";
        return jdbcTemplate.queryForObject(countQuery, Integer.class,instName);
    }
}
