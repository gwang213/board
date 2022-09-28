package org.zerock.board.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResultDTO;
import org.zerock.board.entity.Board;

@SpringBootTest
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister(){

        BoardDTO dto = BoardDTO.builder()
                .title("Test.")
                .content("Test...")
                .writerEmail("user55@aaa.com") //현재 데이터베이스에 존재하는 회원 이메일
                .build();

        Long bno = boardService.register(dto);
    }

    @Test
    public void testList(){

        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        //PageRequestDTO 객체를 생성하여 pageRequestDTO변수로 선언한다.
        PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);
        //PageResultDTO 객체를 BoardDTO, Object[] 통로도 사용하여 result 변수로 선언
        for(BoardDTO boardDTO : result.getDtoList()){
            System.out.println(boardDTO);
        }//BoardDTO 객체를 향상된 for문을 이용하여 boardDTO를 출력한다.
    }

    @Test
    public void testGet(){

        Long bno = 100L;

        BoardDTO boardDTO = boardService.get(bno);

        System.out.println(boardDTO);
    }

    @Test
    public void testRemove(){

        Long bno = 5L;

        boardService.removeWithReplies(bno);
    }

    @Test
    public void testModify(){

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(2L)
                .title("제목 변경합니다.")
                .content("내용 변경합니다.")
                .build();

        boardService.modify(boardDTO);
    }
}
