package extent_Report_Classes;

public class ConfigFileReader {

	public String getReportConfigPath(){
		String reportConfigPath = "/Users/vivek/Downloads/HRO-Exit/extent-config.xml";
		if(reportConfigPath!= null) return reportConfigPath;
		else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");		
	}
	
}
