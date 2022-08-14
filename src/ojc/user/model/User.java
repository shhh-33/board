package ojc.user.model;

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
 * @author jclee
 *
 * 오라클쪽의 XUSER 테이블에 대응되는 모델 클래스
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private String email;
	private String password;
}
