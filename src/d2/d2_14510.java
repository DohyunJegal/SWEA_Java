package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d2_14510 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N, tallest, tmp, odd, even, days;
		int[] trees;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			tallest = (int) -1e9;
			even = 0;
			odd = 0;
			days = 0;

			// 나무 수 체크
			N = Integer.parseInt(br.readLine());
			trees = new int[N];

			// 나무들의 키 입력
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				tmp = Integer.parseInt(st.nextToken());
				trees[i] = tmp;
				// 제일 큰 나무 골라내기
				tallest = Math.max(tallest, tmp);
			}

			// 전체 성장해야 할 나무의 높이를 1과 2로 분리
			// 홀수 날에 1만큼, 짝수 날에 2만큼 처리하면 효율적
			for (int i = 0; i < N; i++) {
				tmp = tallest - trees[i];
				even += tmp / 2;
				odd += tmp % 2;
			}

			// 처리할 나무가 없을 때 까지 반복
			while (even + odd > 0) {
				// 날짜 증가
				days++;

				if (days % 2 == 0) {
					// 짝수 날에는 2만큼 처리
					if (even > 0) {
						even--;						
					} 
				} else {
					// 홀수 날에는 1만큼 처리
					if (odd > 0) {
						odd--;
					} else if (even >= 2) {
						// 홀수 날인데 처리할 1이 없고, 2가 하나보다 많다면 2를 1 2개로 분해
						// 2가 하나만 있는 경우 다음날 처리하면 되기 때문
						even--;
						odd++;
					}
				}
			}

			sb.append("#").append(t).append(" ").append(days).append("\n");
		}

		System.out.println(sb);
	}
}