package com.asfinder.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "TOUSER")
@Getter @Setter
public class User implements Serializable {

    public User(){
    }

    public User (String firstName, String lastName, String country){
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUser;

    private String firstName;

    private String lastName;

    private String country;

    private Date creationDate;

    private String userName;

    private String password;

}
