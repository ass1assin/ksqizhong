package com.exam.dao;

import com.exam.entity.Department;


import java.util.List;

public interface DepartmentDao {
    //    总查询
    List<Department> findAllWithPagination(int page, int pageSize);
    int getTotalCount();
    List<Department> showDep();

    //    增加
    int addDepinfo(Department department);

    //    删除
    int deleteDep(String id);

    //    修改
    int updataDep(Department department);


    //    模糊查询
    List<Department> findByName(String depID,String depName,int page, int pageSize);
    int getTotalCountByName(String depName);

}
