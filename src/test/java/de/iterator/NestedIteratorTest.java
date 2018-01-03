package de.iterator;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

public class NestedIteratorTest {

    @Test
    public void testIterationSingleNested(){
        Object[] testArray = new Object[4];
        Object[] nestedArray = new Object[2];

        testArray[0] = 0;
        testArray[1] = 1;
        testArray[2] = nestedArray;
        testArray[3] = 3;

        nestedArray[0] = 4;
        nestedArray[1] = 5;

        NestedIterator nestedIterator = new NestedIterator(testArray);

        Assert.assertTrue(nestedIterator.hasNext());
        Assert.assertEquals(0, nestedIterator.next().intValue());

        Assert.assertTrue(nestedIterator.hasNext());
        Assert.assertEquals(1, nestedIterator.next().intValue());

        Assert.assertTrue(nestedIterator.hasNext());
        Assert.assertEquals(4, nestedIterator.next().intValue());

        Assert.assertTrue(nestedIterator.hasNext());
        Assert.assertEquals(5, nestedIterator.next().intValue());

        Assert.assertTrue(nestedIterator.hasNext());
        Assert.assertEquals(3, nestedIterator.next().intValue());

        Assert.assertFalse(nestedIterator.hasNext());
    }

    @Test
    public void testIterationDoublyNested(){
        Object[] testArray = new Object[3];
        Object[] nestedArrayA = new Object[3];
        Object[] nestedArrayB = new Object[2];

        testArray[0] = 0;
        testArray[1] = nestedArrayA;
        testArray[2] = 1;

        nestedArrayA[0] = 2;
        nestedArrayA[1] = nestedArrayB;
        nestedArrayA[2] = 3;

        nestedArrayB[0] = 4;
        nestedArrayB[1] = 5;

        NestedIterator nestedIterator = new NestedIterator(testArray);

        Assert.assertTrue(nestedIterator.hasNext());
        Assert.assertEquals(0, nestedIterator.next().intValue());

        Assert.assertTrue(nestedIterator.hasNext());
        Assert.assertEquals(2, nestedIterator.next().intValue());

        Assert.assertTrue(nestedIterator.hasNext());
        Assert.assertEquals(4, nestedIterator.next().intValue());

        Assert.assertTrue(nestedIterator.hasNext());
        Assert.assertEquals(5, nestedIterator.next().intValue());

        Assert.assertTrue(nestedIterator.hasNext());
        Assert.assertEquals(3, nestedIterator.next().intValue());

        Assert.assertTrue(nestedIterator.hasNext());
        Assert.assertEquals(1, nestedIterator.next().intValue());

        Assert.assertFalse(nestedIterator.hasNext());
    }

    @Test
    public void testIterationNestedWithEmpty(){
        Object[] testArray = new Object[5];
        Object[] nestedArrayA = new Object[2];
        Object[] nestedArrayB = new Object[0];

        testArray[1] = 0;
        testArray[2] = nestedArrayA;
        testArray[3] = 1;

        nestedArrayA[0] = nestedArrayB;
        nestedArrayA[1] = 2;

        NestedIterator nestedIterator = new NestedIterator(testArray);

        Assert.assertTrue(nestedIterator.hasNext());
        Assert.assertEquals(0, nestedIterator.next().intValue());

        Assert.assertTrue(nestedIterator.hasNext());
        Assert.assertEquals(2, nestedIterator.next().intValue());

        Assert.assertTrue(nestedIterator.hasNext());
        Assert.assertEquals(1, nestedIterator.next().intValue());

        Assert.assertFalse(nestedIterator.hasNext());
    }


    @Test
    public void testIterationWithNulls(){
        Object[] testArray = new Object[10];

        testArray[2] = 1;
        testArray[7] = 2;

        NestedIterator nestedIterator = new NestedIterator(testArray);

        Assert.assertTrue(nestedIterator.hasNext());
        Assert.assertEquals(1, nestedIterator.next().intValue());

        Assert.assertTrue(nestedIterator.hasNext());
        Assert.assertEquals(2, nestedIterator.next().intValue());

        Assert.assertFalse(nestedIterator.hasNext());
    }

    @Test
    public void testIterationWithNonInteger(){
        Object[] testArray = new Object[10];

        testArray[2] = 1;
        testArray[5] = 5.0;
        testArray[6] = "string";
        testArray[7] = 7;

        NestedIterator nestedIterator = new NestedIterator(testArray);

        Assert.assertTrue(nestedIterator.hasNext());
        Assert.assertEquals(1, nestedIterator.next().intValue());

        Assert.assertTrue(nestedIterator.hasNext());
        Assert.assertEquals(7, nestedIterator.next().intValue());

        Assert.assertFalse(nestedIterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNoSuchElementException(){
        Object[] testArray = new Object[0];

        NestedIterator nestedIterator = new NestedIterator(testArray);

        Assert.assertFalse(nestedIterator.hasNext());
        nestedIterator.next();
    }

}
