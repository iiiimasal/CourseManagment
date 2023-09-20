package com.example.CourseManagment.Abstraction;

import com.example.CourseManagment.entity.Lessons;

import java.util.List;

public interface LessonInterface {
    public List<Lessons> getLessons();
    public void createNewLesson(Lessons lesson);
    public void DeleteLesson(String lessonName);
    public void AddDepartment(String lessonName, String departmentName);

    public float averageOfLessonScore(String lessonName);
}
