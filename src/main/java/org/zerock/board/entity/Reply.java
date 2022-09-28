package org.zerock.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "board")
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//자동번호 생성
    private Long rno;
    private String text;
    private String replyer;

    @ManyToOne
    private Board board; //보드 타입에 보드 객체를 사용하여 연관관계를 맺으면
    // 알아서 pk를 연관 관계로 설정한다.
    //board와 연관 관계는 차후에 설정
}
