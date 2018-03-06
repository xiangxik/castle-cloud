package me.xiangxik.service.invoker;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class RestTemplateController {

	@Autowired
	private RestService restService;

	@RequestMapping("/call")
	public Foo call() {
		return restService.call(10);
	}

	@RequestMapping("/cache")
	public Foo cache() {
		restService.cache(6);
		restService.cache(6);
		restService.cache(7);
		restService.cache(6);
		restService.cache(7);
		restService.cache(8);
		restService.cache(8);
		restService.removeCache(7);
		restService.removeCache(6);
		restService.cache(6);
		restService.cache(6);
		restService.cache(6);
		return restService.cache(7);
	}

	@RequestMapping("/collapse")
	public String collapse() throws InterruptedException, ExecutionException {
		Future<Foo> f1 = restService.getFoo(1);
		Future<Foo> f2 = restService.getFoo(2);
		Future<Foo> f4 = restService.getFoo(4);

		Foo p1 = f1.get();
		Foo p2 = f2.get();
		Foo p4 = f4.get();

		System.out.println("out");
		System.out.println(p1.getName());
		System.out.println(p2.getName());
		System.out.println(p4.getName());
		return "ss";
	}
}
