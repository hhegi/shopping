package com.jiho.shopping.config.auth;

import com.jiho.shopping.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class PrincipalDetails implements UserDetails {
    private final long serialVersionUID = 1L;
    private User user;

    public PrincipalDetails(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        Collection<GrantedAuthority> collector = new ArrayList<>();
        collector.add(()->{
            return user.getRole();
        });
        return collector;
    }

    @Override
    public String getPassword(){
        return user.getPw();
    }

    @Override
    public String getUsername(){
        return user.getId();
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }


}
