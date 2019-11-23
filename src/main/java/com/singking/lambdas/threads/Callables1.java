package com.singking.lambdas.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Callables1 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Callable<Integer> task = () -> {
			try {
				TimeUnit.SECONDS.sleep(1);
				return 123;
			} catch (InterruptedException e) {
				throw new IllegalStateException("task interrupted", e);
			}
		};

		/*
		 * Callables can be submitted to executor services just like runnables.
		 * But what about the callables result? Since submit() doesn't wait
		 * until the task completes, the executor service cannot return the
		 * result of the callable directly. Instead the executor returns a
		 * special result of type Future which can be used to retrieve the
		 * actual result at a later point in time.
		 */

		ExecutorService executor = Executors.newFixedThreadPool(1);
		Future<Integer> future = executor.submit(task);

		System.out.println("future done? " + future.isDone());

		Integer result = future.get();

		/*
		 * After submitting the callable to the executor we first check if the
		 * future has already been finished execution via isDone(). I'm pretty
		 * sure this isn't the case since the above callable sleeps for one
		 * second before returning the integer.
		 *
		 * Calling the method get() blocks the current thread and waits until
		 * the callable completes before returning the actual result 123. Now
		 * the future is finally done and we see the following result on the
		 * console:
		 */
		System.out.println("future done? " + future.isDone());
		System.out.print("result: " + result);
	}
}
