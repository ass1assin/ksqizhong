package test;

import com.exam.Controller.counsellor.StuInfoContraller;
import com.exam.Controller.student.LeaveContraller;
import com.exam.dao.counsellor.StuInfoDao;
import com.exam.entity.Leave;
import com.exam.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;



@ContextConfiguration({"classpath:spring.xml"})

@RunWith(SpringRunner.class)

public class test01 {

    @Autowired
    private StuInfoDao stuInfoDao;
    @Autowired
    private LeaveContraller leaveContraller;
   @Test
    public void test(){
//       System.out.println("111");
//       List<Student> students = stuInfoDao.showStudent();
//       System.out.println(stuInfoDao.showStudent());
//       ApplicationContext sc=new ClassPathXmlApplicationContext("spring.xml");
//       LeaveContraller leaveContraller=sc.getBean(LeaveContraller.class);
       Leave leave = new Leave();
       leave.setCourseID("1");
       leave.setDaynum(3);
       leave.setReason("asdasdasd");

//       String s = leaveContraller.addCourse(leave);
       //…Û∫À
       leave.setBo(true);
       leave.setLeaveID("20231104121536-315");
       int s = leaveContraller.audit(leave);
       System.out.println(s);


   };
}
