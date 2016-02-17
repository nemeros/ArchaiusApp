package com;

import javax.sql.DataSource;

import org.apache.commons.configuration.AbstractConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.config.AbstractPollingScheduler;
import com.netflix.config.ConcurrentMapConfiguration;
import com.netflix.config.ConfigurationManager;
import com.netflix.config.FixedDelayPollingScheduler;
import com.netflix.config.PolledConfigurationSource;
import com.netflix.config.sources.JDBCConfigurationSource;

@Configuration
public class ArchaiusConf {

	@Autowired
	DataSource ds;
	
	@Bean
	public AbstractConfiguration initArchauis(){
		AbstractConfiguration myConfiguration = new ConcurrentMapConfiguration();
		
		AbstractPollingScheduler scheduler = new FixedDelayPollingScheduler(5000, 2000, false);
		PolledConfigurationSource polConf = new JDBCConfigurationSource(ds, 
				"SELECT prop_key, prop_value FROM T_PROP", "prop_key", "prop_value");
		
		scheduler.startPolling(polConf, myConfiguration);
		
		ConfigurationManager.install(myConfiguration);
		
		return myConfiguration;
	}
}
