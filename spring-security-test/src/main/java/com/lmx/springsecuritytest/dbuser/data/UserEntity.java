package com.lmx.springsecuritytest.dbuser.data;

import lombok.Data;

import javax.persistence.*;

/**
 * @author lmx
 * @date 2020/9/16 3:40 下午
 */
@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String username;
    String password;
    int enabled;
    String authority;

}
