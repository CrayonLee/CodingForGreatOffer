package class27;

import java.util.Arrays;

/**
 * @author lizihao
 * @date 2021/9/25 20:50
 *
 *      企鹅厂活动发文化衫，文化衫有很多种，企鹅们都穿文化衫
 * 采访中，企鹅会说还有多少企鹅跟他穿一种文化衫
 * 有些企鹅没被采访到
 * 将这些回答放在answers数组里，返回活动中企鹅的最少数量
 */
public class Code02_MinPeople {
    public static int numRabbits(int[] arr) {
        if (arr == null ||arr.length==0) {
            return 0;
        }
        Arrays.sort(arr);
        int x=arr[0];
        int c=1;
        int ans=0;
        for (int i = 1; i < arr.length; i++) {
            if(x==arr[i]){
                c++;
            }else{
                ans = ans + (int) (Math.ceil((x / (c + 1))))*(c+1);
                x=arr[i];
                c=1;
            }
        }
        return ans+((c+x)/(c+1))/(c+1);
    }
}
