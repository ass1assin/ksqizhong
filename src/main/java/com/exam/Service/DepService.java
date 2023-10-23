package com.exam.Service;

import com.exam.entity.Dep;

import java.util.List;

public interface DepService {
    int addDepinfo(Dep dep);
    List<Dep> showDep();
}
