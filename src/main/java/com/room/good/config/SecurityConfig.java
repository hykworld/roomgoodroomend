package com.room.good.config;



import com.room.good.security.handler.ClubLoginSuccessHandler;
import com.room.good.security.handler.CustomAuthenticationFailureHandler;
import com.room.good.security.service.ClubUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true ,prePostEnabled = true)
public class SecurityConfig {
    //비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private ClubUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 현재 depricated됨. 람다식사용으로 바뀜. controller에서 annotation사용해서 처리 추천.
//        http.authorizeHttpRequests()
//                .requestMatchers("/sample/all").permitAll()
//                .requestMatchers("/sample/member").hasRole("USER")
//                .requestMatchers("/sample/admin").hasRole("ADMIN");

        http
                //html 공격 막기 위한 csrf 비활성화
                //.csrf(AbstractHttpConfigurer::disable)
                .formLogin((formLogin) -> formLogin
                        .loginPage("/Loginshop")//로그인무비페이지로 가라
                        .defaultSuccessUrl("/blog", true).failureHandler(customAuthenticationFailureHandler()))//
                .oauth2Login(oauth2->oauth2.successHandler(clubLoginSuccessHandler()));

        ;

        http.rememberMe((rememberMe)-> rememberMe.tokenValiditySeconds(60*60*24*7).userDetailsService(userDetailsService));

        http.logout((logout)->logout.logoutUrl("/logout").logoutSuccessUrl("/blog"));
        return http.build();
    }

    @Bean
    public ClubLoginSuccessHandler clubLoginSuccessHandler() {
        return new ClubLoginSuccessHandler(passwordEncoder());
    }

    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
}
