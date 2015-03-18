package betaTest;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;


public class memory {

	private String total;
	private String available;
	
	 int mb = 1024*1024;
	
	public void getMemory(){
	OperatingSystemMXBean osMBean =
			(OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	this.total="Total physical memory:\t" + osMBean.getTotalPhysicalMemorySize()/mb +" MB";
	this.available="Free physical memory:\t" + osMBean.getFreePhysicalMemorySize()/mb +" MB" ;

	}

	public String getTotal() {
		return total;
	}
	public String getAvailable() {
		return available;
	}
	
}