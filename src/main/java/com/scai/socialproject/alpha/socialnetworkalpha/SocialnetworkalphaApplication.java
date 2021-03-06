package com.scai.socialproject.alpha.socialnetworkalpha;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SocialnetworkalphaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialnetworkalphaApplication.class, args);
	}
	
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				//.allowedOrigins("http://localhost:4200")
//				.allowedOrigins("https://60eef23bed63150007123bdf--silly-bose-c6201f.netlify.app/")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.exposedHeaders("Authentication");
			}
		};
	}

}
