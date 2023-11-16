package com.exam.dao.counsellor;

import com.exam.entity.Classes;
import com.exam.entity.Inst;
import com.exam.entity.Student;

import java.util.List;

public interface ClassDao {
    //    总查询
    List<Classes> findAllWithPagination(int page, int pageSize);
    int getTotalCount();
    List<Classes> showClass();

    //    增加
    int addClass(Classes classes);

    //    删除
    int deleteClasses(String id);

    //    修改
    int updataClasses(Classes classes);


    //    模糊查询
    List<Classes> findByName(String classID,String className,int page, int pageSize);
    int getTotalCountByName(String className);
}
