package ojc.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * -------------------------------------
 * STS에서 Lombok을 사용하기 위해서는 
 * -------------------------------------
 * 1. pom.xml에 lombok dependency를 추가하고
 * 2. .m2 아래 lombok-1.16.16.jar를 복사후 lombok.jar로 이름 변경
 * 3. SpringToolSuite4.ini 파일의 맨아래에 -javaagent:lombok.jar 추가
 * 4. STS 다시 시작
 * 
 * @author jclee
 * 로그인에서 사용되는 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {
	private String email;
	private String password;
	private String error;  //오류 메시지 셋팅위한 것 추가됨
}
