package com.example.helloboot.test;

import com.example.config.HelloWorldAnnotation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;

public class HelloWorldAnnotationTest {

    @HelloWorldAnnotation(name = "이순신")
    class HelloWorld {}

    @Test
    @DisplayName("HelloWorld테스트")
    public void HelloWorldTest() throws Exception {
        Annotation[] annotations = HelloWorld.class.getAnnotations();
        for (Annotation annotation : annotations) {
            HelloWorldAnnotation helloWorldAnnotation = (HelloWorldAnnotation) annotation;
            System.out.println("helloWorldAnnotation = " + helloWorldAnnotation.name());
        }
    }

}
