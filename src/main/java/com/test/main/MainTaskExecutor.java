package com.test.main;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.test.runnables.PriceCalculator;
import com.test.utils.TaskResource;

public class MainTaskExecutor {
	public static void main(String[] args) {

		int threadCounter = 0;
		
		JSONParser jsonParser = new JSONParser();
		JSONArray componentsList = null;
		
		try {
			FileReader reader = new FileReader("./src/main/resources/components.json");
			Object obj = jsonParser.parse(reader);
			componentsList = (JSONArray) obj;
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		JSONObject obj = (JSONObject) componentsList.get(0);
		JSONObject components = (JSONObject) obj.get("components");
		

		TaskResource taskResource = new TaskResource((String)components.get("tyreType"),(String)components.get("frameType"),(String)components.get("numbersOfGear"), (String)components.get("date"));
		
		BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(5000);

		
		 ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 1000, TimeUnit.MILLISECONDS, blockingQueue);
		  executor.setMaximumPoolSize(10);
		  executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
			  public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				executor.execute(r);
			}
		});
		
		while (true) {
			threadCounter++;
			executor.execute(new PriceCalculator(taskResource));
			if (threadCounter == 1000)
				break;
		}
		executor.shutdown();
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
