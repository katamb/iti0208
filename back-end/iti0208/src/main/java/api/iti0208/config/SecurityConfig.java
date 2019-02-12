package api.iti0208.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // todo: remove later
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/api/posts").permitAll()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/logout").permitAll()
                .antMatchers("/api/**").hasAnyRole("USER", "ADMIN");

        http.formLogin();

        http.logout().logoutUrl("/logout");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {

        builder.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user")
                .password("$2a$10$fEa7rRKbc21Tq/BvY3TvJOulMqezkVwqY6uPVby.pQiOq59eea8xG")
                .roles("USER");

    }
}
