package ojc.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ojc.user.model.User;

/**
 * /board**로 진입시 로그인 여부를 체크하기 위한 인터셉터
 * 로그인이 안되어 있는 경우 로그인 화면(/login) 으로  보냄
 * @author jclee
 *
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			String url = session.getServletContext().getContextPath() + "/login";
			response.sendRedirect(url);
			System.out.println("LoginInterceptor # preHandle() : NO PASS");
			return false;
		}
		System.out.println("LoginInterceptor # preHandle() : PASS");
		return true;
	}
}
