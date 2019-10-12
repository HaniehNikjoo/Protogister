package com.example.prototypedesignpattern.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "member")
public class Member {
    @PrimaryKey
    @NonNull public String id;
    public String name;
    public String family;
    public String phone;
    public String address;
    public String age;
    public String weight;
    public String height;

    public Member(){

    }
    public Member(String id, String name, String family, String phone, String address, String age, String weight, String height) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.phone = phone;
        this.address = address;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }


    public void setId(@NonNull String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setHeight(String height) {
        this.height = height;
    }

}
