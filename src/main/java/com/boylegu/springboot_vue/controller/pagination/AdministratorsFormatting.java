package com.boylegu.springboot_vue.controller.pagination;

/**
 * Created by wang on 2017/12/8.
 */
import com.boylegu.springboot_vue.dao.AdminRepository;
import com.boylegu.springboot_vue.entities.Administrators;
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
class SpringUtilOfAdm implements ApplicationContextAware {

	private static ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

		if (SpringUtilOfAdm.applicationContext == null) {

			SpringUtilOfAdm.applicationContext = applicationContext;

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


interface TypesOfAdm {

	public Page<Administrators> query();

	public Integer getCount();

	public Integer getPageNumber();

	public Long getTotal();

	public Object getContent();
}

class BasePaginationInfoOfAdm {

	public Pageable pageable;

	public AdminRepository instance = SpringUtil.getBean(AdminRepository.class);

	public String number, username;

	public BasePaginationInfoOfAdm(String username, String number, Pageable pageable) {

		this.pageable = pageable;

		this.username = username;

		this.number = number;
	}
}

class AllTypeOfAdm extends BasePaginationInfoOfAdm implements TypesOfAdm {


	public AllTypeOfAdm(String username, String number, Pageable pageable) { //String stu_username, String stu_number,

		super(username, number, pageable);

	}

	public Page<Administrators> query() {

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

class admType extends BasePaginationInfoOfAdm implements TypesOfAdm {

	public admType(String username, String number, Pageable pageable) {

		super(username, number, pageable);

	}

	public Page<Administrators> query() {

		return this.instance.findByUsernameAndNumberContains(

						this.username,

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

class admTypePageable extends BasePaginationInfoOfAdm implements TypesOfAdm {

	public admTypePageable(String username, String number, Pageable pageable) { //String usernameusername, String number,

		super(username, number, pageable);
	}

	public Page<Administrators> query() {

		return this.instance.findByUsername(

						this.username,

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


public class AdministratorsFormatting {

	private AdminMultiTypeValuesHelper multiValue = new AdminMultiTypeValuesHelper();

	private Map<String, AdminMultiTypeValuesHelper> results = new HashMap<>();

	public Map<String, AdminMultiTypeValuesHelper> filterQuery(String username, String number, Pageable pageable) {

		TypesOfAdm typeInstance;

		if (username.length() == 0 && number.length() == 0) {

			typeInstance = new AllTypeOfAdm(username, number, pageable);

		} else if (username.length() > 0 && number.length() > 0) {

			typeInstance = new admType(username, number, pageable);

		} else {
			typeInstance = new admTypePageable(username, number, pageable);
		}

		this.multiValue.setCount(typeInstance.getCount());

		this.multiValue.setPage(typeInstance.getPageNumber() + 1);

		this.multiValue.setResults(typeInstance.getContent());

		this.multiValue.setTotal(typeInstance.getTotal());

		this.results.put("data", this.multiValue);

		return results;
	}

}