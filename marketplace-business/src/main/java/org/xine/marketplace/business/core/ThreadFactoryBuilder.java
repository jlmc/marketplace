package org.xine.marketplace.business.core;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * The Class ThreadFactoryBuilder.
 * 
 * Every thread pool has an associated thread factory that creates the threads for the pool.
 * The thread factory not just creates thread instances but also configures them. A thread factory usually does the following:
 *   <ul>
 *       <li>Names the threads</li>
 *       <li>Sets daemon status</li>
 *       <li>Assigns thread priority</li>
 *       <li>Assigns the thread to a thread group</li>
 *       <li>Sets handler for uncaught exceptions</li>
 *   </ul>
 * 
 * The ThreadFactory instance should not be shared across thread pools.
 * Use one factory instance for one pool. Otherwise,
 * all the threads of the pools that share factory would have similar names and
 * you would not be able identify which thread belongs to which pool.
 */
public class ThreadFactoryBuilder {
	
	/** The name prefix. */
	private String namePrefix = null;
	
	/** The daemon. */
	private boolean daemon = false;
	
	/** The priority. */
	private int priority = Thread.NORM_PRIORITY;
	
	/** The backing thread factory. */
	private ThreadFactory backingThreadFactory = null;
	
	/** The uncaught exception handler. */
	private UncaughtExceptionHandler uncaughtExceptionHandler = null;
	
	/**
	 * Builds the.
	 *
	 * @return the thread factory
	 */
	public ThreadFactory build() {
		return build(this);
	}
	
	/**
	 * Builds the.
	 *
	 * @param builder the thread factory builder
	 * @return the thread factory
	 */
	private static ThreadFactory build(final ThreadFactoryBuilder builder) {
		final String namePrefix = builder.namePrefix;
		final Boolean daemon = builder.daemon;
		final Integer priority = builder.priority;
		final UncaughtExceptionHandler uncaughtExceptionHandler = builder.uncaughtExceptionHandler;
		
		final ThreadFactory backingThreadFactory = (builder.backingThreadFactory != null) ?
				builder.backingThreadFactory :
					Executors.defaultThreadFactory();
		
		final AtomicLong count = new AtomicLong(0);
		
		
		/*new ThreadFactory() {public Thread newThread(Runnable runnable) { */
		return runnable -> {
			final Thread thread = backingThreadFactory.newThread(runnable);
			if (namePrefix != null) {
				thread.setName(namePrefix + "-" + count.getAndIncrement());
			}
			if (daemon != null) {
				thread.setDaemon(daemon);
			}
			if (priority != null) {
				thread.setPriority(priority);
			}
			if (uncaughtExceptionHandler != null) {
				thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
			}
			return thread;
		};
	}

	/**
	 * Sets the uncaught exception handler.
	 *
	 * @param uncaughtExceptionHandler the uncaught exception handler
	 * @return the thread factory builder
	 */
	public ThreadFactoryBuilder setUncaughtExceptionHandler(final UncaughtExceptionHandler uncaughtExceptionHandler) {
		if (null == uncaughtExceptionHandler) {
			throw new NullPointerException("UncaughtExceptionHandler cannot be null");
		}
		this.uncaughtExceptionHandler = uncaughtExceptionHandler;
		
		return this;
	}
 
	/**
	 * Sets the thread factory.
	 *
	 * @param backingThreadFactory the backing thread factory
	 * @return the thread factory builder
	 */
	public ThreadFactoryBuilder setThreadFactory(final ThreadFactory backingThreadFactory) {
		if (null == this.uncaughtExceptionHandler) {
			throw new NullPointerException("BackingThreadFactory cannot be null");
		}
		this.backingThreadFactory = backingThreadFactory;
		
		return this;
	}
	
	/**
	 * Sets the priority.
	 *
	 * @param priority the priority
	 * @return the thread factory builder
	 */
	public ThreadFactoryBuilder setPriority(final int priority){
		if(priority < Thread.MIN_PRIORITY){
			throw new IllegalArgumentException(
					String.format("Thread priority (%s) must be >= %s", priority, Thread.MIN_PRIORITY));
		}
		if(priority > Thread.MAX_PRIORITY){
			throw new IllegalArgumentException(
					String.format("Thread priority (%s) must be <= %s", priority, Thread.MAX_PRIORITY));
		}
		
		this.priority = priority;
		
		return this;
	}
	
	/**
	 * Sets the daemon.
	 *
	 * @param daemon the daemon
	 * @return the thread factory builder
	 */
	public ThreadFactoryBuilder setDaemon(final boolean daemon){
		this.daemon = daemon;
		return this;
	}
	
	/**
	 * Sets the name prefix.
	 *
	 * @param namePrefix the name prefix
	 * @return the thread factory builder
	 */
	public ThreadFactoryBuilder setNamePrefix(final String namePrefix){
		if(namePrefix == null) {
			throw new NullPointerException();
		}
		this.namePrefix = namePrefix;
		
		return this;
	}
}
