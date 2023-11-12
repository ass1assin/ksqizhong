package com.exam.dao.student;

import com.exam.entity.Leave;

import java.util.List;

public interface LeaveDao {
    int addLeave(Leave leave);
    List<Leave> showLeave();
    List<Leave> findAllWithPagination(int page, int pageSize);

    int getTotalCount();
    int deleteLeave(String ids);
    Leave findbyID(int id);
    int audit(Leave leave);
}
