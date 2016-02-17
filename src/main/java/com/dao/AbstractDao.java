package com.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class AbstractDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private void setJdbcTemplate(DataSource dataSource){
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	protected JdbcTemplate getJdbcTemplate(){
		return jdbcTemplate;
	}
}
