package NumFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class numFileHandling {
	
	
	
	 public String createFile() throws IOException{
	  
		File myfile = new File("Numbers.txt"); 
		if(myfile.createNewFile()) {
			System.out.println("file created: " + myfile.getName()); } 
		else {
			 System.out.println("File already exists"); 
		} 
		return myfile.getAbsolutePath();
	  
	 }
	 
	
	 
	 //---------------------------------------------------------------------------
	 
	 	public void EditFile(int rangeStart, int rangeEnd, String filePath) throws IOException{
		
		try {
			PrintWriter writer = new PrintWriter (new File(filePath));
			writer.print("");
			writer.close();
			FileWriter writer1 = new FileWriter("Numbers.txt", true);
			
			for(int i = rangeStart; i<= rangeEnd; i++) {
				
				writer1.write(i + "\n");
				
			}
			writer1.close();
			
		}
			
		catch(IOException e) {
			System.out.println("An error occured while writing");
		}
	}

//-------------------------------------------------------------------------------
	public void calcNumbers(String filePath) throws FileNotFoundException {
		
		Scanner s = new Scanner(new File(filePath));
		
		int sum = 0;
		int num = 0;
		int numberOfElements = 0;
		
		while(s.hasNextInt()) {
			
			numberOfElements++;
			num = s.nextInt();
			System.out.println(num);
			sum = sum + num;
		}
		
		System.out.println("Sum: " + sum);
		System.out.println("average: " + sum/numberOfElements);
	}
	
//--------------------------------------------------------------------------
	public static void main(String[] args) throws IOException {
		
		
		numFileHandling obj1 = new numFileHandling();
		
		Scanner input = new Scanner(System.in);
		 System.out.println("Enter start of range");
		 int rangeStart = input.nextInt();
		 System.out.println("Enter end of range");
		 int rangeEnd = input.nextInt();
		 
		String filePath = obj1.createFile();
		obj1.EditFile(rangeStart, rangeEnd,filePath);
		obj1.calcNumbers(filePath);
	}
}
