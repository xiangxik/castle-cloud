package me.xiangxik.service.invoker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;

@Configuration
@Service
// @DefaultProperties
public class RestService {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public Foo call(Integer id) {
		RestTemplate tpl = restTemplate();
		return tpl.getForObject("http://castle-service-provider/call/{id}", Foo.class, id);
	}

	@HystrixCommand(fallbackMethod = "getFallback", groupKey = "fooGroup", commandKey = "fooCommand", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000") }, threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "2") })
	public Foo get(Integer id) {
		return call(id);
	}

	@CacheResult
	@HystrixCommand(commandKey = "fooCache")
	public Foo cache(Integer id) {
		System.out.println("ca");
		return call(id);
	}

	@CacheRemove(commandKey = "fooCache")
	@HystrixCommand
	public void removeCache(Integer id) {
		System.out.println("remove");
	}

	@HystrixCollapser(batchMethod = "getFoos", collapserProperties = {
			@HystrixProperty(name = "timerDelayInMilliseconds", value = "1000") })
	public Future<Foo> getFoo(Integer id) {
		System.out.println("single");
		return null;
	}

	@HystrixCommand
	public List<Foo> getFoos(List<Integer> ids) {
		List<Foo> list = new ArrayList<>();
		System.out.println("cc");
		for (Integer id : ids) {
			Foo f = new Foo();
			f.setId(id);
			f.setName("dd" + id);
			list.add(f);
		}

		return list;
	}

	public Foo getFallback(Integer id) {
		Foo foo = new Foo();
		foo.setId(id);
		foo.setName("error");
		return foo;
	}

}
