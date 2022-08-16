package ojc.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerExceptionHandler {

	/**
	 * Controller계층에서 발생하는 에러를 잡아서 메서드로 처리
	 * 오류발생 -> error.jsp 로딩
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(Exception e) {
		//데이터와 뷰 동시에 설정 
		ModelAndView mav = new ModelAndView("error/error"); //view 경로
		mav.addObject("errorMsg", e.getMessage()); //view로 보낼 데이터
		return mav;
	}

}
