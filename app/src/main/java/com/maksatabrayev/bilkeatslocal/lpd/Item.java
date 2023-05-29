package com.maksatabrayev.bilkeatslocal.lpd;

import java.io.Serializable;

public class Item implements Serializable {
    String description;
    String imageUrl;
    String date;

    public Item(String description, String date, String imageUrl) {
        this.description = description;
        this.date = date;
        this.imageUrl = imageUrl;
    }
}
