package concurrency;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DelayQ {
	public static void main(String[] args){
		DelayQueue<Job> dq = new DelayQueue<Job>();
		Random rand = new Random();
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i=0;i<5;++i){
			exec.submit(new Runnable(){
				@Override
				public void run(){
					Client client = new Client(dq);
					Runnable runnable = new Runnable(){
						@Override
						public void run(){
							System.out.println("Executing thread"+Thread.currentThread().getName());
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e){}
						}
					};
					Job job = new Job(runnable,System.nanoTime()+rand.nextInt(500),TimeUnit.MILLISECONDS);
					client.addJob(job);
				}
			});
		}
		exec.submit(new Runnable(){
			@Override
			public void run(){
				Server server = new Server(dq);
				server.executeJobs();
			}
		});
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e){}
		exec.shutdown();
	}
}

class Job implements Delayed{
	private Runnable job;
	private long startTime;
	private TimeUnit timeUnit;
	public Job(Runnable job, long startTime,TimeUnit timeUnit){
		this.job = job;
		this.timeUnit = timeUnit;
		this.startTime = TimeUnit.NANOSECONDS.convert(startTime,this.timeUnit);
		System.out.println(this.startTime);
	}

	@Override
	public long getDelay(TimeUnit timeUnit){
		return TimeUnit.NANOSECONDS.convert(this.startTime-System.nanoTime(),this.timeUnit);
	}

	@Override
	public int compareTo(Delayed o){
		if(o != null && o instanceof Job){
			if(this.startTime < ((Job)o).startTime)
				return -1;
			else if(this.startTime > ((Job)o).startTime)
				return 1;
			else
				return 0;
		}
		return -1;
	}

	public Runnable getJob(){
		return this.job;
	}
}

class Client {
	private DelayQueue<Job> dq;
	public Client(DelayQueue<Job> dq){
		this.dq = dq;
	}
	public void addJob(Job job){
		this.dq.add(job);
	}
}

class Server {
	private DelayQueue<Job> dq;
	public Server(DelayQueue<Job> dq){
		this.dq = dq;
	}

	public void executeJobs(){
		while(true){
			try {
				dq.take().getJob().run();
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
