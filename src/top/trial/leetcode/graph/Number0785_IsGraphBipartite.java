package top.trial.leetcode.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 给定一个无向图graph，当这个图为二分图时返回true。
 * <p>
 * 如果我们能将一个图的节点集合分割成两个独立的子集A和B，并使图中的每一条边的两个节点一个来自A集合，一个来自B集合，我们就将这个图称为二分图。
 * <p>
 * graph将会以邻接表方式给出，graph[i]表示图中与节点i相连的所有节点。每个节点都是一个在0到graph.length-1之间的整数。这图中没有自环和平行边： graph[i] 中不存在i，并且graph[i]中没有重复的值。
 * <p>
 * <p>
 * 示例 1:
 * 输入: [[1,3], [0,2], [1,3], [0,2]]
 * 输出: true
 * 解释:
 * 无向图如下:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * 我们可以将节点分成两组: {0, 2} 和 {1, 3}。
 * <p>
 * 示例 2:
 * 输入: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * 输出: false
 * 解释:
 * 无向图如下:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * 我们不能将节点分割成两个独立的子集。
 * 注意:
 * <p>
 * graph 的长度范围为 [1, 100]。
 * graph[i] 中的元素的范围为 [0, graph.length - 1]。
 * graph[i] 不会包含 i 或者有重复的值。
 * 图是无向的: 如果j 在 graph[i]里边, 那么 i 也会在 graph[j]里边。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/is-graph-bipartite
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/7/16
 * @Version 1.0
 */
public class Number0785_IsGraphBipartite {

    @Test
    public void solution() {
        int[][][] graphs = {{{1, 3}, {0, 2}, {1, 3}, {0, 2}}, {{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}}};
        boolean[] expected = {true, false};
        boolean[] results = new boolean[2];
        results[0] = isGraphBipartiteOne(graphs[0]);
        results[1] = isGraphBipartiteOne(graphs[1]);
        //TODO 官方题解使用DFS和BFS两种方法，另并查集是否可用？
        Assert.assertArrayEquals(expected, results);
    }

    /**
     * 拿到二维数组，作为邻接表，看是否是二分图
     * <p>
     * 就是分成两部分，使得对于所有i，graph[i]里的所有值都是另一部分
     * <p>
     * 咋感觉除了概念，解法跟图没有任何关系
     *
     * @param graph 邻接表
     * @return 是否是二分图
     */
    private boolean isGraphBipartiteOne(int[][] graph) {

        int len = graph.length;

        //构造两个ArrayList？
        List<Integer> subGraph1 = new ArrayList<>();
        List<Integer> subGraph2 = new ArrayList<>();
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < len; ++i)
            queue.add(i);

        while (!queue.isEmpty()) {
            int n = queue.remove();
            if (graph[n].length == 0) {
                continue;
            }
            if (!subGraph1.contains(n) && !subGraph2.contains(n)) {
                subGraph1.add(n);
                for (int i : graph[n]) {
                    subGraph2.add(i);
                    if (queue.contains(i)) {
                        queue.remove(i);
                        queue.addFirst(i);
                    }
                }
            } else if (subGraph1.contains(n)) {
                for (int i : graph[n]) {
                    if (subGraph1.contains(i))
                        return false;
                    if (!subGraph2.contains(i))
                        subGraph2.add(i);
                    if (queue.contains(i)) {
                        queue.remove(i);
                        queue.addFirst(i);
                    }
                }
            } else if (subGraph2.contains(n)) {
                for (int i : graph[n]) {
                    if (subGraph2.contains(i))
                        return false;
                    if (!subGraph1.contains(i))
                        subGraph1.add(i);
                    if (queue.contains(i)) {
                        queue.remove(i);
                        queue.addFirst(i);
                    }
                }
            }
        }
        return true;
    }
}
