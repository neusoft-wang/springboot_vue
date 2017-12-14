package com.boylegu.springboot_vue.controller.pagination;

/**
 * Created by wang on 2017/12/8.
 */

import com.boylegu.springboot_vue.dao.TeacherRepository;
import com.boylegu.springboot_vue.entities.Teacher;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wang on 2017/12/8.
 */

@Component
class SpringUtilOfTea implements ApplicationContextAware {

	private static ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

		if (SpringUtilOfTea.applicationContext == null) {

			SpringUtilOfTea.applicationContext = applicationContext;

		}
	}

	public static ApplicationContext getApplicationContext() {

		return applicationContext;

	}

	public static Object getBean(String username) {

		return getApplicationContext().getBean(username);

	}

	public static <T> T getBean(Class<T> clazz) {

		return getApplicationContext().getBean(clazz);

	}

	public static <T> T getBean(String username, Class<T> clazz) {

		return getApplicationContext().getBean(username, clazz);

	}
}


interface TypesOfTea {

	public Page<Teacher> query();

	public Integer getCount();

	public Integer getPageNumber();

	public Long getTotal();

	public Object getContent();
}

class BasePaginationInfoOfTea {

	public Pageable pageable;

	public TeacherRepository instance = SpringUtil.getBean(TeacherRepository.class);

	public String subject, number;

	public BasePaginationInfoOfTea(String subject, String number, Pageable pageable) {

		this.pageable = pageable;

		this.subject = subject;

		this.number = number;
	}
}

class AllTypeOfTea extends BasePaginationInfoOfTea implements TypesOfTea {


	public AllTypeOfTea(String subject, String number, Pageable pageable) { //String stu_username, String stu_number,

		super(subject, number, pageable);

	}

	public Page<Teacher> query() {

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

class SubjectNumberTypeTea extends BasePaginationInfoOfTea implements TypesOfTea {

	public SubjectNumberTypeTea(String subject, String number, Pageable pageable) {

		super(subject, number, pageable);

	}

	public Page<Teacher> query() {

		return this.instance.findBySubjectAndNumberContains(

						this.subject,

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

class SubjectTypeTea extends BasePaginationInfoOfTea implements TypesOfTea {

	public SubjectTypeTea(String subject, String number, Pageable pageable) { //String usernameusername, String number,

		super(subject, number, pageable);
	}

	public Page<Teacher> query() {

		return this.instance.findBySubject(

						this.subject,

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


public class TeacherFormatting {

	private TeacherMultiTypeValuesHelper multiValue = new TeacherMultiTypeValuesHelper();

	private Map<String, TeacherMultiTypeValuesHelper> results = new HashMap<>();

	public Map<String, TeacherMultiTypeValuesHelper> filterQuery(String subject, String number, Pageable pageable) {

		TypesOfTea typeInstance;

		if (subject.length() == 0 && number.length() == 0) {

			typeInstance = new AllTypeOfTea(subject, number, pageable);

		} else if (subject.length() > 0 && number.length() > 0) {

			typeInstance = new SubjectNumberTypeTea(subject, number, pageable);

		} else {
			typeInstance = new SubjectTypeTea(subject, number, pageable);
		}

		this.multiValue.setCount(typeInstance.getCount());

		this.multiValue.setPage(typeInstance.getPageNumber() + 1);

		this.multiValue.setResults(typeInstance.getContent());

		this.multiValue.setTotal(typeInstance.getTotal());

		this.results.put("data", this.multiValue);

		return results;
	}

}