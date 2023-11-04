package com.exam.Service.counsellor.Impl;

import com.exam.Service.counsellor.ClassService;
import com.exam.Service.counsellor.StuInfoService;
import com.exam.dao.counsellor.ClassDao;
import com.exam.dao.counsellor.StuInfoDao;
import com.exam.entity.Classes;
import com.exam.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassDao classDao;

    @Override
    public List<Classes> showClass() {
        List<Classes> classesList= classDao.showClass();
        return classesList;
    }


    //    CM07-01
//    功能名称： 添加学生信息模块
    @Override
    public int addClass(Classes classes) {
        int addClass= classDao.addClass(classes);
        return addClass;
    }


    //    CM07-02
//    功能名称： 删除学生信息模块
    @Override
    public int deleteClass(List<Integer> ids) {
        int deleteClass = classDao.deleteClass(ids);
        return deleteClass;
    }


    //    CM07-03
//    功能名称： 修改学生信息模块
    @Override
    public int updataClass(Classes classes) {
        int updataClass = classDao.updataClass(classes);
        return updataClass;
    }


    //    CM07-04
//    功能名称： 查询学生信息模块
    @Override
    public List<Classes> findByName(String className) {
        List<Classes> findByname = classDao.findByName(className);
        return findByname;
    }



}
