package com.project.cricket.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;

@Component
public class ExecutorUtil {

	public ExecutorService getThreadPool(int numOfThreads) {
		return Executors.newFixedThreadPool(numOfThreads);
	}

}
