package Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetCurrentDate {
	
	public String getDateTime() {
		SimpleDateFormat nowDateTime = new SimpleDateFormat("yyyyMMddHHmmsss");
	    Date now = new Date();
	    String strDate = nowDateTime.format(now);
	    return strDate;
	}
	
}
