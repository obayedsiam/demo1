package com.example.studentCrud.helper;

import com.example.studentCrud.entity.Course;
import com.example.studentCrud.enums.RecordStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseHelper {

    public void getSaveData(Course course, RecordStatus recordStatus) {
        course.setRecordStatus(recordStatus);
    }

    public void getUpdateData(Course course, RecordStatus status){
        course.setRecordStatus(status);
    }
}
