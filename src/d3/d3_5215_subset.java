package d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d3_5215_subset {
	static int N, L, result ;
	static boolean[] isSelected;
	static int[][] ingredientInfo;
	
	public static void calcScore() {		
		int selectedScore = 0, selectedCal = 0;
		
		// 부분집합으로 선택된 인덱스 순회
		for (int i = 0; i < N; i++) {
			if(isSelected[i]) {
				// 점수와 칼로리 합 구하기
				selectedScore += ingredientInfo[i][0];
				selectedCal += ingredientInfo[i][1];
				
				// 만약 칼로리 초과시 종료
				if (selectedCal > L) {
					return;
				}
			}
		}
		
		result = Math.max(result, selectedScore);
	}

	public static void generateSubset(int cnt) {
		if (cnt == N) {
			// 구한 부분집합으로 점수 계산
			calcScore();
			return;
		}

		isSelected[cnt] = true;
		generateSubset(cnt + 1);
		isSelected[cnt] = false;
		generateSubset(cnt + 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			result = 0;
			// 부분집합 체크용 배열
			isSelected = new boolean[N];
			// 재료 정보 저장용 배열
			ingredientInfo = new int[N][2];

			// 재료 정보 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				ingredientInfo[i][0] = Integer.parseInt(st.nextToken());
				ingredientInfo[i][1] = Integer.parseInt(st.nextToken());
			}
			
			// 부분집합 생성
			generateSubset(0);
			
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb);
	}
}