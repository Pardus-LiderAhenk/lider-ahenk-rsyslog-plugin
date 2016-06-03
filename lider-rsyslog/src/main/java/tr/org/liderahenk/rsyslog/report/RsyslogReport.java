package tr.org.liderahenk.rsyslog.report;

import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;

import tr.org.liderahenk.lider.core.api.persistence.entities.IReportTemplate;
import tr.org.liderahenk.lider.core.api.persistence.entities.IReportTemplateColumn;
import tr.org.liderahenk.lider.core.api.persistence.entities.IReportTemplateParameter;
import tr.org.liderahenk.lider.core.api.persistence.enums.ParameterType;
import tr.org.liderahenk.lider.core.api.plugin.BaseReportTemplate;

public class RsyslogReport extends BaseReportTemplate {

	private static final long serialVersionUID = -4121593523951121045L;

	@Override
	public String getName() {
		return "Rsyslog Konfigurasyonu";
	}

	@Override
	public String getDescription() {
		return "Log dosyalarının konfigurasyonu hakkında detay rapor";
	}

	@Override
	public String getQuery() {
		return "SELECT s.fromHost as fromhost,s.eventUser as eventuser, s.eventSource as eventsource,s.eventLogType as eventlogtype , "
				+ "s.genericFileName as genericfilename, s.message as message, s.receivedAt as receivedat, s.sysLogTag as syslogtag "
				+ "FROM SystemEventsImpl s " + "WHERE s.fromHost LIKE :fromhostparam";
	}

	@Override
	public Set<? extends IReportTemplateParameter> getTemplateParams() {
		Set<IReportTemplateParameter> params = new HashSet<IReportTemplateParameter>();
		// Plugin name
		params.add(new IReportTemplateParameter() {

			private static final long serialVersionUID = -6579501320904978340L;

			@Override
			public ParameterType getType() {
				return ParameterType.STRING;
			}

			@Override
			public IReportTemplate getTemplate() {
				return getSelf();
			}

			@Override
			public String getLabel() {
				return "Makina İsmi";
			}

			@Override
			public String getKey() {
				return "fromhostparam";
			}

			@Override
			public Long getId() {
				return null;
			}
		});
		// Plugin version
		return params;
	}

	@Override
	public Set<? extends IReportTemplateColumn> getTemplateColumns() {
		Set<IReportTemplateColumn> columns = new HashSet<IReportTemplateColumn>();

		// Plugin name
		columns.add(new IReportTemplateColumn() {

			private static final long serialVersionUID = -7169058053952522305L;

			@Override
			public boolean isVisible() {
				return true;
			}

			@Override
			public Integer getWidth() {
				return null;
			}

			@Override
			public IReportTemplate getTemplate() {
				return getSelf();
			}

			@Override
			public String getName() {
				return "From Host";
			}

			@Override
			public Long getId() {
				return null;
			}

			@Override
			public Integer getColumnOrder() {
				return 1;
			}
		});
		// Plugin name
		columns.add(new IReportTemplateColumn() {

			private static final long serialVersionUID = -7169058053952522305L;

			@Override
			public boolean isVisible() {
				return true;
			}

			@Override
			public Integer getWidth() {
				return null;
			}

			@Override
			public IReportTemplate getTemplate() {
				return getSelf();
			}

			@Override
			public String getName() {
				return "Event User";
			}

			@Override
			public Long getId() {
				return null;
			}

			@Override
			public Integer getColumnOrder() {
				return 2;
			}
		});
		// Plugin name
		columns.add(new IReportTemplateColumn() {

			private static final long serialVersionUID = -7169058053952522305L;

			@Override
			public boolean isVisible() {
				return true;
			}

			@Override
			public Integer getWidth() {
				return null;
			}

			@Override
			public IReportTemplate getTemplate() {
				return getSelf();
			}

			@Override
			public String getName() {
				return "Event Source";
			}

			@Override
			public Long getId() {
				return null;
			}

			@Override
			public Integer getColumnOrder() {
				return 3;
			}
		});
		columns.add(new IReportTemplateColumn() {

			private static final long serialVersionUID = -7169058053952522305L;

			@Override
			public boolean isVisible() {
				return true;
			}

			@Override
			public Integer getWidth() {
				return null;
			}

			@Override
			public IReportTemplate getTemplate() {
				return getSelf();
			}

			@Override
			public String getName() {
				return "Event Log Type";
			}

			@Override
			public Long getId() {
				return null;
			}

			@Override
			public Integer getColumnOrder() {
				return 4;
			}
		});
		// Plugin name
		columns.add(new IReportTemplateColumn() {

			private static final long serialVersionUID = -7169058053952522305L;

			@Override
			public boolean isVisible() {
				return true;
			}

			@Override
			public Integer getWidth() {
				return null;
			}

			@Override
			public IReportTemplate getTemplate() {
				return getSelf();
			}

			@Override
			public String getName() {
				return "Generic File Name";
			}

			@Override
			public Long getId() {
				return null;
			}

			@Override
			public Integer getColumnOrder() {
				return 5;
			}
		});
		// Plugin name
		columns.add(new IReportTemplateColumn() {

			private static final long serialVersionUID = -7169058053952522305L;

			@Override
			public boolean isVisible() {
				return true;
			}

			@Override
			public Integer getWidth() {
				return null;
			}

			@Override
			public IReportTemplate getTemplate() {
				return getSelf();
			}

			@Override
			public String getName() {
				return "Message";
			}

			@Override
			public Long getId() {
				return null;
			}

			@Override
			public Integer getColumnOrder() {
				return 6;
			}
		});
		// Plugin name
		columns.add(new IReportTemplateColumn() {

			private static final long serialVersionUID = -7169058053952522305L;

			@Override
			public boolean isVisible() {
				return true;
			}

			@Override
			public Integer getWidth() {
				return null;
			}

			@Override
			public IReportTemplate getTemplate() {
				return getSelf();
			}

			@Override
			public String getName() {
				return "Received At";
			}

			@Override
			public Long getId() {
				return null;
			}

			@Override
			public Integer getColumnOrder() {
				return 7;
			}
		});
		// Plugin name
		columns.add(new IReportTemplateColumn() {

			private static final long serialVersionUID = -7169058053952522305L;

			@Override
			public boolean isVisible() {
				return true;
			}

			@Override
			public Integer getWidth() {
				return null;
			}

			@Override
			public IReportTemplate getTemplate() {
				return getSelf();
			}

			@Override
			public String getName() {
				return "Sys Log Tag";
			}

			@Override
			public Long getId() {
				return null;
			}

			@Override
			public Integer getColumnOrder() {
				return 8;
			}

		});
		return columns;
	}

	@Override
	public String getReportHeader() {
		return null;
	}

	@Override
	public String getReportFooter() {
		return null;
	}

	@Override
	public String toJson() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected RsyslogReport getSelf() {
		return this;
	}

}
