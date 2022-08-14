package ojc.board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import ojc.board.model.Board;

/**
 * 매퍼 클래스
 * board-mapper.xml의 namespace에 기술됨
 * @author jclee
 *
 */
@Mapper
public interface BoardMapper {
	public int insert(Board board);
	public int update(Board board);
	public int delete(long id);
	
	@Select("SELECT COUNT(*) FROM xboard")
	public int count();
	@Select("SELECT * FROM xboard ORDER BY id DESC")
	public List<Board> selectAll();
	
	public Board selectById(long id);
	
	// 페이지 나누기 쿼리 : page : 현재페이지, size:페이지당 게시물개수
	public List<Board> selectByLimit(@Param("page") int page, @Param("size") int size);
	
	//조회수 증가 쿼리,본인이 쓴 글은 조회수를 증가 안하기 위해 글쓴이(requester)를 넘김
	public int increment(@Param("id") long id, @Param("requester") String requester);
}
