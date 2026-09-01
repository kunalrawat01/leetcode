class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length, n = classroom[0].length();
        int sr = 0, sc = 0, cnt = 0;
        int[][] id = new int[m][n];

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);
                if (ch == 'S') { sr = i; sc = j; }
                if (ch == 'L') id[i][j] = cnt++;
            }

        if (cnt == 0) return 0;

        int full = (1 << cnt) - 1;
        boolean[][][][] vis = new boolean[m][n][energy + 1][1 << cnt];
        java.util.Queue<int[]> q = new java.util.LinkedList<>();

        q.offer(new int[]{sr, sc, energy, 0});
        vis[sr][sc][energy][0] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int moves = 0;

        while (!q.isEmpty()) {
            for (int size = q.size(); size > 0; size--) {
                int[] a = q.poll();
                int r = a[0], c = a[1], e = a[2], mask = a[3];

                if (mask == full) return moves;
                if (e == 0) continue;

                for (int k = 0; k < 4; k++) {
                    int nr = r + dr[k], nc = c + dc[k];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n ||
                        classroom[nr].charAt(nc) == 'X') continue;

                    int ne = e - 1;
                    if (classroom[nr].charAt(nc) == 'R') ne = energy;

                    int nm = mask;
                    if (classroom[nr].charAt(nc) == 'L')
                        nm |= 1 << id[nr][nc];

                    if (!vis[nr][nc][ne][nm]) {
                        vis[nr][nc][ne][nm] = true;
                        q.offer(new int[]{nr, nc, ne, nm});
                    }
                }
            }
            moves++;
        }

        return -1;
    }
}