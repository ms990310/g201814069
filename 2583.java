import java.io.*;
import java.util.*;

public class Main {
    static int M, N, K;
    static int[][] map;
    static boolean[][] visited;
    
    // 상, 하, 좌, 우 이동을 위한 배열
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 세로 크기 (행)
        N = Integer.parseInt(st.nextToken()); // 가로 크기 (열)
        K = Integer.parseInt(st.nextToken()); // 직사각형 개수

        map = new int[M][N];
        visited = new boolean[M][N];

        // K개의 직사각형 영역을 1로 채우기
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            // 직사각형의 내부 영역을 배열에서 1로 표시
            // y좌표가 행(M), x좌표가 열(N)에 대응됩니다.
            for (int y = y1; y < y2; y++) {
                for (int x = x1; x < x2; x++) {
                    map[y][x] = 1;
                }
            }
        }

        ArrayList<Integer> areas = new ArrayList<>();

        // 전체 맵을 돌면서 방문하지 않은 빈 영역(0)을 찾으면 BFS 시작
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    areas.add(bfs(i, j));
                }
            }
        }

        // 영역의 넓이를 오름차순으로 정렬
        Collections.sort(areas);

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        sb.append(areas.size()).append("\n");
        for (int area : areas) {
            sb.append(area).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    // BFS를 통해 연결된 빈 공간의 넓이를 계산하는 메서드
    static int bfs(int startY, int startX) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startY, startX});
        visited[startY][startX] = true;
        
        int count = 1; // 현재 영역의 넓이 (시작점 포함하므로 1부터 시작)

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cy = current[0];
            int cx = current[1];

            // 상하좌우 네 방향 확인
            for (int d = 0; d < 4; d++) {
                int ny = cy + dy[d];
                int nx = cx + dx[d];

                // 배열 범위를 벗어나지 않는지 체크
                if (ny >= 0 && ny < M && nx >= 0 && nx < N) {
                    // 직사각형이 아니고(0), 아직 방문하지 않았다면
                    if (map[ny][nx] == 0 && !visited[ny][nx]) {
                        visited[ny][nx] = true;
                        queue.offer(new int[]{ny, nx});
                        count++; // 넓이 증가
                    }
                }
            }
        }
        return count;
    }
}
