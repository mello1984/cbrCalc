package mello.cbrcalc.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "t_users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(unique = true, name = "username")
    @NotBlank(message = "Username must be not blank")
    @Size(min = 2, max = 15, message = "Username must be between 2 and 15 symbols")
    @Pattern(regexp = "[_\\w\\d]*", message = "username can contain only 'a-zA-Z0-9_' symbols")
    String username;

    @Column(name = "password")
    @NotBlank(message = "Password must be not blank")
    @Size(min = 4, message = "Password must be minimum 4 symbols")
    String password;

    @Transient
    @NotBlank(message = "Password confirm must be not blank")
    @Size(min = 4, message = "Password confirm must be minimum 4 symbols")
    String passwordConfirm;

    @Column(name = "first_name")
    @NotBlank(message = "First name must be not blank")
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 symbols")
    String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Last name must be not blank")
    @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 symbols")
    String lastName;

    @Column(name = "email")
    @NotBlank(message = "E-mail must be not blank")
    @Size(min = 5, max = 30, message = "E-mail must be between 5 and 30 symbols")
    @Pattern(regexp = ".*@.*", message = "E-mail must contain @")
    String email;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
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
