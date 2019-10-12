package com.example.prototypedesignpattern;

public class RegisterModel {
    private String id;
    private String name;
    private String family;
    private String phone;
    private String address;
    private String age;
    private String weight;
    private String height;

    public RegisterModel(String id, String name, String family, String phone, String address, String age, String weight, String height) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.phone = phone;
        this.address = address;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }
}
