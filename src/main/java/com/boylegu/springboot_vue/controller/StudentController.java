package com.boylegu.springboot_vue.controller;

import com.boylegu.springboot_vue.controller.pagination.DormitoryMultiTypeValuesHelper;
import com.boylegu.springboot_vue.controller.pagination.StudentFormatting;
import com.boylegu.springboot_vue.controller.pagination.StudentMultiTypeValuesHelper;
import com.boylegu.springboot_vue.dao.DormitoryRepository;
import com.boylegu.springboot_vue.dao.StudentRepository;
import com.boylegu.springboot_vue.entities.Dormitory;
import com.boylegu.springboot_vue.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/Student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DormitoryRepository dormitoryRepository;

    @Value(("${com.boylegu.paginatio.max-per-page}"))
    Integer maxPerPage;

    @RequestMapping(value = "/stu/class", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getClassAll() {

        ArrayList<Map<String, String>> results = new ArrayList<>();

        for (Object value : studentRepository.findClass()) {

            Map<String, String> classInfo = new HashMap<>();

            classInfo.put("label", value.toString());

            classInfo.put("value", value.toString());

            results.add(classInfo);
        }

        ResponseEntity<ArrayList<Map<String, String>>> responseEntity = new ResponseEntity<>(results,
                HttpStatus.OK);

        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, StudentMultiTypeValuesHelper> getStudentAll(
            @RequestParam(value = "page", required = false) Integer pages,
            @RequestParam("classInfo") String classInfo,
            @RequestParam("number") String number
    ) {
        if (pages == null) {

            pages = 1;

        }

        Sort sort = new Sort(Direction.ASC, "id");

        Pageable pageable = new PageRequest(pages - 1, maxPerPage, sort);

        StudentFormatting paginInstance = new StudentFormatting();

        return paginInstance.filterQuery(classInfo, number, pageable);
    }

    @RequestMapping(value = "/detailOfStu/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getUserDetail(@PathVariable Long id) {

        Student user = studentRepository.findById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/detailOfStu/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Student updateUser(@PathVariable Long id, @RequestBody Student data) {

        Student student = studentRepository.findById(id);

        student.setUsername(data.getUsername());
        student.setCreate_datetime(new Date().toString());
        student.setEmail(data.getEmail());
        student.setPhone(data.getPhone());
        student.setClassInfo(data.getClassInfo());

        return studentRepository.save(student);
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Student addDormitory(@RequestBody Student data) {

        Student student = new Student();

        student.setName(data.getName());
        student.setPassWord(data.getPassWord());
        student.setClassInfo(data.getClassInfo());
        student.setCreate_datetime(new Date().toString());
        student.setEmail(data.getEmail());
        student.setNumber(data.getNumber());
        student.setPayState(false);
        student.setSex(data.getSex());
        student.setUse(false);
        student.setUseTime(data.getUseTime());
        student.setPhone(data.getPhone());
        student.setUsername(data.getUsername());

        return studentRepository.save(student);
    }

    @RequestMapping(value = "/deleteStu/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteStu(@PathVariable Long id) {

        Student student = studentRepository.findById(id);

        try
        {
            studentRepository.delete(student);
            return true;
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUserDetail(@RequestParam( "username") String username) {

        String passWord = studentRepository.findByUsername1(username);

        return new ResponseEntity<>(passWord, HttpStatus.OK);
    }

    @RequestMapping(value = "/addDormitory",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Student addDormitory(
                    @RequestParam("username") String username,
                    @RequestParam("id") String id) {

        Student student = studentRepository.findByUsername(username);

        student.setDormitory(Long.parseLong(id));
        student.setStatus(Long.parseLong("1"));

        return studentRepository.save(student);
    }

    @RequestMapping(value = "/showHistory",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Dormitory showHistory(
                    @RequestParam("username") String username){

        Dormitory dormitory = dormitoryRepository.findById(studentRepository.findHistory(username));

        return dormitory;
    }

}