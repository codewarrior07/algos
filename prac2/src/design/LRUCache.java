package design;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LRUCache {
    private ConcurrentMap<Integer,Node> map;
    private DLL dll;
    int maxSize;
    public LRUCache(int capacity) {
        maxSize = capacity;
        map = new ConcurrentHashMap<Integer,Node>();
        dll = new DLL();
    }
    private class Node {
        int key;
        int val;
        Node next;
        Node prev;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    private class DLL {
        Node head;
        Node tail;
        int currSize;
        DLL() {
            currSize = 0;
        }
        void remove(Node node) {
            if(node == null)
                return;
            Node next = node.next;
            if(next != null)
            	next.prev = node.prev;
            Node prev = node.prev;
            if(prev != null)
            	prev.next = node.next;
            if(next != null || prev != null)
            	--currSize;
            if(node == head)
            	head = next;
            if(node == tail)
            	tail = prev;
            node.next = null;
            node.prev = null;
        }
        void moveToFront(Node node) {
            if(node == head)
                return;
            remove(node);
            node.next = head;
           if(head != null)
        	   head.prev = node;
            head = node;
            if(tail == null)
                tail = node;
        }
    }
    public int get(int key) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            dll.moveToFront(node);
            return node.val;
        }
        return -1;
    }
    
    public void set(int key, int value) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            dll.moveToFront(node);
        } else {
            if(dll.currSize == maxSize) {
                map.remove(dll.tail.key);
                dll.remove(dll.tail);
            }
            Node node = new Node(key,value);
            map.put(key,node);
            ++dll.currSize;
            dll.moveToFront(node);
        }
    }
    
    public static void main(String[] args) {
    	LRUCache cache = new LRUCache(5);
    	for(int i=0;i<5;++i)
    		cache.set(i,100+i);
    	cache.set(5,105);
    	System.out.println(cache.dll.currSize);
    }
}
