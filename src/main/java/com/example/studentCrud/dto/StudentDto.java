package com.example.studentCrud.dto;

import com.example.studentCrud.entity.Address;
import com.example.studentCrud.entity.Student;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class StudentDto {

    private Long id;

    private String name;

    private List<Address> addressList;


    public static StudentDto response(Student student) {
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setAddressList(student.getAddressList());
        //      dto.setEnclosure(student.getEnclosure());
        return dto;
    }

    public Student to() {
        Student student = new Student();
        student.setName(this.name);
        student.setAddressList(this.addressList);
//        List<Course> temp = new ArrayList<>();
//
//        for(int i=0; i<=this.courseList.size();i++){
//            Course course = new Course();
//            course.setDuration(courseList.get(i).getDuration());
//            course.setName(courseList.get(i).getName());
//            temp.add(course);
//        }
//        student.setCourseList(temp);
////      student.setEnclosure(this.enclosure);
        return student;
    }

    public Student update(Student student) {
        student.setName(this.name);
        return student;
    }

}
