package com.boylegu.springboot_vue.controller;

import com.boylegu.springboot_vue.controller.pagination.TeacherFormatting;
import com.boylegu.springboot_vue.controller.pagination.TeacherMultiTypeValuesHelper;
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
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/Teacher")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Value(("${com.boylegu.paginatio.max-per-page}"))
    Integer maxPerPage;

    @RequestMapping(value = "/tea/sex", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSexAll() {

        /*
         * @api {GET} /api/Teacher/number Get all numberList
         * @apiusername GetAllNumberList
         * @apiGroup Info Manage
         * @apiVersion 1.0.0
         * @apiExample {httpie} Example usage:
         *
         *     http /api/Teacher/Number
         *
         * @apiSuccess {String} label
         * @apiSuccess {String} value
         */

        ArrayList<Map<String, String>> results = new ArrayList<>();

        for (Object value : teacherRepository.findSubject()) {

            Map<String, String> subject = new HashMap<>();

            subject.put("label", value.toString());

            subject.put("value", value.toString());

            results.add(subject);
        }

        ResponseEntity<ArrayList<Map<String, String>>> responseEntity = new ResponseEntity<>(results,
                HttpStatus.OK);

        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, TeacherMultiTypeValuesHelper> getTeacherAll(
            @RequestParam(value = "page", required = false) Integer pages,
            @RequestParam("username") String username,
            @RequestParam("number") String number
    ) {

        /*
         *   @api {GET} /api/Teachers   Get all or a part of Teacher info
         *   @apiusername GetAllInfoOFStuList
         *   @apiGroup Info Manage
         *   @apiVersion 1.0.0
         *
         *   @apiExample {httpie} Example usage: (support combinatorial search)
         *
         *       All Teacher：
         *       http /api/Teacher
         *
         *       You can according to 'username | number' or 'username & number'
         *       http /api/Teacher?username=xxx&number=xx
         *       http /api/Teacher?username=xxx
         *       http /api/Teacher?number=xx
         *
         *   @apiParam {String} username
         *   @apiParam {String} number
         *
         *   @apiSuccess {String} create_datetime
         *   @apiSuccess {String} number
         *   @apiSuccess {String} id
         *   @apiSuccess {String} phone
         *   @apiSuccess {String} username
         *   @apiSuccess {String} userusername
         *   @apiSuccess {String} zone
         */

        if (pages == null) {

            pages = 1;

        }

        Sort sort = new Sort(Direction.ASC, "id");

        Pageable pageable = new PageRequest(pages - 1, maxPerPage, sort);

        TeacherFormatting paginInstance = new TeacherFormatting();

        return paginInstance.filterQuery(username, number, pageable);
    }

    @RequestMapping(value = "/detailOfStu/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Teacher> getUserDetail(@PathVariable Long id) {

        /*
        *    @api {GET} /api/Teacher/detail/:id  details info
        *    @apiusername GetPersonDetails
        *    @apiGroup Info Manage
        *    @apiVersion 1.0.0
        *
        *    @apiExample {httpie} Example usage:
        *
        *        http GET http://127.0.0.1:8000/api/Teacher/detail/1
        *
        *    @apiSuccess {String} number
        *    @apiSuccess {String} id
        *    @apiSuccess {String} phone
        *    @apiSuccess {String} username
        *    @apiSuccess {String} userusername
        *    @apiSuccess {String} zone
        */

        Teacher user = teacherRepository.findById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/detailOfStu/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Teacher updateUser(@PathVariable Long id, @RequestBody Teacher data) {

        /*
         *  @api {PUT} /api/Teacher/detail/:id  update Teacher info
         *  @apiusername PutPersonDetails
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
         *  @apiSuccess {String} username
         *  @apiSuccess {String} userusername
         *  @apiSuccess {String} zone

        */
        Teacher user = teacherRepository.findById(id);

        user.setPhone(data.getPhone());

        user.setPassWord(data.getPassWord());

        return teacherRepository.save(user);
    }

}