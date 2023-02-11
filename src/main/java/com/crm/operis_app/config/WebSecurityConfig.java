package com.crm.operis_app.config;

import com.crm.operis_app.security.jwt.JwtAuthEntryPoint;
import com.crm.operis_app.security.jwt.JwtAuthTokenFilter;
import com.crm.operis_app.services.authUser.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
//@EnableGlobalMethodSecurity(prePostEnabled = true)

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    DataSource dataSource;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

    @Bean
    SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
   }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

       authenticationManagerBuilder.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select username,password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select role from user_roles where username=?");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() {	//(5)
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .maximumSessions(1)
                .sessionRegistry(sessionRegistry());

        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/api/auth/**","/forgotPassword/**","/resetPass/**","/resetPassword/**").permitAll()
                .antMatchers("https://api64.ipify.org/?format=json").permitAll()
                .antMatchers(HttpMethod.OPTIONS,"/api/auth/**","/forgotPassword/**","/resetPass/**","/resetPassword/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS,"**" ,"https://api64.ipify.org/?format=json").permitAll()
                .antMatchers("/**").permitAll()
                .antMatchers("/api/file/**","/api/planningGannts/project/**",
                        "/api/image/**", "/api/ipAddress","/api/user/username/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.headers().frameOptions().disable();

        http.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry());

    }
    
}