package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 	'd:/d_other'폴더에 있는 '코알라.jpg'파일을
 	'd:/d_other/연습용'폴더에 '복사본-코알라.jpg'파일로 복사하는 프로그램을 만드시오.
 */

public class FileCopyTest {

	public static void main(String[] args) {
		try {
			
			FileInputStream fis = new FileInputStream("d:/d_other/코알라.jpg");
			FileOutputStream fos = new FileOutputStream("d:/d_other/연습용/복사본-코알라.jpg");
			
			int data = 0;
			while((data = fis.read()) != -1){
				fos.write(data);
			}
			System.out.println("완료");
			fis.close();
			fos.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
