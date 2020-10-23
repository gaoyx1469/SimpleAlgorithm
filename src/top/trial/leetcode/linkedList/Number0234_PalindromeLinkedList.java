package top.trial.leetcode.linkedList;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/10/23
 * @Version 1.0
 */
public class Number0234_PalindromeLinkedList {
    @Test
    public void solution() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        Assert.assertTrue(isPalindromeOne(head));
    }

    /**
     * 判断是否是回文链表
     * 用 O(n) 时间复杂度和 O(1) 空间复杂度
     * <p>
     * 若是数组，会很方便，双指针就行
     * 链表的话，无法反向遍历，如何解决？
     * <p>
     * 可以遍历一遍，找到中间节点，【也可以使用快慢指针找中间节点】
     * 从中间节点后面开始，重新改变链表结构，链表进行反转
     * 然后双指针比较
     * 比较完毕恢复反转的节点
     * <p>
     * 此处暂且不那么麻烦，使用O(n)空间复杂度，直接复制到ArrayList中
     *
     * @param head ListNode
     * @return boolean
     */
    private boolean isPalindromeOne(ListNode head) {

        if (head == null)
            return true;
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        int len = list.size();
        int start = 0;
        int end = len - 1;
        while (start < end) {
            if (list.get(start).val != list.get(end).val)
                return false;
            start++;
            end--;
        }

        return true;
    }
}
