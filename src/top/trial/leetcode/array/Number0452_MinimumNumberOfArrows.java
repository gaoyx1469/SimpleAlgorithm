package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。
 * 一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
 * 给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。
 * <p>
 * 示例 1：
 * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
 * 输出：2
 * 解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球
 * 示例 2：
 * 输入：points = [[1,2],[3,4],[5,6],[7,8]]
 * 输出：4
 * 示例 3：
 * 输入：points = [[1,2],[2,3],[3,4],[4,5]]
 * 输出：2
 * 示例 4：
 * 输入：points = [[1,2]]
 * 输出：1
 * 示例 5：
 * 输入：points = [[2,3],[2,3]]
 * 输出：1
 *  
 * 提示：
 * 0 <= points.length <= 10^4
 * points[i].length == 2
 * -2^31 <= xstart < xend <= 2^31 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/11/23
 * @Version 1.0
 */
public class Number0452_MinimumNumberOfArrows {
    @Test
    public void solution() {
        int[][] points = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        int expected = 2;
        int result = getMinimumNumberOfArrows(points);
        Assert.assertEquals(expected, result);
    }

    private int getMinimumNumberOfArrows(int[][] points) {
        //判断空数组
        if (points.length == 0) {
            return 0;
        }
        //数组右边界排序
        Arrays.sort(points, Comparator.comparingInt(point -> point[1]));
        int minLeft = points[0][1];//最小右边界
        int result = 1;
        for (int[] point : points) {
            if (point[0] > minLeft) {//下一个气球左边界大于pos的右边界，则下一个气球右边界作为新pos且箭数+1
                minLeft = point[1];
                ++result;
            }
        }
        return result;
    }
}
