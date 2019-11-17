给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。

假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们
就认为你掌握了这个单词。

注意：每次拼写时，chars 中的每个字母都只能用一次。

返回词汇表 words 中你掌握的所有单词的 长度之和。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-words-that-can-be-formed-by-characters
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int countCharacters(String[] words, String chars) {
        int[] arr=new int[26];
        char[] c1=chars.toCharArray();
        int num=0;
        for(char c:c1){
            arr[(int)c-'a']++;
        }
        for(String str:words){
            char[] s=str.toCharArray();
            int len=0;
            int[] tmp=arr.clone();
            for(char c:s){
                if(tmp[c-'a']-->0){
                    len++;
                }
            }
            if(len==s.length){
                num+=len;
            }
        }
        return num;
    }
}

学校在拍年度纪念照时，一般要求学生按照 非递减 的高度顺序排列。

请你返回至少有多少个学生没有站在正确位置数量。该人数指的是：能让所有学生以 非递减 
高度排列的必要移动人数。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/height-checker
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int heightChecker(int[] heights) {
        int[] arr=heights.clone();
        Arrays.sort(heights);
        int num=0;
        for(int i=0;i<heights.length;i++){
            if(arr[i]!=heights[i]){
                num++;
            }
        }
        return num;
    }
}

给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的
元素相邻，并按照红色、白色、蓝色顺序排列。

此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

注意:
不能使用代码库中的排序函数来解决这道题。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sort-colors
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public void sortColors(int[] nums) {
        int p0=0;
        int p2=nums.length-1;
        int cur=0;
        while(cur<=p2){
            if(nums[cur]==0){
                int tmp=nums[p0];
                nums[p0++]=nums[cur];
                nums[cur++]=tmp;
            }else if(nums[cur]==2){
                int tmp=nums[p2];
                nums[p2--]=nums[cur];
                nums[cur]=tmp;
            }else{
                cur++;
            }
        }
    }
}

在《英雄联盟》的世界中，有一个叫 “提莫” 的英雄，他的攻击可以让敌方英雄艾希（编者注：
寒冰射手）进入中毒状态。现在，给出提莫对艾希的攻击时间序列和提莫攻击的中毒持续时间，
你需要输出艾希的中毒状态总时长。

你可以认为提莫在给定的时间点进行攻击，并立即使艾希处于中毒状态。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/teemo-attacking
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if(timeSeries.length==0){
            return 0;
        }
        int sum=duration;
        for(int i=0;i<timeSeries.length-1;i++){
           if(timeSeries[i]+duration<=timeSeries[i+1]){
               sum+=duration;
           }else{
               sum+=timeSeries[i+1]-timeSeries[i];
           }
        }
        return sum;
    }
}

给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
class Solution {
    public int subarraySum(int[] nums, int k) {
        int num=0;
        for(int i=0;i<nums.length-1;i++){
            int sum=0;
            for(int j=i;j<nums.length;j++){
                sum+=nums[j];
                if(sum==k){
                    num++;
                }
            }
        }
        return nums[nums.length-1]==k?num+1:num;
    }
}

给定一个正整数数组 nums。

找出该数组内乘积小于 k 的连续的子数组的个数。
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int num=0;
        int l=0;
        int sum=1;
        for(int r=0;r<nums.length;r++){
            sum*=nums[r];
            while(l<=r&&sum>=k){
                sum/=nums[l++];
            }
            num+=r-l+1;
        }
        return num;
    }
}