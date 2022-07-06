package com.example.springboot.config.auth;

import com.example.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.userinfo.CustomUserTypesOAuth2UserService;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()// 세션을 사용하지 않고 JWT 토큰을 활용하여 진행, csrf토큰검사를 비활성화
        .headers().frameOptions().disable()
        .and()
        .authorizeRequests()// 인증절차에 대한 설정을 진행
        .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll() //전체 열람가능
        .antMatchers("/api/v1/**").hasRole(Role.USER.name()) //User 권한만 열람가능
        .anyRequest().authenticated() //나머지 URL은 인증된 사용자들에게 허용
        .and()
        .logout()// 로그아웃 설정
        .logoutSuccessUrl("/")// 로그아웃 성공시 리다이렉트 주소
        .and()
        .oauth2Login()
        .userInfoEndpoint()
        .userService(customOAuth2UserService);
    }
}
