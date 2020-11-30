package com.example.trc.model.users;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Surkov Aleksey (stibium128@gmail.com)
 * @date 07.10.2020 13:24
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    /**
     * User id.
     */
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Full name user.
     */
    @NotNull
    @Column(name = "name")
    private String name;

    /**
     * Login user.
     */
    @NotNull
    @Column(name = "username")
    private String username;

    /**
     * Password user.
     */
    @NotNull
    @Column(name = "password")
    private String password;

    /**
     * Age user.
     */
    @NotNull
    @Column(name = "age")
    private int age;

    /**
     * Id of the group that the student belongs to.
     */
    @NotNull
    @Column(name = "group_id")
    private int groupId;

    /**
     * Student rating.
     */
    @NotNull
    @Column(name = "rating")
    private double rating;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public User(String name, String username, String password, int age, Role role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.age = age;
        this.groupId = 0;
        this.rating = 0;
        this.role = role;
        this.status = Status.ACTIVE;
    }
}
