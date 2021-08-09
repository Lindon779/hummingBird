package com.example.demo.service;

import com.example.demo.dao.loginDao;
import com.example.demo.entity.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class loginService implements loginDao {
    @Autowired
    private loginDao loginDao;

    @Override
    public Login checkMail(String mail) {
        return loginDao.checkMail(mail);
    }

    @Override
    public int updateToken(String token, int id) {
        return loginDao.updateToken(token, id);
    }

    @Override
    public String getLoginToken(int id) {
        return loginDao.getLoginToken(id);
    }

    @Override
    public int insertLogin(String mail) {
        try{
            loginDao.selectIdByMail(mail);
            return 2;
        }
        catch (Exception e){
            return loginDao.insertLogin(mail);
        }

    }

    @Override
    public int selectIdByMail(String mail) {
        return loginDao.selectIdByMail(mail);
    }

    @Override
    public int delUserById(int id) {
        return loginDao.delUserById(id);
    }

    @Override
    public int updateMail(String mail, int id) {
        return loginDao.updateMail(mail, id);
    }
}
