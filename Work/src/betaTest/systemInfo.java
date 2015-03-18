package betaTest;


public class systemInfo {
	 private Runtime runtime = Runtime.getRuntime();

	    public String Info() {
	        StringBuilder sb = new StringBuilder();
	        sb.append(this.OsInfo());
	   
	        return sb.toString();
	    }

	    public String OSname() {
	        return System.getProperty("os.name");
	    }

	    public String OSversion() {
	        return System.getProperty("os.version");
	    }

	    public String OsArch() {
	        return System.getProperty("os.arch");
	    }

	 

	    public String OsInfo() {
	        StringBuilder sb = new StringBuilder();
	        sb.append("OS: ");
	        sb.append(this.OSname());
	        sb.append(""+"\n\r" + "\n\r"+"");
	        sb.append("Version: ");
	        sb.append(this.OSversion());
	        sb.append("\n\r");;
	        sb.append(": ");
	        sb.append(this.OsArch());
	        sb.append("\n\r" + "\n\r");
	        sb.append("Available processors (cores): ");
	        sb.append(runtime.availableProcessors());
	        sb.append("\n\r");
	        return sb.toString();
	    }

	   
	}