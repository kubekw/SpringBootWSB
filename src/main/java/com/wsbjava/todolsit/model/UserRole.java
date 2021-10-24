package com.wsbjava.todolsit.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private UserRoleValue roleValue;
    @ManyToOne
    private User user;


    public UserRole(UserRoleValue roleValue, User user) {
        this.roleValue = roleValue;
        this.user = user;
    }
}


