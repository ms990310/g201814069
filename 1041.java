import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long N = sc.nextLong();
        long[] dice = new long[6];

        for (int i = 0; i < 6; i++) {
            dice[i] = sc.nextLong();
        }

        // N == 1 예외 처리
        if (N == 1) {
            long sum = 0;
            long max = 0;

            for (int i = 0; i < 6; i++) {
                sum += dice[i];
                max = Math.max(max, dice[i]);
            }

            System.out.println(sum - max);
            return;
        }

        // 1면 최소
        long min1 = Arrays.stream(dice).min().getAsLong();

        // 마주보는 면: 0-5, 1-4, 2-3
        int[][] opposite = {
                {0,5}, {1,4}, {2,3}
        };

        // 2면 최소
        long min2 = Long.MAX_VALUE;
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                // 마주보는 면 제외
                if ((i == 0 && j == 5) || (i == 5 && j == 0) ||
                    (i == 1 && j == 4) || (i == 4 && j == 1) ||
                    (i == 2 && j == 3) || (i == 3 && j == 2)) continue;

                min2 = Math.min(min2, dice[i] + dice[j]);
            }
        }

        // 3면 최소 (8개 경우 중 실제 필요한 건 4개지만 최소만 구하면 됨)
        long min3 = Long.MAX_VALUE;
        int[][] three = {
                {0,1,2}, {0,2,4}, {0,3,1}, {0,4,3},
                {5,1,2}, {5,2,4}, {5,3,1}, {5,4,3}
        };

        for (int[] t : three) {
            long sum = dice[t[0]] + dice[t[1]] + dice[t[2]];
            min3 = Math.min(min3, sum);
        }

        long count3 = 4;
        long count2 = 8 * N - 12;
        long count1 = (N - 2) * (5 * N - 6);

        long result = count3 * min3 + count2 * min2 + count1 * min1;

        System.out.println(result);
    }
}
