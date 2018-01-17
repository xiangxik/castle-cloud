package me.xiangxik.thymeleaf;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

@Component
public class ThymeleafVariableInitializer implements SmartInitializingSingleton {

	@Autowired
	private ThymeleafViewResolver thymeleafViewResolver;

	@Autowired(required = false)
	private ThymeleafViewVariable viewVariable;

	@Override
	public void afterSingletonsInstantiated() {
		if (viewVariable != null) {
			thymeleafViewResolver.addStaticVariable("vv", viewVariable);
		}
	}

}
