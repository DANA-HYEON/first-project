package com.dana.book.firstproject.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //롬복의 annotation

//파라미터가 없는 기본생성사 생성
@NoArgsConstructor //롬복의 annotation

//테이블과 링크될 클래스임을 나타냄
//기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이빙(_)으로 테이믈 이름을 매칭
//ex) SalesManager.java -> sales_manager table
@Entity //JPA의 annotation
public class Posts extends BaseTimeEntity{

    @Id //테이블의 PK필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK생성 규칙(auto_increment)
    private Long id;

    //테이블의 컬럼, 굳이 선언하지 않아도 해당 클래스의 필드는 모두 컬럼이 된다
    //사용하는 이유는 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용한다
    //문자열의 경우 VARCHAR(255)가 기본인데, 사이즈를 500으로 늘리고 싶거나 타입을 TEXT로 변경하고 싶거나 하는 경우에 사용
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    //해당 클래스의 빌더 패턴 클래스 생성
    //생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
