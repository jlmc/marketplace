package org.xine.marketplace.business.core;

import java.util.concurrent.ThreadFactory;

import org.junit.Test;

public class ThreadFactoryBuilderTest {
	
	@Test
	public void testBuildSimple(){
		final ThreadFactory custom = new ThreadFactoryBuilder()
				.setNamePrefix("ImageDownloaderPool - Thread")
				.build();
	}

	@Test
	public void testBuildComplete() {
		final ThreadFactory custom = new ThreadFactoryBuilder()
				.setNamePrefix("ImageDownloaderPool - Thread")
				.setDaemon(false)
				.setPriority(Thread.MAX_PRIORITY)
				.setUncaughtExceptionHandler((thread, throwable) -> System.out.println(
						String.format(
						"Thread %s threw exception - %s", thread.getName(), throwable.getMessage())))
				.build();
				
	}

}
