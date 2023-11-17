package com.exam.Service.Impl;

import com.exam.Service.ClassService;
import com.exam.dao.ClassDao;
import com.exam.entity.Classes;
import com.exam.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassDao classDao;

    @Override
    public List<Department> showDepartment() {
        List<Department> departments = classDao.showDepartment();
        return departments;
    }

    @Override
    public List<Classes> getClassesWithPagination(int page, int pageSize) {
        return classDao.findAllWithPagination(page,pageSize);
    }
    public int getTotalPages(int pageSize) {
        int totalCount = classDao.getTotalCount();
        int pageSum = (totalCount / pageSize )+1;
        return pageSum;
    }
    //    CM07-01
//    功能名称： 添加学生信息模块
    @Override
    public int addClasses(Classes classes) {
        int addClass= classDao.addClass(classes);
        return addClass;
    }


    //    CM07-02
//    功能名称： 删除学生信息模块
    @Override
    public int deleteClasses(String id) {
        int deleteClass = classDao.deleteClasses(id);
        return deleteClass;
    }


    //    CM07-03
//    功能名称： 修改学生信息模块
    @Override
    public int updataClasses(Classes classes) {
        int updataClass = classDao.updataClasses(classes);
        return updataClass;
    }


    //    CM07-04
//    功能名称： 查询学生信息模块

    @Override
    public List<Classes> findByName(String classID,String className, int page, int pageSize) {
        List<Classes> findByname = classDao.findByName(classID,className,page,pageSize);
        return findByname;
    }

    @Override
    public int getTotalPagesByName(int pageSize,String Name) {
        int totalCount = classDao.getTotalCountByName(Name);
        int pageSum = (totalCount / pageSize )+1;
        return pageSum;
    }




}
