class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int[] freq = new int[26];

        for (char c : s.toCharArray())
            freq[c - 'a']++;

        char[] ans = target.toCharArray();

        int i = 0;

        // Match target as much as possible
        while (i < target.length()) {

            int x = target.charAt(i) - 'a';

            // Use the same character
            if (freq[x] > 0) {
                freq[x]--;
                i++;
                continue;
            }

            // Cannot use target[i].
            // Try the smallest character greater than target[i].
            for (int c = x + 1; c < 26; c++) {

                if (freq[c] > 0) {
                    ans[i] = (char) ('a' + c);
                    freq[c]--;

                    return build(ans, i + 1, freq);
                }
            }

            // Cannot make it greater here.
            break;
        }

        // Backtrack
        for (int j = i - 1; j >= 0; j--) {

            // Return the character used at j
            freq[target.charAt(j) - 'a']++;

            int x = target.charAt(j) - 'a';

            // Find the smallest character greater than target[j]
            for (int c = x + 1; c < 26; c++) {

                if (freq[c] > 0) {
                    ans[j] = (char) ('a' + c);
                    freq[c]--;

                    return build(ans, j + 1, freq);
                }
            }
        }

        return "";
    }

    private String build(char[] ans, int start, int[] freq) {

        int p = start;

        for (int c = 0; c < 26; c++) {
            while (freq[c] > 0) {
                ans[p++] = (char) ('a' + c);
                freq[c]--;
            }
        }

        return new String(ans);
    }
}