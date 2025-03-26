package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class d4_1238 {
	static List<Integer>[] lst;
	
	static int bfs(int start) {
		boolean[] visited = new boolean[101];
		Queue<Integer> q = new ArrayDeque<>();
		
		visited[start] = true;
		q.add(start);
		
		int size, curr, res = 0;
		while(!q.isEmpty()) {
			size = q.size();
			res = 0;
			
			for (int i = 0; i < size; i++) {
				curr = q.poll();
				res = Math.max(res, curr);
				
				for (int next : lst[curr]) {
					if (!visited[next]) {
						visited[next] = true;
						q.add(next);
					}
				}
			}
		}
		
		return res;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for (int t = 1; t <= 10; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int data = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			lst = new ArrayList[101];
			for (int i = 1; i <= 100; i++) {
				lst[i] = new ArrayList<>();
			}
			
			int from, to;
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < data/2; i++) {
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				lst[from].add(to);
			}
			
			sb.append("#").append(t).append(" ").append(bfs(start)).append("\n");
		}
		
		System.out.println(sb);
	}
}