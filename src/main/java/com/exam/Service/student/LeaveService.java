package com.exam.Service.student;

import com.exam.entity.Leave;

import java.util.List;

public interface LeaveService {
    List<Leave> showLeave();

    List<Leave> getLeavesWithPagination(int page, int pageSize);
    int getTotalPages(int pageSize);

    int addLeave(Leave leave);

    int deleteLeave(String id);
    int audit(Leave leave);

    List<Leave> findAllWithPaginationBystuID(String stuID, int page, int pageSize);

    int getTotalCountByID(String stuID,int pageSize);


}
