package com.cyb.xtqx.bean;

import java.util.Set;  

import javax.persistence.CascadeType;  
import javax.persistence.Column;  
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;  
import javax.persistence.Id;  
import javax.persistence.OneToMany;  
import javax.persistence.Table;  
  
import org.hibernate.annotations.GenericGenerator;  
  
  
/**  
 * 一对多  一端
 * @ClassName: Clazz  
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @author 
 * @date 
 *   
 */  
@Entity   
@Table(name="Z_CLASS")   
public class Clazz implements java.io.Serializable{  
    /** 
     *  
     */  
    private static final long serialVersionUID = -6224738252966513441L;  
    private String id;  
    private String name;  
    private Set<Student> students;  
      
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
      
    @Column(name = "NAME", length = 50)  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
      
    @OneToMany(cascade=CascadeType.ALL,mappedBy="clazz")   
    //@OneToMany(cascade=CascadeType.ALL,mappedBy="clazz",fetch=FetchType.EAGER) 关闭延迟加载。          
    public Set<Student> getStudents() {  
        return students;  
    }  
    public void setStudents(Set<Student> students) {  
        this.students = students;  
    }       
}  