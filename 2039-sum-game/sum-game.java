class Solution {
    public boolean sumGame(String num) {
        int i = 0;
        int j = num.length() - 1;
        int leftSum = 0;
        int rightSum = 0;
        int leftQ = 0;
        int rightQ = 0;
        while (i < j) {
            if (num.charAt(i) == '?') {
                leftQ++;
            } else {
                leftSum += num.charAt(i) - '0';
            }
            if (num.charAt(j) == '?') {
                rightQ++;
            } else {
                rightSum += num.charAt(j) - '0';
            }
            i++;
            j--;
        }

        if (leftQ % 2 != rightQ % 2) {
            return true;
        }

        int diff = leftSum - rightSum;

        return diff != (rightQ - leftQ) / 2 * 9;
    }
}