package com.example.ecommerce.dominio.repostory.stereotypes;


import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service

public @interface UseCase
{
    @AliasFor(annotation = Service.class) String value() default "";
}
