package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 我们得到了一副藏宝图，藏宝图显示，在一个迷宫中存在着未被世人发现的宝藏。
 * <p>
 * 迷宫是一个二维矩阵，用一个字符串数组表示。它标识了唯一的入口（用 'S' 表示），和唯一的宝藏地点（用 'T' 表示）。但是，宝藏被一些隐蔽的机关保护了起来。在地图上有若干个机关点（用 'M' 表示），只有所有机关均被触发，才可以拿到宝藏。
 * <p>
 * 要保持机关的触发，需要把一个重石放在上面。迷宫中有若干个石堆（用 'O' 表示），每个石堆都有无限个足够触发机关的重石。但是由于石头太重，我们一次只能搬一个石头到指定地点。
 * <p>
 * 迷宫中同样有一些墙壁（用 '#' 表示），我们不能走入墙壁。剩余的都是可随意通行的点（用 '.' 表示）。石堆、机关、起点和终点（无论是否能拿到宝藏）也是可以通行的。
 * <p>
 * 我们每步可以选择向上/向下/向左/向右移动一格，并且不能移出迷宫。搬起石头和放下石头不算步数。那么，从起点开始，我们最少需要多少步才能最后拿到宝藏呢？如果无法拿到宝藏，返回 -1 。
 * <p>
 * 示例 1：
 * <p>
 * 输入： ["S#O", "M..", "M.T"]
 * <p>
 * 输出：16
 * <p>
 * 解释：最优路线为： S->O, cost = 4, 去搬石头 O->第二行的M, cost = 3, M机关触发 第二行的M->O, cost = 3, 我们需要继续回去 O 搬石头。 O->第三行的M, cost = 4, 此时所有机关均触发 第三行的M->T, cost = 2，去T点拿宝藏。 总步数为16。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xun-bao
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/7/29
 * @Version 1.0
 */
public class NumberL013_XunBao {
    @Test
    public void solution() {
        String[] maze = {"S#O", "M..", "M.T"};
        int expected = 16;
        //int result = xunBaoOne(maze);
        int result = xunBaoTwo(maze);
        Assert.assertEquals(expected, result);
    }


    /**
     * 参考官方题解思路
     *
     * @param maze String[]
     * @return int
     */
    private int xunBaoTwo(String[] maze) {

        // 没有机关的情况，直接BFS求得S到T的最短距离

        // 0、遍历，得到S、T的坐标和O、M的坐标集合
        // 1、BFS求得所有S到所有其它点的最短距离，二维数组存储A
        // 2、若无机关，直接返回S到T的最短距离
        // 3、若有机关，继续构造一个二维数组B，用来存储机关M到其它机关M‘和S、T的最短距离，先填充值-1
        // 4、构造一个三维数组C，存储所有机关M到所有其它点的距离【第一维长度是机关数，然后遍历并使用BFS得到当前机关到所有点的距离二维数组】
        // 在遍历的同时，为3中的二维数组B的最后一列赋值，即此处可以拿到所有机关到T的最短距离
        // 5、遍历所有机关[第一层]，
        // 在此基础上，先遍历所有石堆[第二层]如果机关可到石堆且起点可到石堆，拿到一个石堆坐标，使得起点到该石堆到当前机关的路径和最短，值存到3中的二维数组B的倒数第二列
        // 再在此基础上，再遍历其余机关[第二层]，继续遍历所有石堆[第三层]，如果第一层机关可到石堆且第二层机关可到石堆，拿到一个石堆坐标，使得第一层机关到石堆再到第二层机关的路径和最短，值存到3种的二维数组B的对应位置
        // 此时，二维数组B已填满
        // 6、遍历二维数组B的第一维，若存在某机关到起点S或终点T的最短距离为-1，即无法到达，直接返回-1无法到达
        // 7、构造二维数组D，一维是1左移机关数量位[2的机关数量次幂？用每一位记录哪些机关尚未放置石头]，二维是机关数量，先全部填充-1，最后一行填充机关到起点S的最短距离
        // 8、遍历二维数组D[两层，分别是mask和i]，遍历所有机关[第三层j]，动态规划填充D[next][j] = MIN(所有j)【D[mask][i] + B[i][j]】，next是mask|1<<j
        // 9、遍历所有机关i，得到最小的D[finalMask][i] + D[i][nb + 1]即为结果，finalMask是(1<<机关个数)-1，代表所有机关都经过了

        return 0;
    }


    /**
     * 题解有问题，因为没对mechanisms的遍历顺序优化，mechanisms的遍历顺序对结果有影响！
     *
     * @param maze String[]
     * @return int
     */
    private int xunBaoOne(String[] maze) {

        int result = 0;

        int[] start = new int[2];
        int[] target = new int[2];
        List<int[]> mechanisms = new ArrayList<>();

        // 遍历迷宫，找到起始点、目标点、机关
        for (int i = 0; i < maze.length; ++i) {
            for (int j = 0; j < maze[0].length(); ++j) {
                char c = maze[i].charAt(j);
                if (c == 'S') {//起始点
                    start = new int[]{i, j};
                } else if (c == 'T') {//终止点
                    target = new int[]{i, j};
                } else if (c == 'M') {//机关
                    mechanisms.add(new int[]{i, j});
                }
                //其余是石堆/墙/可通行

            }
        }

        // 没有机关，则直接看能不能到终点
        if (mechanisms.size() == 0) {
            result += getMinPath(maze, start, target, false);
        } else {
            // 有机关
            // 循环以下步骤：【如果还有未放巨石的机关】
            // 当前位置先走到未放巨石的机关，且要经过一个石堆【使得路径最小】
            int[] startPoint = start;
            for (int[] mechanism : mechanisms) {
                result += getMinPath(maze, startPoint, mechanism, true);
                startPoint = mechanism;
            }
            // 走到终点
            result += getMinPath(maze, startPoint, target, false);
        }
        return result;
    }

    /**
     * 起始点到终止点
     *
     * @param maze   迷宫
     * @param start  起始点
     * @param target 终止点
     * @param b      是否必须经过一个石堆
     * @return 最短路径长度
     */
    private int getMinPath(String[] maze, int[] start, int[] target, boolean b) {
        return 0;
    }

}
