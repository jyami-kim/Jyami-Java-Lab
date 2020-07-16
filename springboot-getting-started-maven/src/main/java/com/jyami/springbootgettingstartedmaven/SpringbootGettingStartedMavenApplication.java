package com.jyami.springbootgettingstartedmaven;

import com.jyami.springbootgettingstartedmaven.runner.SampleListener;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootGettingStartedMavenApplication {

	@GetMapping("hello")
	public String hello(){
		return "hello Spring~";
	}

	@Bean
	public ServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		tomcat.addAdditionalTomcatConnectors(createStandardConnector());
		return tomcat;
	}

	private Connector createStandardConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setPort(8080);
		return connector;
	}

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(SpringbootGettingStartedMavenApplication.class);
//		springApplication.addListeners(new SampleListener());
//		springApplication.setWebApplicationType(WebApplicationType.NONE);
		springApplication.run(args);
	}

}
