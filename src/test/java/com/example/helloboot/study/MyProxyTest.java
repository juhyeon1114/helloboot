package com.example.helloboot.study;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class MyProxyTest {

    @Test
    @DisplayName("싱글톤 검증")
    public void proxyCommonMethod() throws Exception {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(MyConfig.class);
        ac.refresh();
        Bean1 bean1 = ac.getBean(Bean1.class);
        Bean2 bean2 = ac.getBean(Bean2.class);
        Assertions.assertThat(bean1.commonBean).isSameAs(bean2.commonBean);
    }

    // 설정 Component
    @Configuration
//    @Configuration(proxyBeanMethods = false)
    static class MyConfig {
        @Bean
        CommonBean commonBean() {
            return new CommonBean();
        }

        @Bean
        Bean1 bean1() {
            return new Bean1(commonBean());
        }

        @Bean
        Bean2 bean2() {
            return new Bean2(commonBean());
        }
    }

    // Bean으로 만들어줄 클래스 #1
    @RequiredArgsConstructor
    static class Bean1 {
        private final CommonBean commonBean;
    }
    // Bean으로 만들어줄 클래스 #2
    @RequiredArgsConstructor
    static class Bean2 {
        private final CommonBean commonBean;
    }
    // Bean으로 만들어줄 클래스 #3
    static class CommonBean {}

}
