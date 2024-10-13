class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        // nums = [1,2,3,4], targer = 6
        // nums : {1, 0}, {2, 1}, {3, 2}, {4, 3}
        for(int i = 0; i < nums.length; i++) {
        Integer remainder = map.get(target - nums[i]);
        
        // i = 0, remainder = 5, map.get(5);
        // i = 1, remainder = 4, map.get(4);
        // i = 2, remainder = 3, map.get(3);
        // i = 3, remainder = 2, map.get(2);
        if(remainder != null && remainder != i) {
            return new int[]{i, remainder};
        }
    }
    return null;
    }
}