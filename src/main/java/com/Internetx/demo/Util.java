package com.Internetx.demo;

import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Service
public class Util {

    public LinkedHashMap<String, Boolean> analyze(Object obj){
        LinkedHashMap<String, Boolean> result = new LinkedHashMap<>();
        ReflectionUtils.doWithFields(obj.getClass(), field -> {


            System.out.println("Field name: " + field.getName());
            field.setAccessible(true);
            System.out.println("Field value: "+ field.get(obj));
            result.put(field.getName(),(boolean) field.get(obj));

        });
        return result;
    }
    public LinkedHashMap<String, String> analyzeUserModel(Object obj){
        LinkedHashMap<String, String> result = new LinkedHashMap<>();
        ReflectionUtils.doWithFields(obj.getClass(), field -> {


            System.out.println("Field name: " + field.getName());
            field.setAccessible(true);
            System.out.println("Field value: "+ field.get(obj));
            if(field.get(obj) !=  null)   result.put(field.getName(),  field.get(obj).toString());

        });
        return result;
    }
}
