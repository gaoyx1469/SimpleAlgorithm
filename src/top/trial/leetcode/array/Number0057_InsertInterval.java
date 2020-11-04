package top.trial.leetcode.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * <p>
 * 示例 1：
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * 示例 2：
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 *  
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-interval
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/11/4
 * @Version 1.0
 */
public class Number0057_InsertInterval {
    @Test
    public void solution() {
        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = {4, 8};
        System.out.println(Arrays.deepToString(insertOne(intervals, newInterval)));
    }

    private int[][] insertOne(int[][] intervals, int[] newInterval) {
        int start = newInterval[0];
        int end = newInterval[1];
        int len = intervals.length;
        if (len == 0) {
            int[][] result = new int[1][];
            result[0] = newInterval;
            return result;
        }
        int firstIndex = 0;//找到所处位置
        while (firstIndex < len && intervals[firstIndex][1] < start)
            firstIndex++;
        int endIndex = len - 1;
        while (endIndex >= 0 && intervals[endIndex][0] > end)
            endIndex--;
        int newLen = len + firstIndex - endIndex;
        int[][] result = new int[newLen][2];
        for (int i = 0; i < firstIndex; ++i) {//拷贝前段
            result[i][0] = intervals[i][0];
            result[i][1] = intervals[i][1];
        }
        for (int i = 0; i < len - endIndex - 1; ++i) {//拷贝后段
            result[newLen - 1 - i][0] = intervals[len - 1 - i][0];
            result[newLen - 1 - i][1] = intervals[len - 1 - i][1];
        }
        result[firstIndex][0] = firstIndex >= len ? newInterval[0] : Math.min(intervals[firstIndex][0], newInterval[0]);
        result[firstIndex][1] = endIndex < 0 ? newInterval[1] : Math.max(intervals[endIndex][1], newInterval[1]);
        return result;
    }
}
