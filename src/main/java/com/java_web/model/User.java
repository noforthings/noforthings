package com.java_web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tbluser")
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    @Column(name = "user_name")
    private String username;
    @Column(name = "user_pass")
    private String password;
    @Column(name = "user_fullname")
    private String fullName;
    @Column(name = "user_birthday")
    private String birthday;
    @Column(name = "user_gender")
    private String gender;
    @Column(name = "user_mobilephone")
    private String mobilePhone;
    @Column(name = "user_homephone")
    private String homePhone;
    @Column(name = "user_officephone")
    private String officePhone;
    @Column(name = "user_email")
    private String email;
    @Column(name = "user_address")
    private String address;
    @Column(name = "user_jobarea")
    private String jobarea;
    @Column(name = "user_job")
    private String job;
    @Column(name = "user_position")
    private String position;
    @Column(name = "user_applyyear")
    private String applyYear;
    @Column(name = "user_permission")
    private Short permission;
    @Column(name = "user_notes")
    private String notes;
    @Column(name = "user_roles")
    private String roles;
    @Column(name = "user_logined")
    private Short logined;
    @Column(name = "user_created_time")
    private String createdTime;
    @Column(name = "user_modified_time")
    private String modifiedTime;
    @Column(name = "user_last_logined")
    private String lastLogined;
    @Column(name = "user_parent_id")
    private Integer parentId;
    @Column(name = "user_actions")
    private Byte actions;
    @Column(name = "user_deleted")
    private Byte deleted;

    @OneToOne(mappedBy = "user")
    private StudyCredit studyCredit;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TodoList> todoLists = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Score> scores = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
