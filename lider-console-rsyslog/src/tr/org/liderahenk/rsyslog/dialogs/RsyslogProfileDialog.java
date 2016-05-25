package tr.org.liderahenk.rsyslog.dialogs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.org.liderahenk.liderconsole.core.dialogs.IProfileDialog;
import tr.org.liderahenk.liderconsole.core.model.Profile;
import tr.org.liderahenk.rsyslog.constants.RsyslogConstants;
import tr.org.liderahenk.rsyslog.i18n.Messages;
import tr.org.liderahenk.rsyslog.model.LogFileListItem;
import tr.org.liderahenk.rsyslog.utils.RsyslogUtils;

public class RsyslogProfileDialog implements IProfileDialog {
	
	private static final Logger logger = LoggerFactory.getLogger(RsyslogProfileDialog.class);

//	Widgets
	
	private Label lblLogRotation;
	private Label lblLogFreq;
	private Label lblKeptLogCount;
	private Text txtlogFileSize;
	private Label lblLogFileSize;
	private Combo cmbRotationFreq;
	private Button btnCheckNewLogFileAfterRotation;
	private Button btnCheckCompressOldLogFiles;
	private Button btnCheckSkipWithoutError;
	private Spinner spinnerLogRotationCount;
	private Label lblRemoteServerInfo;
	private Label lblRemoteServerAddress;
	private Label lblGate;
	private Label lblProtocol;
	private Combo cmbProtocol;
	private Text txtRemoteServerAddress;
	private Text txtGate;
	private Label lblManagedLogs;
	private Button btnAdd;
	private Button btnDelete;
	private Button btnEdit;
	
	private final String[] rotationFreqArray = new String[] { "DAILY","WEEKLY","MONTHLY","YEARLY" };
	private final String[] protocolArray = new String[] { "UDP","TCP" };
	

	private TableViewer tableViewer;
	
	@Override
	public void init() {
	}
	
	@Override
	public void createDialogArea(Composite parent, Profile profile) {
		logger.debug("Profile recieved: {}", profile != null ? profile.toString() : null);
		createLogFileInfoInputs(parent, profile);
		createTableArea(parent, profile);
	}
	
	private TableViewerColumn createTableViewerColumn(String title, int bound) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(false);
		column.setAlignment(SWT.LEFT);
		return viewerColumn;
	}
	
	private void createTableColumns() {

		String[] titles = { Messages.getString("LOCAL_LOG"), Messages.getString("RECORD_DESCRIPTION"),
				Messages.getString("LOG_FILE_PATH") };
		int[] bounds = { 200, 200, 200 };

		TableViewerColumn vendorColumn = createTableViewerColumn(titles[0], bounds[0]);
		vendorColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
//				if (element instanceof BlacklistWhitelistItem) {
//					return ((BlacklistWhitelistItem) element).getVendor();
//				}
				return Messages.getString("UNTITLED");
			}
		});

		TableViewerColumn modelColumn = createTableViewerColumn(titles[1], bounds[1]);
		modelColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
//				if (element instanceof BlacklistWhitelistItem) {
//					return ((BlacklistWhitelistItem) element).getModel();
//				}
				return Messages.getString("UNTITLED");
			}
		});

		TableViewerColumn serialNumberColumn = createTableViewerColumn(titles[2], bounds[2]);
		serialNumberColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
//				if (element instanceof BlacklistWhitelistItem) {
//					return ((BlacklistWhitelistItem) element).getSerialNumber();
//				}
				return Messages.getString("UNTITLED");
			}
		});
	}

	private void createTableArea(Composite parent, Profile profile) {
		tableViewer = new TableViewer(parent,
				SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

		// Create table columns
		createTableColumns();

		// Configure table layout
		final Table table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.getVerticalBar().setEnabled(true);
		table.getVerticalBar().setVisible(true);
		tableViewer.setContentProvider(new ArrayContentProvider());

		if (profile != null && profile.getProfileData() != null
				/*&& profile.getProfileData().get(UsbConstants.PARAMETERS.LIST_ITEMS) != null*/) {
//			@SuppressWarnings("unchecked")
//			ArrayList<LinkedHashMap<String, String>> list = (ArrayList<LinkedHashMap<String, String>>) profile
//					.getProfileData().get(UsbConstants.PARAMETERS.LIST_ITEMS);
//			if (list != null) {
//				List<BlacklistWhitelistItem> items = new ArrayList<BlacklistWhitelistItem>();
//				for (LinkedHashMap<String, String> map : list) {
//					BlacklistWhitelistItem item = new BlacklistWhitelistItem((String) map.get("vendor"),
//							(String) map.get("model"), (String) map.get("serialNumber"));
//					items.add(item);
//				}
//				tableViewer.setInput(items);
//				tableViewer.refresh();
//			}
		}

		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 3;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.heightHint = 140;
		gridData.horizontalAlignment = GridData.FILL;
		tableViewer.getControl().setLayoutData(gridData);

		// Hook up listeners
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
//				IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
//				Object firstElement = selection.getFirstElement();
//				firstElement = (BlacklistWhitelistItem) firstElement;
//				if (firstElement instanceof BlacklistWhitelistItem) {
//					setItem((BlacklistWhitelistItem) firstElement);
//				}
//				btnEditListItem.setEnabled(true);
//				btnDeleteListItem.setEnabled(true);
			}
		});
		tableViewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
//				BlacklistWhitelistItemDialog dialog = new BlacklistWhitelistItemDialog(parent.getShell(), getItem(),
//						tableViewer);
//				dialog.open();
			}
		});
		
	}

	private void createLogFileInfoInputs(Composite parent, Profile profile) {

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		
		lblLogRotation = new Label(composite, SWT.BOLD);
		lblLogRotation.setText(Messages.getString("LOG_ROTATION"));
		FontDescriptor descriptor = FontDescriptor.createFrom(lblLogRotation.getFont());
		// setStyle method returns a new font descriptor for the given style
		descriptor = descriptor.setStyle(SWT.BOLD);
		lblLogRotation.setFont(descriptor.createFont(lblLogRotation.getDisplay()));
		
		new Label(composite, SWT.NONE);
		
		lblLogFreq = new Label(composite, SWT.NONE);
		lblLogFreq.setText(Messages.getString("ROTATION_FREQUENCY"));
		
		cmbRotationFreq = new Combo(composite, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY);
		cmbRotationFreq.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		for (int i = 0; i < rotationFreqArray.length; i++) {
			String i18n = Messages.getString(rotationFreqArray[i]);
			if (i18n != null && !i18n.isEmpty()) {
				cmbRotationFreq.add(i18n);
				cmbRotationFreq.setData(i + "", rotationFreqArray[i]);
			}
		}
		cmbRotationFreq.select(0);

				
		lblKeptLogCount = new Label(composite, SWT.NONE);
		lblKeptLogCount.setText(Messages.getString("KEPT_LOG_COUNT"));
		
		spinnerLogRotationCount = new Spinner(composite, SWT.BORDER);
		spinnerLogRotationCount.setMinimum(1);
		spinnerLogRotationCount.setMaximum(99999);
		
		lblLogFileSize = new Label(composite, SWT.NONE);
		lblLogFileSize.setText(Messages.getString("LOG_FILE_SIZE"));
		
		txtlogFileSize = new Text(composite, SWT.BORDER);
		
		btnCheckNewLogFileAfterRotation = new Button(composite, SWT.CHECK);
		btnCheckNewLogFileAfterRotation.setText(Messages.getString("NEW_LOG_FILE_AFTER_ROTATION"));
		
		new Label(composite, SWT.NONE);

		btnCheckCompressOldLogFiles = new Button(composite, SWT.CHECK);
		btnCheckCompressOldLogFiles.setText(Messages.getString("COMPRESS_OLD_LOG_FILES"));
		
		new Label(composite, SWT.NONE);

		btnCheckSkipWithoutError = new Button(composite, SWT.CHECK);
		btnCheckSkipWithoutError.setText(Messages.getString("SKIP_WITHOUT_ERROR"));
		
		new Label(composite, SWT.NONE);
		
		lblRemoteServerInfo = new Label(composite, SWT.BOLD);
		lblRemoteServerInfo.setText(Messages.getString("REMOTE_SERVER_INFO"));
		// setStyle method returns a new font descriptor for the given style
		descriptor = descriptor.setStyle(SWT.BOLD);
		lblRemoteServerInfo.setFont(descriptor.createFont(lblRemoteServerInfo.getDisplay()));
		
		new Label(composite, SWT.NONE);
		
		lblRemoteServerAddress = new Label(composite, SWT.NONE);
		lblRemoteServerAddress.setText(Messages.getString("ADDRESS"));

		txtRemoteServerAddress = new Text(composite, SWT.BORDER);
		
		lblGate = new Label(composite, SWT.NONE);
		lblGate.setText(Messages.getString("GATE"));
		
		txtGate = new Text(composite, SWT.BORDER);
		
		lblProtocol = new Label(composite, SWT.NONE);	
		lblProtocol.setText(Messages.getString("PROTOCOL"));
		
		cmbProtocol = new Combo(composite, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY);
		cmbProtocol.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		for (int i = 0; i < protocolArray.length; i++) {
			String i18n = Messages.getString(protocolArray[i]);
			if (i18n != null && !i18n.isEmpty()) {
				cmbProtocol.add(i18n);
				cmbProtocol.setData(i + "", protocolArray[i]);
			}
		}
		cmbProtocol.select(0);
		
		lblManagedLogs = new Label(composite, SWT.NONE);
		lblManagedLogs.setText(Messages.getString("MANAGED_LOGS"));
		descriptor = descriptor.setStyle(SWT.BOLD);
		lblManagedLogs.setFont(descriptor.createFont(lblManagedLogs.getDisplay()));
		
		new Label(composite, SWT.NONE);
		
		Composite managedLogComposite = new Composite(composite, SWT.NONE);
		managedLogComposite.setLayout(new GridLayout(3, false));
		
		btnAdd = new Button(managedLogComposite, SWT.BUTTON1);
		btnAdd.setText(Messages.getString("ADD"));

		btnDelete = new Button(managedLogComposite, SWT.BUTTON1);
		btnDelete.setText(Messages.getString("DELETE"));

		btnEdit = new Button(managedLogComposite, SWT.BUTTON1);
		btnEdit.setText(Messages.getString("EDIT"));
		
	}

	@Override
	public Map<String, Object> getProfileData() throws Exception {		
	Map<String, Object> profileData = new HashMap<String, Object>();
	profileData.put(RsyslogConstants.PARAMETERS.ROTATION_FREQUENCY, RsyslogUtils.getSelectedValue(cmbRotationFreq));
	profileData.put(RsyslogConstants.PARAMETERS.OLD_LOG_COUNT, spinnerLogRotationCount.getSelection());
	if (txtlogFileSize != null && !"".equals(txtlogFileSize.getText())) {
		profileData.put(RsyslogConstants.PARAMETERS.LOG_FILE_SIZE, txtlogFileSize.getText());
	}
	profileData.put(RsyslogConstants.PARAMETERS.NEW_LOG_FILE_AFTER_ROTATION, btnCheckNewLogFileAfterRotation.getSelection());
	profileData.put(RsyslogConstants.PARAMETERS.COMPRESS_OLD_LOG_FILE, btnCheckCompressOldLogFiles.getSelection());
	profileData.put(RsyslogConstants.PARAMETERS.PASS_AWAY_WITHOUT_ERROR_IF_FILE_NOT_EXIST, btnCheckSkipWithoutError.getSelection());
	if (txtRemoteServerAddress != null && !"".equals(txtRemoteServerAddress.getText())) {
		profileData.put(RsyslogConstants.PARAMETERS.LOG_FILE_SIZE, txtRemoteServerAddress.getText());
	}
	if (txtGate != null && !"".equals(txtGate.getText())) {
		profileData.put(RsyslogConstants.PARAMETERS.LOG_FILE_SIZE, txtGate.getText());
	}
	profileData.put(RsyslogConstants.PARAMETERS.PROTOCOL, RsyslogUtils.getSelectedValue(cmbProtocol));

	@SuppressWarnings("unchecked")
	List<LogFileListItem> items = (List<LogFileListItem>) tableViewer.getInput();
	if (items != null) {
		profileData.put(RsyslogConstants.PARAMETERS.LIST_ITEMS, items);
	}
	return profileData;
	}
	
}
