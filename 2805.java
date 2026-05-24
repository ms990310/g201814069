import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        long[] tree = new long[N];

        st = new StringTokenizer(br.readLine());

        long max = 0;

        for (int i = 0; i < N; i++) {
            tree[i] = Long.parseLong(st.nextToken());
            max = Math.max(max, tree[i]);
        }

        long left = 0;
        long right = max;
        long answer = 0;

        // 이분 탐색
        while (left <= right) {
            long mid = (left + right) / 2;

            long sum = 0;

            // mid 높이로 잘랐을 때 얻는 나무 길이 계산
            for (int i = 0; i < N; i++) {
                if (tree[i] > mid) {
                    sum += (tree[i] - mid);
                }
            }

            // 필요한 나무 길이 이상이면 높이를 더 높여본다
            if (sum >= M) {
                answer = mid;
                left = mid + 1;
            }
            // 부족하면 높이를 낮춘다
            else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }
}
