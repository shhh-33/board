package ojc.web.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 최초 localhost:8080 으로 요청이 오는 경우 처리
 * @author jclee
 *
 */
@Controller
@RequestMapping("/")
public class HomeController {
	@GetMapping
	public String getHomeView(HttpServletRequest request) {
		return "home";
	}

	@GetMapping("/404.html")
	public String get404View() {
		return "error/404";
	}

	@GetMapping("/throw")
	public String testControllerAdvice() {
		throw new RuntimeException("Error Test In Controller.");
	}
}
