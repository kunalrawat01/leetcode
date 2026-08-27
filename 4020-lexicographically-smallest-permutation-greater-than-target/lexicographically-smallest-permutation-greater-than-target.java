class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] count = new int[26];
        for (char c : s.toCharArray())
            count[c - 'a']++;
        char[] ans = target.toCharArray();
        for (int i = 0; i < target.length(); i++) {
            int x = target.charAt(i) - 'a';
            if (count[x] > 0) {
                count[x]--;
            } else {
                for (int j = i; j >= 0; j--) {
                    if (j < i)
                        count[ans[j] - 'a']++;
                    int cur = target.charAt(j) - 'a';
                    for (int k = cur + 1; k < 26; k++) {
                        if (count[k] > 0) {
                            ans[j] = (char) ('a' + k);
                            count[k]--;
                            int p = j + 1;
                            for (int c = 0; c < 26; c++) {
                                while (count[c] > 0) {
                                    ans[p++] = (char) ('a' + c);
                                    count[c]--;
                                }
                            }
                            return new String(ans);
                        }
                    }
                }
                return "";
            }
        }
        for (int i = target.length() - 1; i >= 0; i--) {
            count[ans[i] - 'a']++;
            int cur = ans[i] - 'a';
            for (int k = cur + 1; k < 26; k++) {
                if (count[k] > 0) {
                    ans[i] = (char) ('a' + k);
                    count[k]--;
                    int p = i + 1;
                    for (int c = 0; c < 26; c++) {
                        while (count[c] > 0) {
                            ans[p++] = (char) ('a' + c);
                            count[c]--;
                        }
                    }
                    return new String(ans);
                }
            }
        }
        return "";
    }
}