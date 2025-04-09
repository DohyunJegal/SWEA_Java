package d6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d6_1263 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int N = Integer.parseInt(st.nextToken());
			
			// 인접행렬 입력
			int[][] matrix = new int[N][N];
			for (int i = 0; i < N * N; i++) {
				matrix[i/N][i%N] = Integer.parseInt(st.nextToken());
			}
			
			// 인접행렬 MAX_VALUE 설정
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i != j && matrix[i][j] == 0)
						matrix[i][j] = (int) 1e9;
				}
			}
			
			// 플로이드워셜 알고리즘 적용
			for (int mid = 0; mid < N; mid++) {
				for (int from = 0; from < N; from++) {
					for (int to = 0; to < N; to++) {
						matrix[from][to] = Math.min(matrix[from][to], matrix[from][mid] + matrix[mid][to]);
					}
				}
			}
			
			// CC 계산, 최솟값 저장
			int res = (int) 1e9;
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					if (i != j)
						sum += matrix[i][j];
				}
				
				res = Math.min(sum, res);
			}
			
			sb.append("#").append(t).append(" ").append(res).append("\n");
		}
		
		System.out.println(sb);
	}
}