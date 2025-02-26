package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sample_4008 {
	static int N, minVal, maxVal;
	static int[] nums, operator = new int[4];

	public static void dfs(int depth, int res) {
		if (depth == N) {
			minVal = Math.min(minVal, res);
			maxVal = Math.max(maxVal, res);
			return;
		}

		for (int i = 0; i < 4; i++) {
			// 연산자를 선택할 수 있는 경우
			if (operator[i] != 0) {
				// 연산자 사용 처리
				operator[i]--;

				// 연산자 종류에 따라 재귀 선택
				switch (i) {
				case 0:
					dfs(depth + 1, res + nums[depth]);
					break;
				case 1:
					dfs(depth + 1, res - nums[depth]);
					break;
				case 2:
					dfs(depth + 1, res * nums[depth]);
					break;
				case 3:
					dfs(depth + 1, res / nums[depth]);
					break;
				}

				// 연산자 미사용 처리
				operator[i]++;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int result;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			minVal = (int) 1e9;
			maxVal = (int) -1e9;

			N = Integer.parseInt(br.readLine());
			nums = new int[N];

			// 연산자 개수 카운트
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 4; i++) {
				operator[i] = Integer.parseInt(st.nextToken());
			}

			// 수식에 사용되는 숫자들 입력
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}

			dfs(1, nums[0]);

			result = maxVal - minVal;
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}

		System.out.println(sb);
	}
}