package com.exam.Service.manager.Impl;

import com.exam.Service.manager.InstInfoService;
import com.exam.dao.manager.InstInfoDao;
import com.exam.entity.Department;
import com.exam.entity.Inst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstInfoServiceImpl implements InstInfoService {
    @Autowired
    private InstInfoDao instInfoDao;

    @Override
    public List<Inst> showInst() {
        List<Inst> inst = instInfoDao.showInst();
        return inst;
    }
    @Override
    public List<Inst> getDepsWithPagination(int page, int pageSize) {
        return instInfoDao.findAllWithPagination(page,pageSize);
    }
    public int getTotalPages(int pageSize) {
        int totalCount = instInfoDao.getTotalCount();
        int pageSum = (totalCount / pageSize )+1;
        return pageSum;
    }

    //    CM03-01
//    功能名称： 添加辅导员信息模块
    @Override
    public int addInst(Inst inst) {
        int addInst = instInfoDao.addInst(inst);
        return addInst;
    }


    //    CM03-02
//    功能名称： 删除辅导员信息模块
    @Override
    public int deleteInst(String id) {
        int deleteInst = instInfoDao.deleteInst(id);
        return deleteInst;
    }


    //    CM03-03
//    功能名称： 修改辅导员信息模块
    @Override
    public int updataInst(Inst inst) {
        int updataInst = instInfoDao.updataInst(inst);
        return updataInst;
    }


    //    CM03-04
//    功能名称： 查询辅导员信息模块

    @Override
    public List<Inst> findByName(String instName, int page, int pageSize) {
        List<Inst> findByname = instInfoDao.findByName(instName,page,pageSize);
        return findByname;
    }

    @Override
    public int getTotalPagesByName(int pageSize,String Name) {
        int totalCount = instInfoDao.getTotalCountByName(Name);
        int pageSum = (totalCount / pageSize )+1;
        return pageSum;
    }



}
