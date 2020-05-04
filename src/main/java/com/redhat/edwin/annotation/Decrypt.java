package com.redhat.edwin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 *     com.redhat.edwin.annotation.Decrypt
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 04 Mei 2020 11:50
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Decrypt {
    String[] values();
}
