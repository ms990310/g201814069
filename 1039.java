import java.util.*;

public class Main {
    static int N, K;
    static String answer = "-1";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        bfs(String.valueOf(N));
        System.out.println(answer);
    }

    static void bfs(String start) {
        Queue<String> q = new LinkedList<>();
        q.offer(start);

        for (int depth = 0; depth < K; depth++) {
            Set<String> visited = new HashSet<>();
            int size = q.size();

            for (int s = 0; s < size; s++) {
                String cur = q.poll();
                char[] arr = cur.toCharArray();

                for (int i = 0; i < arr.length - 1; i++) {
                    for (int j = i + 1; j < arr.length; j++) {
                        // swap
                        char temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;

                        // 앞자리가 0이면 무효
                        if (arr[0] != '0') {
                            String next = new String(arr);
                            if (!visited.contains(next)) {
                                visited.add(next);
                                q.offer(next);
                            }
                        }

                        // 다시 원상복구
                        temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                    }
                }
            }

            // 더 이상 진행 불가능
            if (q.isEmpty()) return;
        }

        // K번 교환 후 최대값 찾기
        int max = -1;
        for (String s : q) {
            max = Math.max(max, Integer.parseInt(s));
        }

        answer = String.valueOf(max);
    }
}
