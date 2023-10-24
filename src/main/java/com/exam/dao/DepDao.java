package com.exam.dao;

import com.exam.entity.Dep;

import java.util.List;

public interface DepDao {
    int addDepinfo(Dep dep);
    List<Dep> showDep();
    int deleteDep(int id);
    int updataDep(Dep dep);
    List<Dep> findByName(String depName);
}
