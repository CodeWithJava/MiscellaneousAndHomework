/*
Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum is greater than or equal to s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,

the subarray [4,3] has the minimal length under the problem constraint
*/
public class Solution
{
    public int minSubArrayLen(int s, int[] nums)
    {
        int i = 0;
        int j = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        while(i < nums.length && j < nums.length)
        {
            while(sum < s && j < nums.length)
                sum += nums[j++];
            while(sum >= s && i <= j)
            {
                min = Math.min(min,j - i);
                sum -= nums[i++];
            }
        }
        return min == Integer.MAX_VALUE ? 0:min;
    }
}