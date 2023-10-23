package com.exam.Service.Impl;

import com.exam.Service.DepService;
import com.exam.dao.DepDao;
import com.exam.entity.Dep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepServiceImpl implements DepService {
    @Autowired
    private DepDao depDao;
    @Override
    public int addDepinfo(Dep dep) {
        int i = depDao.addDepinfo(dep);
        return i;
    }

    @Override
    public List<Dep> showDep() {
        List<Dep> deps = depDao.showDep();
        return deps;
    }
}
