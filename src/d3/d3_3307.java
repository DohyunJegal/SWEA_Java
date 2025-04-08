package d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d3_3307 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());

			int[] arr = new int[N];
			int[] lis = new int[N];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int res = 0;
			for (int i = 0; i < N; i++) {
				lis[i] = 1;
				
				for (int j = 0; j < i; j++) {
					if (arr[i] > arr[j] && lis[j] + 1 > lis[i]) {
						lis[i] = lis[j] + 1;
					}
				}
				
				res = Math.max(res, lis[i]);
			}

			sb.append("#").append(t).append(" ").append(res).append("\n");
		}

		System.out.println(sb);
	}
}