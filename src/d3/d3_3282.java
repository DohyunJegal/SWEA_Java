package d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d3_3282 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");

			int items = Integer.parseInt(st.nextToken());
			int maxSize = Integer.parseInt(st.nextToken());

			int[] size = new int[items + 1];
			int[] value = new int[items + 1];

			for (int i = 1; i <= items; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				size[i] = Integer.parseInt(st.nextToken());
				value[i] = Integer.parseInt(st.nextToken());
			}

			int[][] d = new int[items + 1][maxSize + 1];
			for (int i = 1; i <= items; i++) {
				for (int j = 1; j <= maxSize; j++) {
					if (size[i] <= j) {
						d[i][j] = Math.max(d[i - 1][j], d[i - 1][j - size[i]] + value[i]);
					} else {
						d[i][j] = d[i - 1][j];
					}
				}
			}
			
			sb.append("#").append(t).append(" ").append(d[items][maxSize]).append("\n");
		}
		
		System.out.println(sb);
	}
}
