package com.example.studentCrud.helper;

import com.example.studentCrud.entity.Enclosure;
import com.example.studentCrud.entity.Student;
import com.example.studentCrud.enums.RecordStatus;
import com.example.studentCrud.utils.FileUpload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class StudentHelper extends FileUpload {

    @Resource
    private Environment env;

    public void getSaveData(Student student, RecordStatus recordStatus) {
        student.setRecordStatus(recordStatus);
    }

    public void getUpdateData(Student student, RecordStatus status) {
        student.setRecordStatus(status);
    }

    public Enclosure getStudentEnclosure(
            MultipartFile file1,
            String request1,
            Student student) throws JsonProcessingException {

        //       Enclosure encloser = new Enclosure();
        //       if (student.getEnclosure() != null) {
//            if (!student.getEnclosure().isEmpty()) {
//                List<Enclosure> lists = new ArrayList<>(student.getEnclosure());
//                encloser.addAll(lists);
//            }
        //      }

        Enclosure enclosure = new ObjectMapper().readValue(request1, Enclosure.class);

        // enclosers.add(encloser_1);

        if (nonNull(file1)) {
            setEncloserFile(enclosure, file1);
        }
        return enclosure;
    }

//    public void setStudentCourse(StudentCourseListDTO studentCourseListDTO, Student student) {
//        student.getStudentCourses().clear();
//        if(Objects.isNull(studentCourseListDTO)
//                || Objects.isNull(studentCourseListDTO.getStudentCourses())){
//            return;
//        }
//        for(StudentCourseDTO studentCourseDTO : studentCourseListDTO.getStudentCourses()){
//            StudentCourse studentCourse = studentCourseDTO.toEntity(student);
////            setBaseEntityProperties(studentCourse, recordStatus);
//        }
//    }

    public void setEncloserFile(Enclosure enclosure, MultipartFile file1) {
        enclosure.setUrl(upload(file1, env.getProperty("ftpFileUploadPath")));
    }
}
