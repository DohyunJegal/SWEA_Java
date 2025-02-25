package d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d3_5215_BM {
	static int N, L, R, result;
	static int[][] ingredientInfo;

	static void combination(int start, int flag, int currScore, int currCal) {
		int nextScore, nextCal;
		
		// 결과 비교 후 저장
		result = Math.max(result, currScore);

		for (int i = start; i < N; i++) {
			// 오른쪽에서 i비트만큼 떨어진 자리가 0비트가 아님 -> 이미 이전에 선택됨
			if ((flag & (1 << i)) != 0)
				continue;

			// 점수와 칼로리 계산
			nextScore = currScore + ingredientInfo[i][0];
			nextCal = currCal + ingredientInfo[i][1];
			// 만약 최대 칼로리를 넘은 경우 다음 경우로
			if (nextCal > L)
				continue;

			combination(i + 1, flag | (1 << i), nextScore, nextCal);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			// 결과 초기화
			result = 0;

			// 재료 정보 저장용 배열
			ingredientInfo = new int[N][2];
			// 재료 정보 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				ingredientInfo[i][0] = Integer.parseInt(st.nextToken());
				ingredientInfo[i][1] = Integer.parseInt(st.nextToken());
			}

			combination(0, 0, 0, 0);

			sb.append("#").append(t).append(" ").append(result).append("\n");
		}

		System.out.println(sb);
	}
}