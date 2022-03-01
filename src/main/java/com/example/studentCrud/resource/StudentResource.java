package com.example.studentCrud.resource;

import com.example.studentCrud.dto.StudentCourseListDTO;
import com.example.studentCrud.dto.StudentDto;
import com.example.studentCrud.entity.Address;
import com.example.studentCrud.entity.Enclosure;
import com.example.studentCrud.entity.Student;
import com.example.studentCrud.enums.RecordStatus;
import com.example.studentCrud.exception.ResourceNotFoundException;
import com.example.studentCrud.helper.StudentHelper;
import com.example.studentCrud.service.StudentService;
import com.example.studentCrud.utils.CommonDataHelper;
import com.example.studentCrud.utils.PaginatedResponse;
import com.example.studentCrud.validation.StudentValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.istack.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.studentCrud.exception.ApiError.fieldError;
import static com.example.studentCrud.utils.ResponseBuilder.*;
import static com.example.studentCrud.utils.StringUtils.isEmpty;
import static com.example.studentCrud.utils.StringUtils.nonNull;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/student")
@Api(tags = "Student's data")
public class StudentResource {

    private final StudentValidator validator;

    private final StudentService service;

    private final CommonDataHelper helper;

    private final StudentHelper studentHelper;


    // Method to save Student
    @RequestMapping(path = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "save student info with Image image", response = String.class)
    public ResponseEntity<JSONObject> save(@RequestBody StudentDto dto, BindingResult bindingResult) {

        //  log.info("Got request for creating a student.");
        ValidationUtils.invokeValidator(validator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            // error handling code goes here.
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }
        Student student = service.insertStudent(dto, RecordStatus.DRAFT);

        return ok(success(StudentDto.response(student), "Student Save Successfully").getJson());
    }


    // Method to save course
//    @RequestMapping(path = "/save/course", method = RequestMethod.POST)
//    @ApiOperation(value = "save student info with Image image", response = String.class)
//    public ResponseEntity<JSONObject> saveCourse(@RequestBody StudentCourseListDTO dto, BindingResult bindingResult) {
//
//        //  log.info("Got request for creating a student.");
//        // ValidationUtils.invokeValidator(validator, dto, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            // error handling code goes here.
//            return badRequest().body(error(fieldError(bindingResult)).getJson());
//        }
//        StudentCourseList studentCourseList = service.insertStudent(dto, RecordStatus.DRAFT);
//
//
//        return ok(success(StudentCourseListDTO.response(studentCourseList), "Student Save Successfully").getJson());
//    }


    // Method to save Address
//    @RequestMapping(path = "/save/address", method = RequestMethod.POST)
//    @ApiOperation(value = "save student info with Image image", response = String.class)
//    public ResponseEntity<JSONObject> saveAddress(@RequestBody Address address, BindingResult bindingResult) {
//
//
//        //  log.info("Got request for creating a student.");
//        // ValidationUtils.invokeValidator(validator, dto, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            // error handling code goes here.
//            return badRequest().body(error(fieldError(bindingResult)).getJson());
//        }
//        //  StudentCourseListDTO student = service.insertStudent(dto, RecordStatus.DRAFT);
//        Student student1 = new Student();
//
//        return ok(success(StudentDto.response(student1), "Student Save Successfully").getJson());
//    }



    // Method to save enclosure
    @RequestMapping(
            path = "/save/encloser/{studentId}",
            method = RequestMethod.POST,
            consumes = {"multipart/form-data"})
    @ApiOperation(value = "save student info with Image image", response = StudentDto.class)
    public ResponseEntity<JSONObject> saveEnclosure(@PathVariable("studentId") @NotNull Long studentId,
                                                    @RequestPart(value = "file1", required = false) MultipartFile file1,
                                                    @RequestParam(value = "request1", required = false) String request1) {

        Student student = service.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student " + studentId));

        if (nonNull(file1) && isEmpty(request1))
            return badRequest().body(
                    error("file 1 data must be selected").getJson());


        Enclosure enclosure = null;
        try {
            enclosure = studentHelper.getStudentEnclosure(file1, request1, student);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        student.addEnclosure(enclosure);
        service.saveEncloser(student);
        return ok(success(null, "success").getJson());
    }



    // Method to get Student by ID
    @GetMapping("/find/{id}")
    @ApiOperation(value = "Get student by id", response = String.class)
    public ResponseEntity<JSONObject> findById(@PathVariable Long id) {

        Optional<Student> student = service.findById(id);

        return ok(success(student).getJson());
    }


    // Method to get All Students
    @GetMapping("/findAll")
    @ResponseBody
    //@ApiOperation(value = "Get student by id", response = StudentResponse.class)
    public ResponseEntity<JSONObject> findAll() {

        List<Student> student = service.findAll();

        return ok(success(student).getJson());
    }



    @GetMapping("/list")
    @ApiOperation(value = "Get Student list", response = StudentDto.class)
    public ResponseEntity<JSONObject> getList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {

        helper.setPageSize(page, size);

        PaginatedResponse response = new PaginatedResponse();

        Map<String, Object> filterMap = new HashMap<String, Object>();

        Map<String, Object> countryMap = service.getList(page, size);

        List<Student> studentList = (List<Student>) countryMap.get("lists");
        List<StudentDto> responses = studentList.stream().map(StudentDto::response)
                .collect(Collectors.toList());

        helper.getCommonData(page, size, countryMap, response, responses);

        return ok(paginatedSuccess(response).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "Update student", response = String.class)
    public ResponseEntity<JSONObject> update(@RequestBody StudentDto dto, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Student student = service.update(dto, RecordStatus.DRAFT);

        return ok(success(StudentDto.response(student), "Student Edited Successfully").getJson());
    }

//    @PostMapping("/save-student-course/{studentId}")
//    @ApiOperation(value = "Update student", response = String.class)
//    public ResponseEntity<JSONObject> update(@PathVariable("studentId") @NotNull Long studentId, @RequestBody StudentCourseListDTO dto) {
//
//        Student student = service.findById(studentId)
//                .orElseThrow(() -> new ResourceNotFoundException("Student " + studentId));
//        service.studentCourse(dto, student);
//
//        return ok(success(null, "Student Edited Successfully").getJson());
//    }
}
