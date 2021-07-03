package com.evotek.nagakawa_be.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private RoleName roleName;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Boolean status;

    public boolean isAdminRole() {
        return null != this && this.roleName.equals(RoleName.ROLE_ADMIN);
    }
}
