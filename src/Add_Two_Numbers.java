public class Add_Two_Numbers {

    public static ListNode test(ListNode l1, ListNode l2){
        ListNode res = new ListNode(0);
        int i = 0;
        ListNode n1 = l1, n2 = l2, n3 = res;
        while (n1 != null || n2 != null || i != 0){
            int a = n1 != null ? n1.val : 0;
            int b = n2 != null ? n2.val : 0;
            int sum = a + b + i;
            i = sum /10;
            n3.val = sum % 10;

            n1 = n1 != null ? n1.next : null;
            n2 = n2 != null ? n2.next : null;
            if(n1 != null || n2 != null || i != 0){
                n3.next = new ListNode(0);
                n3 = n3.next;
            }
        }
        return res;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }


    /**
     * test case:
     * l1=[0,1]
     * l2=[0,1,2]l2=[0,1,2]
     *
     * l1=[]
     * l2=[0,1]l2=[0,1]
     *
     * l1=[9,9]
     * l2=[1]
     *
     * @param args
     */
    public static void main(String[] args){
        ListNode l2 = new ListNode(0);
        ListNode l4 = new ListNode(1);
//        ListNode l3 = new ListNode(3);
        l2.next = l4;
//        l4.next = l3;

        ListNode l5 = new ListNode(0);
        ListNode l6 = new ListNode(1);
        ListNode l4_ = new ListNode(2);
        l5.next = l6;
        l6.next = l4_;

        ListNode a = test(l2, l5);
        System.out.println(a);
    }
}
