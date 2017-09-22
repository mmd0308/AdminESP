package com.cnpc.gateway;

import com.cnpc.gateway.utils.DBLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableFeignClients
public class GatewayApplication {

	public static void main(String[] args) {

		//开启一个线程持久化日志
		DBLog.getInstance().start();
		SpringApplication.run(GatewayApplication.class, args);
	}
}
