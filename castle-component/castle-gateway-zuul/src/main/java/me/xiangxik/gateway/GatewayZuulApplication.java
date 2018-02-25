package me.xiangxik.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableZuulProxy
@RestController
public class GatewayZuulApplication {

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		new SpringApplicationBuilder(GatewayZuulApplication.class).run(args);
	}

	@RequestMapping(value = "/gp", method = RequestMethod.GET)
	public String getProp() {
		return env.getProperty("test.user.name");
	}

}
