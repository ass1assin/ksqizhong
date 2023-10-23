package com.exam.dao;

import com.exam.entity.Dep;

import java.util.List;

public interface DepDao {
    int addDepinfo(Dep dep);
    List<Dep> showDep();

}
