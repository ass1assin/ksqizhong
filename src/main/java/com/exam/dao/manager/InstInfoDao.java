package com.exam.dao.manager;

import com.exam.entity.Department;
import com.exam.entity.Inst;

import java.util.List;

public interface InstInfoDao {

    //    总查询
     List<Department> showDepartment();
    List<Inst> findAllWithPagination(int page, int pageSize);
    int getTotalCount();
//    List<Inst> showInst();

    //    增加
    int addInst(Inst inst);

    //    删除
    int deleteInst(String id);

    //    修改
    int updataInst(Inst inst);


    //    模糊查询
    List<Inst> findByName(String instID,String instName,int page, int pageSize);
    int getTotalCountByName(String instName);
}
