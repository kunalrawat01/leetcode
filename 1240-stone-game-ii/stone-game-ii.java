class Solution {
    int[][] dp;
    int[] suffix;

    public int stoneGameII(int[] piles) {
        int n = piles.length;

        dp = new int[n][n + 1];
        suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        return solve(piles, 0, 1);
    }

    int solve(int[] piles, int i, int M) {
        if (i >= piles.length) {
            return 0;
        }

        if (dp[i][M] != 0) {
            return dp[i][M];
        }

        int best = 0;

        for (int x = 1; x <= 2 * M && i + x <= piles.length; x++) {
            best = Math.max(
                best,
                suffix[i] - solve(piles, i + x, Math.max(M, x))
            );
        }

        return dp[i][M] = best;
    }
}