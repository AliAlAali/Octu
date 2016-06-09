package octu.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Command {

	public static final String MACHINE_SHUTDOWN = "/s";
	public static final String MACHINE_RESTART = "/r";
	public static final String MACHINE_LOG_OFF = "/l";
	
	public static final String ATTRIB_HIDE = "+h";
	public static final String ATTRIB_REVEAL = "-h";
	
	public static void main(String[] args) {

		Command c = new Command();
		
		try {
//			new Command().lunchExternalProgramm("c:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
//			c.renameFile("c:\\users\\ali\\desktop\\javaCmd\\what.txttxt", "what.txt");
//			c.moveFile("c:\\users\\ali\\desktop\\javaCmd\\b\\heythere.txt", "c:\\users\\ali\\desktop\\javaCmd\\a");
//			c.deleteDirectory("c:\\users\\ali\\desktop\\javaCmd\\b");
//			c.makeDirecotry("c:\\users\\ali\\desktop\\javaCmd\\B");
//			System.out.println(c.checkConnectionWithWebsite("www.google.com.sa"));
//			c.getConnectedDevices();
			c.changeFileAttribute("c:\\users\\ali\\desktop\\javaCmd\\list.txt", ATTRIB_HIDE);
//			c.shutMachine(MACHINE_LOG_OFF);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		System.out.println(new Command().fixPath("C:\Program Files (x86)\Google\Chrome\Application\chrome.exe"));
	}
	
	public Process lunchExternalProgramm(String path) throws IOException{
		return Runtime.getRuntime().exec(path);
	}
	
	public Process renameFile(String path, String newName) throws IOException{
		return Runtime.getRuntime().exec("cmd /c rename " + path + " " + newName);
	}
	
	public Process moveFile(String path, String newPath) throws IOException{
		return Runtime.getRuntime().exec("cmd /c move " + path + " " + newPath);
	}
	
	public Process deleteFile(String path) throws IOException{
		return Runtime.getRuntime().exec("cmd /c del " + path);
	}
	
	public Process deleteDirectory(String path) throws IOException{
		return Runtime.getRuntime().exec("cmd /c rd " + path);
	}
	
	public Process makeDirecotry(String path) throws IOException{
		return Runtime.getRuntime().exec("cmd /c mkdir " + path);
	}
	
	public boolean checkConnectionWithWebsite(String url) throws IOException{
		Runtime r = Runtime.getRuntime();
		Process p = r.exec("cmd /c ping " + url);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		
		String s = null, res = "";
		while((s = reader.readLine()) != null){
			res += s + "\n";
		}
		
		int count = -1;
		int lost = 0;
		String[] arr = res.split(" ");
		for (int i = 0; i < arr.length; i++) {
			if(arr[i].startsWith("Received")){
				count = Integer.parseInt(arr[i + 2].substring(0, arr[i + 2].indexOf(',')));
			}else if(arr[i].startsWith("Lost")){
				lost = Integer.parseInt(arr[i + 2]);
			}
		}
		
		if(count >= lost){
			return true;
		}else{
			return false;
		}
		
		
	}
	
	public String[] getConnectedDevices() throws IOException{
		Runtime r = Runtime.getRuntime();
		Process p = r.exec("cmd /c net view");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		
		String s = null, res = "";
		while((s = reader.readLine()) != null){
			res += s + "\n";
		}
		
		res = res.substring(res.indexOf("\\"), res.indexOf("The command completed successfully."));
		
		return res.split("\n");
	}
	
	public Process shutMachine(String operation) throws IOException{
		/*
		 * check the constants MACHINE_?OPERATIONS?
		 */
		return Runtime.getRuntime().exec("cmd /c shutdown " + operation);
	}
	
	public Process copyFile(String path, String newPath) throws IOException{
		return Runtime.getRuntime().exec("cmd /c copy " + path + " " + newPath);
	}
	
	public Process changeFileAttribute(String path, String attrib) throws IOException{
		return Runtime.getRuntime().exec("cmd /c attrib " + attrib + " " + path);
	}
	
//	public String[] getHiddenFiles() throws IOException{
//		Runtime r = Runtime.getRuntime();
//		Process p = r.exec("cmd /c net view");
//		
//		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
//		
//		String s = null, res = "";
//		while((s = reader.readLine()) != null){
//			res += s + "\n";
//		}
//	}
	
	public String fixPath(String path){
		for (int i = 0; i < path.length()-1; i++) {
			if(path.substring(i, i+1) == "\\"){
				path = path.substring(i, i+1) + "\\" + path.substring(i+1);
			}
					
		}
		return path;
	}
        
        public static void preventComputerFromSleeping(){
            //move mouse and return it back to its original position
            
        }
}

            //supposed to do that every 3 mintuest without any action