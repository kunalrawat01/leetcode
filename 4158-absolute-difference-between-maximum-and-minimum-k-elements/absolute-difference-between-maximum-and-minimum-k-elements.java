import java.util.Arrays;

class Solution {
    public int absDifference(int[] nums, int k) {
        Arrays.sort(nums);

        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < k; i++) {
            ans += nums[n - 1 - i] - nums[i];
        }

        return ans;
    }
}