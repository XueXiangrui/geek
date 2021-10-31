package com.geek.week05.mustDo08;

import lombok.Data;

/**
 * Created by xiangrui.xue on 2021/10/28.
 */
@Data
public class Student {
    private int id;
    private String name;


    public void init(){
        System.out.println("hello...........");
    }

}
