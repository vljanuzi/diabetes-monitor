package com.city.rest.diabetes.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

@javax.ws.rs.NameBinding
@Retention(RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface JWTAuthed {
}
