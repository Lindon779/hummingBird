package com.example.demo.Controller;

import com.example.demo.entity.Res;
import com.example.demo.entity.Student_info;
import com.example.demo.service.loginService;
import com.example.demo.service.studentInfoService;
import com.example.demo.tools.res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@CrossOrigin
public class studentInfoController {
    @Autowired
    private studentInfoService studentInfoService;
    @Autowired
    private res res1;
    @Autowired
    private loginService loginService;

    @RequestMapping("/getStudentInfo")
    public Res getStudentInfo(){
        try {
            List<Student_info> student_infos = studentInfoService.getStudentInfo();
            return res1.success("success", student_infos);
        }
        catch (Exception e){
            return res1.fail("error.", e);
        }

    }

    @PostMapping("/insertStudentInfo")
    public Res insertStudentInfo(@RequestBody Map<String, String> StudentInfo){
        try{
            int i = loginService.insertLogin(StudentInfo.get("mail"));
            if (i != 1){
                return res1.fail("邮箱账号已存在", null);
            }
            int id = loginService.selectIdByMail(StudentInfo.get("mail"));
            studentInfoService.insertStudentInfo(
                    StudentInfo.get("name"), StudentInfo.get("sex").equals("男")? 1 : 2,
                    StudentInfo.get("birthday"), Integer.parseInt(StudentInfo.get("level")), StudentInfo.get("mobile"), id, Integer.parseInt(StudentInfo.get("class_hour")));
            return res1.success("success", null);
        }
        catch (Exception e){
            return res1.fail("error.", e);
        }
    }

    @PostMapping("/updateStudentInfo")
    public Res updateStudentInfo(@RequestBody Map<String, String> updateInfo){
        try{
            studentInfoService.updateStudentInfo(updateInfo.get("name"), updateInfo.get("sex").equals("男")? 1 : 2, updateInfo.get("birthday"), Integer.parseInt(updateInfo.get("level")), updateInfo.get("mobile"), Integer.parseInt(updateInfo.get("class_hour")), Integer.parseInt(updateInfo.get("id")));
            return res1.success("success", null);
        }
        catch (Exception e){
            return res1.fail("error.", null);
        }
    }

    @PostMapping("/getStudentInfoById")
    public Res getStudentInfoById(@RequestBody Map<String, Integer> student_id){
        try{
            Student_info student_info = studentInfoService.getStudentInfoById(student_id.get("student_id"));
            return res1.success("success", student_info);
        }
        catch (Exception e){
            return res1.fail("error.", null);
        }
    }

    @PostMapping("/updateStudentPersonInfo")
    public Res updateStudentPersonInfo(@RequestBody Map<String, String> new_info){
        try{
            studentInfoService.updateStudentPersonInfo(new_info.get("name"), new_info.get("sex").equals("男")? 1 : 2, new_info.get("birthday"), new_info.get("mobile"), Integer.parseInt(new_info.get("id")));
            return res1.success("success", null);
        }
        catch (Exception e){
            return res1.fail("error.", null);
        }
    }

    @PostMapping("/updateStudentImage")
    public Res updateStudentImage(@RequestBody Map<String, String> imageInfo){
        try{
            studentInfoService.updateStduentImage(Integer.parseInt(imageInfo.get("login_id")), imageInfo.get("image"));
            return res1.success("success", null);
        }
        catch (Exception e){
            return res1.fail("error.", e);
        }
    }
}
