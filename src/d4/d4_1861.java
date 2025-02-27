package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d4_1861 {
	static int N, maxMoves, startNum;
	static int[][] rooms;
	static int[][] dir = new int[][] { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

	static void moveRoom(int x, int y, int start, int moves) {
		int nx, ny;

		for (int[] d : dir) {
			nx = x + d[0];
			ny = y + d[1];

			// 다음 방이 범위를 벗어나지 않는 경우
			if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
				// 다음 칸이 현재 칸보다 1 큰 경우 탐색
				if (rooms[nx][ny] == rooms[x][y] + 1) {
					moveRoom(nx, ny, start, moves + 1);
				}
			}
		}
		
		if (maxMoves < moves) {
			maxMoves = moves;
			startNum = start;
		} else if (maxMoves == moves) {
			// '이동할 수 있는 방의 개수가 최대인 방이 여럿이라면 그 중에서 적힌 수가 가장 작은 것을 출력한다.'
			startNum = Math.min(startNum, start);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			maxMoves = 1;
			startNum = 1;

			N = Integer.parseInt(br.readLine());
			
			// 방 정보 입력
			rooms = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					rooms[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 각 방마다 순회
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					moveRoom(i, j, rooms[i][j], 1);
				}
			}
			
			sb.append("#").append(t).append(" ").append(startNum).append(" ").append(maxMoves).append("\n");
		}
		
		System.out.println(sb);
	}
}