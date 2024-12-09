package com.example.springboot.models;

import com.example.springboot.enums.UserRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name = "tb_users")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")


public class UserModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String login;


    private String password;


    @Enumerated(EnumType.STRING)
    private UserRoles role;


   public UserModel(String login,  String password, UserRoles role)  {
       this.login = login;
       this.password = password;
       this.role = role;

   }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // Adiciona roles baseadas na role do usuário
        switch (this.role) {
            case ANALISTA:
                authorities.add(new SimpleGrantedAuthority("ROLE_ANALISTA"));
                break;
            case REVISOR:
                authorities.add(new SimpleGrantedAuthority("ROLE_REVISOR"));
                break;
            case APROVADOR:
                authorities.add(new SimpleGrantedAuthority("ROLE_APROVADOR"));
                break;
            case USER:
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                break;
            default:
                // Caso um usuário tenha uma role não mapeada
                break;
        }

        return authorities;
    }


    @Override
    public String getUsername() {
        return login;
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
