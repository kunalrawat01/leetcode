import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }

        Arrays.sort(a, (x, y) -> {
            if (x[1] != y[1])
                return Integer.compare(x[1], y[1]);
            return Integer.compare(x[0], y[0]);
        });


        int[] previous = new int[n];

        for (int i = 0; i < n; i++) {
            int l = 0, r = i - 1;
            int pos = -1;

            while (l <= r) {
                int mid = l + (r - l) / 2;

                if (a[mid][1] < a[i][0]) {
                    pos = mid;
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }

            previous[i] = pos + 1;
        }

        long[][] dp = new long[n + 1][5];
        List<Integer>[][] best = new ArrayList[n + 1][5];

        for (int i = 0; i <= n; i++)
            for (int k = 0; k <= 4; k++)
                best[i][k] = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            int idx = i - 1;

            for (int k = 0; k <= 4; k++) {
                dp[i][k] = dp[i - 1][k];
                best[i][k] = new ArrayList<>(best[i - 1][k]);
            }

            for (int k = 1; k <= 4; k++) {
                long value = dp[previous[idx]][k - 1] + a[idx][2];

                List<Integer> take =
                    new ArrayList<>(best[previous[idx]][k - 1]);

                take.add(a[idx][3]);
                Collections.sort(take);

                if (value > dp[i][k] ||
                    (value == dp[i][k] &&
                     compare(take, best[i][k]) < 0)) {

                    dp[i][k] = value;
                    best[i][k] = take;
                }
            }
        }

        List<Integer> answer = new ArrayList<>();

        for (int k = 1; k <= 4; k++) {
            if (dp[n][k] > dp[n][answer.size()] ||
                (dp[n][k] == dp[n][answer.size()] &&
                 compare(best[n][k], answer) < 0)) {

                answer = best[n][k];
            }
        }

        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++)
            result[i] = answer.get(i);

        return result;
    }

    private int compare(List<Integer> a, List<Integer> b) {
        for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
            if (!a.get(i).equals(b.get(i)))
                return Integer.compare(a.get(i), b.get(i));
        }

        return Integer.compare(a.size(), b.size());
    }
}