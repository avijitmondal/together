package com.avijitmondal.together.auth.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "BINARY(16)")
    @ApiModelProperty(notes = "auto generated user id")
    private UUID id;

    @Column(name = "username", nullable = false, unique = true)
    @ApiModelProperty(notes = "user name")
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    @Size(min = 0, max = 50)
    private String email;

    @Column(name = "password", nullable = false)
    @ApiModelProperty(notes = "User password")
    private String password;

    @Column(name = "enabled", nullable = false)
    @ApiModelProperty(notes = "Indicates whether the user is enabled or disabled. A disabled user cannot be authenticated.")
    private boolean enabled;

    @Column(name = "created_time", insertable = true, updatable = false)
    @ApiModelProperty(notes = "The database generated user, token and session mapping created time.")
    private LocalDateTime createdTime;

    @Column(name = "updated_time", insertable = false, updatable = true)
    @ApiModelProperty(notes = "The database generated user, token and session mapping updated time.")
    private LocalDateTime updatedTime;

    @Size(max = 100)
    @Column(name = "activation_key")
    private String activationKey;

    @Size(max = 100)
    @Column(name = "reset_password_key")
    private String resetPasswordKey;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "authority_users",
            joinColumns = { @JoinColumn(name = "users_id") },
            inverseJoinColumns = { @JoinColumn(name = "authority_id") })
    private Set<Authority> authorities;

    public User() {
    }

    @PrePersist
    protected void onCreate() {
        createdTime = LocalDateTime.now();
        updatedTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedTime = LocalDateTime.now();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
        return enabled;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
