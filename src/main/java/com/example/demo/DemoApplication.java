package com.example.demo;

import com.example.demo.filter.Encoding;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

@SpringBootApplication
@ServletComponentScan
public class DemoApplication {
	@Bean
	public FilterRegistrationBean buildFilterObject(){
		FilterRegistrationBean filter=new FilterRegistrationBean();
		filter.setFilter(new Encoding());
		filter.setOrder(1);
		filter.setName("Encoder");
		filter.addInitParameter("encoding","utf-8");
		filter.addUrlPatterns("/*");
		return filter;
	}
	@Bean(value = "Encoder")
	public Filter encodingFilter(){
		return new Encoding();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
