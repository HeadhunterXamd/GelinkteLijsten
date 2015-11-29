package nl.hr.cmi.infdev226a;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.NoSuchElementException;

public class GelinkteLijst {
	
	
	/**
	 * Alleen de gelinkte lijst heeft toegang tot de node
	 */
	private class Node{
		//Dit is de data die je opslaat
		Object data;
		
		// referenties naar de eerste en laatste nodes
		Node next, previous;

	}
	
	
	private Node first, last;
	private int size;


    public Object getFirst(){
		if(first != null){
			return first.data;
		}
		return null;
	}


    public Object getLast(){
		if(last != null){
			return last.data;
		}
		return null;
	}	
	
	/**
	 * Voeg toe aan de voorkant
	 */
    public void insertFirst(Object o){
		//Create a new node and set all its references
		Node newNode = new Node();
		newNode.data = o;
		newNode.next = first;
		newNode.previous = null;

		//if the list is not empty
        if(this.first != null) {
            //set the old first's previous to the newNode
            this.first.previous = newNode;
        }else{
            //list was empty, newNode will be the only node.
            //so also set the last
            this.last = newNode;
        }

		//set the head to the newNode
		this.first = newNode;
	}

	/**
	 * Voeg toe aan de achterkant
	 */
    public void insertLast(Object o){
		Node newNode = new Node();
		newNode.data = o;
		if(last != null){
			newNode.previous = last;
			newNode.previous.next = newNode;
		}
		newNode.next = null;
		last = newNode;
	}
	
	/**
	 * Voeg toe voor een ander element
	 */
    public void insertBefore(Object o, Object before){
		Node bef = RecursiveFind(first, before);
		Node newnode = new Node();
		newnode.data = o;
		if(bef.previous != null){
			newnode.previous = bef.previous;
			newnode.previous.next = newnode;
			bef.previous = newnode;
			newnode.next = bef;
		}else{
			bef.previous = newnode;
			first = newnode;
			newnode.next = bef;
		}
	}
	
	/**
	 * Voeg toe na een ander element
	 */
    public void insertAfter(Object o, Object after){
		Node afternode = RecursiveFind(first, after);
		Node node = new Node();
		node.data = o;
		if(afternode.next != null){
			Node old = afternode.next;
			afternode.next = node;
			node.next = old;
			old.previous = node;
		}else{
			afternode.next = node;
			last = node;
		}
	}

	
	/**
	 * Verwijder een element
	 * @param data
	 */
    public void remove(Object data){
		Node n = RecursiveFind(first, data);
		if(n != null){
			if(n.next != null){
				if(n.previous != null){
					n.next.previous = n.previous;
					n.previous.next = n.next;
				}else{
					n.next.previous = null;
					first = n.next;
				}
			}else{
				if(n.previous != null){
					n.previous.next = null;
					last = n.previous;
				}else{
					first = null;
					last = null;
				}
			}
		}else{
			throw new NoSuchElementException();
		}
	}

	/**
	 * this recursive find function is an sequential search
	 * @return the Node which the data compares with the data given.
	 */
	private Node RecursiveFind(Node n, Object CompareData){
		if(n.data.equals(CompareData)){
			return n;
		}
		return RecursiveFind(n.next, CompareData);
	}
	
	/**
	 * Het aantal elementen in de gelinkte lijst
	 * @return
	 */
    public int getSize(){
        size = 0;
		if(first != null){
			RecursiveCount(first);
		}
		return size;
	}

	/**
	 * Recursieve functie om de aantal Nodes te tellen.
	 */
	private void RecursiveCount(Node n){
		if(n.next != null){
			size++;
			RecursiveCount(n.next);
		}else{
			size++;
		}
	}

	public void print(){
		recursivePrint(first);
	}

	private void recursivePrint(Node n){
		System.out.println(n.data);
		if(n.next != null){
			recursivePrint(n.next);
		}
	}

}
