class Solution {
    private static final int MOD = 1_000_000_007;
    public int distinctSubseqII(String s) {
        int[] dp = new int[26];
        int ans = 0;
        for (char ch : s.toCharArray()) {
            int idx = ch - 'a';
            int add = (ans - dp[idx] + 1 + MOD) % MOD;
            ans = (ans + add) % MOD;
            dp[idx] = (dp[idx] + add) % MOD;
        }
        return ans;
    }
}
