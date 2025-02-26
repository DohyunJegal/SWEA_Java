package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d4_1486 {
	static int N, B, result;
	static int[] height, selected;

	static void makeTower() {
		int total = 0;

		// 조합에 선택된 점원들 키 합치기
		for (int s : selected)
			total += s;

		// 선반 높이보다 낮으면 실패
		if (total < B)
			return;
		else
			result = Math.min(result, total - B);
	}

	static void combi(int depth, int start, int end, int flag) {
		if (depth == end) {
			makeTower();
			return;
		}

		for (int i = start; i < N; i++) {
			if ((flag & (1 << i)) != 0)
				continue;

			selected[depth] = height[i];

			combi(depth + 1, i + 1, end, flag | (1 << i));
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 결과값 초기화
			result = (int) 1e9;

			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			height = new int[N];

			// 점원들의 키 입력
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}

			// 점원들 조합 생성
			for (int i = 1; i <= N; i++) {
				selected = new int[i];
				combi(0, 0, i, 0);
			}

			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb);
	}
}