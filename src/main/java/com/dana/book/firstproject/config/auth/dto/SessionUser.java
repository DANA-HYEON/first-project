package com.dana.book.firstproject.config.auth.dto;

import com.dana.book.firstproject.domain.posts.User;
import lombok.Getter;

import java.io.Serializable;

//SessionUser에는 인증된 사용자 정보만 필요
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}

//왜 User클래스를 사용하지 않고 SessionUser를 사용하나?
//세션에 저장하기 위해 User클래스를 세션에 저장하려고 하니, User클래스에 직렬화를 구현하지 않았다는 에러가 뜬다
//User클래스는 엔티티로, 언제 다른 엔티티와 관계가 형성될지 모른다
//자식 엔티티를 갖고 있다면 직렬화대상이 자식들까지 포함되니 성능이슈, 부수효과가 발생활 확률이 높다
//그래서 직렬화 기능을 가진 세션 Dto를 하나 추가로 만드는 것이 이후 운영 및 유지보수 때 많은 도움이 된다.