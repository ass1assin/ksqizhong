package com.exam.Service.student;

import com.exam.entity.Course;
import com.exam.entity.Department;
import com.exam.entity.Leave;
import com.exam.entity.LeaveDTO;

import java.util.List;

public interface LeaveService {
    List<Leave> showLeave();

    List<Leave> getLeavesWithPagination(int page, int pageSize);
    int getTotalPages(int pageSize);

    int addLeave(Leave leave);

    int deleteLeave(String id);
    int audit(Leave leave);

}
