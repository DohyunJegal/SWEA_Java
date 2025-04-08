package d5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class d5_3308 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 수열 길이
			int size = Integer.parseInt(br.readLine());

			// 최장 증가 부분 수열을 ArrayList로 저장
			// 리스트의 마지막 값을 비교해가며 사용하기에 미리 0을 넣어줌
			ArrayList<Integer> lst = new ArrayList<>();
			lst.add(0);

			// 수열을 분리하여 하나씩 입력
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < size; i++) {
				int curr = Integer.parseInt(st.nextToken());

				if (curr > lst.get(lst.size() - 1)) {
					// curr이 리스트에 저장된 마지막 수보다 큰 경우 리스트 마지막에 저장
					lst.add(curr);
				} else {
					// binarySearch를 이용해 삽입 추천 위치를 받음
					int position = Collections.binarySearch(lst, curr);
					// binarySearch가 삽입 추천 인덱스를 '-(위치)-1' 형태로 반환하기에 변환
					if (position < 0)
						position = -(position + 1);

					// 해당 위치의 수를 curr로 변경
					lst.set(position, curr);
				}
			}

			// 앞에서 저장해준 0을 제외한 길이 저장
			sb.append("#").append(t).append(" ").append(lst.size() - 1).append("\n");
		}

		System.out.println(sb);
	}
}