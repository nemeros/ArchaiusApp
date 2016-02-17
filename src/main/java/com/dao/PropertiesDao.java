package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pojo.KeyValuePojo;

@Repository
public class PropertiesDao extends AbstractDao {

	/**
	 * Return all the T_PROP values from the DB
	 * @return
	 */
	public List<KeyValuePojo> getAllProperties(){
		
		StringBuilder query = new StringBuilder(100);
		
		query.append("SELECT prop_key, prop_value FROM T_PROP ");
		
		return this.getJdbcTemplate().query(
				query.toString(), 
				(rs, rowNum) -> {
					return new KeyValuePojo(rs.getString("prop_key"), rs.getString("prop_value"));
		});
	}
	
	/**
	 * return a single key/value
	 * @param key
	 * @return
	 */
	public KeyValuePojo getSpecificProperties(String key){
		StringBuilder query = new StringBuilder(100);
		
		query.append("SELECT prop_key, prop_value FROM T_PROP WHERE prop_key = ?");
		
		return this.getJdbcTemplate().queryForObject(
				query.toString(), 
				new Object[]{key}, 
				(rs, rowNum)-> {
					return new KeyValuePojo(rs.getString("prop_key"), rs.getString("prop_value"));
				});
	}
	
	/**
	 * update a value 
	 * @param key
	 * @param value
	 */
	public void updateKey(String key, String value){
		StringBuilder query = new StringBuilder(100);
		query.append("UPDATE T_PROP SET prop_value = ? WHERE prop_key = ? ");
		
		this.getJdbcTemplate().update(query.toString(), new Object[]{value, key});
	}
	
	/**
	 * 
	 * @param pojo
	 */
	public void insertKeyValue(KeyValuePojo pojo){
		StringBuilder query = new StringBuilder(100);
		query.append("INSERT INTO T_PROP (prop_key, prop_value) VALUES (?, ?)");
		
		this.getJdbcTemplate().update(query.toString(), new Object[]{pojo.getKey(), pojo.getValue()});
	}
}
