package top.trial.leetcode.math;

import org.junit.Test;

import java.util.Arrays;

/**
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 * 注意：
 * 总人数少于1100人。
 * <p>
 * 示例
 * 输入:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * 输出:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/11/16
 * @Version 1.0
 */
public class Number0406_QueueRestructionByHeight {
    @Test
    public void solution() {
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        System.out.println(Arrays.deepToString(reconstructionQueue(people)));
    }

    private int[][] reconstructionQueue(int[][] people) {
        int len = people.length;
        int[][] result = new int[len][];

        //给people排序，按第一位升序第二维降序排列，然后根据要求插入即可

        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0])
                return b[1] - a[1];
            else
                return a[0] - b[0];
        });
        for (int[] curr : people) {
            int index = curr[1]+1;
            for (int i = 0; i < len; ++i) {
                if (result[i] == null) {
                    index--;
                    if (index == 0) {
                        result[i] = curr;
                        break;
                    }
                }
            }
        }

        return result;
    }
}
