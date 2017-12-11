package com.boylegu.springboot_vue.controller;

import com.boylegu.springboot_vue.controller.pagination.StudentFormatting;
import com.boylegu.springboot_vue.controller.pagination.StudentMultiTypeValuesHelper;
import com.boylegu.springboot_vue.dao.StudentRepository;
import com.boylegu.springboot_vue.entities.Student;
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
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/Student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Value(("${com.boylegu.paginatio.max-per-page}"))
    Integer maxPerPage;

    @RequestMapping(value = "/stu/sex", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSexAll() {

        /*
         * @api {GET} /api/student/number Get all numberList
         * @apiName GetAllNumberList
         * @apiGroup Info Manage
         * @apiVersion 1.0.0
         * @apiExample {httpie} Example usage:
         *
         *     http /api/student/Number
         *
         * @apiSuccess {String} label
         * @apiSuccess {String} value
         */

        ArrayList<Map<String, String>> results = new ArrayList<>();

        for (Object value : studentRepository.findSex()) {

            Map<String, String> sex = new HashMap<>();

            sex.put("label", value.toString());

            sex.put("value", value.toString());

            results.add(sex);
        }

        ResponseEntity<ArrayList<Map<String, String>>> responseEntity = new ResponseEntity<>(results,
                HttpStatus.OK);

        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, StudentMultiTypeValuesHelper> getStudentAll(
            @RequestParam(value = "page", required = false) Integer pages,
            @RequestParam("name") String name,
            @RequestParam("number") String number
    ) {

        /*
         *   @api {GET} /api/students   Get all or a part of student info
         *   @apiName GetAllInfoOFStuList
         *   @apiGroup Info Manage
         *   @apiVersion 1.0.0
         *
         *   @apiExample {httpie} Example usage: (support combinatorial search)
         *
         *       All student：
         *       http /api/student
         *
         *       You can according to 'name | number' or 'name & number'
         *       http /api/student?name=xxx&number=xx
         *       http /api/student?name=xxx
         *       http /api/student?number=xx
         *
         *   @apiParam {String} name
         *   @apiParam {String} number
         *
         *   @apiSuccess {String} create_datetime
         *   @apiSuccess {String} number
         *   @apiSuccess {String} id
         *   @apiSuccess {String} phone
         *   @apiSuccess {String} name
         *   @apiSuccess {String} username
         *   @apiSuccess {String} zone
         */

        if (pages == null) {

            pages = 1;

        }

        Sort sort = new Sort(Direction.ASC, "id");

        Pageable pageable = new PageRequest(pages - 1, maxPerPage, sort);

        StudentFormatting paginInstance = new StudentFormatting();

        return paginInstance.filterQuery(name, number, pageable);
    }

    @RequestMapping(value = "/detailOfStu/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getUserDetail(@PathVariable Long id) {

        /*
        *    @api {GET} /api/student/detail/:id  details info
        *    @apiName GetPersonDetails
        *    @apiGroup Info Manage
        *    @apiVersion 1.0.0
        *
        *    @apiExample {httpie} Example usage:
        *
        *        http GET http://127.0.0.1:8000/api/student/detail/1
        *
        *    @apiSuccess {String} number
        *    @apiSuccess {String} id
        *    @apiSuccess {String} phone
        *    @apiSuccess {String} name
        *    @apiSuccess {String} username
        *    @apiSuccess {String} zone
        */

        Student user = studentRepository.findById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/detailOfStu/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Student updateUser(@PathVariable Long id, @RequestBody Student data) {

        /*
         *  @api {PUT} /api/student/detail/:id  update student info
         *  @apiName PutPersonDetails
         *  @apiGroup Info Manage
         *  @apiVersion 1.0.0
         *
         *  @apiParam {String} phone
         *  @apiParam {String} zone
         *
         *  @apiSuccess {String} create_datetime
         *  @apiSuccess {String} number
         *  @apiSuccess {String} id
         *  @apiSuccess {String} phone
         *  @apiSuccess {String} name
         *  @apiSuccess {String} username
         *  @apiSuccess {String} zone

        */
        Student user = studentRepository.findById(id);

        user.setPhone(data.getPhone());

        user.setPassWord(data.getPassWord());

        return studentRepository.save(user);
    }

}