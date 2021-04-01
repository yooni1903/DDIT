package selfTest;

public class SelfTest1 {

	public static void main(String[] args) {
		int n = 10;
		int answer = 0;
		int count = 0;
		for(int i = 1; i <=10; i++){
			count = 0;
			for(int j = 1; j <= i; j++){
				if(i % j == 0 && i != 1&& j != 1 ){
					System.out.println(i + " 와 " + j);
					count++;
				}
			}
			if(count == 1) answer++;
		}

		while(true){
			
			
		}
	}

}
