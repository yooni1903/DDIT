package kr.or.ddit.basic.fileupload;

import java.io.Serializable;

// Upload한 파일의 정보가 저장될 객체
public class UploadDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long fileSize;			// 파일 크기
	private String fileName;		// 파일 이름
	private String uploadStatus;	// 전송 상태(성공 : success, 실패 : fail)
	
	
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUploadStatus() {
		return uploadStatus;
	}
	public void setUploadStatus(String uploadStatus) {
		this.uploadStatus = uploadStatus;
	}
	
	

}
