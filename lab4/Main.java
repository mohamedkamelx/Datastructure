package lab4;


public class Main {
    public static void main(String[] args){
        LinkedList<Integer> x = new LinkedList<>(1,2,3,4,9);
        LinkedList<Integer> y = new LinkedList<>(2,3,5);
        System.out.println(x);
        System.out.println(y);

        System.out.println(merge(x,y));

        // ILinkedList<Integer> y=reverse(x);
        // y.remove(0);
        // y.add(9999);
        // System.out.println(y);
        //System.out.println(reverse(x));


        // System.out.println(x.isEmpty());
        // x.add(2,6);
        // x.set(0,9000);
        // System.out.println(x);
        // ILinkedList<Integer> y=x.sublist(0, 3);
        // System.out.println(y);

    }
    public static <E> ILinkedList<E> reverse(LinkedList<E> list) {
        Node<E> temp=list.getHead();
        Node<E> temp2=temp.getNext();
        Node<E> next;

        temp.setNext(null);
        while(temp2 !=null) {
            next = temp2.getNext();
            temp2.setNext(temp);
            temp=temp2;
            temp2=next;
        }
        list.getHead().setNext(temp);
        return list;
    }

    public static <E> ILinkedList<E> reverse(DLinkedList<E> list) {
        DNode<E> current = list.getHead();
        DNode<E> temp = null;
        while (current != null) {
            temp = current.getPrev();
            current.setPrev(current.getNext());
            current.setNext(temp);
            current = current.getPrev();
        }
        if (temp != null) {
            list.setTail(list.getHead());
            list.setHead(temp.getPrev());
        }
        return list;
    }

    public static <E> boolean compare(ILinkedList<E> list1, ILinkedList<E> list2) {
        if (list1.size() != list2.size()) 
            return false;

        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i)!=list2.get(i)) 
                return false;
        }
        return true;
    }
public static ILinkedList<Integer> merge(ILinkedList<Integer> list1, ILinkedList<Integer> list2) {
    if (list2.isEmpty()) {
        return list1;
    }
    if (list1.isEmpty()) {
        return list2;
    }
    int i = 0; 
    int j = 0;
    
    while (j < list2.size()) {
        Integer val2 = list2.get(j);
        while (i < list1.size() && list1.get(i) <= val2) {
            i++;
        }
        list1.add(i, val2);
        // Don't increment i here - let the outer loop find the right position
        j++; 
    }
    return list1;
}

}