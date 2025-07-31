/*
 * Copyright 2015 Benedikt Ritter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.code.config;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.code.service.StorageService;
import com.code.model.StorageProperties;
/**
 * @author Adarsh Kumar
 * @author $LastChangedBy: Adarsh Kumar$
 * @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
 * @Espark @copyright all right reserve
 */

@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.code")
@EntityScan(basePackages = "com.code.model")
@EnableJpaRepositories(basePackages = "com.code.dao")
@PropertySource("classpath:application.properties")
@EnableConfigurationProperties(StorageProperties.class)
public class WebMvcJspApplication extends SpringBootServletInitializer {
	private static  Logger LOGGER =  LoggerFactory.getLogger(WebMvcJspApplication.class);
	private int maxUploadSizeInMb = 10 * 1024 * 1024; // 10 MB

	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WebMvcJspApplication.class);
	}
	public static void main(String[] args) throws Exception {
		LOGGER.info("Starting Main Application...");
		SpringApplication.run(WebMvcJspApplication.class, args);
		LOGGER.info("Access URLs: http://localhost:8080\n");
		WebMvcJspApplication obj = new WebMvcJspApplication();
		LOGGER.info("Access Datasourc"+ obj.dataSource().getDriverClassName());
		LOGGER.info("Access Datasourc"+ obj.dataSource().getConnectionProperties());
		LOGGER.info("Access Datasourc"+ obj.dataSource().getUsername());
	}
	@Bean
	CommandLineRunner init(StorageService storageService) {
			return (args) -> {
	           // storageService.deleteAll();
	           //storageService.init();
			};
	 }
	 @Bean
	 @ConfigurationProperties("spring.datasource")
	 public DataSource dataSource() {
	     return (DataSource) DataSourceBuilder.create().build();
	 }
	 
	 
	 //Tomcat large file upload connection reset
	    //http://www.mkyong.com/spring/spring-file-upload-and-connection-reset-issue/
	    @Bean
	    public TomcatServletWebServerFactory tomcatEmbedded() {

	        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();

	        tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
	            if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
	                //-1 means unlimited
	                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(maxUploadSizeInMb);
	            }
	        });

	        return tomcat;

	    }
}
