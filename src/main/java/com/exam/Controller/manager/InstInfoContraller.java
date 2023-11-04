package com.exam.Controller.manager;

import com.exam.Service.manager.InstInfoService;
import com.exam.entity.Inst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inst")
public class InstInfoContraller {
    @Autowired
    private InstInfoService instInfoService;

//显示所有信息
    @GetMapping("/showInst")
    public List showInst(){
        List<Inst> insts = instInfoService.showInst();
        return insts;
    }

//    CM03-01
//    功能名称： 添加辅导员信息模块
    @PostMapping("/addInst")
    public String addInst(@RequestBody Inst inst){
        int i = instInfoService.addInst(inst);

        return "添加成功";
    }


    //    CM03-02
//    功能名称： 删除辅导员信息模块
    @DeleteMapping("/{id}")
    public String deleteInst(@PathVariable List<Integer> ids){
        int i = instInfoService.deleteInst(ids);
        return "成功";
    }


    //    CM03-03
//    功能名称： 修改辅导员信息模块
    @PostMapping("/updataInst")
    public String updataInst(Inst inst){
        int i = instInfoService.updataInst(inst);
        if (i!=0){
            return "成功";
        }else {
            return "失败";
        }
    }


    //    CM03-04
//    功能名称： 查询辅导员信息模块
    @GetMapping("/likename")
    public List showDep(@PathVariable String name){
        List<Inst> byName = instInfoService.findByName(name);
        return byName;
    }
}
