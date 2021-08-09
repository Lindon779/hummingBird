package com.example.demo.service;

import com.example.demo.dao.veriCodeDao;
import com.example.demo.entity.VeriCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class veriCodeService implements veriCodeDao {
    @Autowired
    private veriCodeDao veriCodeDao;

    @Override
    public int addVeriCode(String code, String mail) {
        return veriCodeDao.addVeriCode(code, mail);
    }

    @Override
    public VeriCode queryCodeBYMail(String mail) {
        return veriCodeDao.queryCodeBYMail(mail);
    }

    @Override
    public int updateCodeState(int id) {
        return veriCodeDao.updateCodeState(id);
    }

}
