package com.exam.dao.manager.Impl;

import com.exam.dao.manager.InstInfoDao;
import com.exam.entity.Department;
import com.exam.entity.Inst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
public List<Inst> findByName(String instID, String instName, int page, int pageSize) {
    int offset = (page - 1) * pageSize;

    // 构建基本的 SQL 查询
    StringBuilder sql = new StringBuilder("SELECT * FROM sys_instructor WHERE 1 = 1");

    // 使用 ArrayList 来保存占位符对应的参数值
    List<Object> params = new ArrayList<>();


    // 仅在 instID 不为空时添加条件
    if (instID != null && !instID.isEmpty()) {
        sql.append(" AND instID = ?");
        params.add(instID);
    }

    // 仅在 instName 不为空时添加条件
    if (instName != null && !instName.isEmpty()) {
        sql.append(" AND instName LIKE ?");
        params.add("%" + instName + "%");
    }

    // 添加分页条件
    sql.append(" LIMIT ? OFFSET ?");


    // 添加分页参数值
    params.add(pageSize);
    params.add(offset);

    // 执行查询
    RowMapper<Inst> rowMapper = new BeanPropertyRowMapper<>(Inst.class);
    return jdbcTemplate.query(sql.toString(), rowMapper, params.toArray());
}


    @Override
    public int getTotalCountByName(String instName) {
        String countQuery = "SELECT COUNT(*) FROM sys_instructor where instName like concat('%',?,'%')";
        return jdbcTemplate.queryForObject(countQuery, Integer.class,instName);
    }
}
