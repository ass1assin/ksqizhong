package com.exam.Service.counsellor;

import com.exam.entity.Classes;
import com.exam.entity.Inst;
import com.exam.entity.Student;

import java.util.List;

public interface ClassService {
    //    总查询
    List<Classes> showClasses();
    List<Classes> getClassesWithPagination(int page, int pageSize);
    int getTotalPages(int pageSize);

    //    增加
    int addClasses(Classes classes);

    //    删除
    int deleteClasses(String id);

    //    修改
    int updataClasses(Classes classes);


    //    模糊查询
    List<Classes> findByName(String classID,String className,int page, int  pageSize);
    int getTotalPagesByName(int pageSize ,String className);

}
