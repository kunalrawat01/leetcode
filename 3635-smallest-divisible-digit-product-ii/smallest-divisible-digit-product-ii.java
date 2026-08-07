class Solution {
    int[][] f = {
        {0,0,0,0},{0,0,0,0},{1,0,0,0},{0,1,0,0},
        {2,0,0,0},{0,0,1,0},{1,1,0,0},{0,0,0,1},
        {3,0,0,0},{0,2,0,0}
    };

    public String smallestNumber(String num, long t) {
        int[] need = fact(t);
        if (need == null) return "-1";

        int n = num.length();
        int[] base = make(need);

        if (cnt(base) > n)
            return build(base);

        int[] all = new int[4];
        int zero = n;

        for (int i = 0; i < n; i++) {
            int d = num.charAt(i) - '0';
            if (d == 0 && zero == n) zero = i;
            add(all, f[d]);
        }

        if (zero == n && ok(all, need))
            return num;

        int[] pre = all.clone();

        for (int i = n - 1; i >= 0; i--) {
            int d = num.charAt(i) - '0';
            sub(pre, f[d]);

            if (i > zero) continue;

            for (int x = d + 1; x <= 9; x++) {
                int[] r = need.clone();

                for (int j = 0; j < 4; j++)
                    r[j] = Math.max(0, r[j] - pre[j] - f[x][j]);

                int[] z = make(r);
                int left = n - i - 1;

                if (cnt(z) <= left) {
                    StringBuilder s = new StringBuilder();
                    s.append(num, 0, i).append(x);

                    for (int j = cnt(z); j < left; j++)
                        s.append('1');

                    s.append(build(z));
                    return s.toString();
                }
            }
        }

        return "1".repeat(n + 1 - cnt(base)) + build(base);
    }

    int[] fact(long t) {
        int[] r = new int[4];
        int[] p = {2,3,5,7};

        for (int i = 0; i < 4; i++)
            while (t % p[i] == 0) {
                r[i]++;
                t /= p[i];
            }

        return t == 1 ? r : null;
    }

    int[] make(int[] c) {
        int a=c[0], b=c[1], e=c[2], g=c[3];
        int[] r = new int[8];

        r[6] = a / 3; a %= 3;
        r[7] = b / 2; b %= 2;
        r[2] = a / 2; a %= 2;

        if (a == 1 && b == 1) {
            r[4] = 1;
            a = b = 0;
        }

        if (b == 1 && r[2] == 1) {
            r[2] = 0;
            r[4] = 1;
            a = 1;
            b = 0;
        }

        r[0] = a;
        r[1] = b;
        r[3] = e;
        r[5] = g;

        return r;
    }

    String build(int[] r) {
        StringBuilder s = new StringBuilder();

        for (int d = 2; d <= 9; d++)
            for (int i = 0; i < r[d-2]; i++)
                s.append(d);

        return s.toString();
    }

    int cnt(int[] r) {
        int s = 0;
        for (int x : r) s += x;
        return s;
    }

    boolean ok(int[] a, int[] b) {
        for (int i = 0; i < 4; i++)
            if (a[i] < b[i]) return false;
        return true;
    }

    void add(int[] a, int[] b) {
        for (int i = 0; i < 4; i++)
            a[i] += b[i];
    }

    void sub(int[] a, int[] b) {
        for (int i = 0; i < 4; i++)
            a[i] -= b[i];
    }
}