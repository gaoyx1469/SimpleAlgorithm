package top.trial.leetcode.linkedList;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * <p>
 * 说明：不允许修改给定的链表
 * <p>
 * 进阶：
 * 你是否可以不用额外空间解决此题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/10/10
 * @Version 1.0
 */
public class Number0142_LinkedListCycle {
    @Test
    public void solution() {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next;
        System.out.println(detectCycleTwo(head).val);
    }

    /**
     * 根据分析，使用双指针，当相遇时【存在环】，在起点处继续起另一个慢指针，两个慢指针将在成环点相遇
     *
     * @param head ListNode
     * @return ListNode
     */
    private ListNode detectCycleTwo(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null) {
            //先走
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }

            //出现套圈
            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow) {//继续走ptr和slow
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }


    /**
     * 先使用额外空间
     *
     * @param head ListNode
     * @return ListNode
     */
    private ListNode detectCycleOne(ListNode head) {

        Set<ListNode> set = new HashSet<>();
        ListNode curr = head;
        while (curr != null) {
            if (!set.add(curr)) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }
}
