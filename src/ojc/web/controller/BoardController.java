package ojc.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ojc.board.dto.Pager;
import ojc.board.model.Board;
import ojc.board.repository.BoardMapper;
import ojc.user.model.User;

/**
 * 게시판 요청 처리를 위한 컨트롤러
 * @author jclee
 *
 */
@Controller
@RequestMapping("/boards")
public class BoardController {
	@Autowired
	private BoardMapper boardMapper;	
	
	/**
	 * 게시판 리스트 보기
	 * @param page : 조회를 원하는 페이지
	 * @param size : 한페이지당 보여지는 게시물 건수
	 * @param bsize : 하나의 페이지 블럭에 포함되는 페이지 수(본 예제에서는 5개의 페이지를 보인다)
	 * @return
	 */
	@GetMapping()
	public ModelAndView getBoardsView(
			@RequestParam(name="page", required=false, defaultValue="1") int page,
			@RequestParam(name="size", required=false, defaultValue="10") int size,
			@RequestParam(name="bsize", required=false, defaultValue="5") int bsize) {
		ModelAndView mav = new ModelAndView("board_list");
		mav.addObject("boards", boardMapper.selectByLimit(page, size));
		mav.addObject("pager", new Pager(page, size, bsize, boardMapper.count()));
		return mav;
	}
	
	/**
	 * 게시물 상세보기
	 * @param id
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/view/{id}")
	public String getBoardView(@PathVariable long id, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return session.getServletContext().getContextPath() + "/login";
		}
		
		boardMapper.increment(id, user.getEmail());
		model.addAttribute("board", boardMapper.selectById(id));
		return "board_view";
	}
	
	/**
	 * 글쓰기 화면 board_write.jsp 로딩
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/write")
	public String getInsertView(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return session.getServletContext().getContextPath() + "/login";
		}
		model.addAttribute("user", user);
		return "board_write";
	}
	
	/**
	 * 글쓰고 저장 하기
	 * XBOARD 테이블에 INSERT
	 * @param board
	 * @param session
	 * @param model
	 * @return
	 */
	@PostMapping("/write")
	public String postInsert(Board board, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		
		if (user != null && board != null) {
			if (user.getEmail().equals(board.getWriter())) {
				boardMapper.insert(board);
				return "redirect:/boards";
			}
		}
		return session.getServletContext().getContextPath() + "/boards";
	}
	
	/**
	 * 수정화면 board_update.jsp 로딩
	 * @param id
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/update/{id}")
	public String getUpdateView(@PathVariable long id, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		Board board = boardMapper.selectById(id);
		
		if (user != null && board != null) {
			if (user.getEmail().equals(board.getWriter())) {
				model.addAttribute("board", board);
				return "board_update";
			}
		}
		return session.getServletContext().getContextPath() + "/boards";
	}
	
	/**
	 * 수정후 저장하기 XBOARD 테이블 UPDATE 쿼리 실행
	 * @param board
	 * @param session
	 * @param model
	 * @return
	 */
	@PostMapping("/update")
	public String postUpdate(Board board, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		
		if (user != null && board != null) {
			if (user.getEmail().equals(board.getWriter())) {
				boardMapper.update(board);
				return "redirect:/boards/view/" + board.getId();
			}
		}
		return session.getServletContext().getContextPath() + "/boards";
	}
	
	/**
	 * 게시물 삭제
	 * @param id : 게시물 ID
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public String getDelete(@PathVariable long id, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		
		if (user != null) {
			Board board = boardMapper.selectById(id);
			
			if (user.getEmail().equals(board.getWriter())) {
				boardMapper.delete(id);
				return "redirect:/boards";
			}
		}
		return session.getServletContext().getContextPath() + "/boards";
	}
	
}
