package com.exam.Service.manager;

import com.exam.entity.Inst;

import java.util.List;

public interface InstInfoService {
    int addInst(Inst inst);
    List<Inst> showInst();

    int deleteInst(List<Integer> ids);
    int updataInst(Inst inst);
    List<Inst> findByName(String depName);
}
