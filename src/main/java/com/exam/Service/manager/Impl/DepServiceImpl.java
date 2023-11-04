package com.exam.Service.manager.Impl;

import com.exam.Service.manager.DepService;
import com.exam.dao.manager.DepDao;
import com.exam.entity.Dep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepServiceImpl implements DepService {
    @Autowired
    private DepDao depDao;

    @Override
    public List<Dep> showDep() {
        List<Dep> deps = depDao.showDep();
        return deps;
    }


    //    CM03-01
//    功能名称： 添加二级学院信息模块
    @Override
    public int addDepinfo(Dep dep) {
        int addDep = depDao.addDepinfo(dep);
        return addDep;
    }


    //    CM03-02
//    功能名称： 删除二级学院信息模块
    @Override
    public int deleteDep(List<Integer> ids) {
        int deleteDep = depDao.deleteDep(ids);
        return deleteDep;
    }


    //    CM03-03
//    功能名称： 修改二级学院信息模块
    @Override
    public int updataDep(Dep dep) {
        int updataDep = depDao.updataDep(dep);
        return updataDep;
    }


    //    CM03-04
//    功能名称： 查询二级学院信息模块
    @Override
    public List<Dep> findByName(String depName) {
        List<Dep> findByname = depDao.findByName(depName);
        return findByname;
    }



}
