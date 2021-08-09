package com.example.demo.service;

import com.example.demo.dao.lessonDao;
import com.example.demo.entity.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class lessonService implements lessonDao {
    @Autowired
    private lessonDao lessonDao;

    @Override
    public List<Lesson> getLesson() {
        return lessonDao.getLesson();
    }

    @Override
    public int insertLesson(String class_name, int level) {
        return lessonDao.insertLesson(class_name, level);
    }

    @Override
    public int delLesson(int id) {
        return lessonDao.delLesson(id);
    }

    @Override
    public int updateLesson(String class_name, int level, int id) {
        return lessonDao.updateLesson(class_name, level, id);
    }
}
