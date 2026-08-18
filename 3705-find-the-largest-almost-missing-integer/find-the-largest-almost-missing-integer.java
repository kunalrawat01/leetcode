class Solution {
    public int largestInteger(int[] nums, int k) {
        int ans = -1;

        for (int i = 0; i < nums.length; i++) {
            int count = 0;

            for (int j = 0; j <= nums.length - k; j++) {
                boolean found = false;

                for (int x = j; x < j + k; x++) {
                    if (nums[x] == nums[i]) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    count++;
                }
            }

            if (count == 1) {
                ans = Math.max(ans, nums[i]);
            }
        }

        return ans;
    }
}