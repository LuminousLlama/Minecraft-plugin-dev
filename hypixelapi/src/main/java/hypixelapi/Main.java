package hypixelapi;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.gson.JsonArray;

import net.hypixel.api.HypixelAPI;

public class Main {

	
	public static void main(String args[]) throws InterruptedException, ExecutionException {
		HypixelAPI api = new HypixelAPI(UUID.fromString("777d8c79-672a-494e-bab2-097dfdb38ec1"));
		
		JsonArray allAuctions = new JsonArray();
		int totalPages = api.getSkyBlockAuctions(0).get().getTotalPages();
		
		api.getSkyBlockAuctions(0).whenComplete((page,throwable) -> {
			if(throwable != null) {
				throwable.printStackTrace();
				return;
			}
			
			allAuctions.add(page.getAuctions());
			System.out.println("------------------------ DONE -------------------");
			System.out.println(allAuctions.size());
			System.out.println(allAuctions.get(0));
		});

//		List<CompletableFuture<JsonArray>> futureList = new ArrayList<>();
//		
//		for(int pageNum = 0; pageNum<totalPages;pageNum++) {
//		
//			api.getSkyBlockAuctions(pageNum).whenComplete((page, throwable) -> allAuctions.add(page.getAuctions()));				
//		}
//	
//		try {
//		    CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0])).get();
//		   
//		    // Ensures that all the futures are done meaning that all pages have been gotten and all auctions are in the json array
//		}catch (Exception e){
//		    e.printStackTrace(); // Something went wrong :(
//		}
		
		
		
		
//		Runnable helloRunnable = new Runnable() {
//		    public void run() {
//		    	System.out.println("test");
//		    }
//		};
//		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//		executor.scheduleAtFixedRate(helloRunnable, 0, 1, TimeUnit.SECONDS);
		
		
//		System.out.println(allAuctions.size());
//		for(int i =0;i<10;i++) {
//			System.out.println(allAuctions.get(i).toString());
//		}
		
		
//		String s = "testString";
//		System.out.println(s.contains("000"));
		
		
	}
}
