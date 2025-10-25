package lab4;
/* assumptions
 * 1. dummy head node equal null for empty list and its out of index 
 * so if you get(0) for empty list you get null
 * 2. keeping track of the tail node for O(1) append
 * 3. tail is grounded to null
*/
public class LinkedList<E> implements ILinkedList<E> {
    private Node<E> head= new Node<>(null); // dummy head
    private Node<E> tail= null;


    public LinkedList() {
        this.tail=this.head;
    }
    @SafeVarargs
    public LinkedList( E... nodes) {
        this.tail=this.head;
        for(E i:nodes){
            this.add(i);
        }
    }
    public LinkedList( Node<E> head) {
        this.head.setNext(head);
        this.tail=head;
    }

    @Override
    public void add(int index, E element){
        if ((this.tail == null && index != 0) || index<0 || index>this.size()){
            System.out.println("cant add to list at index "+index);
            return ;
        }else{
            Node<E> newN = new Node<>(element);
            Node<E> temp =this.head; //this is the dummy head & this pointer will always point to the previous node to the index

            for (int i=0;i<index;i++){
                temp = temp.getNext();
            }
            newN.setNext(temp.getNext());
            temp.setNext(newN);
            return;

        }
    }

    @Override
    public void add(E element){
        Node<E> current = new Node<>(element);
        this.tail.setNext(current);
        this.tail = current;
        return;
    }

    @Override
    public int size(){
        int s =0;
        Node<E> temp =this.head.getNext();
        while(temp != null){
            s++;
            temp=temp.getNext();
        }
        return s;
    }

    @Override
    public E get(int index){
        if (this.tail == null || index<0 || index>=this.size()){
            return null;
        }else{
            Node<E> temp =this.head.getNext();

            for (int i=0;i<index;i++){
                temp = temp.getNext();
            }
            return temp.getElement();
        }
    }

    @Override
    public void set(int index, E element){
        if (index<0 || index>this.size()){
            return;
        }else{
            Node<E> temp =this.head.getNext();

            for (int i=0;i<index;i++){
                temp = temp.getNext();
            }
            temp.setElement(element);
            return;
        }
    }

    @Override
    public void clear(){
            //when i dont reference the object the garbage collector will auto delete it 
            //i didnt delete the elemnts myself so if they are used (يعني referenced) some where else in the code
            this.head.setNext(null);
            this.tail=null;
        }

    @Override
    public boolean isEmpty(){
        return this.head.getNext()==null;}


     @Override
    public void remove(int index){
        if (index<0 || index>=this.size()){
                return;
        }else{

                Node<E> temp =this.head;

                for (int i=0;i<index;i++){
                    temp = temp.getNext();
                }
                Node<E> trash =temp.getNext();

                temp.setNext(trash.getNext());

                if (trash==this.tail) {
                    this.tail=temp;
                }
                trash.setNext(null);
                return;
            }

        }

    @Override
    public ILinkedList<E> sublist(int fromIndex, int toIndex){
        if (fromIndex<0 ||toIndex<fromIndex || toIndex>this.size()){
                return null;
        }           
        
        Node<E> temp =this.head.getNext();     
        LinkedList<E> newL=new LinkedList<E>();

        for (int i=0;i<toIndex+1;i++){
                if (fromIndex <= i){
                    newL.add(temp.getElement());
                }
                temp = temp.getNext();
            }
        return newL;
    }

    @Override
    public boolean contains(E o){
        Node<E> temp =this.head.getNext();
        while(temp != null){
            if (temp.getElement()==o){return true;}
            temp=temp.getNext();
        }
        return false;
    }

    @Override
    public String toString(){
        String s ="";
        Node<E> temp =this.head.getNext();
        while(temp != null){
            s+=("|"+temp.getElement().toString()+"|"+"---->");
            temp=temp.getNext();
        }
        s+="null";
        return s;
    }

    public Node<E> getHead(){
        return this.head.getNext();
    }
    public Node<E> getTail(){
        return this.tail;
    }
    // public void setTail(){
    //     Node<E> temp =this.head.getNext();
    //     while(temp != null){
    //         if (temp.getNext()==null){
    //             this.tail=temp;
    //         }
    //         temp=temp.getNext();
    //     }
    // }
}
