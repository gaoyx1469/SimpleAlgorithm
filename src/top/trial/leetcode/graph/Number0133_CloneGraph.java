package top.trial.leetcode.graph;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 * <p>
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 * <p>
 * class Node {
 * public int val;
 * public List<Node> neighbors;
 * }
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/clone-graph
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/8/12
 * @Version 1.0
 */
public class Number0133_CloneGraph {
    @Test
    public void solution() {

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        Node newNode = cloneGraph(node1);
    }

    /**
     * 深拷贝图，每个节点值唯一，可用来判断是否已遍历
     *
     * @param node Node
     * @return Node
     */
    private Node cloneGraph(Node node) {

        if (node == null)
            return null;

        Map<Integer, Node> map = new HashMap<>();
        Node root = new Node(node.val);
        map.put(root.val, root);
        dfs(root, node, map);
        return root;
    }

    private void dfs(Node root, Node node, Map<Integer, Node> map) {
        for (Node n : node.neighbors) {//遍历全部node的相邻节点
            if (!map.containsKey(n.val)) {//若未遍历过
                Node newNode = new Node(n.val);
                map.put(newNode.val, newNode);
                root.neighbors.add(newNode);
                dfs(newNode, n, map);
            } else {//若已遍历过
                root.neighbors.add(map.get(n.val));
            }
        }
    }
}
