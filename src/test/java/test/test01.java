package test;

import com.exam.Controller.counsellor.StuInfoContraller;
import com.exam.dao.counsellor.StuInfoDao;
import com.exam.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;



@ContextConfiguration({"classpath:spring.xml"})
//@ContextConfiguration(locations = {"classpath:spring.xml"})
@RunWith(SpringRunner.class)

public class test01 {
//    @Autowired
//    private StuInfoContraller stuInfoContraller;
    @Autowired
    private StuInfoDao stuInfoDao;
   @Test
    public void test(){
       System.out.println("111");
//       List list = stuInfoContraller.showStudent();
//       System.out.println(list);
       List<Student> students = stuInfoDao.showStudent();
       System.out.println(stuInfoDao.showStudent());
//       System.out.println("miniinini");
//       int i = stuInfoDao.deleteStudent(1);
//       StuInfoDao students= new StuInfoDao() {
//
//           @Override
//           public int addStudentinfo(Student student) {
//               return 0;
//           }
//
//           @Override
//           public List<Student> showStudent() {
//               return null;
//           }
//
//           @Override
//           public int deleteStudent(int id) {
//               return 0;
//           }
//
//           @Override
//           public int updataStudent(Student student) {
//               return 0;
//           }
//
//           @Override
//           public List<Student> findByName(String stuName) {
//               return null;
//           }

   };
}
