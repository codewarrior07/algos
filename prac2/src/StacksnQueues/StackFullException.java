package StacksnQueues;

public class StackFullException extends Exception {
	String msg;
	public StackFullException(String msg) {
		this.msg = msg;
	}
	
	public String toString() {
		return msg;
	}
}