package com.example.demo.auth;

import com.example.demo.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// spring security의 login 처리 정책
// login 처리 완료 시 security context holder에 session 정보를 저장
// session 정보는 Authentication 객체
// Authentication 객체는 사용지 정보인 UserDetails 객체를 가지고 있음
public class PrincipalDetails implements UserDetails {
    private User user;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    // 해당 user의 권환을 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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

    // 예시: 1년 동안 사용자가 login하지 않으면 휴먼 계정으로 간주할 때
    @Override
    public boolean isEnabled() {
        return true;
    }
}
