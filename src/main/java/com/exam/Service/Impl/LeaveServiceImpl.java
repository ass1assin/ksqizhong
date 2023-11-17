package com.exam.Service.Impl;

import com.exam.Service.LeaveService;
import com.exam.dao.LeaveDao;
import com.exam.entity.Course;
import com.exam.entity.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {
    @Autowired
    private LeaveDao leaveDao;
    @Override
    public List<Course> showCourse() {
        List<Course> CourseList= leaveDao.showCourse();
        return CourseList;
    }
    @Override
    public List<Leave> showLeave() {
        List<Leave> LeaveList= leaveDao.showLeave();
        return LeaveList;
    }

    @Override
    public List<Leave> getLeavesWithPagination(int page, int pageSize) {
        return leaveDao.findAllWithPagination(page,pageSize);
    }

    @Override
    public int getTotalPages(int pageSize) {
        int totalCount = leaveDao.getTotalCount();
        int pageSum = (totalCount / pageSize )+1;
        return pageSum;
    }


    //    CM07-01
//    功能名称： 添加学生信息模块
    @Override
    public int addLeave(Leave leave) {
        //创建时间
        Date date = new Date();


        //请假单号（自动生成），编号规则：yyyyMMddHHmmsss+3 位随机数字
        Random random = new Random();
        int randomNum = random.nextInt(1000);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss-");
        String currentTime = dateFormat.format(date);

        leave.setLeaveID(currentTime+randomNum);

        //状态默认为0
        leave.setStatus("0");

        int addLeave= leaveDao.addLeave(leave);
        return addLeave;
    }


    //    CM07-02
//    功能名称： 删除学生信息模块
    @Override
    public int deleteLeave(String id) {
        int deleteClass = leaveDao.deleteLeave(id);
        return deleteClass;
    }

    @Override
    public int audit(Leave leave) {
        if (leave.getBo()==true){
            leave.setStatus("1");
        }else {
            leave.setStatus("2");
        }
        int audit = leaveDao.audit(leave);
        return audit;
    }

    @Override
    public List<Leave> findAllWithPaginationBystuID(String stuID, int page, int pageSize) {
        return leaveDao.findAllWithPaginationBystuID(stuID,page,pageSize);
    }

    @Override
    public int getTotalCountByID(String stuID,int pageSize) {
        int totalCount = leaveDao.getTotalCountByID(stuID);
        int pageSum = (totalCount / pageSize )+1;
        return pageSum;
    }



}
