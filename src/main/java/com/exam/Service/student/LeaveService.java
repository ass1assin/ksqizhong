package com.exam.Service.student;

import com.exam.entity.Course;
import com.exam.entity.Leave;
import com.exam.entity.LeaveDTO;

import java.util.List;

public interface LeaveService {
    int addLeave(Leave leave);
    List<Leave> showLeave();
    int deleteLeave(List<Integer> ids);
    int audit(Leave leave);

}
