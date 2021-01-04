package com.example.bootspringsecuritu.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> user;

    public String getName() {
        return name;
    }
    @Override
    public String getAuthority() {
        return getName();
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        int result = 17;
        return 37 * result + (this.name == null ? 0 : this.name.hashCode());
    }

    @Override
    public boolean equals(Object o) {
        return this.name.equals(o.toString());
    }
}
