package ojc.board.model;

import java.util.Date;

import lombok.Data;

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
 * 오라클쪽의 XBOARD 테이블에 대응하는 모델 클래스
 */
@Data
public class Board {
	private long id;        //게시물ID
	private String writer;  //작성자
	private String title;   //제목
	private String content; //본문내용
	private Date regDate;   //등록일시
	private long hitCount;  //조회수
}
