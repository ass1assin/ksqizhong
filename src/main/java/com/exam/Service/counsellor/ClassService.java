package com.exam.Service.counsellor;

import com.exam.entity.Classes;
import com.exam.entity.Student;

import java.util.List;

public interface ClassService {
    int addClass(Classes classes);
    List<Classes> showClass();

    int deleteClass(List<Integer> ids);
    int updataClass(Classes classes);
    List<Classes> findByName(String className);
}
