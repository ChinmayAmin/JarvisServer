import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class mainClass {

	public static void main(String[] args) {
		while (true) {
		    try {
		    	//This is just to get the path of where we are running
		        String internalPath = mainClass.class.getName().replace(".", File.separator);
		        String externalPathLibs = System.getProperty("user.dir")+File.separator+"libs";
		        String externalPath = System.getProperty("user.dir")+File.separator+"CommandListFile.txt";
		        
		        //Create Hashmap of intent and command associated with it
		        HashMap<String,String> myMap = new HashMap<String,String>();
		        
		        //Parse through the command list GG
		        File f = new File(externalPath);
		        Scanner scanner = new Scanner(f);
		        while(scanner.hasNextLine())
		        {
		        	String s = scanner.nextLine();
		        	String[] split = s.split(":");
		        	myMap.put(split[0], split[1]);
		        }
		         
		        //The server part..pretty straight forward
		    	ServerSocket serverSocket = new ServerSocket(4544);
		    	System.out.println("running");
		        Socket clientSocket = serverSocket.accept(); 
		        InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
		        BufferedReader bufferedReader = new BufferedReader(inputStreamReader); //get the client message
		        String message = bufferedReader.readLine();
		        
		        //Print the user's intent
		        System.out.println(message);
		        
		        //Get the id and value associated with it
		        for (Entry<String, String> entry : myMap.entrySet()) {
		        	//BOOM goes the dynamite..matching command
		        	String s = entry.getKey().trim();
		        	
		            if (s.equalsIgnoreCase(message.trim())) {
		            	if(entry.getValue().contains("%s"))
		            	{
		            		String a = entry.getValue().replace("%s", externalPathLibs);
			                Runtime.getRuntime().exec(a);
		            	}else
		            	{
			                Runtime.getRuntime().exec(entry.getValue());
		            	}
		            }
		        }
		        
		        //Find the command associated with what user wants to do
		        inputStreamReader.close();
		        clientSocket.close();

		    } catch (IOException ex) {}
		}

	}
}
