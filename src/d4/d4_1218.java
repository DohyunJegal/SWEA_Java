package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class d4_1218 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int length;
		char c, pl;
		char[] input;
		boolean isCorrect;
		ArrayDeque<Character> stack = new ArrayDeque<>();

		for (int t = 1; t <= 10; t++) {
			// 스택 초기화
			isCorrect = true;
			stack.clear();

			// 길이와 괄호 문자열 저장
			length = Integer.parseInt(br.readLine());
			input = br.readLine().trim().toCharArray();

			for (int i = 0; i < length; i++) {
				c = input[i];

				if (c == '(' || c == '{' || c == '[' || c == '<') {
					// 여는 괄호를 만나면 스택에 저장
					stack.addLast(c);
				} else {
					// 닫는 괄호를 만나면...
					// 스택이 비어있다면 올바르지 않은 문자열
					if (stack.isEmpty()) {
						isCorrect = false;
						break;
					}

					pl = stack.peekLast();
					// 만약 스택 맨 위 괄호가 현재 괄호와 짝이 맞으면 pop
					if ((pl == '(' && c == ')') || (pl == '{' && c == '}') || (pl == '[' && c == ']')
							|| (pl == '<' && c == '>')) {
						stack.removeLast();
					} else {
						isCorrect = false;
						break;
					}
				}
			}

			// 만약 스택에 괄호가 남아있다면 올바르지 않은 문자열
			if (!stack.isEmpty())
				isCorrect = false;

			sb.append("#").append(t).append(" ").append(isCorrect ? 1 : 0).append("\n");
		}

		System.out.println(sb);
	}
}