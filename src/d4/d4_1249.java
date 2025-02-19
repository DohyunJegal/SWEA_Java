package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class d4_1249 {
	static int[][] ways, steps;
	static int N = 0;
	
	public static void bfs(int a, int b) {
		int[][] direction = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; 
		int[] qTmp;
		int x, y, dx, dy;
		ArrayDeque<int[]> q = new ArrayDeque<>();
		
		q.addLast(new int[] {a, b});
		steps[0][0] = 0;
		
		while(!q.isEmpty()) {
			qTmp = q.removeFirst();
			x = qTmp[0];
			y = qTmp[1];
			
			for (int[] d : direction) {
				dx = x + d[0];
				dy = y + d[1];
				
				if (0 <= dx && dx < N && 0 <= dy && dy < N && steps[x][y] + ways[dx][dy] < steps[dx][dy]) {
					steps[dx][dy] = steps[x][y] + ways[dx][dy];
					q.addLast(new int[] {dx, dy});
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		char[] tmp;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			// 배열 생성 후 초기화
			ways = new int[N][N];
			steps = new int[N][N];
			for (int i = 0; i < N; i++) {
				tmp = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					ways[i][j] = Character.getNumericValue(tmp[j]);
					steps[i][j] = (int) 1e9;
				}
			}
			
			bfs(0, 0);
			
			sb.append("#").append(t).append(" ").append(steps[N-1][N-1]).append("\n");
		}
		
		System.out.print(sb);
	}
}