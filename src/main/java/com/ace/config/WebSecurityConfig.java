package com.ace.config;

import com.ace.security.TokenAuthenticationFilter;
import com.ace.security.TokenAuthenticationManager;
import com.ace.service.concerns.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configurable
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//允许进入页面方法前检验
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    TokenService tokenService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                .and()
                .formLogin()
                .defaultSuccessUrl("/admin/")
                .loginPage("http://pmp.baobanwang.com/page/logout")
                .permitAll();
        http.addFilterAt(tokenAuth(), UsernamePasswordAuthenticationFilter.class);
        super.configure(http);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/callback/**",
                "/admin/logout",
                "/doc.html",
                "/v2/**",
                "/check/**",
                "/swagger-resources/**",
                "/webjars/**",
                "/api/**",//app接口
                "/druid/**", //数据库连接池监控
                "/static/**",
                "/assets/**", // 后台静态资源
                "/css/**", // 样式表资源
                "/js/**" // 页面脚本资源
        );
    }

    private TokenAuthenticationFilter tokenAuth() {
        TokenAuthenticationFilter authenticationFilter = new TokenAuthenticationFilter("/admin/login");
        SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
        successHandler.setAlwaysUseDefaultTargetUrl(true);
        successHandler.setDefaultTargetUrl("/admin/");
        authenticationFilter.setAuthenticationManager(new TokenAuthenticationManager(tokenService));
        authenticationFilter.setAuthenticationSuccessHandler(successHandler);
        return authenticationFilter;
    }
}
