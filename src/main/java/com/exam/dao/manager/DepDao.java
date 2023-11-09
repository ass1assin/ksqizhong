package com.exam.dao.manager;

import com.exam.entity.Dep;

import java.util.List;

public interface DepDao {
    int addDepinfo(Dep dep);
    List<Dep> showDep();
    int deleteDep(List<String> ids);
    int updataDep(Dep dep);
    List<Dep> findByName(String depName);
}
