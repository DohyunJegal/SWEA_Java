package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d4_1210 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// 현재 좌표
		int cx = 0, cy = 0;
		// 테스트 케이스 번호, 입력용 변수
		int t, input = 0;
		// 사다리
		int[][] ladder;

		for (int a = 0; a < 10; a++) {
			t = Integer.parseInt(br.readLine());
			sb.append("#").append(t).append(" ");
			
			// 사다리 정보 입력
			ladder = new int[100][100];
			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 100; j++) {
					input = Integer.parseInt(st.nextToken());
					ladder[i][j] = input;
					// 현재 좌표에 시작 좌표 설정
					if (input == 2) {
						cx = i;
						cy = j;
					}
				}
			}
			
			// 맨 꼭대기에 도달할 때까지 반복
			while(cx != 0) {
				if ((cy-1) >= 0 && ladder[cx][cy-1] == 1) {
					// 왼쪽으로 갈 수 있으면 왼쪽으로 이동
					ladder[cx][cy] = 0;
					cy -= 1;
				} else if ((cy+1) < 100 && ladder[cx][cy+1] == 1) {
					// 오른쪽으로 갈 수 있으면 오른쪽으로 이동
					ladder[cx][cy] = 0;
					cy += 1;
				} else if ((cx-1) >= 0 && ladder[cx-1][cy] == 1) {
					// 둘 다 아니라면 위로 이동
					ladder[cx][cy] = 0;
					cx -= 1;
				}
			}
			sb.append(cy).append("\n");
		}
		
		System.out.println(sb);
	}
}