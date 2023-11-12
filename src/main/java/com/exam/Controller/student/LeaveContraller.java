package com.exam.Controller.student;

import com.exam.Service.counsellor.CourseService;
import com.exam.Service.student.LeaveService;
import com.exam.entity.Course;
import com.exam.entity.Leave;
import com.exam.entity.LeaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leave")
public class LeaveContraller {
    @Autowired
    private LeaveService leaveService;

//显示所有信息
    @GetMapping("/showLeave")

    public List showLeave(){
        List<Leave> LeaveList = leaveService.showLeave();
        return LeaveList;
    }

//    CM07-01
//    功能名称： 添加学生信息模块
    @PostMapping("/addLeave")
    public String addCourse(@RequestBody Leave leave){
        int i =leaveService.addLeave(leave);

        return "添加成功";
    }


    //    CM07-02
//    功能名称： 删除学生信息模块
    @DeleteMapping("/delete/{id}")
    public String deleteLeave(@PathVariable List<Integer> ids){
        int i =leaveService.deleteLeave(ids);
        return "成功";
    }

    @PostMapping("/auditLeave")
    public int audit(@PathVariable Leave leave){
        int audit = leaveService.audit(leave);
        return audit;
    }

}
