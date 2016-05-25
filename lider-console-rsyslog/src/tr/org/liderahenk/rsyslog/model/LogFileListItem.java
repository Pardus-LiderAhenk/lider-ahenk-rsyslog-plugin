package tr.org.liderahenk.rsyslog.model;

import java.io.Serializable;

public class LogFileListItem implements Serializable {

	private static final long serialVersionUID = -4060913846099252209L;

	private String localLog;
	private String recordDescription;
	private String logFilePath;
	
	public LogFileListItem() {
		super();
	}
	
	public LogFileListItem(String localLog, String recordDescription, String logFilePath) {
		super();
		this.localLog = localLog;
		this.recordDescription = recordDescription;
		this.logFilePath = logFilePath;
	}
	public String localLog() {
		return localLog;
	}
	public void setLocalLog(String localLog) {
		this.localLog = localLog;
	}
	public String getLogFilePath() {
		return logFilePath;
	}
	public void setLogFilePath(String logFilePath) {
		this.logFilePath = logFilePath;
	}
	public String getRecordDescription() {
		return recordDescription;
	}
	public void setRecordDescription(String recordDescription) {
		this.recordDescription = recordDescription;
	}

}
