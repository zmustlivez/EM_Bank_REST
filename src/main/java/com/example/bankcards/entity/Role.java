package com.example.bankcards.entity;

import com.example.bankcards.enums.Roles;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role{

    @Id
    @Column(name ="id")
    private Long id;

    @Column(name = "rolename", unique = true)
    @Enumerated(EnumType.STRING)
    private Roles rolename;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id)
                && Objects.equals(rolename, role.rolename)
                && Objects.equals(users, role.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rolename, users);
    }
}
