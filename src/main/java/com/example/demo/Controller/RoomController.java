package com.example.demo.Controller;

import com.example.demo.entity.AppointStudents;
import com.example.demo.entity.Res;
import com.example.demo.entity.Room;
import com.example.demo.service.RoomService;
import com.example.demo.service.classRecordService;
import com.example.demo.tools.res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@CrossOrigin
public class RoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private res res1;
    @Autowired
    private classRecordService classRecordService;

    @RequestMapping("/getRooms")
    public Res getRooms(){
        try{
            List<Room> rooms = roomService.getRooms();
            List<AppointStudents> students = classRecordService.getAppointStudents();
            for (int i = 0; i < rooms.size(); i++){
                List<String> s = new ArrayList<String>();
                for (int k = 0; k < students.size(); k++){
                    if (rooms.get(i).getId() == students.get(k).getRoom_id()){
                        s.add(students.get(k).getStudent_name());
                    }
                }
                int percentage = (rooms.get(i).getCurrent_num() * 100 ) / rooms.get(i).getLimit_quantity();
                rooms.get(i).setPercentage(percentage);
                rooms.get(i).setStudents(s);
            }
            return res1.success("success", rooms);
        }
        catch (Exception e){
            return res1.fail("error.", e);
        }
    }

    @PostMapping("/insertRoom")
    public Res insertRoom(@RequestBody Map<String, String> roomInfo){
        try{
            roomService.insertRoom(Integer.parseInt(roomInfo.get("lesson_id")), Integer.parseInt(roomInfo.get("teacher_id")), roomInfo.get("class_time"), Integer.parseInt(roomInfo.get("limit_quantity")), roomInfo.get("remark"));
            return res1.success("success", null);
        }
        catch (Exception e){
            return res1.fail("error.", e);
        }
    }

    @PostMapping("/updateRoom")
    public Res updateRoom(@RequestBody Map<String, String> roomInfo){
        try{
            roomService.updateRoom(Integer.parseInt(roomInfo.get("lesson_id")), Integer.parseInt(roomInfo.get("teacher_id")), roomInfo.get("class_time"), Integer.parseInt(roomInfo.get("limit_quantity")), roomInfo.get("remark"), Integer.parseInt(roomInfo.get("id")));
            return res1.success("success", null);
        }
        catch (Exception e){
            return res1.fail("error.", e);
        }
    }

    @PostMapping("/delRoom")
    public Res delRoom(@RequestBody Map<String, Integer> roomId){
        try{
            roomService.delRoom(roomId.get("id"));
            return res1.success("success", null);
        }
        catch (Exception e){
            return res1.fail("error.", e);
        }
    }

    @PostMapping("/getRoomsByLevel")
    public Res getRoomsByLevel(@RequestBody Map<String, Integer> level){
        try{
            List<Room> roomsByLevel = roomService.getRoomByLevel(level.get("level"));
            List<AppointStudents> students = classRecordService.getAppointStudents();
            for (int i = 0; i < roomsByLevel.size(); i++){
                List<String> s = new ArrayList<String>();
                for (int k = 0; k < students.size(); k++){
                    if (roomsByLevel.get(i).getId() == students.get(k).getRoom_id()){
                        s.add(students.get(k).getStudent_name());
                    }
                }
                int percentage = (roomsByLevel.get(i).getCurrent_num() * 100 ) / roomsByLevel.get(i).getLimit_quantity();
                roomsByLevel.get(i).setPercentage(percentage);
                roomsByLevel.get(i).setStudents(s);
            }
            return res1.success("success", roomsByLevel);
        }
        catch (Exception e){
            return res1.fail("error.", null);
        }
    }

    @PostMapping("/getRoomsByTeacherId")
    public Res getRoomsByTeacherId(@RequestBody Map<String, Integer> teacher_id){
        try{
            List<Room> roomsByTeacherId = roomService.getRoomsByTeacherId(teacher_id.get("teacher_id"));
            List<AppointStudents> students = classRecordService.getAppointStudents();
            for (int i = 0; i < roomsByTeacherId.size(); i++){
                List<String> s = new ArrayList<String>();
                for (int k = 0; k < students.size(); k++){
                    if (roomsByTeacherId.get(i).getId() == students.get(k).getRoom_id()){
                        s.add(students.get(k).getStudent_name());
                    }
                }
                int percentage = (roomsByTeacherId.get(i).getCurrent_num() * 100 ) / roomsByTeacherId.get(i).getLimit_quantity();
                roomsByTeacherId.get(i).setPercentage(percentage);
                roomsByTeacherId.get(i).setStudents(s);
            }
            return res1.success("success", roomsByTeacherId);
        }
        catch (Exception e){
            return res1.fail("error.", null);
        }
    }

    @PostMapping("/getRoomsByStudentID")
    public Res getRoomsByStudentID(@RequestBody Map<String, Integer> student_id){
        try{
            List<Room> roomsByStudent_id = roomService.getRoomsByStudentId(student_id.get("student_id"));
            List<AppointStudents> students = classRecordService.getAppointStudents();
            for (int i = 0; i < roomsByStudent_id.size(); i++){
                List<String> s = new ArrayList<String>();
                for (int k = 0; k < students.size(); k++){
                    if (roomsByStudent_id.get(i).getId() == students.get(k).getRoom_id()){
                        s.add(students.get(k).getStudent_name());
                    }
                }
                int percentage = (roomsByStudent_id.get(i).getCurrent_num() * 100 ) / roomsByStudent_id.get(i).getLimit_quantity();
                roomsByStudent_id.get(i).setPercentage(percentage);
                roomsByStudent_id.get(i).setStudents(s);
            }
            return res1.success("success", roomsByStudent_id);
        }
        catch (Exception e){
            return res1.fail("error.", null);
        }
    }
}
