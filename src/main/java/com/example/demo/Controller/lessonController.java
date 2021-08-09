package com.example.demo.Controller;

import com.example.demo.entity.Lesson;
import com.example.demo.entity.Res;
import com.example.demo.service.lessonService;
import com.example.demo.tools.res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@CrossOrigin
public class lessonController {
    @Autowired
    private res res1;
    @Autowired
    private lessonService lessonService;

    @RequestMapping("/getLesson")
    public Res getLesson(){
        try{
            List<Lesson> lessons = lessonService.getLesson();
            return res1.success("success", lessons);
        }
        catch (Exception e){
            return res1.fail("fail", e);
        }
    }

    @PostMapping("/delLesson")
    public Res delLesson(@RequestBody Map<String, Integer> lessonId){
        try{
            lessonService.delLesson(lessonId.get("id"));
            return res1.success("success", null);
        }
        catch (Exception e){
            return res1.fail("error.", null);
        }
    }

    @PostMapping("/insertLesson")
    public Res insertLesson(@RequestBody Map<String, String> newLesson){
        try{
            lessonService.insertLesson(newLesson.get("class_name"), Integer.parseInt(newLesson.get("level")));
            return res1.success("success", null);
        }
        catch (Exception e){
            return res1.fail("error.", e);
        }
    }

    @PostMapping("/updateLesson")
    public Res updateLesson(@RequestBody Map<String, String> updateInfo){
        try{
            lessonService.updateLesson(updateInfo.get("class_name"), Integer.parseInt(updateInfo.get("level")), Integer.parseInt(updateInfo.get("id")));
            return res1.success("success", null);
        }
        catch (Exception e){
            return res1.fail("error.", null);
        }
    }
}
