package com.example.demo.Controller;

import com.example.demo.entity.*;
import com.example.demo.service.loginService;
import com.example.demo.service.studentInfoService;
import com.example.demo.service.teacherInfoService;
import com.example.demo.service.veriCodeService;
import com.example.demo.tools.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static com.example.demo.tools.Token.analysisToken;

@RestController
@ResponseBody
@CrossOrigin
public class veriCodeController {
    @Autowired
    private veriCodeService veriCodeService;
    @Autowired
    private SendMail sendMail;
    @Autowired
    private res res1;
    @Autowired
    private loginService loginService;
    @Autowired
    private com.example.demo.service.studentInfoService studentInfoService;
    @Autowired
    private com.example.demo.service.teacherInfoService teacherInfoService;

    @PostMapping("/getCode")
    public Res addVeriCode(@RequestBody Map<String, String> mail){
//        String create_time = getCurrentTime.getTime();       // 获取当前时间
        try{
            String code = sendMail.sendmail(mail.get("mail"));   // 发送邮件并返回生成的验证码
            int i = veriCodeService.addVeriCode(code, mail.get("mail"));    // 将验证码信息写入数据库
            if (i == 1){
                return res1.success("验证码发送成功！", null);
            }
            return res1.fail("验证码发送失败。", null);
        }
        catch (Exception e){
            return res1.fail("邮箱账户不存在。", null);
        }

    }

    @PostMapping("/login")
    public Res login(@RequestBody Map<String, String> loginInfo, HttpServletResponse response){
        String mail = loginInfo.get("mail");    // 获取接收到的邮箱号
        String code = loginInfo.get("code");    // 获取接收到的验证码
        try{
            Login login = loginService.checkMail(mail);    // 查询登录表中有无该邮箱号
            if (login != null){
                VeriCode veriCode = veriCodeService.queryCodeBYMail(mail);    // 根据接收的邮箱号到数据库中查找最新的一条未使用的验证码
                String time = veriCode.getCreate_time();    // 获取该验证码的创建时间
                if (compareTime.compareTime(time) > 3){// 验证码超过3分钟即失效
                    return res1.fail("验证码失效。", null);
                }
                if (code.equals(veriCode.getCode()) == true){    // 当验证码正确时
                    veriCodeService.updateCodeState(veriCode.getId());    // 将该验证码状态改为“已使用”
                    String token = Token.createToken(login.getId(), login.getPower());    // 生成token
                    loginService.updateToken(token, login.getId());    // 更新登录表中的token
                    Map<String, String> data = new HashMap<>();
                    data.put("token", token);  // 用hash去接收生成的token
                    if(login.getPower() == 1){
                        Student_info student_info = studentInfoService.getStudentInfoById(login.getId());
                        return res1.doubleSuccess("success", data, student_info);
                    }
                    else {
                        Teacher_info teacher_info = teacherInfoService.getTeacherInfoById(login.getId());
                        return res1.doubleSuccess("success", data, teacher_info);
                    }
                }
            }
        }catch (Exception e){
            return res1.fail("用户不存在。", e);
        }
        return res1.fail("验证码不正确.", null);
    }

    @PostMapping("/delUser")
    public Res delUser(@RequestBody Map<String, Integer> user){
        try{
            loginService.delUserById(user.get("id"));
            return res1.success("success", null);
        }
        catch (Exception e){
            return res1.fail("error.", e);
        }
    }

    @RequestMapping("/checkLogin")
    public Res checkLogin(HttpServletRequest request, HttpServletResponse response){
        String token = request.getHeader("Authorization");
        try {
            Map<String, String> token_info = Token.analysisToken(token);   // 解析接收到的token
            int id = Integer.parseInt(token_info.get("id"));    // 提取接收到的id
            String login_token = loginService.getLoginToken(id);    // 用提取到的id去数据库登录表拿对应的token
            if (token.equals(login_token)){
                if(Integer.parseInt(token_info.get("power")) == 1){
                    Student_info student_info = studentInfoService.getStudentInfoById(id);
                    return res1.success("success", student_info);
                }
                else {
                    Teacher_info teacher_info = teacherInfoService.getTeacherInfoById(id);
                    return res1.success("success", teacher_info);
                }
            }
            else {
                response.setStatus(302);
                return res1.fail("登录已失效", null);
            }

        }
        catch(Exception e){
            response.setStatus(302);
            return res1.fail("request fail", null);
        }
    }
    @RequestMapping("/loginPageCheck")
    public Res loginPageCheck(HttpServletRequest request, HttpServletResponse response){
        String token = request.getHeader("Authorization");
        try {
            Map<String, String> token_info = Token.analysisToken(token);   // 解析接收到的token
            int id = Integer.parseInt(token_info.get("id"));    // 提取接收到的id
            String login_token = loginService.getLoginToken(id);    // 用提取到的id去数据库登录表拿对应的token
            if (token.equals(login_token)){
                return res1.success("request success", token_info);
            }
            else {
                return res1.fail("request fail", null);
            }

        }
        catch(Exception e){
            return res1.fail("request fail", null);
        }
    }


    @PostMapping("/veriCode")
    public Res veriCode(@RequestBody Map<String, String> loginInfo){
        String mail = loginInfo.get("mail");    // 获取接收到的邮箱号
        String code = loginInfo.get("code");    // 获取接收到的验证码
        try{
            Login login = loginService.checkMail(mail);    // 查询登录表中有无该邮箱号
            if (login != null){
                VeriCode veriCode = veriCodeService.queryCodeBYMail(mail);    // 根据接收的邮箱号到数据库中查找最新的一条未使用的验证码
                String time = veriCode.getCreate_time();    // 获取该验证码的创建时间
                if (compareTime.compareTime(time) > 3){// 验证码超过3分钟即失效
                    return res1.fail("验证码失效。", null);
                }
                if (code.equals(veriCode.getCode()) == true){    // 当验证码正确时
                    veriCodeService.updateCodeState(veriCode.getId());    // 将该验证码状态改为“已使用”
                    return res1.success("验证通过", null);
                }
            }
        }catch (Exception e){
            return res1.fail("用户不存在。", e);
        }
        return res1.fail("server error.", null);
    }

    @PostMapping("/updateMail")
    public Res updateMail(@RequestBody Map<String, String> newInfo) throws ParseException {
        try{
            Login login = loginService.checkMail(newInfo.get("mail"));    // 查询登录表中有无该邮箱号
            if (login == null){
                VeriCode veriCode = veriCodeService.queryCodeBYMail(newInfo.get("mail"));    // 根据接收的邮箱号到数据库中查找最新的一条未使用的验证码
                String time = veriCode.getCreate_time();    // 获取该验证码的创建时间
                if (compareTime.compareTime(time) > 3){// 验证码超过3分钟即失效
                    return res1.fail("验证码失效。", null);
                }
                if (newInfo.get("code").equals(veriCode.getCode()) == true){    // 当验证码正确时
                    veriCodeService.updateCodeState(veriCode.getId());    // 将该验证码状态改为“已使用”
                    loginService.updateMail(newInfo.get("mail"), Integer.parseInt(newInfo.get("id")));  // 更改邮箱号
                    return res1.success("success", null);
                }
                else {
                    return res1.success("验证码错误！", null);
                }
            }
            else {
                return res1.fail("邮箱账户已存在。", null);
            }
        }
        catch (Exception e){
            return res1.success("service error.", null);
        }
    }


    @RequestMapping("getTime")
    public Res getTime() throws ParseException {
        String time = compareTime.showTime();
        return res1.success("success", time);
    }
}
