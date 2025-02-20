package d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d3_6808 {
	static int[] gyuYeong;
	static int[] inYeong;
	static boolean[] visited;
	static int[] output;
	static int gyuWin, gyuLose;

	// 인영이의 카드 정보로 순열 구하기
	static void permu(int depth) {
		if (depth > 9) {
			checker();
			return;
		}

		for (int i = 1; i <= 9; i++) {
			if (!visited[i]) {
				visited[i] = true;
				output[depth] = inYeong[i];
				permu(depth + 1);
				visited[i] = false;
			}
		}
	}
	
	// 인영이의 순열과 규영이와의 점수 비교
	static void checker() {
		int gyuScore = 0, inScore = 0;
		
		for (int i = 1; i <= 9; i++) {
			// 이긴 사람만 두 카드의 합 점수 획득
			if (gyuYeong[i] > output[i]) {
				gyuScore += gyuYeong[i] + output[i];
			} else if (gyuYeong[i] < output[i]) {
				inScore += gyuYeong[i] + output[i];
			}
		}
		
		if (gyuScore > inScore) {
			gyuWin++;
		} else if (gyuScore < inScore) {
			gyuLose++;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int temp;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int[] cards = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 };
			gyuYeong = new int[10];
			inYeong = new int[10];
			visited = new boolean[10];
			output = new int[10];
			gyuWin = 0;
			gyuLose = 0;

			// 규영이의 카드 정보 입력
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= 9; i++) {
				temp = Integer.parseInt(st.nextToken());
				gyuYeong[i] = temp;
				cards[temp] = 0;
			}

			// 인영이의 카드 정보 입력
			temp = 1;
			for (int i = 1; i <= 18; i++) {
				if (cards[i] != 0) {
					inYeong[temp++] = cards[i];
				}
			}

			// 인영이 카드 정보로 순열 구하기
			permu(1);
			
			sb.append("#").append(t).append(" ").append(gyuWin).append(" ").append(gyuLose).append("\n");
		}
		
		System.out.println(sb);
	}
}