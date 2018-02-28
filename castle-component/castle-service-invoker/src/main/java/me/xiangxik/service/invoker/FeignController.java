package me.xiangxik.service.invoker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
public class FeignController {

	@Autowired
	private FooClient fooClient;

	@RequestMapping("/feign")
	public Foo call() {
		return fooClient.call(5);
	}
}
