package com.crossover.medijour.service.apigateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableZuulProxy
public class APIGatewayApplication  extends WebSecurityConfigurerAdapter {
	
	public static void main(String[] args) {
        SpringApplication.run(APIGatewayApplication.class, args);
    }
	  

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	      .antMatcher("/**")
	      .authorizeRequests()	      
	      .antMatchers("/", "/login**","/catalogue","/listing","/webjars/**","/assets/**")
	        .permitAll()
	      .anyRequest()
	        .authenticated();
	}	
}
