package com.example.prototypedesignpattern;

import android.arch.persistence.room.Room;
import com.example.prototypedesignpattern.db.AppDatabase;
import com.example.prototypedesignpattern.db.Member;
import com.example.prototypedesignpattern.db.MemberDao;

import java.util.ArrayList;
import java.util.List;

public class Register implements Cloneable {
    public List<Member> rgisterList;
    private String id;
    private String name;
    private String family;
    private String phone;
    private String address;
    private String sex;
    private String age;
    private String weight;
    private String height;
    MemberDao memberDAO;
    Member member;

    public Register() {
    }

    public Register(Member member){
        rgisterList = new ArrayList<>();
        AppDatabase database = Room.databaseBuilder(App.context, AppDatabase.class, "mydb")
                .allowMainThreadQueries()
                .build();
        memberDAO = database.getMemberDAO();
        this.member=member;
    }

    public Register(List<Member> list){
        this.rgisterList=list;
    }

    public void addMember(String id,String name,String family,String phone,String address,String age,String weight,String height){
        //read all employees from database and put into the list
        rgisterList.add(new Member(id,name,family,phone,address,age,weight,height));
    }

    public void addMember(){
        memberDAO.insert(member);
    }


    public List<Member> getAllMembers(){
        return memberDAO.getAllMembers();
    }


    public void removeMember(int id){
        rgisterList.remove(id);
    }

    public List<Member> getRegisterList(){
        return this.rgisterList;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        List<Member> temp = new ArrayList<>();
        for (Member list : this.getRegisterList()){
            temp.add(list);
        }
        return new Register(temp);
    }

    public void setId(String id) {
        member.setId(id);
    }
    public void setName(String name) {
        member.setName(name);
    }

    public void setFamily(String family) {
        member.setFamily(family);
    }

    public void setPhone(String phone) {
        member.setPhone(phone);
    }

    public void setAddress(String address) {
        member.setAddress(address);
    }

    public void setAge(String age) {
        member.setAge(age);
    }

    public void setWeight(String weight) {
        member.setWeight(weight);
    }

    public void setHeight(String height) {
        member.setHeight(height);
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
