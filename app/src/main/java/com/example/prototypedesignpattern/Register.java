package com.example.prototypedesignpattern;

import java.util.ArrayList;
import java.util.List;

public class Register implements Cloneable {
    public List<RegisterModel> rgisterList;

    public Register(){
        rgisterList = new ArrayList<>();
    }

    public Register(List<RegisterModel> list){
        this.rgisterList=list;
    }

    public void addMember(int id,String name,String family,String phone,String address,String age,String weight,String height){
        //read all employees from database and put into the list
        rgisterList.add(new RegisterModel(id,name,family,phone,address,age,weight,height));
    }

    public void removeMember(int id){
        rgisterList.remove(id);
    }

    public List<RegisterModel> getRegisterList(){
        return this.rgisterList;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        List<RegisterModel> temp = new ArrayList<>();
        for (RegisterModel list : this.getRegisterList()){
            temp.add(list);
        }
        return new Register(temp);
    }

}
