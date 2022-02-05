package pl.sda.springwebrest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityRestConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${app.page}")
    private int page;

    @Value("${app.theme}")
    private String theme;

    public String getTheme() {
        return theme;
    }

    public int getPage() {
        return page;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .csrf()
                .disable()
                .headers()
                .and()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.POST, "/api/shoppinglists").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/shoppinglists/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/shoppinglists/*").hasRole("USER")
                .antMatchers(HttpMethod.PATCH, "/api/shoppinglists/*").denyAll()
                .antMatchers(HttpMethod.GET, "/api/shoppinglists", "/api/shoppinglists/*").authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("adam").password("$2a$12$NHUvIQBLpNPQzQAZNpRBA.a1hivE4arNrQAWtdFdStDIQ2hvox0FC").roles("ADMIN")
                .and()
                .withUser("ewa").password("$2a$12$28n/mSFl3HqUSPaNxI4JAuM3U4x4KvoqD1zF0N/lZ0P5hGxUKbBJi").roles("USER")
                .and()
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
