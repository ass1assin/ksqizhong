package com.exam.dao.manager;

import com.exam.entity.Department;
import com.exam.entity.Inst;

import java.util.List;

public interface InstInfoDao {
//    int addInst(Inst inst);
//    List<Inst> showInst();
//    int deleteInst(List<Integer> ids);
//    int updataInst(Inst inst);
//    List<Inst> findByName(String instName);

    //    总查询
    List<Inst> findAllWithPagination(int page, int pageSize);
    int getTotalCount();
    List<Inst> showInst();

    //    增加
    int addInst(Inst inst);

    //    删除
    int deleteInst(String id);

    //    修改
    int updataInst(Inst inst);


    //    模糊查询
    List<Inst> findByName(String instName,int page, int pageSize);
    int getTotalCountByName(String instName);
}
