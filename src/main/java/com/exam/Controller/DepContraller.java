package com.exam.Controller;

import com.exam.Service.DepService;
import com.exam.entity.Dep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dep")
public class DepContraller {
    @Autowired
    private DepService depService;

//显示所有信息
    @GetMapping
    public List showDep(){
        List<Dep> deps = depService.showDep();
        return deps;
    }

//    CM03-01
//    功能名称： 添加二级学院信息模块
    @PostMapping("/addDep")
    public String addDepinfo(@RequestBody Dep dep){
        int i = depService.addDepinfo(dep);

        return "添加成功";
    }


    //    CM03-02
//    功能名称： 删除二级学院信息模块
    @DeleteMapping("/{id}")
    public String deleteDep(@PathVariable int id){
        int i = depService.deleteDep(id);
        return "成功";
    }


    //    CM03-03
//    功能名称： 修改二级学院信息模块
    @PostMapping("/updataDep")
    public String updataDep(Dep dep){
        int i = depService.updataDep(dep);
        if (i!=0){
            return "成功";
        }else {
            return "失败";
        }
    }


    //    CM03-04
//    功能名称： 查询二级学院信息模块
    @GetMapping("/likename")
    public List showDep(@PathVariable String name){
        List<Dep> byName = depService.findByName(name);
        return byName;
    }
}
