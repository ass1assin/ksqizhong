package com.exam.dao.counsellor;

import com.exam.entity.Classes;
import com.exam.entity.Student;

import java.util.List;

public interface ClassDao {
    int addClass(Classes classes);
    List<Classes> showClass();
    int deleteClass(List<Integer> ids);
    int updataClass(Classes classes);
    List<Classes> findByName(String className);
}
