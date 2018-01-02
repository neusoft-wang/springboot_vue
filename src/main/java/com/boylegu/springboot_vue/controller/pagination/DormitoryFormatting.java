package com.boylegu.springboot_vue.controller.pagination;

import com.boylegu.springboot_vue.dao.DormitoryRepository;
import com.boylegu.springboot_vue.entities.Dormitory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wang on 2017/12/13.
 */
@Component
class SpringUtilOfDor implements ApplicationContextAware {

	private static ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

		if (SpringUtilOfDor.applicationContext == null) {

			SpringUtilOfDor.applicationContext = applicationContext;

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


interface TypesOfDor {

	public Page<Dormitory> query();

	public Integer getCount();

	public Integer getPageNumber();

	public Long getTotal();

	public Object getContent();
}

class BasePaginationInfoOfDor {

	public Pageable pageable;

	public DormitoryRepository instance = SpringUtil.getBean(DormitoryRepository.class);

	public String name, state;

	public Long id;

	public BasePaginationInfoOfDor(String state, String name, Pageable pageable) {

		this.pageable = pageable;

		this.state = state;

		this.name = name;
	}

	public BasePaginationInfoOfDor(Long id, Pageable pageable) {

		this.id = id;
		this.pageable = pageable;
	}
}

class AllTypeOfDor extends BasePaginationInfoOfDor implements TypesOfDor {


	public AllTypeOfDor(String state, String name, Pageable pageable) { //String stu_username, String stu_number,

		super(state, name, pageable);

	}

	public Page<Dormitory> query() {

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

class FindByID extends BasePaginationInfoOfDor implements TypesOfDor {

	public FindByID(Long id, Pageable pageable) { //String stu_username, String stu_number,

		super(id, pageable);

	}

	public Page<Dormitory> query() {

		return this.instance.findById(

						this.id,
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

class IsUseNameTypeDor extends BasePaginationInfoOfDor implements TypesOfDor {

	public IsUseNameTypeDor(String state, String name, Pageable pageable) {

		super(state, name, pageable);

	}

	public Page<Dormitory> query() {

		return this.instance.findByStateAndName(

						this.state,

						this.name,

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

class IsUseTypeDor extends BasePaginationInfoOfDor implements TypesOfDor {

	public IsUseTypeDor(String state, String name, Pageable pageable) { //String usernameusername, String number,

		super(state, name, pageable);
	}

	public Page<Dormitory> query() {

		return this.instance.findByState(

						this.state,

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


public class DormitoryFormatting {

	private DormitoryMultiTypeValuesHelper multiValue = new DormitoryMultiTypeValuesHelper();

	private Map<String, DormitoryMultiTypeValuesHelper> results = new HashMap<>();

	public Map<String, DormitoryMultiTypeValuesHelper> filterQuery(String state, String name, Pageable pageable) {

		TypesOfDor typeInstance = null;

		if (state.length() == 0 && name.length() == 0) {

			typeInstance = new AllTypeOfDor(state, name, pageable);

		} else if (state.length() > 0 && name.length() > 0) {

			typeInstance = new IsUseNameTypeDor(state, name, pageable);

		} else {
			typeInstance = new IsUseTypeDor(state, name, pageable);
		}

		this.multiValue.setCount(typeInstance.getCount());

		this.multiValue.setPage(typeInstance.getPageNumber() + 1);

		this.multiValue.setResults(typeInstance.getContent());

		this.multiValue.setTotal(typeInstance.getTotal());

		this.results.put("data", this.multiValue);

		return results;
	}

	public Map<String, DormitoryMultiTypeValuesHelper> findByID(Long id, Pageable pageable) {

		TypesOfDor typeInstance;

		typeInstance = new FindByID(id, pageable);

		this.multiValue.setCount(typeInstance.getCount());

		this.multiValue.setPage(typeInstance.getPageNumber() + 1);

		this.multiValue.setResults(typeInstance.getContent());

		this.multiValue.setTotal(typeInstance.getTotal());

		this.results.put("data", this.multiValue);

		return results;
	}
}