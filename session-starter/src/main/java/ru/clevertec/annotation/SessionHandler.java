package ru.clevertec.annotation;

import ru.clevertec.property.BlackListProvider;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SessionHandler {
    Class<? extends BlackListProvider>[] blacklist() default {};
}
