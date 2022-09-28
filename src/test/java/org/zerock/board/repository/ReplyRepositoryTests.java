package org.zerock.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Reply;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertReply() {

        IntStream.rangeClosed(1, 300).forEach(i -> {
            //1부터 100까지의 임의의 번호
            long bno = (long) (Math.random() * 100) + 1;
            // 댓글이 없는 게시물도 있고 댓글이 많은 게시물도 존재
            Board board = Board.builder().bno(bno).build();
            // bno는 보드 객체를 활용하여 생성하도록 유도
            Reply reply = Reply.builder()
                    .text("Reply......." + i)
                    .board(board) //board에 bno가 작성된다.
                    .replyer("guest")
                    .build();

            replyRepository.save(reply);

        });
    }

    @Test
    public void readReply1(){

        Optional<Reply> result = replyRepository.findById(1L);    //데이터베이스에 존재하는 번호

        Reply reply = result.get();

        System.out.println(reply);
        System.out.println(reply.getBoard());
    }
}