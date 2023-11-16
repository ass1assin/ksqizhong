package com.exam.Service.manager;

import com.exam.entity.Department;


import java.util.List;

public interface DepartmentService {

    //    总查询
    List<Department> showDep();
    List<Department> getDepsWithPagination(int page, int pageSize);
    int getTotalPages(int pageSize);

    //    增加
    int addDepinfo(Department department);

    //    删除
    int deleteDep(String id);

    //    修改
    int updataDep(Department department);


    //    模糊查询
    List<Department> findByName(String depID,String depName,int page, int  pageSize);
    int getTotalPagesByName(int pageSize ,String depName);

}
