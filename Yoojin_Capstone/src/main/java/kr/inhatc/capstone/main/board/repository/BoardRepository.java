package kr.inhatc.capstone.main.board.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.capstone.main.board.entity.Board;
import kr.inhatc.capstone.main.umbrella.entity.Umbrella;

public interface BoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board>{

    @Query(value = "select * from t_board where delete_yn = 'N' order by board_id desc", nativeQuery = true)
    List<Board> findAllBoard();
    
    @Query(value = "select * from t_board where board_id =:boardId", nativeQuery = true)
    Board findBoard(Long boardId);
    
   
    @Modifying
    @Query(value = "insert into t_board (title, content, create_date, delete_yn) values(:title, :content, :createDate, :deleteYn)", nativeQuery = true)
    void boardWrite(@Param ("title")String title, @Param ("content")String content, @Param ("createDate")LocalDateTime createDate, @Param ("deleteYn")String deleteYn);
    
    @Modifying 
    @Transactional 
    @Query(value = "update t_board set title=:title, content =:content where board_id =:id", nativeQuery = true)
    void boardUpdate(@Param ("id")Long id, @Param ("title")String title, @Param ("content")String content);
    
    @Modifying 
    @Transactional 
    @Query(value = "update t_board set delete_yn ='Y' where board_id =:boardId", nativeQuery = true)
    void boardDelete(Long boardId);
    
}
