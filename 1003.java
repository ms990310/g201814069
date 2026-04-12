import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        // 최대 N = 40
        int[][] dp = new int[41][2];

        // 초기값
        dp[0][0] = 1; // 0 출력 횟수
        dp[0][1] = 0; // 1 출력 횟수

        dp[1][0] = 0;
        dp[1][1] = 1;

        // DP 채우기
        for (int i = 2; i <= 40; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
            dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
        }

        // 테스트 케이스 처리
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            System.out.println(dp[n][0] + " " + dp[n][1]);
        }

        sc.close();
    }
}
