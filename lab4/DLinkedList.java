package lab4;

public class DLinkedList<E> implements ILinkedList<E>{
    private DNode<E> head; // dummy head
    private DNode<E> trailer;// last real node (not dummy)

    
        /*
         * theempty Linked List will be at the form of: |dummyhead|<--->|dummytail|
        */
    public DLinkedList() {
        this.head = new DNode<>(null);      
        this.trailer = this.head;  
        this.head.setNext(this.trailer);
        this.trailer.setPrev(this.head);
    }

    @SafeVarargs
    public DLinkedList( E... nodes) {
        this.head = new DNode<>(null);     
        this.trailer = this.head;  
        this.head.setNext(this.trailer);
        this.trailer.setPrev(this.head);
        for(E i:nodes){
            this.add(i);
        }
    }
    public DLinkedList( DNode<E> head) {
        //add a starting node 
        this.head.setNext(head);
        head.setPrev(this.head);

        this.trailer=head;
    }
    @Override
    public void add(int index, E element){
        if (index == this.size()) {
            this.add(element);
            return;
        }
    
        if ((this.trailer == null && index != 0) || index<0 || index>this.size()){
            System.out.println("cant add to list at index "+index);
            return ;
        }else{
            DNode<E> newN = new DNode<>(element);
            DNode<E> temp =this.head; //this is the dummy head & this pointer will always point to the previous node to the index

            for (int i=0;i<index;i++){
                temp = temp.getNext();
            }

            newN.setNext(temp.getNext());
            newN.setPrev(temp);
            temp.getNext().setPrev(newN);
            temp.setNext(newN);
            return;


        }
    }

    @Override
    public void add(E element){
        DNode<E> current = new DNode<>(element);
        current.setPrev(this.getTail());
        this.trailer.setNext(current);
        this.trailer = current;
        return;
    }

    @Override
    public int size(){
        //we can store size in a avariable insted to reduce complexity of O(n)
        int s =0;
        DNode<E> temp =this.head.getNext();
        while(temp != null){
            s++;
            temp=temp.getNext();
        }
        return s;
    }

    @Override
    public E get(int index){
        if (this.trailer == null || index<0 || index>this.size()){
            return null;
        }else{
            DNode<E> temp =this.head.getNext();

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
            DNode<E> temp =this.head.getNext();

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
            this.trailer=this.head;
        }

    @Override
    public boolean isEmpty(){
        return this.head.getNext()==this.trailer;}


    @Override
    public void remove(int index){
        if (index<0 || index>=this.size()){
                return;
        }else{

                DNode<E> temp =this.getHead();//the one that will be removed when found

                for (int i=0;i<index;i++){
                    temp = temp.getNext();
                }
                temp.getPrev().setNext(temp.getNext());

                temp.getNext().setPrev( temp.getPrev());
                return;

            }

        }

    @Override
    public ILinkedList<E> sublist(int fromIndex, int toIndex){
        if (fromIndex<0 ||toIndex<=fromIndex || toIndex>this.size()){
                return null;
        }           
        
        DNode<E> temp =this.head.getNext();     
        DLinkedList<E> newL=new DLinkedList<E>();

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
        DNode<E> temp =this.head.getNext();
        while(temp != this.trailer){
            temp=temp.getNext();
            if (temp.getElement()==o){return true;}
        }
        return false;
    }

    @Override
    public String toString(){
        String s ="";
        DNode<E> temp =this.head.getNext();
        while(temp != null){
            s+=("|"+temp.getElement().toString()+"|"+"<---->");
            temp=temp.getNext();
        }
        s+="null";
        return s;
    }
    public DNode<E> getHead(){
        return this.head.getNext();
    }
    
    public DNode<E> getTail(){
        return this.trailer;
    }
    public void setTail(DNode<E> n){
        this.trailer=n;
    }
    public void setHead(DNode<E> n){
        this.head.setNext(n);
        n.setPrev(this.head);
    }
}
