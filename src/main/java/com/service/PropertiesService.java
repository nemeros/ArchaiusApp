package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dao.PropertiesDao;
import com.pojo.KeyValuePojo;

@RestController
@RequestMapping("/propdb")
public class PropertiesService {

	@Autowired
	private PropertiesDao propDao;
	
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public List<KeyValuePojo> getAllPropertiesFromDb(){
		return propDao.getAllProperties();
	}
	
	
	@RequestMapping(value="/{key}", method=RequestMethod.GET, produces="application/json")
	public KeyValuePojo getspecificPropertiesFromDb(@PathVariable(value="key") String key){
		return propDao.getSpecificProperties(key);
	}
	
	
	@RequestMapping(value="/{key}",method=RequestMethod.POST, consumes="application/json", produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public KeyValuePojo updateKey(@PathVariable(value="key") String key, 
			@RequestBody(required=true) String value){
		propDao.updateKey(key, value);
		
		return propDao.getSpecificProperties(key);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void putNewKeyvalue(@RequestBody(required=true) KeyValuePojo pojo){
		propDao.insertKeyValue(pojo);
	}
}
