package com.redhat.edwin.utility;

import com.redhat.edwin.annotation.Decrypt;
import com.redhat.edwin.annotation.Encrypt;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     com.redhat.edwin.utility.EncryptionAspect
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 04 Mei 2020 11:59
 */
@Aspect
@Component
public class EncryptionAspect {

    private PooledPBEStringEncryptor encryptor;

    public EncryptionAspect() {
        encryptor = new PooledPBEStringEncryptor();
        encryptor.setPoolSize(4);
        encryptor.setPassword("mypassword");
    }

    @Before("@annotation(encrypt)")
    public Object decrypt(JoinPoint pjp, Encrypt encrypt) throws Throwable {
        String values[] = encrypt.values();
        MethodSignature signature = (MethodSignature) pjp.getSignature();

        // encrypt first param only
        Class<?> clazz = signature.getParameterTypes()[0];
        Object param = pjp.getArgs()[0];

        for (String value : values) {
            Field field = clazz.getDeclaredField(value);
            field.setAccessible(true);
            Object fieldValue = field.get(param);
            if(fieldValue != null)
                field.set(param, encrypt(fieldValue.toString()));
        }

        return param;
    }

    @Around("@annotation(decrypt)")
    public Object decrypt(ProceedingJoinPoint pjp, Decrypt decrypt) throws Throwable {
        String values[] = decrypt.values();
        Object returnObject = pjp.proceed();

        // this return array response
        if(returnObject instanceof List || returnObject instanceof ArrayList) {
            List<Object> list = (List<Object>)pjp.proceed();
            for (Object o : list) {
                if(o != null)
                    decrypt(o, values);
            }
        } else {
            if(returnObject != null)
                decrypt(returnObject, values);
        }

        return returnObject;
    }

    private String encrypt(String plainText) throws Exception {
        return encryptor.encrypt(plainText);
    }

    private void decrypt(Object returnObject, String values[]) throws Exception {
        Class<?> clazz = returnObject.getClass();
        for (String value : values) {
            Field field = clazz.getDeclaredField(value);
            field.setAccessible(true);
            Object fieldValue = field.get(returnObject);
            if(fieldValue != null)
                field.set(returnObject, decrypt(fieldValue.toString()));
        }
    }

    private String decrypt(String encryptedString) {
        try {
            return encryptor.decrypt(encryptedString);
        } catch (Exception ex) {
            return "==FAILED DECRYPT==";
        }
    }
}
