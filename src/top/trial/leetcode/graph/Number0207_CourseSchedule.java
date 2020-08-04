package top.trial.leetcode.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 * <p>
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * 示例 2:
 * <p>
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 *  
 * <p>
 * 提示：
 * <p>
 * 输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 1 <= numCourses <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/8/4
 * @Version 1.0
 */
public class Number0207_CourseSchedule {
    @Test
    public void solution() {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}, {0, 1}};
        boolean result = canFinish(numCourses, prerequisites);
        Assert.assertFalse(result);
    }

    /**
     * 按我的理解，遍历，图不成环就行
     *
     * @param numCourses    int
     * @param prerequisites int[][]
     * @return boolean
     */
    private boolean canFinish(int numCourses, int[][] prerequisites) {

        //怎么判断成环呢？
        //初始化图
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            graph.add(new ArrayList<>());
        }

        //成图
        for (int[] i : prerequisites) {
            graph.get(i[0]).add(i[1]);
        }

        //遍历
        //结果存储
        int[] visited = new int[numCourses];//0表示未访问，1表示访问中，2表示访问结束，深度优先搜索，访问中再次遇到访问中，即成环
        for (int i = 0; i < numCourses; ++i) {
            if (visited[i] == 0) {
                //开始深度优先搜索
                if (dfs(i, graph, visited))
                    return false;
            }
        }
        return true;
    }

    /**
     * @param i       int
     * @param graph   List<List<Integer>>
     * @param visited int[]
     * @return boolean 是否成环
     */
    private boolean dfs(int i, List<List<Integer>> graph, int[] visited) {

        visited[i] = 1;//访问中
        for (int n : graph.get(i)) {
            if (visited[n] == 0) {//未访问过
                if (dfs(n, graph, visited))//继续调用dfs方法，看是否成环
                    return true;
            } else if (visited[n] == 1) {//遍历到访问中的点
                return true;
            }
        }
        visited[i] = 2;//访问结束
        return false;
    }
}
