import java.util.Timer;
import java.util.TimerTask;
import java.io.*; 
import java.net.*;
import java.time.*; 

public class Principale {
	
	
	public static boolean pingInProgress = false;
	
	public static int pingMs;
	public static String host = "104.160.141.3";
	
	public static Overlay over = new Overlay();
	
	
	public static Timer timer = new Timer();
	public static TimerTask taskPing = new TimerTask() {
		public void run() {
			if(!pingInProgress) {
				
				String pingMs = ping(host).toString();//.replaceAll("[]", "");
				System.out.println(pingMs);
				over.setPing(pingMs);
				
			}
		}
	};
	
	
	
	public static void main(String[] args) {
		timer.scheduleAtFixedRate(taskPing, 0,1000);
	}
	
	public static Duration ping(String host) {
		pingInProgress = true;
	    Instant startTime = Instant.now();
	    try {
	        InetAddress address = InetAddress.getByName(host);
	        if (address.isReachable(5000)) {
	        	pingInProgress = false;
	            return Duration.between(startTime, Instant.now());
	        }
	    } catch (IOException e) {
	        
	    }
	    pingInProgress = false;
	    return Duration.ZERO;
	}
	
}
