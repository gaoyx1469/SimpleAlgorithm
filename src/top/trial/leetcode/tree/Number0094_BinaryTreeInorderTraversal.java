package top.trial.leetcode.tree;

import org.junit.Test;
import top.trial.leetcode.utils.ArrayListUtil;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回它的中序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/9/14
 * @Version 1.0
 */
public class Number0094_BinaryTreeInorderTraversal {
    @Test
    public void solution() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        ArrayListUtil.getOneDimensionalSysoLikeArray(inorderTraversal(root));
    }

    /**
     * 中序遍历好说，但是要求通过迭代算法完成，其实是将递归时JVM维护的栈变为自己显式维护
     *
     * @param root TreeNode
     * @return List<Integer>
     */
    private List<Integer> inorderTraversal(TreeNode root) {

        Deque<TreeNode> list = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        while (root != null || !list.isEmpty()) {
            while (root != null) {
                list.addFirst(root);
                root = root.left;
            }
            root = list.poll();
            result.add(root.val);
            root = root.right;//导致root可能为null
        }

        return result;
    }
}
