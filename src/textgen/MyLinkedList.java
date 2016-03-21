package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if(element == null)
			throw new NullPointerException("Element cannot be Null.");
		LLNode<E> temp = new LLNode<E>(element);
		temp.data = element;
		temp.next = null;
		temp.prev = null;
		if(size() == 0){
			head.next = temp;
			temp.prev = head;
			temp.next = tail;
			tail.prev = temp;
			size++;
			return true;
		}
		else if(size() != 0){
			(tail.prev).next = temp;
			temp.prev = tail.prev;
			temp.next = tail;
			tail.prev = temp;
			size++;
			return true;
		}
		return false;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if(index == -1 || index >= size)
			throw new IndexOutOfBoundsException("Array Index is out of Bounds.");
		LLNode<E> temp = head;
		for(int i = 0; i <= index; i++){
			temp = temp.next;
		}
		return temp.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if(index == -1 || index > size)
			throw new IndexOutOfBoundsException("Array Index is out of Bounds.");
		if(element == null)
			throw new NullPointerException("Element cannot be Null.");
		LLNode<E> temp = new LLNode<E>(element);
		LLNode<E> temp1 = head;
 		temp.data = element;
		temp.next = null;
		temp.prev = null;
		for(int i = 0; i <= index - 1; i++)
			temp1 = temp1.next;
		temp.prev = temp1;
		temp.next = temp1.next;
		(temp1.next).prev = temp;
		temp1.next = temp;
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
		/*LLNode<E> temp = head;
		int count = -1;
		while(temp.next != null){
			temp = temp.next;
			count++;
		}
		return count;
		*/
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if(index == -1 || index > size || size == 0)
			throw new IndexOutOfBoundsException("Array Index is out of Bounds.");
		LLNode<E> temp = head;
		for(int i = 0; i <= index - 1; i++)
			temp = temp.next;
		LLNode<E> temp1 = new LLNode<E>((temp.next).data);
		temp.next = (temp.next).next;
		(temp.next).prev = temp;
		size--;
		return temp1.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if(index == -1 || index > size || size == 0)
			throw new IndexOutOfBoundsException("Array Index is out of Bounds.");
		if(element == null)
			throw new NullPointerException("Element cannot be Null.");
		LLNode<E> temp = head;
		for(int i = 0; i <= index; i++)
			temp = temp.next;
		LLNode<E> temp1 = new LLNode<E>(temp.data);
		temp.data = element;
		return temp1.data;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
