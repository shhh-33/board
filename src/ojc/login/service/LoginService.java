package ojc.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ojc.login.dto.Login;
import ojc.user.model.User;
import ojc.user.repository.UserMapper;

/**
 * 로그인 서비스
 * 사용자 인증
 * @author jclee
 *
 */
@Service
public class LoginService {
	@Autowired
	private UserMapper userMapper;

	public void authenticate(Login login) {
		User user = userMapper.selectByEmail(login.getEmail());
		if (user == null) {
			login.setError("Email does not exist.");
		} else {
			if (!user.getPassword().equals(login.getPassword())) {
				login.setError("Password is not correct.");
			} else {
				login.setError(null);
			}
		}
	}
}
