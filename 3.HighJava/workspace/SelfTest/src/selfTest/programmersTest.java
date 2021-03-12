package selfTest;

public class programmersTest {
public static void main(String[] args) {
	// 프로그래머스 1단계 java 소수 찾기
	int n = 50;
	
	  int answer = 0;
      if(n >= 2) answer++;
      
      for(int i = 3; i <= n; i++){
          boolean chk = false;
          for(int j = 2; j <= i; j++){
              if(j == i && i % j == 0){
            	  System.out.println("i : " + i);
                  chk = true;
              }
              if(j != i && i % j == 0){
                  break;
              }
          }
          if(chk) answer++;
      }
    
      System.out.println("answer : " + answer);
}
}
