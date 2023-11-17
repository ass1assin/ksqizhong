package com.exam.Service.manager;

import com.exam.entity.Department;
import com.exam.entity.Inst;

import java.util.List;

public interface InstInfoService {

    //    总查询
    List<Department> showDepartment();
    List<Inst> getDepsWithPagination(int page, int pageSize);
    int getTotalPages(int pageSize);

    //    增加
    int addInst(Inst inst);

    //    删除
    int deleteInst(String id);

    //    修改
    int updataInst(Inst inst);


    //    模糊查询
    List<Inst> findByName(String instID,String instName,int page, int  pageSize);
    int getTotalPagesByName(int pageSize ,String depName);

}
