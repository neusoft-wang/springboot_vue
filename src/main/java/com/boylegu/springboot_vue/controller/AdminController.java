package com.boylegu.springboot_vue.controller;

import com.boylegu.springboot_vue.controller.pagination.AdminMultiTypeValuesHelper;
import com.boylegu.springboot_vue.controller.pagination.AdministratorsFormatting;
import com.boylegu.springboot_vue.dao.AdminRepository;
import com.boylegu.springboot_vue.entities.Administrators;
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
@RequestMapping("/api/Admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Value(("${com.boylegu.paginatio.max-per-page}"))
    Integer maxPerPage;

    @RequestMapping(value = "/tea/jurisdiction", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getJurisdictionAll() {

        /*
         * @api {GET} /api/Administrators/number Get all numberList
         * @apiusername GetAllNumberList
         * @apiGroup Info Manage
         * @apiVersion 1.0.0
         * @apiExample {httpie} Example usage:
         *
         *     http /api/Administrators/Number
         *
         * @apiSuccess {String} label
         * @apiSuccess {String} value
         */

        ArrayList<Map<String, String>> results = new ArrayList<>();

        for (Object value : adminRepository.findJurisdiction()) {

            Map<String, String> jurisdiction = new HashMap<>();

            jurisdiction.put("label", value.toString());

            jurisdiction.put("value", value.toString());

            results.add(jurisdiction);
        }

        ResponseEntity<ArrayList<Map<String, String>>> responseEntity = new ResponseEntity<>(results,
                HttpStatus.OK);

        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, AdminMultiTypeValuesHelper> getAdministratorsAll(
            @RequestParam(value = "page", required = false) Integer pages,
            @RequestParam("username") String username,
            @RequestParam("number") String number
    ) {

        /*
         *   @api {GET} /api/Administratorss   Get all or a part of Administrators info
         *   @apiusername GetAllInfoOFStuList
         *   @apiGroup Info Manage
         *   @apiVersion 1.0.0
         *
         *   @apiExample {httpie} Example usage: (support combinatorial search)
         *
         *       All Administrators：
         *       http /api/Administrators
         *
         *       You can according to 'username | number' or 'username & number'
         *       http /api/Administrators?username=xxx&number=xx
         *       http /api/Administrators?username=xxx
         *       http /api/Administrators?number=xx
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

        AdministratorsFormatting paginInstance = new AdministratorsFormatting();

        return paginInstance.filterQuery(username, number, pageable);
    }

    @RequestMapping(value = "/detailOfAdm/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Administrators> getUserDetail(@PathVariable Long id) {

        /*
        *    @api {GET} /api/Administrators/detail/:id  details info
        *    @apiusername GetPersonDetails
        *    @apiGroup Info Manage
        *    @apiVersion 1.0.0
        *
        *    @apiExample {httpie} Example usage:
        *
        *        http GET http://127.0.0.1:8000/api/Administrators/detail/1
        *
        *    @apiSuccess {String} number
        *    @apiSuccess {String} id
        *    @apiSuccess {String} phone
        *    @apiSuccess {String} username
        *    @apiSuccess {String} userusername
        *    @apiSuccess {String} zone
        */

        Administrators admin = adminRepository.findById(id);

        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @RequestMapping(value = "/detailOfStu/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Administrators updateUser(@PathVariable Long id, @RequestBody Administrators data) {

        /*
         *  @api {PUT} /api/Administrators/detail/:id  update Administrators info
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
        Administrators user = adminRepository.findById(id);

        user.setJurisdiction(data.getJurisdiction());

        user.setPassWord(data.getPassWord());

        return adminRepository.save(user);
    }

}