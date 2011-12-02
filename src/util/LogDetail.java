package util;

import org.apache.log4j.Logger;
import org.flacro.resources.PhotosResource;

public class LogDetail {
	static Logger log = Logger.getLogger(PhotosResource.class);

	public static void logexception(Exception e) {
		String logdetail = "Exception:";
		StackTraceElement[] stes = e.getStackTrace();
		if (stes != null) {
			int count = stes.length > 10 ? 10 : stes.length;
			for (int i = 0; i < count; i++)
				logdetail += "\n" + stes[i].toString();
			logdetail += "\n" + "......................";
			log.debug(logdetail);
		}
	}
}