package d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class d3_1225 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T, cycleCnt, num;
		ArrayDeque<Integer> q = new ArrayDeque<>();

		for (int t = 1; t <= 10; t++) {
			q.clear();
			cycleCnt = 1;

			// 테스트 케이스 번호
			T = Integer.parseInt(br.readLine());
			sb.append("#").append(T).append(" ");

			// 8개의 데이터를 큐에 저장
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 8; i++) {
				q.addLast(Integer.parseInt(st.nextToken()));
			}

			while (true) {
				// 큐의 맨 앞에서 숫자를 받아옴
				num = q.removeFirst();
				
				// num을 감소해준 뒤, 감소할 숫자를 증가시켜줌
				num -= cycleCnt;
				cycleCnt = cycleCnt % 5 + 1;
				
				if (num <= 0) {
					// 만약 감소한 숫자가 0보다 작거나 같다면 종료
					q.addLast(0);
					break;
				} else {
					q.addLast(num);
				}
			}

			// 결과 저장
			for (int i : q) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}
}