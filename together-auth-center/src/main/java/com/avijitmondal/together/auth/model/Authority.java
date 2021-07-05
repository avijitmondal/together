package com.avijitmondal.together.auth.model;

import com.avijitmondal.together.auth.model.bean.AuthorityRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "authority")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    @ApiModelProperty(notes = "The database generated user, token and session mapping ID.")
    private Long id;

    @Column(name = "role", columnDefinition = "enum('ROLE_USER','ROLE_ADMIN')", nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthorityRole role;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> users;

    @Override
    public String getAuthority() {
        return role.name();
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", name='" + role.name() + '\'' +
                '}';
    }
}
