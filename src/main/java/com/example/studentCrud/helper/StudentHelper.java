package com.example.studentCrud.helper;

import com.example.studentCrud.entity.Student;
import com.example.studentCrud.enums.RecordStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentHelper {

    public void getSaveData(Student student, RecordStatus recordStatus) {
        student.setRecordStatus(recordStatus);
    }

    public void getUpdateData(Student student, RecordStatus status) {
        student.setRecordStatus(status);
    }
}
