package com.boylegu.springboot_vue.controller;

import com.boylegu.springboot_vue.controller.pagination.StudentFormatting;
import com.boylegu.springboot_vue.controller.pagination.StudentMultiTypeValuesHelper;
import com.boylegu.springboot_vue.controller.pagination.TeacherFormatting;
import com.boylegu.springboot_vue.controller.pagination.TeacherMultiTypeValuesHelper;
import com.boylegu.springboot_vue.dao.StudentRepository;
import com.boylegu.springboot_vue.dao.TeacherRepository;
import com.boylegu.springboot_vue.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/Teacher")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Value(("${com.boylegu.paginatio.max-per-page}"))
    Integer maxPerPage;

    @RequestMapping(value = "/tea/subject", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSubjectAll() {

        ArrayList<Map<String, String>> results = new ArrayList<>();

        for (Object value : teacherRepository.findSubject()) {

            Map<String, String> subject = new HashMap<>();

            subject.put("label", value.toString());

            subject.put("value", value.toString());

            results.add(subject);
        }

        return new ResponseEntity<>(results,
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, TeacherMultiTypeValuesHelper> getTeacherAll(
            @RequestParam(value = "page", required = false) Integer pages,
            @RequestParam("subject") String subject,
            @RequestParam("number") String number
    ) {

        if (pages == null) {

            pages = 1;

        }

        Sort sort = new Sort(Direction.ASC, "id");

        Pageable pageable = new PageRequest(pages - 1, maxPerPage, sort);

        TeacherFormatting paginInstance = new TeacherFormatting();

        return paginInstance.filterQuery(subject, number, pageable);
    }

    @RequestMapping(value = "/detailOfTea/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Teacher> getUserDetail(@PathVariable Long id) {

        Teacher user = teacherRepository.findById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/detailOfTea/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Teacher detailOfTea(@PathVariable Long id, @RequestBody Teacher data) {

        Teacher teacher = teacherRepository.findById(id);

        teacher.setCreate_datetime(new Date().toString());
        teacher.setEmail(data.getEmail());
        teacher.setPhone(data.getPhone());
        teacher.setUsername(data.getUsername());

        return teacherRepository.save(teacher);
    }

    @RequestMapping(value = "/addTeacher", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Teacher addDormitory(@RequestBody Teacher data) {

        Teacher teacher = new Teacher();


        teacher.setUsername(data.getUsername());
        teacher.setPassWord(data.getPassWord());
        teacher.setClassInfo(data.getClassInfo());
        teacher.setCreate_datetime(new Date().toString());
        teacher.setEmail(data.getEmail());
        teacher.setNumber(data.getNumber());
        teacher.setJurisdiction(false);
        teacher.setPhone(data.getPhone());
        teacher.setSubject(data.getSubject());

        return teacherRepository.save(teacher);
    }

    @RequestMapping(value = "/deleteTea/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteTea(@PathVariable Long id) {

        Teacher teacher = teacherRepository.findById(id);

        try
        {
            teacherRepository.delete(teacher);
            return true;
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUserDetail(@RequestParam("username") String username) {

        String passWord = teacherRepository.findByUsername1(username);

        return new ResponseEntity<>(passWord, HttpStatus.OK);
    }

    @RequestMapping(value = "/showStudent",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, StudentMultiTypeValuesHelper> showStudent(
            @RequestParam("username") String username){

        Sort sort = new Sort(Direction.ASC, "id");
        Pageable pageable = new PageRequest(0, maxPerPage, sort);
        StudentFormatting studentFormatting = new StudentFormatting();
        return studentFormatting.findStudentofTeacher(teacherRepository.findClassByUsername1(username),Long.parseLong("1"), pageable);
    }
}