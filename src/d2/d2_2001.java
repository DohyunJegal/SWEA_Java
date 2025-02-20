package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d2_2001 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int[][] flies;
		int fliesSize, flapperSize, gap, fliesKill, res;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			res = (int) -1e9;

			// 파리 배열 크기와 파리채 크기 입력
			st = new StringTokenizer(br.readLine(), " ");
			fliesSize = Integer.parseInt(st.nextToken());
			flapperSize = Integer.parseInt(st.nextToken());

			// 파리 정보 입력
			flies = new int[fliesSize][fliesSize];
			for (int i = 0; i < fliesSize; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < fliesSize; j++) {
					flies[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			gap = fliesSize - flapperSize + 1;
			for (int i = 0; i < gap; i++) {
				for (int j = 0; j < gap; j++) {
					// 죽인 파리 수 계산
					fliesKill = 0;

					for (int a = 0; a < flapperSize; a++) {
						for (int b = 0; b < flapperSize; b++) {
							fliesKill += flies[i + a][j + b];
						}
					}

					// 제일 많이 죽인 파리 수 저장
					res = Math.max(res, fliesKill);
				}
			}

			sb.append("#").append(t).append(" ").append(res).append("\n");
		}

		System.out.println(sb);
	}
}