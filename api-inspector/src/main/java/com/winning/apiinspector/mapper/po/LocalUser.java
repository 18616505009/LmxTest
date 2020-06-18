package com.winning.apiinspector.mapper.po;

import lombok.Data;

import javax.persistence.*;

/**
 * @author lmx
 * @date 2020-06-15 10:47
 */
@Data
@Entity
@Table(name = "winning.local_user")
public class LocalUser {

    @Id
    @GeneratedValue
    int id;
    @Column(name = "username")
    String username;
    @Column(name = "password")
    String password;

}
