package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sample_5656 {
	static int drops, width, height, result;
	static int[] selected;
	static int[][] map, copyMap;

	static void checkRemain() {
		int cnt = 0;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (copyMap[i][j] != 0)
					cnt++;
			}
		}

		result = Math.min(result, cnt);
	}

	static void brickDrop() {
		for (int y = 0; y < width; y++) {
			for (int x = height - 1; x > 0; x--) {
				// 만약 해당 칸이 0이라면...
				if (copyMap[x][y] == 0) {
					// 그 윗칸들을 검색
					for (int i = x - 1; i >= 0; i--) {
						// 윗칸에 0이 아닌 블럭이 있다면 내리기
						if (copyMap[i][y] != 0) {
							copyMap[x][y] = copyMap[i][y];
							copyMap[i][y] = 0;
							break;
						}
					}
				}
			}
		}
	}

	static void explode(int x, int y) {
		// x나 y가 범위를 벗어났거나, 해당 칸이 빈칸인 경우 무시
		if (x < 0 || x >= height || y < 0 || y >= width || copyMap[x][y] == 0)
			return;

		// 폭발 범위 설정
		int range = copyMap[x][y] - 1;
		// 해당 칸 폭발처리
		copyMap[x][y] = 0;

		// 연쇄폭발 처리
		for (int i = 1; i <= range; i++) {
			explode(x - i, y);
			explode(x + i, y);
			explode(x, y - i);
			explode(x, y + i);
		}
	}

	static void marbleDrop(int line) {
		for (int i = 0; i < height; i++) {
			// 해당 라인의 맨 위 요소를 만나면 폭발 시작
			if (copyMap[i][line] != 0) {
				explode(i, line);
				brickDrop();
				break;
			}
		}
	}

	static void copyArray() {
		copyMap = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
	}

	static void lineSelect(int depth) {
		// 모두 정한 경우 떨어트리기 시작
		if (depth >= drops) {
			// 배열 복사
			copyArray();
			// 해당 라인에 구슬 투하
			for (int i = 0; i < drops; i++)
				marbleDrop(selected[i]);
			// 결과 저장
			checkRemain();
			
			return;
		}

		for (int i = 0; i < width; i++) {
			selected[depth] = i;
			lineSelect(depth + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");

			drops = Integer.parseInt(st.nextToken());
			width = Integer.parseInt(st.nextToken());
			height = Integer.parseInt(st.nextToken());

			map = new int[height][width];
			for (int i = 0; i < height; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < width; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 결과 저장 변수 초기화
			result = (int) 1e9;
			// 구슬을 떨어트릴 라인 정하기
			selected = new int[drops];
			lineSelect(0);
			
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb);
	}
}