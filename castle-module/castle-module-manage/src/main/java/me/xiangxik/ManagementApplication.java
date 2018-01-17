package me.xiangxik;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ManagementApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ManagementApplication.class).web(true).run(args);
	}

}
