package selfTest;

import java.util.ArrayList;
import java.util.List;

public class NoSameNumber {

	public static void main(String[] args) {
		
		int[] arr = {1,1,3,3,0,1,1};
		List<Integer> list = solution(arr);
		
		System.out.println(list);

	}
	
	
	 public static List<Integer> solution(int []arr) {
	        List<Integer> answer = new ArrayList<>();
	        answer.add(arr[0]);
	        for(int i = 0; i < arr.length; i++){
	        	int a = answer.size();
	            if(arr[i] != answer.get(a-1)){
	                answer.add(arr[i]);
	            }
	        }
	        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
	        System.out.println("Hello Java");

	        return answer;
	    }
	
	

}
