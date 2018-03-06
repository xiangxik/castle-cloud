package me.xiangxik.service.invoker;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "castle-service-provider", fallback = FooClientFallback.class)
public interface FooClient {

	@RequestMapping(value = "/call/{id}", method = RequestMethod.GET)
	public Foo call(@PathVariable("id") Integer id);
}
