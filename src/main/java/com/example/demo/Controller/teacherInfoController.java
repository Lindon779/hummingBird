package com.example.demo.Controller;

import com.example.demo.entity.Res;
import com.example.demo.entity.Student_info;
import com.example.demo.entity.Teacher_info;
import com.example.demo.service.loginService;
import com.example.demo.service.teacherInfoService;
import com.example.demo.tools.res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@CrossOrigin
public class teacherInfoController {
    @Autowired
    private teacherInfoService teacherInfoService;
    @Autowired
    private loginService loginService;
    @Autowired
    private res res1;

    @RequestMapping("/getTeacherInfo")
    public Res getTeacherInfo(){
        try {
            List<Teacher_info> teacherInfos = teacherInfoService.getTeacherInfo();
            return res1.success("success", teacherInfos);
        }
        catch (Exception e){
            return res1.fail("error.", e);
        }


    }

    @PostMapping("/insertTeacherInfo")
    public Res insertTeacherInfo(@RequestBody Map<String, String> teacherInfo){
        try{
            int i = loginService.insertLogin(teacherInfo.get("mail"));
            if (i == 2){
                return res1.fail("邮箱账号已存在", null);
            }
            int id = loginService.selectIdByMail(teacherInfo.get("mail"));
            teacherInfoService.insertTeacherInfo(
                    teacherInfo.get("name"), teacherInfo.get("sex").equals("男")? 1 : 2,
                    teacherInfo.get("birthday"), Integer.parseInt(teacherInfo.get("level")), teacherInfo.get("mobile"), id, teacherInfo.get("color"));
            return res1.success("success", null);
        }
        catch (Exception e){
            return res1.fail("error.", e);
        }
    }

    @PostMapping("/updateTeacherInfo")
    public Res updateTeacherInfo(@RequestBody Map<String, String> updateInfo){
        try{
            teacherInfoService.updateTeacherInfo(updateInfo.get("name"), updateInfo.get("sex").equals("男")? 1 : 2, updateInfo.get("birthday"), Integer.parseInt(updateInfo.get("level")), updateInfo.get("mobile"), updateInfo.get("color"), Integer.parseInt(updateInfo.get("id")));
            return res1.success("success", null);
        }
        catch (Exception e){
            return res1.fail("error.", null);
        }
    }

    @PostMapping("/getTeacherInfoById")
    public Res getTeacherInfoById(@RequestBody Map<String, Integer> teacher_id){
        try{
            Teacher_info teacher_info = teacherInfoService.getTeacherInfoById(teacher_id.get("teacher_id"));
            return res1.success("success", teacher_info);
        }
        catch (Exception e){
            return res1.fail("error.", null);
        }
    }

    @PostMapping("/updateTeacherPersonInfo")
    public Res updateTeacherPersonInfo(@RequestBody Map<String, String> updateInfo){
        try{
            teacherInfoService.updateTeacherPersonInfo(updateInfo.get("name"), updateInfo.get("sex").equals("男")? 1 : 2, updateInfo.get("birthday"),updateInfo.get("mobile"), Integer.parseInt(updateInfo.get("id")));
            return res1.success("success", null);
        }
        catch (Exception e){
            return res1.fail("error.", null);
        }
    }
    @PostMapping("/updateTeacherImage")
    public Res updateTeacherImage(@RequestBody Map<String, String> imageInfo){
        try{
            teacherInfoService.updateTeacherImage(Integer.parseInt(imageInfo.get("login_id")), imageInfo.get("image"));
            return res1.success("success", null);
        }
        catch (Exception e){
            return res1.fail("error.", e);
        }
    }
}
