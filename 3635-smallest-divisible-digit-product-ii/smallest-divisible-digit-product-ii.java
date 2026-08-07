class Solution {

    // factors[d] = number of factors of 2,3,5,7 in digit d
    // order: 2, 3, 5, 7
    static final int[][] FACTORS = {
        {0, 0, 0, 0}, // 0
        {0, 0, 0, 0}, // 1
        {1, 0, 0, 0}, // 2
        {0, 1, 0, 0}, // 3
        {2, 0, 0, 0}, // 4
        {0, 0, 1, 0}, // 5
        {1, 1, 0, 0}, // 6
        {0, 0, 0, 1}, // 7
        {3, 0, 0, 0}, // 8
        {0, 2, 0, 0}  // 9
    };

    public String smallestNumber(String num, long t) {

        // Required factors of t
        int[] need = factorize(t);

        // t has a prime factor other than 2,3,5,7
        if (need == null) {
            return "-1";
        }

        int n = num.length();

        /*
         * Minimum number of digits required to represent
         * the required factors.
         */
        int[] minDigits = makeDigits(need);
        int requiredLength = countDigits(minDigits);

        // If even the minimum required digits exceed n,
        // the answer must have n+1 or more digits.
        if (requiredLength > n) {
            return build(minDigits, n + 1);
        }

        /*
         * Count prime factors in the whole num.
         */
        int[] total = new int[4];

        int firstZero = n;

        for (int i = 0; i < n; i++) {
            int d = num.charAt(i) - '0';

            if (d == 0 && firstZero == n) {
                firstZero = i;
            }

            add(total, FACTORS[d]);
        }

        /*
         * If num has no zero and already satisfies t,
         * num itself is the answer.
         */
        if (firstZero == n && enough(total, need)) {
            return num;
        }

        /*
         * prefix = factors of digits before i.
         *
         * We start with all factors and remove digits
         * from right to left.
         */
        int[] prefix = total.clone();

        for (int i = n - 1; i >= 0; i--) {

            int d = num.charAt(i) - '0';

            // Remove current digit from prefix.
            remove(prefix, FACTORS[d]);

            /*
             * If i is after the first zero, the unchanged prefix
             * would already contain a zero, so this position cannot
             * produce a zero-free answer.
             */
            if (i > firstZero) {
                continue;
            }

            /*
             * Try making this digit slightly larger.
             */
            for (int bigger = d + 1; bigger <= 9; bigger++) {

                int[] remaining = need.clone();

                // Factors supplied by prefix.
                subtract(remaining, prefix);

                // Factors supplied by the new digit.
                subtract(remaining, FACTORS[bigger]);

                /*
                 * Convert remaining prime factors into the minimum
                 * number of digits required in the suffix.
                 */
                int[] suffixDigits = makeDigits(remaining);

                int suffixLength = countDigits(suffixDigits);
                int available = n - i - 1;

                if (suffixLength <= available) {

                    StringBuilder ans = new StringBuilder(n);

                    // Original prefix
                    ans.append(num, 0, i);

                    // Bigger digit
                    ans.append((char) ('0' + bigger));

                    // Remaining positions can be filled with 1.
                    for (int j = suffixLength; j < available; j++) {
                        ans.append('1');
                    }

                    // Put required digits in increasing order.
                    ans.append(buildDigits(suffixDigits));

                    return ans.toString();
                }
            }
        }

        /*
         * No answer with the same length.
         *
         * Use one extra digit. The smallest possible number
         * starts with enough 1s followed by required digits.
         */
        return build(minDigits, n + 1);
    }

    // Factor t into 2,3,5,7.
    static int[] factorize(long t) {

        int[] count = new int[4];
        int[] primes = {2, 3, 5, 7};

        for (int i = 0; i < 4; i++) {

            while (t % primes[i] == 0) {
                count[i]++;
                t /= primes[i];
            }
        }

        if (t != 1) {
            return null;
        }

        return count;
    }

    /*
     * Convert prime requirements into the minimum number
     * of digits 2..9.
     */
    static int[] makeDigits(int[] count) {

        int c2 = count[0];
        int c3 = count[1];
        int c5 = count[2];
        int c7 = count[3];

        int[] result = new int[8];

        // 2^3 = 8
        result[6] = c2 / 3;
        c2 %= 3;

        // 3^2 = 9
        result[7] = c3 / 2;
        c3 %= 2;

        // 2^2 = 4
        result[2] = c2 / 2;
        c2 %= 2;

        /*
         * If one 2 and one 3 remain,
         * combine them into 6.
         */
        if (c2 == 1 && c3 == 1) {
            result[4] = 1; // digit 6
            c2 = 0;
            c3 = 0;
        }

        /*
         * If one 3 and one 4 remain,
         * 3 × 4 = 12 = 2 × 6.
         */
        if (c3 == 1 && result[2] == 1) {
            result[2] = 0;
            result[4] = 1;
            c2 = 1;
            c3 = 0;
        }

        result[0] = c2; // digit 2
        result[1] = c3; // digit 3
        result[3] = c5; // digit 5
        result[5] = c7; // digit 7

        return result;
    }

    static int countDigits(int[] digits) {
        int count = 0;

        for (int x : digits) {
            count += x;
        }

        return count;
    }

    static String buildDigits(int[] digits) {

        StringBuilder sb = new StringBuilder();

        for (int d = 2; d <= 9; d++) {
            int count = digits[d - 2];

            while (count-- > 0) {
                sb.append((char) ('0' + d));
            }
        }

        return sb.toString();
    }

    static String build(int[] digits, int length) {

        int required = countDigits(digits);

        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length - required; i++) {
            sb.append('1');
        }

        sb.append(buildDigits(digits));

        return sb.toString();
    }

    static void add(int[] a, int[] b) {
        for (int i = 0; i < 4; i++) {
            a[i] += b[i];
        }
    }

    static void remove(int[] a, int[] b) {
        for (int i = 0; i < 4; i++) {
            a[i] -= b[i];
        }
    }

    static void subtract(int[] a, int[] b) {
        for (int i = 0; i < 4; i++) {
            a[i] = Math.max(0, a[i] - b[i]);
        }
    }

    static boolean enough(int[] have, int[] need) {
        for (int i = 0; i < 4; i++) {
            if (have[i] < need[i]) {
                return false;
            }
        }

        return true;
    }
}