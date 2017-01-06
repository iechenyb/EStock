package com.cyb.reflect;

public class UserBean extends Reflector {
    private Integer id;
    private int age;
    private String name;
    private String address;
   
    public UserBean(){
    }
   
    public Integer getId() {
       return id;
    }
    public void setId(Integer id) {
       this.id = id;
    }
    public int getAge() {
       return age;
    }
    public void setAge(int age) {
       this.age = age;
    }
    public String getName() {
       return name;
    }
    public void setName(String name) {
       this.name = name;
    }
    public String getAddress() {
       return address;
    }
    public void setAddress(String address) {
       this.address = address;
    } 
}
