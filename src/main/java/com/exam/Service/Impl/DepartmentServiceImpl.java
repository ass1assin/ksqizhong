package com.exam.Service.Impl;

import com.exam.Service.DepartmentService;
import com.exam.dao.DepartmentDao;
import com.exam.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentDao departmentDao;
    //          功能名称： 展示所有
    @Override
    public List<Department> showDep() {
        List<Department> departments = departmentDao.showDep();
        return departments;
    }

    @Override
    public List<Department> getDepsWithPagination(int page, int pageSize) {
        return departmentDao.findAllWithPagination(page,pageSize);
    }
    public int getTotalPages(int pageSize) {
        int totalCount = departmentDao.getTotalCount();
        int pageSum = (totalCount / pageSize )+1;
        return pageSum;
    }


    //    CM03-01
//    功能名称： 添加二级学院信息模块
    @Override
    public int addDepinfo(Department department) {
        int addDep = departmentDao.addDepinfo(department);
        return addDep;
    }



    //    CM03-02
//    功能名称： 删除二级学院信息模块
    @Override
    public int deleteDep(String id) {
        int deleteDep = departmentDao.deleteDep(id);
        return deleteDep;
    }



    //    CM03-03
//    功能名称： 修改二级学院信息模块
    @Override
    public int updataDep(Department department) {
        int updataDep = departmentDao.updataDep(department);
        return updataDep;
    }



    //    CM03-04
//    功能名称： 查询二级学院信息模块
    @Override
    public List<Department> findByName(String depID,String depName, int page, int pageSize) {
        List<Department> findByname = departmentDao.findByName(depID,depName,page,pageSize);
        return findByname;
    }

    @Override
    public int getTotalPagesByName(int pageSize,String Name) {
        int totalCount = departmentDao.getTotalCountByName(Name);
        int pageSum = (totalCount / pageSize )+1;
        return pageSum;
    }




}
