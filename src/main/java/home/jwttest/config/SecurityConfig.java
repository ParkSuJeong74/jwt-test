package home.jwttest.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
        web
                //h2 console 하위 요청과 파버른 관련 요청은 spring security 로직 수행하지 않음
                .ignoring()
                .antMatchers(
                        "/h2-console/**"
                        ,"/favicon.ico"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests() //httpServletRequest를 사용하는 요청에 대한 접근제한 허용
                .antMatchers("/api/hello").permitAll() //인증 없이 접근 허용
                .anyRequest().authenticated(); //나머지 요청은 인증 필요
    }
}
