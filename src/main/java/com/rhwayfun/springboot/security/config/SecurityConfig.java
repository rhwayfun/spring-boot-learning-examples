package com.rhwayfun.springboot.security.config;

import com.rhwayfun.springboot.security.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by chubin on 2017/8/13.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.inMemoryAuthentication().withUser("rhwayfun").password("1209").roles("USERS")
                .and().withUser("admin").password("123456").roles("ADMIN");*/
        //auth.jdbcAuthentication().dataSource(securityDataSource);
        auth.userDetailsService(userDetailsService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()//配置安全策略
                //.antMatchers("/","/index").permitAll()//定义/请求不需要验证
                .anyRequest().authenticated()//其余的所有请求都需要验证
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/index")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll();//定义logout不需要验证

        http.csrf().disable();
    }

}
