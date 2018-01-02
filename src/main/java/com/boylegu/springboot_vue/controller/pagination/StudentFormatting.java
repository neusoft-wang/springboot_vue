package com.boylegu.springboot_vue.controller.pagination;

/**
 * Created by wang on 2017/12/8.
 */

import com.boylegu.springboot_vue.dao.StudentRepository;
import com.boylegu.springboot_vue.entities.Student;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/*
    Resolve due to @Autowired lead to NullPointerException problem

    Description：
    1. It's limited to general class to invoke spring bean Object.
    2. And This makes the sub package easy to scan by spring boot.

                                                      ———— @Boyle.gu
*/
@Component
class SpringUtilOfStu implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        if (SpringUtilOfStu.applicationContext == null) {

            SpringUtilOfStu.applicationContext = applicationContext;

        }
    }

    public static ApplicationContext getApplicationContext() {

        return applicationContext;

    }

    public static Object getBean(String name) {

        return getApplicationContext().getBean(name);

    }

    public static <T> T getBean(Class<T> clazz) {

        return getApplicationContext().getBean(clazz);

    }

    public static <T> T getBean(String name, Class<T> clazz) {

        return getApplicationContext().getBean(name, clazz);

    }
}


interface TypesOfStu {

    public Page<Student> query();

    public Integer getCount();

    public Integer getPageNumber();

    public Long getTotal();

    public Object getContent();
}

class BasePaginationInfoOfStu {

    public Pageable pageable;

    public StudentRepository instance = SpringUtil.getBean(StudentRepository.class);

    public String number, classInfo;

    public Long status;

    public BasePaginationInfoOfStu(String classInfo, String number, Pageable pageable) {

        this.pageable = pageable;

        this.classInfo = classInfo;

        this.number = number;
    }

    public BasePaginationInfoOfStu(String classInfo, Long status, Pageable pageable) {
        this.classInfo = classInfo;
        this.status = status;
        this.pageable = pageable;
    }
}

class AllTypeOfStu extends BasePaginationInfoOfStu implements TypesOfStu {


    public AllTypeOfStu(String name, String number, Pageable pageable) { //String stu_name, String stu_number,

        super(name, number, pageable);

    }

    public Page<Student> query() {

        return this.instance.findAll(

                this.pageable

        );
    }

    public Integer getCount() {
        return this.query().getSize();
    }

    public Integer getPageNumber() {

        return this.query().getNumber();

    }

    public Long getTotal() {
        return this.query().getTotalElements();
    }

    public Object getContent() {
        return this.query().getContent();
    }
}

class ClassInfoNumber extends BasePaginationInfoOfStu implements TypesOfStu {

    public ClassInfoNumber(String classInfo, String number, Pageable pageable) {

        super(classInfo, number, pageable);

    }

    public Page<Student> query() {

        return this.instance.findByClassInfoAndNumberContains(

                this.classInfo,

                this.number,

                this.pageable
        );
    }

    public Integer getCount() {
        return this.query().getSize();
    }

    public Integer getPageNumber() {

        return this.query().getNumber();

    }

    public Long getTotal() {
        return this.query().getTotalElements();
    }

    public Object getContent() {
        return this.query().getContent();
    }


}

class ClassType extends BasePaginationInfoOfStu implements TypesOfStu {

    public ClassType(String name, String number, Pageable pageable) { //String nameName, String number,

        super(name, number, pageable);
    }

    public Page<Student> query() {

        return this.instance.findByClassInfoAndStatus(

                this.classInfo,
                this.pageable
        );
    }

    public Integer getCount() {
        return this.query().getSize();
    }

    public Integer getPageNumber() {

        return this.query().getNumber();

    }

    public Long getTotal() {
        return this.query().getTotalElements();
    }

    public Object getContent() {
        return this.query().getContent();
    }
}

class StudentOfTeacher extends BasePaginationInfoOfStu implements TypesOfStu {

    public StudentOfTeacher(String classInfo, Long status, Pageable pageable) {

        super(classInfo, status, pageable);
    }

    public Page<Student> query() {

        return this.instance.findByClassInfoAndStatus(

                this.classInfo,
                this.status,
                this.pageable
        );
    }

    public Integer getCount() {
        return this.query().getSize();
    }

    public Integer getPageNumber() {

        return this.query().getNumber();

    }

    public Long getTotal() {
        return this.query().getTotalElements();
    }

    public Object getContent() {
        return this.query().getContent();
    }
}

public class StudentFormatting {

    private StudentMultiTypeValuesHelper multiValue = new StudentMultiTypeValuesHelper();

    private Map<String, StudentMultiTypeValuesHelper> results = new HashMap<>();

    public Map<String, StudentMultiTypeValuesHelper> filterQuery(String classInfo, String number, Pageable pageable) {

        TypesOfStu typeInstance;

        if (classInfo.length() == 0 && number.length() == 0) {

            typeInstance = new AllTypeOfStu(classInfo, number, pageable);

        } else if (classInfo.length() > 0 && number.length() > 0) {

            typeInstance = new ClassInfoNumber(classInfo, number, pageable);

        } else {
            typeInstance = new ClassType(classInfo, number, pageable);
        }

        this.multiValue.setCount(typeInstance.getCount());

        this.multiValue.setPage(typeInstance.getPageNumber() + 1);

        this.multiValue.setResults(typeInstance.getContent());

        this.multiValue.setTotal(typeInstance.getTotal());

        this.results.put("data", this.multiValue);

        return results;
    }

    public Map<String, StudentMultiTypeValuesHelper> findStudentofTeacher(String classInfo, long status, Pageable pageable) {

        TypesOfStu typeInstance;

        typeInstance = new StudentOfTeacher(classInfo, status, pageable);

        this.multiValue.setCount(typeInstance.getCount());

        this.multiValue.setPage(typeInstance.getPageNumber() + 1);

        this.multiValue.setResults(typeInstance.getContent());

        this.multiValue.setTotal(typeInstance.getTotal());

        this.results.put("data", this.multiValue);

        return results;
    }
}