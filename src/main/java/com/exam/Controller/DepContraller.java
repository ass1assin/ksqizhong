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
}
