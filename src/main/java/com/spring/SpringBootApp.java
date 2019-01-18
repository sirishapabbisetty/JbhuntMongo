package com.spring;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2
@SpringBootApplication
@ComponentScan(basePackages = "com")
@PropertySource("classpath:application.yml")
@ImportResource("classpath:app-config.xml")

public class SpringBootApp extends SpringBootServletInitializer {
	//
//	static {
//		System.setProperty("spring.config.location","E:\\JbhuntMongo\\src\\main\\resources\\application.yml");
//	}
//	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SpringBootApp.class);
	//private static final

	protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder)
	{
		return applicationBuilder.sources(SpringBootApp.class);
	}
	public static void main(String[] args) {
//		ConfigurableApplicationContext applicationContext=new SpringApplicationBuilder(SpringBootApp.class).properties("spring.config.name=application","application").build().run(args);
//
//		ConfigurableEnvironment environment=applicationContext.getEnvironment();
//		System.out.println(environment.getProperty("server.port"));
//		SpringApplication.run("SpringBootApp.class", args);

		new SpringApplication(SpringBootApp.class).run(args);

	}

}
