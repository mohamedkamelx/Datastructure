package lab4;

public class Node<E>{
    private E element;
    private Node<E> next;

    public Node(E e) {
        this.element = e;
        this.next=null;
    }

    public E getElement() {
        return this.element;
    }

    public void setElement(E e) {
        this.element = e;
    }

    public Node<E> getNext() {
        return this.next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    
    @Override
    public String toString() {
        return getElement().toString();
    }
}