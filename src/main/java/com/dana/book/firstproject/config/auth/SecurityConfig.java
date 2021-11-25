package com.dana.book.firstproject.config.auth;

import com.dana.book.firstproject.domain.posts.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor //Spring Scurity설정들을 활성화시켜줍니다.
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http
                .csrf().disable()
                .headers().frameOptions().disable() //h2-console화면을 사용하기 위해 해당 옵션들을 disable한다
                .and()
                    .authorizeRequests() //URL별 권한 관리를 설정하는 옵션의 시작점
                                         //authorizeRequests가 선언되어야만 antMatcher옵션을 사용할 수 있다.
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    //권한 관리 대상을 지정하는 옵션
                    //URL, HTTP메소드별로 관리가 가능
                    //"/"등 지정된 url들은 permitAll()옵션을 통해 전체 열람 권한을 주었다
                    //"/api/v1/**"주소를 가진 api는 user권한을 가진 사람만 가능하도록 했다.
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    //anyRequest : 설정된 값들 이외 나머지 url들을 나타낸다
                    //authenticated : 나머지 url들은 모두 인증된 사용자들에게만 허용되게 한다 (인증된 사용자 즉, 로그인한 사용자)
                    .anyRequest().authenticated()
                .and()
                    //로그아웃 기능에 대한 여러 설정의 진입점
                    //로그아웃 성공시 /주소로 이동
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login() //OAuth2로그인 기능에 대한 여러 설정의 진입점
                        .userInfoEndpoint() //OAuth2로그인 성공 이후 사용자 정보를 가져올때의 설정들을 담당
                         //소셜 로그인 성공 시 후속 조치를 진행할 UserService인터페이스의 구현체를 등록
                            //리소스서버(즉, 소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시
                                .userService(customOAuth2UserService);
        
    }
}
