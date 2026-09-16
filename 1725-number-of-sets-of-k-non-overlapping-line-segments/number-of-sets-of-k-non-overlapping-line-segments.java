class Solution {
    private static final int MOD = (int)1e9 + 7;

    public int numberOfSets(int n, int k) {
        int size = n + k;
        long[] fact = new long[size + 1];
        long[] invFact = new long[size + 1];

        fact[0] = 1;
        for (int i = 1; i <= size; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        invFact[size] = pow(fact[size], MOD - 2);
        for (int i = size; i > 0; i--) {
            invFact[i - 1] = invFact[i] * i % MOD;
        }

        return (int) nCr(n + k - 1, 2 * k, fact, invFact);
    }

    private long nCr(int a, int b, long[] fact, long[] invFact) {
        if (b < 0 || b > a) return 0;
        return fact[a] * invFact[b] % MOD * invFact[a - b] % MOD;
    }

    private long pow(long base, int exp) {
        long res = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) res = res * base % MOD;
            base = base * base % MOD;
            exp >>= 1;
        }
        return res;
    }
}
