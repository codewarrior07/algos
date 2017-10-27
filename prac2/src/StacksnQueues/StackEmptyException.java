package StacksnQueues;

public class StackEmptyException extends Exception {
	String msg;
	public StackEmptyException(String msg) {
		this.msg = msg;
	}
	
	public String toString() {
		return msg;
	}
}