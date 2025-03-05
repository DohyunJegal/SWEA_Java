package d5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d5_1247 {
	static int N, res;
	static int[][] customers;
	static int[] company, home, selected;

	static int distance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	static void calcDist() {
		int dist = 0;

		// 회사에서 첫 고객의 집 사이 거리
		dist += distance(company[0], company[1], customers[selected[0]][0], customers[selected[0]][1]);

		// 고객들 간 거리
		for (int i = 0; i < N - 1; i++) {
			dist += distance(customers[selected[i]][0], customers[selected[i]][1], customers[selected[i + 1]][0],
					customers[selected[i + 1]][1]);
		}

		// 마지막 고객에서 집까지 거리
		dist += distance(customers[selected[N - 1]][0], customers[selected[N - 1]][1], home[0], home[1]);

		res = Math.min(res, dist);
	}

	static void makeOrder(int depth, int flag) {
		if (depth == N) {
			calcDist();
			return;
		}

		for (int i = 0; i < N; i++) {
			if ((flag & (1 << i)) != 0)
				continue;

			selected[depth] = i;
			makeOrder(depth + 1, flag | (1 << i));
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			customers = new int[N][2];
			company = new int[2];
			home = new int[2];
			selected = new int[N];

			st = new StringTokenizer(br.readLine(), " ");
			// 회사 좌표 입력
			company[0] = Integer.parseInt(st.nextToken());
			company[1] = Integer.parseInt(st.nextToken());
			// 내 집 좌표 입력
			home[0] = Integer.parseInt(st.nextToken());
			home[1] = Integer.parseInt(st.nextToken());
			// 고객 집 좌표 입력
			for (int i = 0; i < N; i++) {
				customers[i][0] = Integer.parseInt(st.nextToken());
				customers[i][1] = Integer.parseInt(st.nextToken());
			}

			// 순열을 통해 고객 방문 순서를 모두 구함
			res = (int) 1e9;
			makeOrder(0, 0);

			sb.append("#").append(t).append(" ").append(res).append("\n");
		}
		
		System.out.println(sb);
	}
}