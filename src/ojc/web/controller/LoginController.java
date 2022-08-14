package ojc.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ojc.login.dto.Login;
import ojc.login.service.LoginService;
import ojc.user.model.User;

/**
 * 로그인 처리용 컨트롤러
 * @author jclee
 *
 */
@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/login")
	public String getLoginView() {
		return "login";
	}

	/**
	 * 로그인 처리
	 * @param login
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/login")
	public String postLogin(Login login, Model model, HttpSession session) {
		loginService.authenticate(login);
		if (login.getError() != null) {
			model.addAttribute("error", login.getError());
			model.addAttribute("login", login);
			return "login";
		} else {
			User user = new User(login.getEmail(), login.getPassword());
			session.setAttribute("user", user);
			return "redirect:/boards";
		}
	}

	@GetMapping("/logout")
	public String getLogout(HttpSession session) {
		session.invalidate();
		return "login";
	}
}
