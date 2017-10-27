package StacksnQueues;

public class QueueEmptyException extends Exception {
	String msg;
	public QueueEmptyException(){
		this.msg = "Empty queue";
	}
	public QueueEmptyException(String msg){
		this.msg = msg;
	}
	public String toString() {
		return this.msg;
	}
}