package com.cyb.xtqx.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 *  多对一（多端）
 * @author DHUser
 *
 */
@Entity   
@Table(name="Z_STUDENT")   
public class Student {  
    private String id;  
    private String name;  
    private Clazz clazz;  
      
    @Id  
    @GeneratedValue(generator="system-uuid")  
    @GenericGenerator(name="system-uuid", strategy = "uuid")  
    @Column(name = "ID")  
    public String getId() {  
        return id;  
    }  
    public void setId(String id) {  
        this.id = id;  
    }  
      
    @Column(name = "NAME", length = 500)  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
      
    @ManyToOne(cascade={CascadeType.ALL})           
    @JoinColumn(name="class_id")     //student类中对应外键的属性：classid   
    public Clazz getClazz() {  
        return clazz;  
    }  
    public void setClazz(Clazz clazz) {  
        this.clazz = clazz;  
    }  
}