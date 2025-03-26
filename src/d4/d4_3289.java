package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d4_3289 {
	static int n;
	static int[] parents;

	static void make() {
		parents = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if (a == parents[a])
			return a;
		
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot)
			return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	static int isSameRoot(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot)
			return 1;
		else
			return 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			make();

			sb.append("#").append(t).append(" ");
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int order = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if (order == 0)
					union(a, b);
				else if (order == 1) {
					sb.append(isSameRoot(a, b));
				}
					
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}