package org.insa.graphs.algorithm.utils;

import java.util.ArrayList;

/**
 * Implements a binary heap containing elements of type E.
 *
 * Note that all comparisons are based on the compareTo method, hence E must
 * implement Comparable
 * 
 * @author Mark Allen Weiss
 * @author DLB
 */
public class BinaryHeap<E extends Comparable<E>> implements PriorityQueue<E> {

    // Number of elements in heap.
    private int currentSize;

    // The heap array.
    protected final ArrayList<E> array;

    /**
     * Construct a new empty binary heap.
     */
    public BinaryHeap() {
        this.currentSize = 0;
        this.array = new ArrayList<E>();
    }

    /**
     * Construct a copy of the given heap.
     * 
     * @param heap Binary heap to copy.
     */
    public BinaryHeap(BinaryHeap<E> heap) {
        this.currentSize = heap.currentSize;
        this.array = new ArrayList<E>(heap.array);
    }

    /**
     * Set an element at the given index.
     * 
     * @param index Index at which the element should be set.
     * @param value Element to set.
     */
    private void arraySet(int index, E value) {
        if (index == this.array.size()) {
            this.array.add(value);
        }
        else {
            this.array.set(index, value);
        }
    }

    /**
     * @return Index of the parent of the given index.
     */
    protected int indexParent(int index) {
        return (index - 1) / 2;
    }

    /**
     * @return Index of the left child of the given index.
     */
    protected int indexLeft(int index) {
        return index * 2 + 1;
    }

    /**
     * Internal method to percolate up in the heap.
     * 
     * @param index Index at which the percolate begins.
     */
    private void percolateUp(int index) {
        E x = this.array.get(index);

        for (; index > 0
                && x.compareTo(this.array.get(indexParent(index))) < 0; index = indexParent(
                        index)) {
            E moving_val = this.array.get(indexParent(index));
            this.arraySet(index, moving_val);
        }

        this.arraySet(index, x);
    }
    
    /**
     * Internal method to percolate down in the heap.
     * 
     * @param index Index at which the percolate begins.
     */
    private void percolateDown(int index) {
        int ileft = indexLeft(index);
        int iright = ileft + 1;

        if (ileft < this.currentSize) {
            E current = this.array.get(index);
            E left = this.array.get(ileft);
            boolean hasRight = iright < this.currentSize;
            E right = (hasRight) ? this.array.get(iright) : null;

            if (!hasRight || left.compareTo(right) < 0) {
                // Left is smaller
                if (left.compareTo(current) < 0) {
                    this.arraySet(index, left);
                    this.arraySet(ileft, current);
                    this.percolateDown(ileft);
                }
            }
            else {
                // Right is smaller
                if (right.compareTo(current) < 0) {
                    this.arraySet(index, right);
                    this.arraySet(iright, current);
                    this.percolateDown(iright);
                }
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return this.currentSize == 0;
    }

    @Override
    public int size() {
        return this.currentSize;
    }

    @Override
    public void insert(E x) {
        int index = this.currentSize++;
        this.arraySet(index, x);
        this.percolateUp(index);
    }
    
    @Override
    public void remove(E x) throws ElementNotFoundException {
    	//Si le tas est vide, il est inutile de chercher l'élément, on vérifie donc que le tas n'est pas vide
    	if (!this.isEmpty()) {
    		//Si le premier élément du tableau est celui qu'on veut supprimer
    		//il suffit de réutiliser la méthode deleteMin
	    	if (x == this.findMin()) {
	    		this.deleteMin();
	    	}
	    	//Si l'élément que l'on veut supprimer est le dernier du tas, on n'a qu'à
	    	//décrémenter currentSize pour l'ignorer
	    	else if (this.array.get(this.size() - 1) == x){
	    		currentSize--;
	    	}
	    	//Si l'élément n'est pas aux extrémités
	    	else {
	    		//On cherche dans un premier temps le rang de l'élément en parcourant le tableau
		        int ElemIndex = 0;
		        boolean trouve = false;
		        while (ElemIndex < this.size() && !trouve) {
		        	if (this.array.get(ElemIndex) == x) {
		        		trouve = true;
		        	}
		        	else {
		        		ElemIndex++;
		        	}
		        }
		        //Si, en sortant de la boucle while, on n'a pas trouvé l'élément, on renvoie une exception
		        if (!trouve) {
		        	throw new ElementNotFoundException(x);
		        }
		        else {
		        	//si l'élément a été trouvé, on l'échange avec le dernier élément...
			        E temp = this.array.get(this.size() - 1);
			        this.arraySet(this.size() - 1, this.array.get(ElemIndex));
			        this.arraySet(ElemIndex, temp);
			        this.currentSize--; //Element supprimé => taille qui baisse
			        //Puis on replace le tas dans un ordre correct
			        this.percolateUp(ElemIndex);
			        this.percolateDown(ElemIndex);
		        }
	    	}
    	}
    	else {
    		throw new ElementNotFoundException(x);
    	}
    }

    @Override
    public E findMin() throws EmptyPriorityQueueException {
        if (isEmpty())
            throw new EmptyPriorityQueueException();
        return this.array.get(0);
    }

    @Override
    public E deleteMin() throws EmptyPriorityQueueException {
        E minItem = findMin();
        E lastItem = this.array.get(--this.currentSize);
        this.arraySet(0, lastItem);
        this.percolateDown(0);
        return minItem;
    }

    /**
     * Creates a multi-lines string representing a sorted view of this binary heap.
     * 
     * @return a string containing a sorted view this binary heap.
     */
    public String toStringSorted() {
        return BinaryHeapFormatter.toStringSorted(this, -1);
    }

    /**
     * Creates a multi-lines string representing a sorted view of this binary heap.
     * 
     * @param maxElement Maximum number of elements to display. or {@code -1} to
     *                   display all the elements.
     * 
     * @return a string containing a sorted view this binary heap.
     */
    public String toStringSorted(int maxElement) {
        return BinaryHeapFormatter.toStringSorted(this, maxElement);
    }

    /**
     * Creates a multi-lines string representing a tree view of this binary heap.
     * 
     * @return a string containing a tree view of this binary heap.
     */
    public String toStringTree() {
        return BinaryHeapFormatter.toStringTree(this, Integer.MAX_VALUE);
    }

    /**
     * Creates a multi-lines string representing a tree view of this binary heap.
     * 
     * @param maxDepth Maximum depth of the tree to display.
     * 
     * @return a string containing a tree view of this binary heap.
     */
    public String toStringTree(int maxDepth) {
        return BinaryHeapFormatter.toStringTree(this, maxDepth);
    }

    @Override
    public String toString() {
        return BinaryHeapFormatter.toStringTree(this, 8);
    }
    
    public boolean isValid() {
    	boolean valid = true;
    	for (int i = 0; i < this.currentSize && valid; i++) {
    		if (this.indexLeft(i) <= this.currentSize) {
    			if (this.array.get(this.indexLeft(i)).compareTo(this.array.get(i)) == -1) {
    				valid = false;
    			}
    			else {
    				if (this.indexLeft(i) + 1 < this.currentSize) {
    					if (this.array.get(this.indexLeft(i) + 1).compareTo(this.array.get(i)) == -1) {
    	    				valid = false;
    	    			}
    				}
    			}
    		}
    	}
    	return valid;
    }

}