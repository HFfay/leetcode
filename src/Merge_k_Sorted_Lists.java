public class Merge_k_Sorted_Lists {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }


    public static void main(String[] args){
//        1->4->5,
//        1->3->4,
//        2->6
        ListNode n1 = new ListNode(1);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n4;
        n4.next = n5;

        ListNode m1 = new ListNode(1);
        ListNode m3 = new ListNode(3);
        ListNode m4 = new ListNode(4);
        m1.next = m3;
        m3.next = m4;

        ListNode b2 = new ListNode(2);
        ListNode b6 = new ListNode(6);
        b2.next = b6;

        ListNode[] params = new ListNode[]{n1, m1, b2};

        ListNode res = mergeKLists(params);
        System.out.println(res.toString());
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        if(lists.length == 1) return lists[0];

        int i = 0;
        ListNode l1 = lists[i++];
        while (i < lists.length){
            ListNode l2 = lists[i++];
            l1 = merge(l1, l2);
        }

        return l1;
    }


    public static ListNode merge(ListNode n1, ListNode n2){
        if (n1 == null && n2 == null) return null;
        if (n1 == null) return n2;
        if(n2 == null) return n1;

        ListNode res = null;
        ListNode tmp = null;
        while(n1 != null || n2 != null){
            int val = 0;
            if(n1 == null || (n2 != null && n1.val > n2.val)){
                val = n2.val;
                n2 = n2.next;
            } else if(n1 == null || (n2 != null && n1.val <= n2.val)){
                val = n1.val;
                n1 = n1.next;
            } else if(n2 == null || (n1 != null && n1.val <= n2.val)) {
                val = n1.val;
                n1 = n1.next;
            } else if(n2 == null || (n1 != null && n1.val > n2.val)) {
                val = n2.val;
                n2 = n2.next;
            }  else {
                System.out.println("-------------");
            }

            if(res == null){
                tmp = res = new ListNode(val);
            } else {
                ListNode tmp2 = new ListNode(val);
                tmp.next = tmp2;
                tmp = tmp.next;
            }
        }

        return res;
    }

}
