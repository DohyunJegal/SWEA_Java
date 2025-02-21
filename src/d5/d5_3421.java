package d5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d5_3421 {
	static int N, M, result;
	static int[][] conflictCase;
	static boolean[] isVisited;

	static void selectIngredient() {
		int[] arr = new int[N];

		// 방문한 결과를 기반으로 부분집합 생성 
		int index = 0;
		for (int i = 1; i <= N; i++) {
			if (isVisited[i])
				arr[index++] = i;
		}
		
		int ingredientA, ingredientB;
		boolean isA, isB;
		for (int[] ingredients : conflictCase) {
			// 들어가면 안되는 재료 A와 B
			ingredientA = ingredients[0];
			ingredientB = ingredients[1];
			isA = false;
			isB = false;
			
			for (int n : arr) {
				// A와 B가 등장하면 체크
				if (n == ingredientA) isA = true;
				else if (n == ingredientB) isB = true;
			}
			
			// A와 B가 만나면 버거 생성 불가
			if (isA && isB) return;
		}
		
		result++;
	}

	static void subset(int depth) {
		if (depth == N + 1) {
			selectIngredient();
			return;
		}

		isVisited[depth] = true;
		subset(depth + 1);
		isVisited[depth] = false;
		subset(depth + 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			result = 0;
			
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			isVisited = new boolean[N + 1];
			conflictCase = new int[M][2];

			// 동시에 들어가면 안되는 재료 배열 입력
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				conflictCase[i][0] = Integer.parseInt(st.nextToken());
				conflictCase[i][1] = Integer.parseInt(st.nextToken());
			}

			subset(1);
			
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb);
	}
}