package com.maksatabrayev.bilkeatslocal.signup_login;

import java.io.Serializable;

public class Meal implements Serializable {
    String name;
    String imageUrl;
    // int carbohydrate;
    // int protein;
    // int fat;

    public Meal(String name){
      this.name = name;
    }

    public  Meal(String name, String imageUrl){
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
