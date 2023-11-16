package test;

import com.exam.Controller.counsellor.CourseContraller;
import com.exam.Controller.student.LeaveContraller;
import com.exam.dao.counsellor.StuInfoDao;
import com.exam.dao.manager.Impl.InstInfoDaoImpl;
import com.exam.entity.Course;
import com.exam.entity.Inst;
import com.exam.entity.Leave;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private CourseContraller courseContraller;
    @Autowired
    InstInfoDaoImpl instInfoDaoimpl;
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
       //审核
       leave.setBo(true);
       leave.setLeaveID("20231104121536-315");
//       int s = leaveContraller.audit(leave);
//       System.out.println(s);


   };
   @Test
    public void jksd(){
       Inst inst=new Inst();
       inst.setInstID("19");
       inst.setInstName("wd");
       inst.setTelephone("1111111");
       inst.setDepName("大数据学院");
//       instInfoDaoimpl.addInst(inst);
       System.out.println("ssssssssssssssssssss:"+instInfoDaoimpl.addInst(inst));

   }
}
