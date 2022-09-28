package org.zerock.board.service;

import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResultDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;

public interface BoardService {

    Long register(BoardDTO dto);    //등록처리

    PageResultDTO<BoardDTO, Object[]>   getList(PageRequestDTO pageRequestDTO); //목록처리


    BoardDTO get(Long bno); //게시물 조회를 게시물번호를 파라미터로 받아서 처리

    void removeWithReplies(Long bno);   //삭제 기능

    void modify(BoardDTO boardDTO);

    default Board dtoToEntity(BoardDTO dto){

        Member member = Member.builder().email(dto.getWriterEmail()).build();
        //보드에 있는 자룔를 기준으로 맴버에 있는 이메일을 가져오도록 빌더 사용함
        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
        return board;
    }

    default BoardDTO entityTODTO(Board board, Member member, Long replyCount){

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue())      //long으로 나으므로 int로 처리하도록
                .build();
        return boardDTO;
    }

}
