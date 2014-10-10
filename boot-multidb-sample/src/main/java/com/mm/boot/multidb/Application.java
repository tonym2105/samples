package com.mm.boot.multidb;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application extends SpringApplication{

	   public static void main(String[] args) {
	        SpringApplication.run(Application.class, args);
	    }
	    
	    @Bean
	    public ServletRegistrationBean h2Console() {
	        ServletRegistrationBean reg = new ServletRegistrationBean(new WebServlet(), "/console/*");
	        reg.setLoadOnStartup(1);
	        return reg;
	    }
}
