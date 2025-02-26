package d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class d3_2805 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N, delta, result;
		int[][] farm;
		String input;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			result = 0;

			N = Integer.parseInt(br.readLine());

			// 농작물 정보 저장
			farm = new int[N][N];
			for (int i = 0; i < N; i++) {
				input = br.readLine();
				for (int j = 0; j < N; j++) {
					farm[i][j] = input.charAt(j) - '0';
				}
			}

			delta = 0;
			for (int i = 0; i < N; i++) {
				for (int j = N / 2 - delta; j <= N / 2 + delta; j++) {
					result += farm[i][j];
				}

				// 중반까지는 델타 증가, 이후는 델타 감소
				if (i < N / 2)
					delta++;
				else
					delta--;
			}

			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb);
	}
}
