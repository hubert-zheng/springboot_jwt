package com.hubert.springboot_jwt;

import com.hubert.springboot_jwt.auth.HTTPBearerAuthorizeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringbootJwtApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJwtApplication.class, args);
	}

	@Autowired
	private HTTPBearerAuthorizeInterceptor httpBearerAuthorizeInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(httpBearerAuthorizeInterceptor);
	}


}
