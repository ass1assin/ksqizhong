package com.exam.Service.manager;

import com.exam.entity.Dep;

import java.util.List;

public interface DepService {
    int addDepinfo(Dep dep);
    List<Dep> showDep();

    int deleteDep(List<Integer> ids);
    int updataDep(Dep dep);
    List<Dep> findByName(String depName);
}
