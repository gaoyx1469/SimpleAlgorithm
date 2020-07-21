package top.trial.leetcode.tree;

import org.junit.Test;
import top.trial.leetcode.utils.TreeNodeUtil;

import java.util.*;

/**
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 * <p>
 * 示例：
 * <p>
 * 输入：3
 * 输出：
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/7/21
 * @Version 1.0
 */
public class Number0095_UniqueBinarySearchTreesII {

    @Test
    public void solution() {
        int[] ns = {3};
        //List<TreeNode> roots = uniqueBinarySearchTreesIIOne(ns[0]);
        List<TreeNode> roots = uniqueBinarySearchTreesIITwo(ns[0]);
        for (TreeNode root : roots)
            System.out.println(TreeNodeUtil.getStringOfTreeNodeAccordingDepth(root));
    }

    /**
     * 递归似乎好使
     *
     * @param n int
     * @return List
     */
    private List<TreeNode> uniqueBinarySearchTreesIITwo(int n) {
        if (n == 0)
            return new LinkedList<>();

        return getTrees(1, n);
    }

    //获取从i到n的可形成的树
    private List<TreeNode> getTrees(int start, int end) {

        List<TreeNode> trees = new LinkedList<>();
        if (start > end) {
            trees.add(null);//铺底一个null
            return trees;
        }
        for (int i = start; i <= end; ++i) {
            List<TreeNode> leftTrees = getTrees(start, i - 1);
            List<TreeNode> rightTrees = getTrees(i + 1, end);
            for (TreeNode leftNode : leftTrees) {
                for (TreeNode rightNode : rightTrees) {
                    TreeNode tree = new TreeNode(i);
                    tree.left = leftNode;
                    tree.right = rightNode;
                    trees.add(tree);
                }
            }
        }
        return trees;
    }


    /**
     * 也算动态规划？
     *
     * @param n int
     * @return List
     */
    private List<TreeNode> uniqueBinarySearchTreesIIOne(int n) {

        if (n == 0)
            return new ArrayList<>();


        Map<String, List<TreeNode>> results = new HashMap<>();
        for (int i = 1; i <= n; ++i) {
            for (int j = i; j > 0; --j) {
                List<TreeNode> result = new ArrayList<>();//j,i位

                if (i == j) {
                    TreeNode treeNode = new TreeNode(i);
                    result.add(treeNode);
                } else {
                    for (int k = j; k <= i; ++k) {//以k为根
                        if (k == j) {
                            for (TreeNode node : results.get((j + 1) + "," + i)) {
                                TreeNode treeNode = new TreeNode(k);
                                treeNode.right = node;
                                result.add(treeNode);
                            }
                        } else if (k == i) {
                            for (TreeNode node : results.get(j + "," + (i - 1))) {
                                TreeNode treeNode = new TreeNode(k);
                                treeNode.left = node;
                                result.add(treeNode);
                            }
                        } else {
                            for (TreeNode nodel : results.get(j + "," + (k - 1))) {
                                for (TreeNode noder : results.get((k + 1) + "," + i)) {
                                    TreeNode treeNode = new TreeNode(k);
                                    treeNode.left = nodel;
                                    treeNode.right = noder;
                                    result.add(treeNode);
                                }
                            }
                        }
                    }
                }
                results.put(j + "," + i, result);
            }
        }
        return results.get(1 + "," + n);
    }
}
