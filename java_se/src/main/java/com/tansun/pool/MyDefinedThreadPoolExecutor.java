package com.tansun.pool;

import org.springframework.stereotype.Component;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 自定义多线程
 * @author Administrator
 *
 */
@Component
public class MyDefinedThreadPoolExecutor {
	/**
	 * 线程的最大并发量：最大线程数+任务队列数
	 * 线程池-线程启动过程：有新的任务：①先核心线程->②任务队列->
	 * 					③还未达到最大线程数时，会启动新的线程来执行
	 * 线程拒绝执行任务默认策略：$AbortPolicy是内部类
	 * ThreadFactory线程工厂:只有newThread()方法，返回的是线程对象
	 * 						可以修改线程名字
	 */
	private static ThreadPoolExecutor threadPoolExecutor;
	private static BlockingQueue<Runnable> blockingQueue;
	private static ThreadFactory threadFactory;
	private static AtomicLong atomicLong;// CAS原子类

	public static void main(String[] args) {
		blockingQueue = new LinkedBlockingQueue<>(2);// 队列任务数为2
		blockingQueue = new ArrayBlockingQueue<>(2);
		atomicLong = new AtomicLong(1);// 线程安全的整数自增序列类,从1开始

		// 获取线程工厂
		getThreadFactory();
		// 获取线程池
		getThreadPoolExecutor();

		// 让核心线程受keepAliveTime控制--之后线程池也会关闭--没有任务可分配时，允许核心线程超时
//		threadPoolExecutor.allowCoreThreadTimeOut(true);

		//模拟此线程池处理最大的请求数
//		handlerMaxRequestNum();

		//模拟此线程池处理最大的请求 因资源不够 拒绝执行任务
		handlerMaxRequestRejectedExecution();
		
		//模拟线程超时，并消亡，新的任务产生，并启动新的线程来执行任务
//		simulatThreadTimeOutNewThread();
		
		// 执行完任务后，关闭线程资源（关闭了线程池）
//		threadPoolExecutor.shutdown();

	}
/*===============================================================================================*/
	/**
	 * 获取线程工厂，返回的是一个线程
	 */
	public static ThreadFactory getThreadFactory() {
		if (null == threadFactory){
			synchronized (ThreadFactory.class){
				if (null == threadFactory){
					threadFactory  = new ThreadFactory() {
						@Override
						public Thread newThread(Runnable r) {
							//getAndIncrement();从得到的数据开始自增
							//incrementAndGet();从得到的数据，先自增并获取
							String name = "自定义threadName-"+atomicLong.getAndIncrement();
							return new Thread(r, name);
						}
					};
				}
			}
		}
		return threadFactory;
	}

	/**
	 * 利用双重校验锁 创建单例线程池
	 * @return
	 */
	public static ThreadPoolExecutor getThreadPoolExecutor() {
		if (null == threadPoolExecutor){
			synchronized (ThreadPoolExecutor.class){
				if (null == threadPoolExecutor){
					threadPoolExecutor = new ThreadPoolExecutor(2, // corePoolSize核心线程数
							3, // maximumPoolSize 最大线程数
							2, // keepAliveTime 空闲时间
							TimeUnit.SECONDS, // unit 时间单位
							blockingQueue,// workQueue 任务队列
							threadFactory);// threadFactory 线程工厂
//													handler);// handler 拒绝执行任务时的处理策略
				}
			}
		}
		return threadPoolExecutor;
	}

	/**
	 * 模拟此线程池处理最大的请求数
	 */
	private static void handlerMaxRequestNum() {
		threadPoolExecutor.execute(new Runnable() {
			@Override
			public void run() {
				String threadName = Thread.currentThread().getName();
				System.out.println(threadName+"->"+"执行任务01");
				// 模拟线程执行任务消耗时长
				try {Thread.currentThread().sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}
			}
		});
		
		threadPoolExecutor.execute(new Runnable() {
			@Override
			public void run() {
				String threadName = Thread.currentThread().getName();
				System.out.println(threadName+"->"+"执行任务02");
				// 模拟线程执行任务消耗时长
				try {Thread.currentThread().sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}
			}
		});
		
		threadPoolExecutor.execute(new Runnable() {
			@Override
			public void run() {
				String threadName = Thread.currentThread().getName();
				System.out.println(threadName+"->"+"执行任务03");
				// 模拟线程执行任务消耗时长
//				try {Thread.currentThread().sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}
			}
		});
		
		threadPoolExecutor.execute(new Runnable() {
			@Override
			public void run() {
				String threadName = Thread.currentThread().getName();
				System.out.println(threadName+"->"+"执行任务04");
				// 模拟线程执行任务消耗时长
//				try {Thread.currentThread().sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}
			}
		});
		
		threadPoolExecutor.execute(new Runnable() {
			@Override
			public void run() {
				String threadName = Thread.currentThread().getName();
				System.out.println(threadName+"->"+"执行任务05");
				// 模拟线程执行任务消耗时长
//				try {Thread.currentThread().sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}
			}
		});
	}


	private static void handlerMaxRequestRejectedExecution(){
		for (int i = 0; i < 6; i++) {
			int tastNum = i+1;
			threadPoolExecutor.execute(new Runnable() {
				@Override
				public void run() {
					String threadName = Thread.currentThread().getName();
					System.out.println(threadName+"->"+"执行了任务，任务第"+ tastNum +"次被执行");
				}
			});
		}
	}
	

	/**
	 * 模拟线程超时，并消亡，新的任务产生，并启动新的线程来执行任务
	 */
	private static void simulatThreadTimeOutNewThread() {
		// 模拟没有新的任务进来，主线程进入等待，让线程三休眠后，消亡
		System.out.println(Thread.currentThread().getName()+"线程-------等待5秒");
		try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
		
		threadPoolExecutor.execute(new Runnable() {
			@Override
			public void run() {
				String threadName = Thread.currentThread().getName();
				System.out.println(threadName+"->"+"执行任务06");
				// 模拟线程执行任务消耗时长
				try {Thread.currentThread().sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}
			}
		});
		
		
		threadPoolExecutor.execute(new Runnable() {
			@Override
			public void run() {
				String threadName = Thread.currentThread().getName();
				System.out.println(threadName+"->"+"执行任务07");
				// 模拟线程执行任务消耗时长
//				try {Thread.currentThread().sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}
			}
		});

		System.out.println(Thread.currentThread().getName());
		try {Thread.currentThread().sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}

		threadPoolExecutor.execute(new Runnable() {
			@Override
			public void run() {
				String threadName = Thread.currentThread().getName();
				System.out.println(threadName+"->"+"执行任务08");
				// 模拟线程执行任务消耗时长
				try {Thread.currentThread().sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}
			}
		});
	}

	private void get(){}

	public  static  void getget(){};
}
