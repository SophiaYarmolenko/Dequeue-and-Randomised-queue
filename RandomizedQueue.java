//package com.company;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item>
{
    private int n = 0;
    private Item[] que;

    // construct an empty randomized queue
    public RandomizedQueue()
    {
        que = (Item[]) new Object[2];
    }

    // is the randomized queue empty?
    public boolean isEmpty()
    {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size()
    {
        return n;
    }

    private void resize(int capacity)
    {
        assert capacity >= n;
        Item[] que1 = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            que1[i] = que[i];
        }
        que = que1;
    }

    // add the item
    public void enqueue(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException();
        int len = que.length;
        if (n == len)
            resize(len*2);
        que[n++] = item;
    }

    // remove and return a random item
    public Item dequeue()
    {
        if (isEmpty())
            throw new NoSuchElementException();
        int rd = index();
        Item item = que[rd];
        if (rd != n-1){
            que[rd] = que[n-1];
        }
        que[n-1] = null;
        n--;
        int len = que.length;
        if (n > 0 && n == len/4)
            resize(len/2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample()
    {
        if (isEmpty())
            throw new NoSuchElementException();
        return que [ index() ];
    }

    private int index()
    {
        return StdRandom.uniform(0, n);
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RQueueIterator();
    }

    private class RQueueIterator implements Iterator<Item> {
        private Item[] copy =(Item[]) new Object[que.length];
        private int copySize = n;

        public RQueueIterator(){
            int len = que.length;
            for (int i = 0; i < len; i++){
                copy[i] = que[i];
            }
        }

        @Override
        public boolean hasNext() {
            return copySize > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int rd = StdRandom.uniform(copySize);
            Item item = copy[rd];
            if (rd != copySize - 1)
                copy[rd] = copy[copySize-1];
            copy[copySize-1] = null;
            copySize--;
            return item;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args)
    {
        /*RandomizedQueue que = new RandomizedQueue();
        Scanner myVar = new Scanner(System.in);
        que.enqueue(myVar.next());
        que.enqueue(myVar.next());
        que.enqueue(myVar.next());
        que.enqueue(myVar.next());
        System.out.println(que.dequeue());
        System.out.println(que.dequeue());
        System.out.println(que.dequeue());
        System.out.println(que.dequeue());*/
    }

}