package de.iterator;

import com.google.common.base.Preconditions;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator over the Integer entries of a nested Object array.
 */
public class NestedIterator implements Iterator<Integer> {
    private final Object[] nestedArray;
    private NestedIterator nestedIterator;
    private int nextIndex;
    private Integer nextInteger;

    public NestedIterator(Object[] nestedArray){
        this.nestedArray = Preconditions.checkNotNull(nestedArray, "Tried to initialize an iterator with null!");
        this.nextInteger = getNextInteger();
    }

    @Override
    public boolean hasNext() {
       return nextInteger != null;
    }

    @Override
    public Integer next(){
        if (hasNext()){
            Integer nextIntegerCopy = nextInteger;
            nextInteger = getNextInteger();
            return nextIntegerCopy;
        } else {
            throw new NoSuchElementException("No more element at index: " + nextIndex);
        }
    }

    private Integer getNextInteger(){
        if (nestedIterator != null){
            if (nestedIterator.hasNext()){
                return nestedIterator.next();
            } else {
                nestedIterator = null;
            }
        }
        if (nextIndex >= nestedArray.length) {
            return null;
        }
        Object nextElement = nestedArray[nextIndex];
        if (nextElement instanceof Integer) {
            nextIndex++;
            return (Integer) nextElement;
        } else if (nextElement instanceof Object[]){
            nestedIterator = new NestedIterator((Object[]) nestedArray[nextIndex]);
            nextIndex++;
            return getNextInteger();
        } else {
            // ignore empty arrays and unexpected data structures
            nextIndex++;
            return getNextInteger();
        }
    }

}
