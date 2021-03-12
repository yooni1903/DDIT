package kr.or.ddit.basic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class FileTest03 {
	public static void main(String[] args) {
		FileTest03 test = new FileTest03();
		
//		File file = new File("d:/app");
		File file = test.getDir();
		if(file != null){
//			test.displayFileList(file.getParentFile());			//상위폴더만 갖고온다.
			test.displayFileList(file);
		}
		
		test.displayFileList(file);
	}
	
	// 디렉토리를 매개변수로 받아서 해당 디렉토리에 잇는 모든 파일과 디렉토리 목록을 출력하는 메서드
	public void displayFileList(File dir){
		if(!dir.isDirectory()){
			System.out.println("디렉토리(폴더)만 가능합니다.");
			return;
		}
		System.out.println("[" + dir.getAbsolutePath() + "] 디렉토리 내용");
		
		// 해당 디렉토리 안에 있는 모든 파일과 디렉토리 목록을 구한다.
		File[] files = dir.listFiles();
		String[] strfiles = dir.list();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a HH:mm");	
		
		// 가져온 파일과 디렉토리 목록 개수만큼 반복
		for(int i = 0; i < files.length; i++){
			String fileName = files[i].getName();
			
			// 파일의 속성이 저장될 변수 (읽기, 쓰기, 히든, 디렉토리 구분);
			String attr = "";	
			String size = "";			// 파일 크기
			
			if(files[i].isDirectory()){
				attr = "<DIR>";
			}else{
				size = files[i].length() + "";
				
				attr = files[i].canRead() ? "R" : "";
				attr += files[i].canWrite() ? "W" : "";
				attr += files[i].isHidden() ? "H" : "";
			}
			System.out.printf("%s %5s %12s %s \n",  sdf.format(new Date(files[i].lastModified())), attr, size, fileName);
			// s는 문자열을 뜻하고 숫자는 자릿수를 뜻한다.
			
		}
		
	}
	
	//
	public File getDir(){
		JFileChooser filechoose = new JFileChooser();
		
		// 팝업창에서 위치가 내문서로 고정하고 싶지 않으면
//		File curr = new File(System.getProperty("user.dir"));
		File curr = new File("d:/");
		filechoose.setCurrentDirectory(curr);				// '열기'할 디렉토리 설정
		
		// 디렉토리만 선택하게 하기
		filechoose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		int result = filechoose.showOpenDialog(new JPanel());		// 파일 열기할 때
//		int result = filechoose.showSaveDialog(new JPanel());		// 파일 저장할 때
//		int result = filechoose.showDialog(new JPanel(), "");	
		
		File selectFile = null;
		if(result == JFileChooser.APPROVE_OPTION){
			selectFile = filechoose.getSelectedFile();
		}
		return selectFile;
	}
	
	
	
	
	
	
}
