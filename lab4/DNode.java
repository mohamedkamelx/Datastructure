package lab4;

public class DNode<E> extends Node<E>{
    private DNode<E> prev;
    private DNode<E> next;

    public DNode(E e) {
        super(e);
        this.next=null;
        this.prev=null;
    }

    public DNode<E> getNext() {
        return this.next;
    }

    public void setNext(DNode<E> next) {
        this.next = next;
    }

    public DNode<E> getPrev(){
        return this.prev;
    }
    public void setPrev(DNode<E> p) {
        this.prev = p;
    }
}