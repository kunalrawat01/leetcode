class Solution {
    public String removeDuplicates(String s) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (ans.length() > 0 &&
                ans.charAt(ans.length() - 1) == s.charAt(i)) {
                ans.deleteCharAt(ans.length() - 1);
            } else {
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }
}