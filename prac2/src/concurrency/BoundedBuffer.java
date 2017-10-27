package concurrency;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer<T> {
	T[] buffer;
	int count, in, out,size;
	Lock mutex;
	Condition elements;
	Condition spaces;
	@SuppressWarnings("unchecked")
	public BoundedBuffer(Class<T> c,int size) {
		mutex = new ReentrantLock();
		elements = mutex.newCondition();
		spaces = mutex.newCondition();
		buffer = (T[])Array.newInstance(c, size);
	}
	
	public void deposit(T val) throws InterruptedException {
		mutex.lock();
		try {
			while(count==size)
				spaces.await();
			buffer[in] = val;
			in = (in+1)%size;
			++count;
			elements.signal();
		} finally {
			mutex.unlock();
		}
	}
	
	public T fetch() throws InterruptedException {
		mutex.lock();
		T val;
		try {
			while(count==0)
				elements.await();
			val = buffer[out];
			out = (out+1)%size;
			--count;
			spaces.signal();
			return val;
		} finally {
			mutex.unlock();
		}
	}
	
	public static void main(String[] args) {

	}

}
