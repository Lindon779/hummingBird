package com.example.demo.Controller;

import com.example.demo.entity.Class_record;
import com.example.demo.entity.Res;
import com.example.demo.service.classRecordService;
import com.example.demo.tools.res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@CrossOrigin
public class classRecordController {
    @Autowired
    private classRecordService classRecordService;

    @Autowired
    private res res1;

    @PostMapping("insertClassRecord")
    public Res insertClassRecord(@RequestBody Map<String, Integer> recordInfo){
        try{
            int i = classRecordService.insertClassRecord(recordInfo.get("usr_id"), recordInfo.get("room_id"));
            if (i == 0){
                return res1.fail("error.", null);
            }
            return res1.success("success", null);
        }
        catch (Exception e){
            return res1.fail("error.", e);
        }
    }

    @PostMapping("delClassRecord")
    public Res delClassRecord(@RequestBody Map<String, Integer> recordInfo){
        try{
            int i = classRecordService.delClassRecord(recordInfo.get("usr_id"), recordInfo.get("room_id"));
            if (i == 0){
                return res1.fail("error.", null);
            }
            return res1.success("success", null);
        }
        catch (Exception e){
            return res1.fail("error.", e);
        }
    }

    @PostMapping("getAppointByUsrId")
    public Res getAppointByUsrId(@RequestBody Map<String, Integer> usrId){
        try {
            List<Integer> Appoint = classRecordService.getAppointByUsrId(usrId.get("usr_id"));
            return res1.success("success", Appoint);
        }
        catch (Exception e){
            return res1.fail("error.", null);
        }
    }

    @RequestMapping("getClassRecord")
    public Res getClassRecord(){
        try{
            List<Class_record> class_records = classRecordService.getClassRecord();
            return res1.success("success", class_records);
        }
        catch (Exception e){
            return res1.fail("error.", e);
        }
    }
}
