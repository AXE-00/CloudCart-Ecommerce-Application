package com.cart.Cart_Service;

import com.cart.Cart_Service.filter.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class CartServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartServiceApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<AuthFilter> interUrl(){
		FilterRegistrationBean<AuthFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new AuthFilter());
		filterRegistrationBean.addUrlPatterns(
				"/v1/api/cart/addProduct",
				"/v1/api/cart/getCart/details/*"
		);
		return filterRegistrationBean;
	}
}
