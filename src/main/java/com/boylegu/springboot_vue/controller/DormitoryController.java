package com.boylegu.springboot_vue.controller;

import com.boylegu.springboot_vue.controller.pagination.DormitoryFormatting;
import com.boylegu.springboot_vue.controller.pagination.DormitoryMultiTypeValuesHelper;
import com.boylegu.springboot_vue.dao.DormitoryRepository;
import com.boylegu.springboot_vue.entities.Dormitory;
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
@RequestMapping("/api/dormitory")
public class DormitoryController {

    @Autowired
    private DormitoryRepository dormitoryRepository;

    @Value(("${com.boylegu.paginatio.max-per-page}"))
    Integer maxPerPage;

    @RequestMapping(value = "/dor/state", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStateAll() {

        ArrayList<Map<String, String>> results = new ArrayList<>();

        for (Object value : dormitoryRepository.findSTATE()) {

            Map<String, String> state = new HashMap<>();

            state.put("label", value.toString());

            state.put("value", value.toString());

            results.add(state);
        }

        ResponseEntity<ArrayList<Map<String, String>>> responseEntity = new ResponseEntity<>(results,
                HttpStatus.OK);

        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, DormitoryMultiTypeValuesHelper> getDormitoryAll(
            @RequestParam(value = "page", required = false) Integer pages,
            @RequestParam("state") String state,
            @RequestParam("name") String name
    ) {

        if (pages == null) {

            pages = 1;

        }

        Sort sort = new Sort(Direction.ASC, "id");

        Pageable pageable = new PageRequest(pages - 1, maxPerPage, sort);

        DormitoryFormatting paginInstance = new DormitoryFormatting();

        return paginInstance.filterQuery(state, name, pageable);
    }

    @RequestMapping(value = "/detailOfDor/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dormitory> getDormitoryDetail(@PathVariable Long id) {

        Dormitory dor = dormitoryRepository.findById(id);

        return new ResponseEntity<>(dor, HttpStatus.OK);
    }

    @RequestMapping(value = "/detailOfDor/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Dormitory updateDormitory(@PathVariable Long id, @RequestBody Dormitory data) {

        Dormitory dormitory = dormitoryRepository.findById(id);

        dormitory.setCreate_datetime(new Date().toString());
        dormitory.setDescription(data.getDescription());
        dormitory.setName(data.getName());
        dormitory.setDor(data.getDor());
        dormitory.setState(data.getState());

        return dormitoryRepository.save(dormitory);
    }

    @RequestMapping(value = "/addDormitory", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Dormitory addDormitory(@RequestBody Dormitory data) {

        Dormitory dormitory = new Dormitory();

        dormitory.setState(data.getState());

        dormitory.setCreate_datetime(new Date().toString());
        dormitory.setDescription(data.getDescription());
        dormitory.setName(data.getName());
        dormitory.setDor(data.getDor());
        return dormitoryRepository.save(dormitory);
    }

    @RequestMapping(value = "/deleteDor/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteDor(@PathVariable Long id) {

        Dormitory dor = dormitoryRepository.findById(id);

        try
        {
            dormitoryRepository.delete(dor);
            return true;
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            return false;
        }
    }
}