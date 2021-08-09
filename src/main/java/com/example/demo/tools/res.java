package com.example.demo.tools;

import com.example.demo.entity.Res;
import org.springframework.stereotype.Component;

@Component
public class res {
    public static Res success(String msg, Object data){
        Res res = new Res();
        res.setStatus(200);
        res.setMsg(msg);
        res.setData(data);
        return res;
    }
    public static Res doubleSuccess(String msg, Object data1, Object data2){
        Res res = new Res();
        res.setStatus(200);
        res.setMsg(msg);
        res.setData(data1);
        res.setData2(data2);
        return res;
    }
    public static Res fail(String msg, Object data){
        Res res = new Res();
        res.setStatus(400);
        res.setMsg(msg);
        res.setData(data);
        return res;
    }
}
