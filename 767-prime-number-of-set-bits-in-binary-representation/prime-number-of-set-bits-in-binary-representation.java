class Solution {
    public int countPrimeSetBits(int left, int right) {
        int count = 0;

        for (int n = left; n <= right; n++) {
            int ones = 0;
            int x = n;

            while (x > 0) {
                ones += x % 2;
                x /= 2;
            }

            if (isPrime(ones))
                count++;
        }

        return count;
    }

    static boolean isPrime(int n) {
        if (n < 2) return false;

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }

        return true;
    }
}
