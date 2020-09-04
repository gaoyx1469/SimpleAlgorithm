package top.trial.leetcode.tree;

import org.junit.Test;
import top.trial.leetcode.utils.ArrayListUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * <p>
 * 1
 * /   \
 * 2     3
 * \
 * 5
 * <p>
 * 输出: ["1->2->5", "1->3"]
 * <p>
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/9/4
 * @Version 1.0
 */
public class Number0257_BinaryTreePaths {
    @Test
    public void solution() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);

        System.out.println(ArrayListUtil.getOneDimensionalStringLikeArray(binaryTreePaths(root)));
    }

    List<String> results = new ArrayList<>();
    List<Integer> pathValue = new ArrayList<>();
    public final String connector = "->";

    private List<String> binaryTreePaths(TreeNode root) {

        if (root == null)
            return results;
        getPaths(root, 0);
        return results;
    }

    /**
     * 回溯
     *
     * @param node TreeNode 当前结点
     * @param n    int 当前节点level
     */
    private void getPaths(TreeNode node, int n) {
        pathValue.add(n, node.val);
        if (node.left == null && node.right == null) {//到达叶子节点
            putIntoList(n);
            return;
        }
        if (node.left != null) {
            getPaths(node.left, n + 1);
        }
        if (node.right != null) {
            getPaths(node.right, n + 1);
        }
    }

    /**
     * @param n 将pathValue前n个元素格式化为指定格式传入results
     */
    private void putIntoList(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i)
            sb.append(pathValue.get(i)).append(connector);
        sb.append(pathValue.get(n));
        results.add(sb.toString());
    }
}
