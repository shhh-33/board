package ojc.board.dto;

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
 * 페이징을 위한 클래스
 */
@Data
public class Pager {
	private int page; // current page
	private int size; // rows per page
	private int bsize; // pages per block

	private int rows; // total elements
	private int pages; // total pages
	private int blocks; // total blocks

	private int block; // current block
	private int bspage; // current block start page
	private int bepage; // current block end page

	public Pager(int page, int size, int bsize, int rows) {
		//현재페이지
		this.page = page;
		
		//한페이지당 게시물개수
		this.size = size;
		
		//블럭당페이지사이즈(기본5로 되어 있음)
		//페이지5개씩 한블럭에 표시되고 넘어가면 >> 표시가 나옴
		this.bsize = bsize;
		
		//총게시물건수
		this.rows = rows;

		//총페이지
		pages = (int) Math.ceil((double) this.rows / this.size);
		//총블럭
		blocks = (int) Math.ceil((double) pages / this.bsize);

		//현재블럭
		block = (int) Math.ceil((double) this.page / this.bsize);
		//현재블럭종료페이지
		bepage = block * this.bsize;
		//현재페이지시작페이지
		bspage = bepage - this.bsize + 1;
	}
	
	// 두번째 페이지이고 한페이지에 10개씩 이라면 10을 리턴
	public static int seekOffset(int page, int size) {
		if (page > 0) {
			return (page - 1) * size;
		}
		return 0;
	}

	// 두번째 페이지이고 한페이지에 10개씩 이라면 11을 리턴
	public static int seekStart(int page, int size) {
		return ((page - 1) * size) + 1;
	}

	// 두번째 페이지이고 한페이지에 10개씩 이라면 20을 리턴
	public static int seekEnd(int page, int size) {
		return page * size;
	}
}
