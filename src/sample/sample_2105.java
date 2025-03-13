package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sample_2105 {
	static int N, res;
	static int[][] area, dir = new int[][] { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };

	static void tour(int sx, int sy, int lengthA, int lengthB) {
		int cx = sx, cy = sy;
		int cafe = 1;
		boolean[] isVisited = new boolean[101];

		// 시작 위치 방문 체크
		isVisited[area[cx][cy]] = true;

		// dir 1
		for (int i = 1; i <= lengthA; i++) {
			cx += dir[0][0];
			cy += dir[0][1];
			// 다음 칸이 범위를 넘어가거나, 이미 먹은 디저트인 경우 제외
			if (cx < 0 || cx >= N || cy < 0 || cy >= N || isVisited[area[cx][cy]])
				return;

			isVisited[area[cx][cy]] = true;
			cafe++;
		}
		// dir 2
		for (int i = 1; i <= lengthB; i++) {
			cx += dir[1][0];
			cy += dir[1][1];
			
			if (cx < 0 || cx >= N || cy < 0 || cy >= N || isVisited[area[cx][cy]])
				return;

			isVisited[area[cx][cy]] = true;
			cafe++;
		}
		// dir 3
		for (int i = 1; i <= lengthA; i++) {
			cx += dir[2][0];
			cy += dir[2][1];
			
			if (cx < 0 || cx >= N || cy < 0 || cy >= N || isVisited[area[cx][cy]])
				return;

			isVisited[area[cx][cy]] = true;
			cafe++;
		}
		// dir 4
		for (int i = 1; i <= lengthB; i++) {
			cx += dir[3][0];
			cy += dir[3][1];

			// 이동할 카페가 남은 경우
			if (i != lengthB) {
				if (cx < 0 || cx >= N || cy < 0 || cy >= N || isVisited[area[cx][cy]])
					return;

				isVisited[area[cx][cy]] = true;
				cafe++;
			} else {
				// 이동이 끝났는데, 시작점으로 돌아오지 못한 경우
				if (cx != sx || cy != sy)
					return;
			}
		}

		res = Math.max(res, cafe);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= T; t++) {
			// 값 초기화
			res = -1;

			N = Integer.parseInt(br.readLine().trim());

			area = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for (int j = 0; j < N; j++) {
					area[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 모든 칸 탐색
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 이동거리를 변경해가며 체크
					for (int L1 = 1; L1 < N; L1++) {
						for (int L2 = 1; L2 < N; L2++) {
							// 미리 범위를 넘어가지 않는지 체크
							if (i + L1 + L2 < N && j + L1 < N && j - L2 >= 0) {
								tour(i, j, L1, L2);
							}
						}
					}
				}
			}

			sb.append("#").append(t).append(" ").append(res).append("\n");
		}

		System.out.println(sb);
	}
}