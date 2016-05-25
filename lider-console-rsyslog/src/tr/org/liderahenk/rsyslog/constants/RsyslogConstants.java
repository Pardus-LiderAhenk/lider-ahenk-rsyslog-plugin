package tr.org.liderahenk.rsyslog.constants;

public class RsyslogConstants {
	
	public static final String PLUGIN_NAME = "rsyslog";
	
	public static final String PLUGIN_VERSION = "1.0.0";

	public static final class PARAMETERS {
		public static final String ROTATION_FREQUENCY = "ROTATION_FREQUENCY";
		public static final String OLD_LOG_COUNT = "OLD_LOG_COUNT";
		public static final String LOG_FILE_SIZE = "LOG_FILE_SIZE";
		public static final String NEW_LOG_FILE_AFTER_ROTATION = "NEW_LOG_FILE_AFTER_ROTATION";
		public static final String COMPRESS_OLD_LOG_FILE = "COMPRESS_OLD_LOG_FILE";
		public static final String PASS_AWAY_WITHOUT_ERROR_IF_FILE_NOT_EXIST = "PASS_AWAY_WITHOUT_ERROR_IF_FILE_NOT_EXIST";
		public static final String ADDRESS = "ADDRESS";
		public static final String GATE = "GATE";
		public static final String PROTOCOL = "PROTOCOL";
		public static final String LIST_ITEMS = "items";
	}
}