package hypixelapi;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import net.hypixel.api.HypixelAPI;
//import net.minecraft.nbt.CompressedStreamTools;



public class Test {
	
	static int temp;
	static int count=1;
	
	static JsonArray allAuctions = new JsonArray();
	public static void main(String agrs[]) {
		HypixelAPI api = new HypixelAPI(UUID.fromString("777d8c79-672a-494e-bab2-097dfdb38ec1"));

		api.getSkyBlockAuctions(0).whenComplete((page0, throwable) -> {
//			System.out.println(page0.getPage());
//			System.out.println(page0.getTotalPages());
//			System.out.println(page0.getTotalAuctions());
//			
			allAuctions.addAll(page0.getAuctions());
			//System.out.println(allAuctions.size());
			
			setTemp(page0.getTotalPages());
			System.out.println(getTemp());
			for(int i = 1 ; i < page0.getTotalPages() ; i++) {
				api.getSkyBlockAuctions(i).whenComplete((page, throwable0) -> {
					if (throwable0 != null) {
						throwable0.printStackTrace();
						System.exit(0);
						return;
					}
					//System.out.println("checked page: " + page.getPage());
					allAuctions.addAll(page.getAuctions());
					try {
						addCount();
					} catch (IOException e) {
						e.printStackTrace();
					}
					//System.out.println("added count to: " + getCount());
				});
//				if(i==(getTemp()-1)) {
//					JsonElement auction = allAuctions.get(0);
//					System.out.println(auction.getAsJsonObject().get("item_name") + " :: " + auction.getAsJsonObject().get("starting_bid") + "First items price");
//				}
			}
//			System.out.println("called test print");
//			testPrint();
		});
		
		
		
		
		//await();
	}

	public static void setTemp(int x) {
		temp=x;
	}
	public static int getTemp() {
		return temp;
	}
	
	public static void addCount() throws IOException {
		count++;
		if(count==getTemp()) {
			System.out.println("running testprint from addcount()");
			testPrint();
			return;
		}
		
	}
	
	public static int getCount() {
		return count;
	}
	public static void testPrint() throws IOException {
//		Charset utf8 = Charset.forName("UTF-8");
//        PrintStream printStream = new PrintStream(System.out, true, utf8.name());
		System.out.println(allAuctions.size() + " Size");
		for(int i = 0; i<allAuctions.size();i++) {
			JsonElement auction = allAuctions.get(i);
			String s = auction.getAsJsonObject().get("item_name").getAsString();

				System.out.println( s.toString() + " :: " +  auction.getAsJsonObject().get("starting_bid"));
		
	
			
		}
		System.out.println(allAuctions.size() +" size");
	}
	

	
	public static void await() {
		while (!Thread.interrupted()) {
			try {
				Thread.sleep(1000);
				System.out.println("awating");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
