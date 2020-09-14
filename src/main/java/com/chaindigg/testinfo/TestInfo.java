package com.chaindigg.testinfo;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
@MapperScan("com.chaindigg.testinfo.dao")
public class TestInfo {

	public static void main(String[] args) throws UnirestException {
		SpringApplication.run(TestInfo.class, args);
	}

}
