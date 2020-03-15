package com.rvb.springwithapectlog.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "CUSTOMER")
public class Customer extends BaseModel implements Serializable {



    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="CUSTOMER_ID")
    private Long id;

    @Column (name = "CUSTOMER_FNAME")
    @NotNull
    private String fName;

    @Column (name = "CUSTOMER_LNAME")
    private String Lname;

    @Column (name = "CUSTOMER_AGE")
    private String age;

    @Column (name = "CUSTOMER_GENDER")
    private String gender;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



}
