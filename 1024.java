import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int start = -1;   // 시작값
        int len = -1;     // 길이

        // 길이 L부터 최대 100까지 시도
        for (int k = L; k <= 100; k++) {
            // N = k*(2a + k - 1) / 2  → a = (N - k*(k-1)/2) / k
            long temp = N - (long)k * (k - 1) / 2;
            if (temp < 0) break;              // 더 커질수록 temp는 더 작아짐

            if (temp % k == 0) {              // 시작값 a가 정수인지 확인
                long a = temp / k;
                if (a >= 0) {
                    start = (int)a;
                    len = k;
                    break;                    // 가장 짧은 k부터 찾으므로 첫 해답에서 종료
                }
            }
        }

        if (start == -1) {
            System.out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) {
                sb.append(start + i);
                if (i + 1 < len) sb.append(' ');
            }
            System.out.println(sb.toString());
        }
    }
}
