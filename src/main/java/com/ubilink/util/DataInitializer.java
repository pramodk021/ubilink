package com.ubilink.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DataInitializer
{
	public static boolean restoreDatabase(String mySqlPath,InputStream dataScriptIS) throws FileNotFoundException, IOException, InterruptedException
	{
	    List<String> args = new ArrayList<>();

	    args.add(mySqlPath);

	    // Comands
	    args.add("-u");
	    args.add("root");
	    args.add("--password=admin");
	    args.add("ubilink_test_db");

	    System.out.println("Args: "+args.toString());

	    try{
	        ProcessBuilder pb = new ProcessBuilder(args);
	       // pb.redirectError();
	        Process p = pb.start();
	        byte[] b = new byte[4096];

	        while(dataScriptIS.read(b) != -1) {
	            p.getOutputStream().write(b);
	        }

	        p.getOutputStream().close(); 
	        InputStream is = p.getInputStream();

	        int in = -1;
	        while((in = is.read()) != -1)
	        {
	            System.out.print(""+(char) in);
	        }

	        int proccessCompleted = p.waitFor();

	        if(proccessCompleted == 0)
	        {
	            System.out.println("Dump done!");
	            return true;
	        }
	        else
	        {
	            System.out.println("Error doing dump!");
	            return false;
	        }
	    }
	    catch(IOException | InterruptedException ex)
	    {
	        System.out.println("Exception exportDB -> " + ex.getMessage() + "|" + ex.getLocalizedMessage());
	    }
	    return false;
	}
}
