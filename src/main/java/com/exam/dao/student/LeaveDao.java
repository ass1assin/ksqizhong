package com.exam.dao.student;

import com.exam.entity.Course;
import com.exam.entity.Leave;
import com.exam.entity.LeaveDTO;

import java.util.List;

public interface LeaveDao {
    int addLeave(Leave leave);
    List<Leave> showLeave();
    int deleteLeave(List<Integer> ids);
    Leave findbyID(int id);
    int audit(Leave leave);
}
