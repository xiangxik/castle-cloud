package me.xiangxik.service.invoker;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Configuration
public class RestTemplateController {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@RequestMapping("/resttemplate")
	public String call() {
		RestTemplate tpl = restTemplate();
		return tpl.getForObject("http://castle-service-provider/call/4", String.class);
	}
}
