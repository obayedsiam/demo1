package com.example.demo.helper;

import com.example.demo.entity.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseHelper {

    public void getSaveData(Course course) {
        course.setRecordStatus("DRAFT");
    }

    public void getUpdateData(Course course, String status){
        course.setRecordStatus(status);
    }
}
