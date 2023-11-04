package com.exam.Service.student.Impl;

import com.exam.Service.counsellor.CourseService;
import com.exam.Service.student.LeaveService;
import com.exam.dao.counsellor.CourseDao;
import com.exam.dao.student.LeaveDao;
import com.exam.entity.Course;
import com.exam.entity.Leave;
import com.exam.entity.LeaveDTO;
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
    public List<Leave> showLeave() {
        List<Leave> LeaveList= leaveDao.showLeave();
        return LeaveList;
    }


    //    CM07-01
//    功能名称： 添加学生信息模块
    @Override
    public int addLeave(Leave leave) {
        //创建时间
        Date date1 = new Date();
//        leave.setApplytime(date1);

        //请假单号（自动生成），编号规则：yyyyMMddHHmmsss+3 位随机数字
        Random random = new Random();
        int randomNum = random.nextInt(1000);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss-");
        String currentTime = dateFormat.format(date1);
        leave.setLeaveID(currentTime+randomNum);

        int addLeave= leaveDao.addLeave(leave);
        return addLeave;
    }


    //    CM07-02
//    功能名称： 删除学生信息模块
    @Override
    public int deleteLeave(List<Integer> ids) {
        int deleteClass = leaveDao.deleteLeave(ids);
        return deleteClass;
    }

    @Override
    public int audit(Leave leave) {
        if (leave.getBo()==true){
            leave.setStatus("1");
        }else {
            leave.setStatus("2");
        }
        //审核时间
        Date date1 = new Date();
        leave.setAudittime(date1);

        int audit = leaveDao.audit(leave);
        return audit;
    }


}
