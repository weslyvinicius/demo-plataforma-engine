package br.com.itau.co8.Config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "br.com.itau.co8.Feign")
public class FeignConfig {
}
