package d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d3_1873 {
	static int H, W, N, cx, cy;
	static char[][] field;
	static char[] order;
	
	static void up() {
		// 방향 변경
		field[cx][cy] = '^';
		// 이동할 수 있다면 이동 처리
	    if (cx - 1 >= 0 && field[cx - 1][cy] == '.') {
	    	field[cx-1][cy] = '^';
	    	field[cx][cy] = '.';
	    	cx -= 1;
	    }           
	}
	
	static void down() {
		field[cx][cy] = 'v';
	    if (cx + 1 < H && field[cx + 1][cy] == '.'){
	    	field[cx + 1][cy] = 'v';
	    	field[cx][cy] = '.';
	    	cx += 1;
	    }
	}
	
	static void left() {
		field[cx][cy] = '<';
	    if (cy - 1 >= 0 && field[cx][cy - 1] == '.') {
	    	field[cx][cy - 1] = '<';
	    	field[cx][cy] = '.';
	    	cy -= 1;
	    }
	}
	
	static void right() {
		field[cx][cy] = '>';
	    if (cy + 1 < W && field[cx][cy + 1] == '.') {
		    field[cx][cy + 1] = '>';
		    field[cx][cy] = '.';
		    cy += 1;
		}
	}
	
	static void shoot() {
		if (field[cx][cy] == '^') { // 위를 보고 있는 상황
			for (int bullet = cx; bullet >= 0; bullet--) {
				if (field[bullet][cy] == '*') { // 벽돌은 파괴
					field[bullet][cy] = '.';
					break;
				} else if (field[bullet][cy] == '#') // 강철은 파괴 불가
					break;
			}
		} else if (field[cx][cy] == 'v') { // 아래를 보고 있는 상황
			for (int bullet = cx; bullet < H; bullet++) {
				if (field[bullet][cy] == '*') {
					field[bullet][cy] = '.';
		            break;
				} else if (field[bullet][cy] == '#') 
					break;
			}
		} else if (field[cx][cy] == '<') { // 왼쪽을 보고 있는 상황
			for (int bullet = cy; bullet >= 0; bullet--) {
				if (field[cx][bullet] == '*') {
					field[cx][bullet] = '.';
		            break;
				} else if (field[cx][bullet] == '#')
                    break;
			}
			
		} else if (field[cx][cy] == '>') { // 오른쪽을 보고 있는 상황
			for (int bullet = cy; bullet < W; bullet++) {
				if (field[cx][bullet] == '*') {
					field[cx][bullet] = '.';
		            break;
				} else if (field[cx][bullet] == '#')
                    break;
			}
		}
	}
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			// 필드 정보 입력
			field = new char[H][];
			for (int i = 0; i < H; i++) {
				field[i] = br.readLine().trim().toCharArray();
			}
			
			// 명령 정보 입력
			N = Integer.parseInt(br.readLine());
			order = br.readLine().trim().toCharArray();
			
			// 시작 지점 찾기
			char curr;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					curr = field[i][j];
					if (curr == '^' || curr == 'v' || curr == '<' || curr == '>') {
						cx = i;
						cy = j;
					}
				}
			}
			
			// 명령 수행
			for (char c : order) {
				switch(c) {
				case 'U':
					up();
					break;
				case 'D':
					down();
					break;
				case 'L':
					left();
					break;
				case 'R':
					right();
					break;
				case 'S':
					shoot();
					break;
				}
			}
			
			// 정답 입력
			sb.append("#").append(t).append(" ");
			for (char[] line : field) {
				for (char c : line)
					sb.append(c);
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}
}