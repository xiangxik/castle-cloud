package me.xiangxik.service.invoker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.HystrixCircuitBreaker;
import com.netflix.hystrix.HystrixCommandKey;

@RestController
@RequestMapping("/feign")
public class FeignController {

	@Autowired
	private FooClient fooClient;

	@RequestMapping("/call")
	public Foo call() {
		Foo f = fooClient.call(5);
		HystrixCircuitBreaker breaker = HystrixCircuitBreaker.Factory
				.getInstance(HystrixCommandKey.Factory.asKey("FooClient#call(Integer)"));
		System.out.println("断路器：" + breaker.isOpen());
		return f;
	}

}
