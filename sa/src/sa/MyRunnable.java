package sa;


import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//import example.newframe;

public class MyRunnable implements Runnable  {

	private final String ipAddress;
	private final int row;
	
	
	MyRunnable(String ipAddress, int row) {
		this.ipAddress = ipAddress;
		this.row = row;
	}
	
	
	public void setData(String ipData, boolean status, String currTime){		
	  newframe.data[row][1] = ipData;
	  newframe.data[row][3] = currTime;
	  if( status )newframe.data[row][2] = "UP";
	  else newframe.data[row][2] = "DOWN";
	  newframe.table.repaint();
	  newframe.table.getColumnModel().getColumn(2).setCellRenderer(new ColoredTableCellRenderer());
	}
	
	String getTime(){
	  return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
	}
	
	@Override
	public void run() {
		String ip = "";
		boolean status = false;
		String timeStamp ;
		try {
			InetAddress inet = InetAddress.getByName(ipAddress);
			System.out.println("Sending Ping Request to " + ipAddress + "host name = "  );
			
			if (inet.isReachable(5000)) {				
				InetAddress address = InetAddress.getByName(ipAddress); 
				
				ip = address.getHostAddress();
				status = true;
				timeStamp = getTime();
				
				System.out.println(ipAddress + " is reachable." + address.getHostAddress());
				
			} else {				
				System.out.println(ipAddress + " NOT reachable.");
				timeStamp = getTime();
			}
		} catch (Exception e) {
			timeStamp = getTime();
			System.out.println("Exception:" + e.getMessage());
		}
		
		setData(ip, status, timeStamp);
		
	} // connect
	
}
