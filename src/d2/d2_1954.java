package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class d2_1954 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N, x, y, d, nx, ny;
		int[][] snail;
		int[][] dir = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 달팽이 제작용 현재 좌표 (x, y)와 방향 d
			x = 0;
			y = 0;
			d = 0;

			// 달팽이 배열 할당
			N = Integer.parseInt(br.readLine());
			snail = new int[N][N];

			// 달팽이 채워넣기
			for (int i = 1; i <= N * N; i++) {
				snail[x][y] = i;

				// 다음 좌표
				nx = x + dir[d][0];
				ny = y + dir[d][1];

				// 만약 배열의 끝이거나, 이전에 채웠던 숫자를 만나면 방향 변경
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || snail[nx][ny] != 0) {
					// 방향 변경
					d = (d + 1) % 4;
					// 그에 맞는 좌표 설정
					x += dir[d][0];
					y += dir[d][1];
				} else {
					x = nx;
					y = ny;
				}
			}
			
			// StringBuilder에 결과 저장
			sb.append("#").append(t).append("\n");
			for (int[] arr : snail) {
				for (int i : arr) {
					sb.append(i).append(" ");
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}
}