package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class d4_6109 {
	static int N, prev;
	static int[][] gamepan;
	static LinkedList<Integer> q = new LinkedList<>();

	static void up() {
		for (int j = 0; j < N; j++) {
			// 사용할 큐 초기화
			q.clear();
			
			// 이전 값을 저장할 변수
			prev = -1;
			for (int i = 0; i < N; i++) {
				if (gamepan[i][j] != 0) {
					// 만약 이전 값이 현재와 동일하다면
					if (prev != -1 && prev == gamepan[i][j]) {
						// 큐에서 저장된 수를 꺼낸 뒤 x2하여 다시 삽입
						q.removeLast();
						q.addLast(gamepan[i][j] * 2);
						prev = -1;
					} else {
						// 이전 값 할당 후 큐에 저장
						prev = gamepan[i][j];
						q.addLast(gamepan[i][j]);
					}
				}
			}

			// 큐에서 숫자를 꺼내 저장
			for (int i = 0; i < q.size(); i++) {
				gamepan[i][j] = q.get(i);
			}
			// 나머지는 0으로 저장
			for (int i = q.size(); i < N; i++) {
				gamepan[i][j] = 0;
			}
		}
	}

	static void down() {
		for (int j = 0; j < N; j++) {
			q.clear();
			prev = -1;
			for (int i = N - 1; i >= 0; i--) {
				if (gamepan[i][j] != 0) {
					if (prev != -1 && prev == gamepan[i][j]) {
						q.removeLast();
						q.addLast(gamepan[i][j] * 2);
						prev = -1;
					} else {
						prev = gamepan[i][j];
						q.addLast(gamepan[i][j]);
					}
				}
			}

			for (int i = 0; i < q.size(); i++) {
			    gamepan[(N - 1) - i][j] = q.get(i);
			}
			for (int i = 0; i < N - q.size(); i++) {
			    gamepan[i][j] = 0;
			}
		}
	}
	
	static void left() {
		for (int i = 0; i < N; i++) {
			q.clear();
			prev = -1;
			for (int j = 0; j < N; j++) {
				if (gamepan[i][j] != 0) {
					if (prev != -1 && prev == gamepan[i][j]) {
						q.removeLast();
						q.addLast(gamepan[i][j] * 2);
						prev = -1;
					} else {
						prev = gamepan[i][j];
						q.addLast(gamepan[i][j]);
					}
				}
			}

			for (int j = 0; j < q.size(); j++) {
				gamepan[i][j] = q.get(j);
			}
			for (int j = q.size(); j < N; j++) {
				gamepan[i][j] = 0;
			}
		}
	}
	
	static void right() {
		for (int i = 0; i < N; i++) {
			q.clear();
			prev = -1;
			for (int j = N - 1; j >= 0; j--) {
				if (gamepan[i][j] != 0) {
					if (prev != -1 && prev == gamepan[i][j]) {
						q.removeLast();
						q.addLast(gamepan[i][j] * 2);
						prev = -1;
					} else {
						prev = gamepan[i][j];
						q.addLast(gamepan[i][j]);
					}
				}
			}

			for (int j = 0; j < q.size(); j++) {
			    gamepan[i][(N - 1) - j] = q.get(j);
			}
			for (int j = 0; j < N - q.size(); j++) {
			    gamepan[i][j] = 0;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		String command;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			command = st.nextToken().trim();

			// 게임 타일 정보 입력
			gamepan = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					gamepan[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			switch (command) {
			case "up":
				up();
				break;
			case "down":
				down();
				break;
			case "left":
				left();
				break;
			case "right":
				right();
				break;
			}

			// 출력값 저장
			sb.append("#").append(t).append("\n");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(gamepan[i][j]).append(" ");
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}
}
