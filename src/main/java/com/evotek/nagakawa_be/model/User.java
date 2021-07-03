package com.evotek.nagakawa_be.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "address")
    private String address;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "create_date")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(name = "user_create_id")
    private Long userCreateId;


    @Column(name = "user_create_name")
    private String userCreateName;

    @Column(name = "user_edit_id")
    private Long userEditId;

    @Column(name = "user_edit_name")
    private String userEditName;

    @Column(name = "modified_date")
    @Temporal(TemporalType.DATE)
    private Date modifiedDate;

    private String fullname;

    @Transient
    public String getFullname() {
        if (this.firstname != null) {
            this.fullname = this.firstname + " ";
        }
        if (this.lastname != null) {
            this.fullname = this.fullname + this.lastname;
        }

        if (this.fullname != null) {
            this.fullname = this.fullname.trim();
        }

        return this.fullname;
    }


    public User(String email, String password, String firstname, String lastname, Date birthday,
                String address, String description, Boolean gender) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.address = address;
        this.roles = roles;
        this.description = description;
        this.gender = gender;
    }

    public User(String email, String password, String firstname, String lastname) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(this.email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", gender=" + gender +
                ", status=" + status +
                ", createDate=" + createDate +
                ", userCreateId=" + userCreateId +
                ", userCreateName='" + userCreateName + '\'' +
                ", userEditId=" + userEditId +
                ", userEditName='" + userEditName + '\'' +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
