package top.trial.leetcode.linkedList;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给出两个非空的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4) 输出：7 -> 0 -> 8 原因：342 + 465 = 807
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Gaoyx
 */
public class Number0002_AddTwoNumbers {
    @Test
    public void solution() {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode expected = new ListNode(7);
        expected.next = new ListNode(0);
        expected.next.next = new ListNode(8);

        ListNode result = addTwoNumbers(l1, l2);

        Assert.assertEquals(expected.val, result.val);
        Assert.assertEquals(expected.next.val, result.next.val);
        Assert.assertEquals(expected.next.next.val, result.next.next.val);
    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // 定义ListNode初始指针位
        ListNode res = new ListNode(0);

        // 定义ListNode游走位
        ListNode curr = res;

        // 定义进值
        int temp = 0;

        // 当l1和l2任一个当前位有值
        while (l1 != null || l2 != null) {
            // 取得当前位的值，没有补0
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;

            // 取得当前位的和
            int sum = x + y + temp;

            // 取得当前位的进值
            temp = sum / 10;

            // 构造当前位之和的结果，游走指针后移
            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            // l1和l2指针后移
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }

        // 最终进位有值判断
        if (temp != 0) {
            curr.next = new ListNode(temp);
        }

        return res.next;
    }
}


