//package com.company;
import java.util.Iterator;
//import java.util.Scanner;

public class Deque<Item> implements Iterable<Item> {
    private Node <Item> first;
    private Node <Item> last;
    private int n;

    private static class Node <Item>
    {
        private Item item;
        private Node <Item> next;
        private Node <Item> previous;
    }

    // construct an empty deque
    public Deque()
    {
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty()
    {
        return n == 0;
    }

    // return the number of items on the deque
    public int size()
    {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item)
    {
        if(item == null)
            throw new IllegalArgumentException("Item is not correct");

        Node <Item> oldfirst = first;
        first = new Node();
        first.next = oldfirst;
        first.item = item;
        if (n > 0)
            oldfirst.previous = first;
        else last = first;
        n++;

    }

    // add the item to the back
    public void addLast(Item item)
    {
        if(item == null)
            throw new IllegalArgumentException("Item is not correct");

        Node <Item> oldlast = last;
        last = new Node();
        last.previous = oldlast;
        last.item = item;
        if (n > 0)
            oldlast.next = last;
        else first = last;
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst()
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException("There are no elements to delete");


        Item item = (Item) first.item;
        if (n > 1)
        {
            first = first.next;
            first.previous = null;
        }
        else
            {
                first = null;
                last = null;
            }
        n--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast()
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException("There are no elements to delete");

        Item item = (Item) last.item;
        if (n > 1)
        {
            last = last.previous;
            last.next = null;
        }
        else
            {
                first = null;
                last = null;
            }
        n--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator <Item> iterator()
    {
        return new ListIterator();
    }

    private class ListIterator implements Iterator <Item>
    {
        private Node <Item> current = first;
        public boolean hasNext()
        {
            return current != null;
        }
        public Item next()
        {
            if(!hasNext())
                throw new java.util.NoSuchElementException("There are no element");
            Item item = (Item) current.item;
            current = current.next;
            return item;
        }
        public void remove()
        {
            throw new UnsupportedOperationException("Remove() was use");
        }
    }
    // unit testing (required)
    public static void main(String[] args)
    {
        /*Deque deque = new Deque();
        Scanner myVar = new Scanner(System.in);
        deque.addLast(myVar.next());
        deque.addLast(myVar.next());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeLast());*/
    }

}