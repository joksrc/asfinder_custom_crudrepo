package com.asfinder.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TOUSER")
@Getter @Setter @NoArgsConstructor
public class User {

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
