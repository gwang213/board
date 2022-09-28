package org.zerock.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer") //fk로 지정한 필드는 ToString 사용하지 않음(제외)
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//자동번호 생성
    private Long bno;

    private String title ;

    private String content;

    @ManyToOne (fetch = FetchType.LAZY) //기본값은 Eager로딩 지정
    private Member writer; // fk를 생성하여 연관관계가 설정된다.
    // 타입은 Member 타입으로 선언 필수
    // 클래스에 ToString 에서 제외할 것 (writer에 대한 객체가 아닌 문자로 인식)
    // 이메일을 fk를 사용해서 연관 관계를 설정할 것

    public void changeTitle(String title){
        this.title = title;
    }

    public void changeContent(String content){
        this.content = content;
    }
}
