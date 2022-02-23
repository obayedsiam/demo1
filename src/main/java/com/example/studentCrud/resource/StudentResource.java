package com.example.studentCrud.resource;

import com.example.studentCrud.dto.CourseDto;
import com.example.studentCrud.dto.StudentDto;
import com.example.studentCrud.entity.Course;
import com.example.studentCrud.entity.Student;
import com.example.studentCrud.enums.RecordStatus;
import com.example.studentCrud.service.CourseService;
import com.example.studentCrud.service.StudentService;
import com.example.studentCrud.utils.CommonDataHelper;
import com.example.studentCrud.validation.CourseValidator;
import com.example.studentCrud.validation.StudentValidator;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.example.studentCrud.exception.ApiError.fieldError;
import static com.example.studentCrud.utils.ResponseBuilder.error;
import static com.example.studentCrud.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/student")

public class StudentResource {

    private final StudentValidator validator;

    private final StudentService service;

    private final CommonDataHelper helper;

    @RequestMapping(
            path = "/save",
            method = RequestMethod.POST)
     @ApiOperation(value = "save student info with Image image", response = String.class)
    public ResponseEntity<JSONObject> save(@RequestBody StudentDto dto, BindingResult bindingResult) {

        //  log.info("Got request for creating a student.");
        ValidationUtils.invokeValidator(validator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            // error handling code goes here.
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }
        Student student = service.insertCourse(dto, RecordStatus.DRAFT);
        return ok(success(StudentDto.response(student), "Student Save Successfully").getJson());
    }



    @GetMapping("/find/{id}")
    @ResponseBody
    @ApiOperation(value = "Get student by id", response = String.class)
    public ResponseEntity<JSONObject> findById(@PathVariable Long id) {

        Optional<Student> student = service.findById(id, RecordStatus.DRAFT);

        return ok(success(student).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "Update qouta", response = String.class)
    public ResponseEntity<JSONObject> update(@RequestBody StudentDto dto, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }


        Student student = service.update(dto, RecordStatus.DRAFT);

        return ok(success(StudentDto.response(student), "Student Edited Successfully").getJson());
    }
}
