package top.trial.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个二叉树，我们在树的节点上安装摄像头。
 * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
 * 计算监控树的所有节点所需的最小摄像头数量。
 * <p>
 * 示例 1：
 * 输入：[0,0,null,0,0]
 * 输出：1
 * 解释：如图所示，一台摄像头足以监控所有节点。
 * <p>
 * 示例 2：
 * 输入：[0,0,null,0,null,0,null,null,0]
 * 输出：2
 * 解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
 * <p>
 * 提示：
 * 给定树的节点数的范围是 [1, 1000]。
 * 每个节点的值都是 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-cameras
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/9/22
 * @Version 1.0
 */
public class Number0968_BinaryTreeCameras {
    @Test
    public void solution() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.left.left.left = new TreeNode(0);
        root.left.left.left.right = new TreeNode(0);
        int expected = 2;
        int result = getBinaryTreeCamerasNumTwo(root);
        Assert.assertEquals(expected, result);
    }

    //用一个长度为3的数组表示答案，每个节点都维护这么一个数组，数组的三位分别表示：
    // 1、当前结点放置监控器时，监控以该结点为根的树的最少监控器个数；【当前结点及父子结点被监控】
    // 2、当前结点不确定放不放监控器时，监控以该结点为根的树的最少监控器个数；【当前结点及子结点被监控】
    // 3、当前结点不确定放不放监控器时，监控该结点左右子树的最少监控器个数。【当前结点的子结点被监控】
    // 最后root结点的数组第二位就是题解
    // 转移方程：
    //  root的第一种情况等于左右子结点的第三种情况的和+1
    //  root的第二种情况是root的第一种情况与root的某个子结点的第二种情况+另一个子结点的第一种情况之和两者取最小值【当前结点放置监控器或某一个子结点放监控器】
    //  root的第三种情况是root的第一种情况与root的两个子结点的第二种情况之和两者取最小值【当前结点放置监控器或两个子结点自己满足】
    private int getBinaryTreeCamerasNumTwo(TreeNode root) {
        int[] array = dfs(root);
        return array[1];
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{Integer.MAX_VALUE / 2, 0, 0};
        }
        int[] leftArray = dfs(root.left);
        int[] rightArray = dfs(root.right);
        int[] array = new int[3];
        array[0] = leftArray[2] + rightArray[2] + 1;
        array[1] = Math.min(array[0], Math.min(leftArray[0] + rightArray[1], rightArray[0] + leftArray[1]));
        array[2] = Math.min(array[0], leftArray[1] + rightArray[1]);
        return array;
    }

    /**
     * 二叉树，自己想的解法，差劲，主要问题是没剪枝
     *
     * @param root TreeNode
     * @return int
     */
    private int getBinaryTreeCamerasNumOne(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        return Math.min(getMinCamerasNum(root, false, true), getMinCamerasNum(root, false, false));
    }

    private int getMinCamerasNum(TreeNode root, boolean fatherFlag, boolean flag) {
        int result;
        int left = 0;
        int right = 0;
        //root节点加监视器
        if (flag) {
            if (root.left != null) {
                left = Math.min(getMinCamerasNum(root.left, true, true), getMinCamerasNum(root.left, true, false));
            }
            if (root.right != null) {
                right = Math.min(getMinCamerasNum(root.right, true, true), getMinCamerasNum(root.right, true, false));
            }
            result = left + right + 1;
        } else {
            //root节点不加监视器,root父节点加监视器
            if (fatherFlag) {
                //如果子结点有子结点，该子结点可选，如果子结点是叶子节点，子结点必须加监视器
                if (root.left != null) {
                    if (root.left.left == null && root.left.right == null)
                        left = 1;
                    else
                        left = Math.min(getMinCamerasNum(root.left, false, true), getMinCamerasNum(root.left, false, false));
                }
                if (root.right != null) {
                    if (root.right.left == null && root.right.right == null)
                        right = 1;
                    else
                        right = Math.min(getMinCamerasNum(root.right, false, true), getMinCamerasNum(root.right, false, false));
                }
                result = left + right;
            } else { //root节点不加监视器,root父节点也不加监视器，则左右某个子结点必须要有一个加监视器

                if (root.left == null) {//必定右子结点加监视器
                    result = getMinCamerasNum(root.right, false, true);
                } else if (root.right == null) {//必定左子结点加监视器
                    result = getMinCamerasNum(root.left, false, true);
                } else {//左右子结点都存在时

                    //加在左边
                    left = getMinCamerasNum(root.left, false, true);
                    if (root.right != null) {
                        if (root.right.left == null && root.right.right == null)
                            right = 1;
                        else
                            right = Math.min(getMinCamerasNum(root.right, false, true), getMinCamerasNum(root.right, false, false));
                    }
                    result = left + right;

                    //加在右边
                    right = getMinCamerasNum(root.right, false, true);
                    if (root.left != null) {
                        if (root.left.left == null && root.left.right == null)
                            left = 1;
                        else
                            left = Math.min(getMinCamerasNum(root.left, false, true), getMinCamerasNum(root.left, false, false));
                    }
                    result = Math.min(result, left + right);
                }
            }
        }
        return result;
    }
}
