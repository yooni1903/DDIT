package selfTest;

import java.util.Arrays;

public class progTest {

	public static void main(String[] args) {
		// 프로그래머스 1단계 java k번째 수
		
		int[] array = {1, 5, 2, 6, 3, 7, 4};
		int[][] commands = {{2,5,3}, {4,4,1}, {1,7,3}};
		int[] answer = {};
		answer = new int[commands.length];
		
		for(int i = 0; i < commands.length; i++) {
			int[] temp = new int[commands[i][1] - commands[i][0] + 1];
			int a = 0;
			for(int j = (commands[i][0] - 1); j < commands[i][1]; j++) {
				System.out.println("commands[" + i + "][0] : " + commands[i][0]);
				temp[a] = array[j];
				System.out.println(j + "번째를 temp[a]에 넣은 값 : " + temp[a]);
				if(j != commands[i][1] - 1) a++;
			}
			Arrays.sort(temp);
			System.out.println("temp를 정렬한 후 : " + Arrays.toString(temp));
			answer[i] = temp[commands[i][2] - 1];
			System.out.println();
		}
		System.out.println(Arrays.toString(answer));

	}

}
