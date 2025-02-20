package d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d3_5215 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int ingredient, calories, hambugiScore, hambugiCalories;
		int[][] list, d;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 재료 수와 제한 칼로리
			st = new StringTokenizer(br.readLine(), " ");
			ingredient = Integer.parseInt(st.nextToken());
			calories = Integer.parseInt(st.nextToken());

			// 햄버거들의 점수와 칼로리를 저장한 배열
			list = new int[ingredient + 1][];
			list[0] = new int[] { 0, 0 };
			for (int i = 1; i <= ingredient; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				hambugiScore = Integer.parseInt(st.nextToken());
				hambugiCalories = Integer.parseInt(st.nextToken());
				list[i] = new int[] { hambugiScore, hambugiCalories };
			}

			// d의 행은 재료(점수), 열은 칼로리, 각 칸은 그 상황에서의 최대 점수
			d = new int[ingredient + 1][calories + 1];

			for (int i = 1; i <= ingredient; i++) {
				for (int j = 1; j <= calories; j++) {
					// j가 현재 재료의 칼로리보다 작은 경우, 그 재료를 고를 수 없으므로 위 점수를 그대로
					if (j < list[i][1])
						d[i][j] = d[i - 1][j];
					else
						// 위 점수 or (재료의 점수 + 칼로리 내 최고 점수) 중 큰 값 저장
						d[i][j] = Math.max(d[i - 1][j], d[i - 1][j - list[i][1]] + list[i][0]);
				}
			}
			
			sb.append("#").append(t).append(" ").append(d[ingredient][calories]).append("\n");
		}
		
		System.out.println(sb);
	}
}