package me.xiangxik.service.invoker;

import org.springframework.stereotype.Component;

@Component
public class FooClientFallback implements FooClient {

	@Override
	public Foo call(Integer id) {
		Foo foo = new Foo();
		foo.setId(id);
		foo.setName("error2");
		return foo;
	}

}
