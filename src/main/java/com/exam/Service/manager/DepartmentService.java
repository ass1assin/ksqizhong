package com.exam.Service.manager;

import com.exam.entity.Department;


import java.util.List;

public interface DepartmentService {
    int addDepinfo(Department department);

    List<Department> showDep();
    List<Department> getDepsWithPagination(int page, int pageSize);
    int getTotalPages(int pageSize);

    int deleteDep(String id);
    int updataDep(Department department);
    List<Department> findByName(String depName);


}
