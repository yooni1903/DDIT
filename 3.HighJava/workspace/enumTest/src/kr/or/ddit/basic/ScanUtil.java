package kr.or.ddit.basic;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Scanner;

public class ScanUtil {
   
   // 유틸리티 성향의 메서드인 경우 static을 붙인다.
   // Math.random() Math.round() System.out.println()
   
   private static Scanner s = new Scanner(System.in);
   
   public static String nextLine() {
      return s.nextLine();
   }
   
   public static int nextInt() {
      return Integer.parseInt(s.nextLine());  // 문자열로 받은 후 int로 변경
   }
   
}