package com.exam.dao.manager;

import com.exam.entity.Department;


import java.util.List;

public interface DepartmentDao {
    int addDepinfo(Department department);
    List<Department> showDep();
    int deleteDep(String id);
    int updataDep(Department department);
    List<Department> findByName(String depName);
    List<Department> findAllWithPagination(int page, int pageSize);

    int getTotalCount();
}
