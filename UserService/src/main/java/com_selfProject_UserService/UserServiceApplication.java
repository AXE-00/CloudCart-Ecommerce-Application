package com_selfProject_UserService;

import com_selfProject_UserService.filter.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<?> interUrl(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new AuthFilter());
		filterRegistrationBean.addUrlPatterns(
				"/api/v1/userService/update/user",
				"/api/v1/userService/addFav/Item",
				"/api/v1/userService/item/check",
				"/api/v1/userService/remove/Item",
				"/api/v1/userService/get/userName",
				"/api/v1/userService/get/userImage"
		);
		return filterRegistrationBean;
	}

}
