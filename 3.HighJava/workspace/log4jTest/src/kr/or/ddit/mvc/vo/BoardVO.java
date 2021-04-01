package kr.or.ddit.mvc.vo;

import java.util.Date;

public class BoardVO {
	private int board_no;			// 글 번호(시퀀스 사용하여 자동증가)
	private String title;	// 제목
	private String writer;	// 작성자
	private Date date;		// 작성날짜
	private int cnt;		// 조회수
	private String content;	// 내용
	
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
