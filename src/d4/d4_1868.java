package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class d4_1868 {
	static int N;
	static char[][] minefield;
	static int[][] dir = new int[][] { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };

	// 해당 칸 주변에 지뢰가 존재하면 true, 아니라면 false 반환
	static boolean mineChecker(int x, int y) {
		int nx, ny;
		
		for (int[] d : dir) {
			nx = x + d[0];
			ny = y + d[1];

			if (nx >= 0 && nx < N && ny >= 0 && ny < N && minefield[nx][ny] == '*') {
				return true;
			}
		}
		
		return false;
	}

	static void click(int sx, int sy) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		int cx, cy, nx, ny;
		int[] curr;

		// 해당 칸 클릭 표시 후 큐에 저장
		minefield[sx][sy] = 'v';
		q.addLast(new int[] { sx, sy });

		while (!q.isEmpty()) {
			curr = q.removeFirst();
			cx = curr[0];
			cy = curr[1];

			// 해당 칸 주변에 지뢰가 없다면
			if (!mineChecker(cx, cy)) {
				for (int[] d : dir) {
					nx = cx + d[0];
					ny = cy + d[1];
					
					// 다음 칸이 빈칸이면 체크 후 큐에 저장
					if (nx >= 0 && nx < N && ny >= 0 && ny < N && minefield[nx][ny] == '.') {
						minefield[nx][ny] = 'v';
						q.addLast(new int[] { nx, ny });
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			minefield = new char[N][];
			for (int i = 0; i < N; i++) {
				minefield[i] = br.readLine().trim().toCharArray();
			}

			int res = 0;
			// 주변에 지뢰가 없는 빈 칸 먼저 탐색
			// 그냥 전체 탐색 시 최적의 결과값을 뽑아낼 수 없음
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!mineChecker(i, j) && minefield[i][j] == '.') {
						click(i, j);
						res++;
					}
				}
			}
			// 주변에 지뢰가 있는 빈 칸 탐색
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (minefield[i][j] == '.') {
						click(i, j);
						res++;
					}
				}
			}

			sb.append("#").append(t).append(" ").append(res).append("\n");
		}

		System.out.println(sb);
	}
}