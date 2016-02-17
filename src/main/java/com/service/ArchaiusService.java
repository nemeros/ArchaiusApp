package com.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.config.DynamicPropertyFactory;
import com.pojo.KeyValuePojo;

@RestController
@RequestMapping("/archaius")
public class ArchaiusService {

	
	@RequestMapping(value="/{key}", method=RequestMethod.GET, produces="application/json")
	public KeyValuePojo getSpecificProp(@PathVariable("key") String key){
		return new KeyValuePojo(key, DynamicPropertyFactory.getInstance().getStringProperty(key, "missing").getValue());
	}
}
