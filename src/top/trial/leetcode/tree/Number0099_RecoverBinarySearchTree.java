package top.trial.leetcode.tree;

import org.junit.Test;
import top.trial.leetcode.utils.TreeNodeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 二叉搜索树中的两个节点被错误地交换。
 * <p>
 * 请在不改变其结构的情况下，恢复这棵树。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,null,null,2]
 * <p>
 *    1
 *   /
 *  3
 *   \
 *    2
 * <p>
 * 输出: [3,1,null,null,2]
 * <p>
 *    3
 *   /
 *  1
 *   \
 *    2
 * 示例 2:
 * <p>
 * 输入: [3,1,4,null,null,2]
 * <p>
 * 3
 * / \
 * 1   4
 *    /
 *   2
 * <p>
 * 输出: [2,1,4,null,null,3]
 * <p>
 * 2
 * / \
 * 1   4
 *    /
 *  3
 * 进阶:
 * <p>
 * 使用 O(n) 空间复杂度的解法很容易实现。
 * 你能想出一个只使用常数空间的解决方案吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/8/8
 * @Version 1.0
 */
public class Number0099_RecoverBinarySearchTree {
    @Test
    public void solution() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        System.out.println(TreeNodeUtil.getStringOfTreeNodeAccordingDepth(root));
        recoverTree(root);
        System.out.println(TreeNodeUtil.getStringOfTreeNodeAccordingDepth(root));
        // TODO 官方另有两个更优秀的题解，后续需要继续参考
    }

    /**
     * 恢复交换了两个节点的二叉树
     *
     * @param root TreeNode
     */
    private void recoverTree(TreeNode root) {
        //找交换点
        //前序遍历，数值出现减小的节点
        List<Integer> list = new ArrayList<>();
        fillList(list, root);
        int[] wrongNums = new int[2];
        int count = 0;

        int[] nums = new int[list.size()];
        for (int i = 0; i < list.size(); ++i)
            nums[i] = list.get(i);
        Arrays.sort(nums);
        for (int i = 0; i < list.size(); ++i) {
            if (nums[i] != list.get(i)) {
                wrongNums[count] = list.get(i);
                ++count;
            }
        }

        /*

        int count = 0;
        for (int i = 1; i < list.size(); ++i) {
            if (list.get(i) < list.get(i - 1)) {
                wrongNums[count] = list.get(i - 1);
                if (count == 0) {
                    wrongNums[count + 1] = list.get(i);
                }
                ++count;
            }
        }
        */
        //System.out.println(Arrays.toString(wrongNums));

        //交换两个节点
        TreeNode firstNode = fillTreeNode(wrongNums[0], root);
        TreeNode lastNode = fillTreeNode(wrongNums[1], root);

        firstNode.val = wrongNums[1];
        lastNode.val = wrongNums[0];

        /*
        TreeNode firstLeftNode = firstNode.left;
        TreeNode firstRightNode = firstNode.right;
        TreeNode lastLeftNode = lastNode.left;
        TreeNode lastRightNode = lastNode.right;

        System.out.println(firstNode.val);
        System.out.println(lastNode.val);

        int f = -1;
        if (firstNode == root) {
            f = 1;
        } else if (lastNode == root) {
            f = 2;
        }


        //相邻节点
        if (firstRightNode != null && firstRightNode == lastNode) {
            firstNode.left = lastLeftNode;
            firstNode.right = lastRightNode;
            lastNode.left = firstLeftNode;
            lastNode.right = firstNode;
        } else if (lastLeftNode != null && firstNode == lastLeftNode) {
            lastNode.left = firstLeftNode;
            lastNode.right = firstRightNode;
            firstNode.right = lastRightNode;
            firstNode.left = lastNode;
        } else {
            firstNode.left = lastLeftNode;
            firstNode.right = lastRightNode;
            lastNode.left = firstLeftNode;
            lastNode.right = firstRightNode;
        }

        if (f == 1) {
            root = lastNode;
        } else if (f == 2) {
            root = firstNode;
        }
        */
    }

    private TreeNode fillTreeNode(int wrongNum, TreeNode root) {
        if (root == null)
            return null;
        if (root.val == wrongNum) {
            return root;
        } else {
            TreeNode node;
            node = fillTreeNode(wrongNum, root.left);
            if (node == null) {
                node = fillTreeNode(wrongNum, root.right);
            }
            return node;
        }
    }

    /**
     * 前序遍历填充list
     *
     * @param list List<Integer>
     * @param root TreeNode
     */
    private void fillList(List<Integer> list, TreeNode root) {
        if (root.left != null)
            fillList(list, root.left);
        list.add(root.val);
        if (root.right != null)
            fillList(list, root.right);
    }

}
