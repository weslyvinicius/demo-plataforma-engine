package br.com.itau.co8.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.itau.co8.exception.FeignErrorDecoder;
import feign.Logger;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableFeignClients(basePackages = "br.com.itau.co8.feign")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FeignConfig {

    private final ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Encoder feignFormEncoder () {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }

    @Bean
    public FeignErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }

}
