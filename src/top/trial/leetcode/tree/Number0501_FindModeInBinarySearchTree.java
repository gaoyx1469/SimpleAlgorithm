package top.trial.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 * <p>
 * 假定 BST 有如下定义：
 * <p>
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 例如：
 * 给定 BST [1,null,2,2],
 * <p>
 * 1
 * \
 * 2
 * /
 * 2
 * 返回[2].
 * <p>
 * 提示：如果众数超过1个，不需考虑输出顺序
 * <p>
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-mode-in-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/9/24
 * @Version 1.0
 */
public class Number0501_FindModeInBinarySearchTree {
    @Test
    public void solution() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(2);
        int[] result = findModeOne(root);
        int[] expected = {2};
        Assert.assertArrayEquals(expected, result);
    }

    //考虑二叉搜索树特性，则中序遍历，不用再使用map记录次数，而是维护一个常数作为最大出现次数随着遍历更新


    //不考虑进阶要求，使用额外空间的话，不考虑使用二叉搜索树的特性
    private int[] findModeOne(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        dfsTree(root, map);
        List<Integer> list = new ArrayList<>();
        int num = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= num) {
                if (entry.getValue() > num) {
                    num = entry.getValue();
                    list.clear();
                }
                list.add(entry.getKey());
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); ++i)
            result[i] = list.get(i);
        return result;
    }

    private void dfsTree(TreeNode root, Map<Integer, Integer> map) {
        if (root == null)
            return;
        dfsTree(root.left, map);
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        dfsTree(root.right, map);
    }
}
