package top.trial.leetcode.graph;

import org.junit.Test;

import java.util.*;

/**
 * 在本问题中，有根树指满足以下条件的有向图。该树只有一个根节点，所有其他节点都是该根节点的后继。每一个节点只有一个父节点，除了根节点没有父节点。
 * 输入一个有向图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
 * 结果图是一个以边组成的二维数组。 每一个边 的元素是一对 [u, v]，用以表示有向图中连接顶点 u 和顶点 v 的边，其中 u 是 v 的一个父节点。
 * 返回一条能删除的边，使得剩下的图是有N个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,2], [1,3], [2,3]]
 * 输出: [2,3]
 * 解释: 给定的有向图如下:
 * 1
 * / \
 * v   v
 * 2-->3
 * 示例 2:
 * <p>
 * 输入: [[1,2], [2,3], [3,4], [4,1], [1,5]]
 * 输出: [4,1]
 * 解释: 给定的有向图如下:
 * 5 <- 1 -> 2
 * ^    |
 * |    v
 * 4 <- 3
 * 注意:
 * <p>
 * 二维数组大小的在3到1000范围内。
 * 二维数组中的每个整数在1到N之间，其中 N 是二维数组的大小。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/redundant-connection-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/9/18
 * @Version 1.0
 */
public class Number0685_RedundantConnection {
    @Test
    public void solution() {
        int[][] edges = {{1, 2}, {1, 3}, {2, 3}};
        System.out.println(Arrays.toString(findRedundantDirectedConnectionTwo(edges)));
    }

    /**
     * 参考官方题解，思路基本一致，但是增加了并查集的使用，优秀
     *
     * @param edges int[][]
     * @return int[]
     */
    private int[] findRedundantDirectedConnectionTwo(int[][] edges) {
        int nodesCount = edges.length;
        UnionFind uf = new UnionFind(nodesCount + 1);
        int[] parent = new int[nodesCount + 1];
        for (int i = 1; i <= nodesCount; ++i) {
            parent[i] = i;
        }
        int conflict = -1;
        int cycle = -1;
        for (int i = 0; i < nodesCount; ++i) {
            int[] edge = edges[i];
            int node1 = edge[0], node2 = edge[1];
            if (parent[node2] != node2) {
                conflict = i;
            } else {
                parent[node2] = node1;
                if (uf.find(node1) == uf.find(node2)) {
                    cycle = i;
                } else {
                    uf.union(node1, node2);
                }
            }
        }
        if (conflict < 0) {
            return new int[]{edges[cycle][0], edges[cycle][1]};
        } else {
            int[] conflictEdge = edges[conflict];
            if (cycle >= 0) {
                return new int[]{parent[conflictEdge[1]], conflictEdge[1]};
            } else {
                return new int[]{conflictEdge[0], conflictEdge[1]};
            }
        }

    }


    /**
     * 若有多个答案，返回最后出现在给定二维数组的答案。
     * 思路：理论上，除了根结点没有入度外，其余结点都是一个入度。当增加了这个边，则会出现每个结点都有一个入度或出现一个有两个入度的结点
     * 若每个结点都有一个入度，必然会出现环，只要找到edges中最后一个属于环的边即可
     * 若出现一个有两个入度的结点，则其中一个必然是多余的边，删掉一个看是否构成一颗树，若都能构成一棵树，则两个边中后一个是结果，
     * 如有一个能构成树而另一个不能，显然删掉之后能构成树的那个就是结果
     * <p>
     * 超时了！！！！！！
     *
     * @param edges int[][]
     * @return int[]
     */
    private int[] findRedundantDirectedConnectionOne(int[][] edges) {

        int wrongNode = 0;
        int anotherVal = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] edge : edges) {
            if (!map.containsKey(edge[1]))
                map.put(edge[1], edge[0]);
            else {
                wrongNode = edge[1];
                anotherVal = edge[0];
            }

        }

        //找环，有一个环或没有环，迭代删掉所有出度为0的结点即可，剩下的就是那一个环
        List<Integer> outOfCircle = new ArrayList<>();//不在环中的结点
        Set<Integer> keySet = map.keySet();//key就是结点
        boolean cleanFlag = false;
        while (!cleanFlag) {
            cleanFlag = true;
            for (int node : keySet) {//循环遍历所有结点，将出度为0的放到outOfCircle中
                if (outOfCircle.contains(node))
                    continue;
                if (!map.containsValue(node)) {//node结点的出度为0
                    outOfCircle.add(node);
                    cleanFlag = false;
                } else {//node结点出度不是0，找到所有出度结点，看是否都在outOfCircle内，只要有一个不在outOfCircle内，就放过，否则，将node放入outOfCircle
                    boolean flag = true;
                    for (int i : keySet) {
                        if (map.get(i) == node && !outOfCircle.contains(i)) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        outOfCircle.add(node);
                        cleanFlag = false;
                    }
                }
            }
        }
        //wrongNode就是入度有问题的节点
        if (wrongNode == 0) {//成环了，且全部数据入了map
            //从后往前遍历edges，第一个出现在环中的边删掉
            for (int i = edges.length - 1; i >= 0; --i) {
                if (!outOfCircle.contains(edges[i][0]) && !outOfCircle.contains(edges[i][1])) {
                    return new int[]{edges[i][0], edges[i][1]};
                }
            }

        } else {//出现两个入度的结点，删出现在环中的那个[anotherVal,wrongNode]还是[map.get(wrongNode),wrongNode]
            if (outOfCircle.size() == map.size())//未成环
                return new int[]{anotherVal, wrongNode};
            else
                return new int[]{map.get(wrongNode), wrongNode};
        }
        return null;
    }
}

class UnionFind {
    int[] ancestor;

    public UnionFind(int n) {
        ancestor = new int[n];
        for (int i = 0; i < n; ++i) {
            ancestor[i] = i;
        }
    }

    public void union(int index1, int index2) {
        ancestor[find(index1)] = find(index2);
    }

    public int find(int index) {
        if (ancestor[index] != index) {
            ancestor[index] = find(ancestor[index]);
        }
        return ancestor[index];
    }
}