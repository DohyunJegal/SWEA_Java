package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sample_4012 {
	static int[][] synergy;
	static int[] selected, notSelected;
	static int N, minGap;

	static void calcSynergy() {
		int foodA = 0, foodB = 0;

		// 각 재료들의 시너지 계산
		for (int i = 0; i < N / 2 - 1; i++) {
			for (int j = i + 1; j < N / 2; j++) {
				foodA += synergy[selected[i]][selected[j]] + synergy[selected[j]][selected[i]];
				foodB += synergy[notSelected[i]][notSelected[j]] + synergy[notSelected[j]][notSelected[i]];
			}
		}

		// 두 음식 시너지 점수의 차가 최소일 경우 저장
		minGap = Math.min(minGap, Math.abs(foodA - foodB));
	}

	static void makeNotSelectedArr(int flag) {
		int a = 0;
		for (int i = 0; i < N; i++) {
			if ((flag & (1 << i)) == 0) {
				notSelected[a++] = i;
			}
		}
	}

	static void combi(int cnt, int start, int flag) {
		// N/2만큼 선택한 경우
		if (cnt == N / 2) {
			// A 음식에 선택받지 못한 B 음식용 인덱스 배열 생성
			makeNotSelectedArr(flag);
			// 시너지 계산
			calcSynergy();
			return;
		}

		for (int i = start; i < N; i++) {
			// 비트마스킹을 통해 해당 숫자를 사용했는지 확인
			if ((flag & (1 << i)) != 0)
				continue;

			// 사용 안한 숫자 추가
			selected[cnt] = i;

			// 사용 체크 후 재귀
			combi(cnt + 1, i + 1, flag | (1 << i));
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 결과값 초기화
			minGap = (int) 1e9;
			N = Integer.parseInt(br.readLine());

			// 식재료들을 N/2개씩 나눠 저장할 배열 생성
			selected = new int[N / 2];
			notSelected = new int[N / 2];

			// 시너지 정보 입력
			synergy = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					synergy[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// N/2만큼 선택하는 조합 생성
			combi(0, 0, 0);
			
			sb.append("#").append(t).append(" ").append(minGap).append("\n");
		}
		
		System.out.println(sb);
	}
}
