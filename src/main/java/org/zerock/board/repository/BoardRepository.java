package org.zerock.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.board.entity.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    //한개의 로우(Object) 내에 Object[ ]로 나옴
    @Query("select b, w from Board b left join b.writer w where b.bno =:bno")
    Object getBoardWithWriter(@Param("bno") Long bno);
    //한개의 Object에 배열 값으로 나옴
    //Board를 사용하고 있지만 Member를 같이 조회해야 하는 상황
    //b.writer는 Board 클래스는 Member와 연관관계를 맺고 있음
    //보드 입장에서 보면 writer와 연관관계가 있어서 on을 생략함
    @Query("SELECT b, r FROM Board b LEFT JOIN Reply r ON r.board = b WHERE b.bno = :bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);
    //보드 입장에서 보면 Reply에 연관 관계가 없어서 on을 붙인다.

    @Query(value = "SELECT b, w, count(r) " +
            "FROM Board b " +
            " LEFT JOIN b.writer w " +
            " LEFT JOIN Reply r ON r.board = b " +
            " GROUP BY b",
            countQuery ="SELECT count(b) FROM Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);   //목록 화면에 필요한 데이터

    @Query("SELECT b, w, count(r) " +
            "FROM Board b LEFT JOIN b.writer w " +
            "LEFT OUTER JOIN Reply r ON r.board = b " +
            "WHERE b.bno =:bno")
    Object getBoardByBno(@Param("bno") Long bno);
}
