
package accounts;

import business.Account;
import business.Checking;
import business.Savings;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * The application's main frame.
 */
public class AccountsView extends FrameView {
    Account a;
    
    public AccountsView(SingleFrameApplication app) {
        super(app);

        initComponents();
        setInputLine(false);
        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = AccountsApp.getApplication().getMainFrame();
            aboutBox = new AccountsAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        AccountsApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jlblStartVal1 = new javax.swing.JLabel();
        jtxtDisplayName = new javax.swing.JTextField();
        jlblStartVal2 = new javax.swing.JLabel();
        jtxtAcctNo = new javax.swing.JTextField();
        jlblStartVal3 = new javax.swing.JLabel();
        jtxtAcctTypeDesc = new javax.swing.JTextField();
        jlblStartVal4 = new javax.swing.JLabel();
        jtxtBalance = new javax.swing.JTextField();
        jtxtAcctTypeCd = new javax.swing.JTextField();
        jlblTypeCd = new javax.swing.JLabel();
        jtxtTypeCd = new javax.swing.JTextField();
        jlblAcctNm = new javax.swing.JLabel();
        jtxtAcctNm = new javax.swing.JTextField();
        jlblStartVal = new javax.swing.JLabel();
        jtxtStartVal = new javax.swing.JTextField();
        jbtnCreate = new javax.swing.JButton();
        jbtnCancel = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtxtChgAmt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtxtChgDesc = new javax.swing.JTextField();
        jbtnChg = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jtxtPmt = new javax.swing.JTextField();
        jbtnPmt = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jtxtRate = new javax.swing.JTextField();
        jbtnIntTrans = new javax.swing.JButton();
        jbtnLog = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        jmnuAsset = new javax.swing.JMenu();
        jmnuCK = new javax.swing.JMenuItem();
        jmnuSV = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.setName("jPanel1"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(accounts.AccountsApp.class).getContext().getResourceMap(AccountsView.class);
        jlblStartVal1.setFont(resourceMap.getFont("jlblStartVal1.font")); // NOI18N
        jlblStartVal1.setText(resourceMap.getString("jlblStartVal1.text")); // NOI18N
        jlblStartVal1.setName("jlblStartVal1"); // NOI18N

        jtxtDisplayName.setName("jtxtDisplayName"); // NOI18N

        jlblStartVal2.setFont(resourceMap.getFont("jlblStartVal2.font")); // NOI18N
        jlblStartVal2.setText(resourceMap.getString("jlblStartVal2.text")); // NOI18N
        jlblStartVal2.setName("jlblStartVal2"); // NOI18N

        jtxtAcctNo.setName("jtxtAcctNo"); // NOI18N

        jlblStartVal3.setFont(resourceMap.getFont("jlblStartVal3.font")); // NOI18N
        jlblStartVal3.setText(resourceMap.getString("jlblStartVal3.text")); // NOI18N
        jlblStartVal3.setToolTipText(resourceMap.getString("jlblStartVal3.toolTipText")); // NOI18N
        jlblStartVal3.setName("jlblStartVal3"); // NOI18N

        jtxtAcctTypeDesc.setName("jtxtAcctTypeDesc"); // NOI18N

        jlblStartVal4.setFont(resourceMap.getFont("jlblStartVal4.font")); // NOI18N
        jlblStartVal4.setText(resourceMap.getString("jlblStartVal4.text")); // NOI18N
        jlblStartVal4.setToolTipText(resourceMap.getString("jlblStartVal4.toolTipText")); // NOI18N
        jlblStartVal4.setName("jlblStartVal4"); // NOI18N

        jtxtBalance.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtBalance.setName("jtxtBalance"); // NOI18N

        jtxtAcctTypeCd.setText(resourceMap.getString("jtxtAcctTypeCd.text")); // NOI18N
        jtxtAcctTypeCd.setName("jtxtAcctTypeCd"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlblStartVal2)
                        .addGap(17, 17, 17)
                        .addComponent(jtxtAcctNo, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jlblStartVal3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jlblStartVal1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtxtDisplayName, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jtxtAcctTypeCd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtxtAcctTypeDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlblStartVal4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtxtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtxtDisplayName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblStartVal1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtAcctNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblStartVal2)
                    .addComponent(jlblStartVal3)
                    .addComponent(jtxtAcctTypeDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblStartVal4)
                    .addComponent(jtxtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtAcctTypeCd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jlblTypeCd.setFont(resourceMap.getFont("jlblTypeCd.font")); // NOI18N
        jlblTypeCd.setText(resourceMap.getString("jlblTypeCd.text")); // NOI18N
        jlblTypeCd.setName("jlblTypeCd"); // NOI18N

        jtxtTypeCd.setEditable(false);
        jtxtTypeCd.setBackground(resourceMap.getColor("jtxtTypeCd.background")); // NOI18N
        jtxtTypeCd.setText(resourceMap.getString("jtxtTypeCd.text")); // NOI18N
        jtxtTypeCd.setName("jtxtTypeCd"); // NOI18N

        jlblAcctNm.setFont(resourceMap.getFont("jlblAcctNm.font")); // NOI18N
        jlblAcctNm.setText(resourceMap.getString("jlblAcctNm.text")); // NOI18N
        jlblAcctNm.setName("jlblAcctNm"); // NOI18N

        jtxtAcctNm.setName("jtxtAcctNm"); // NOI18N

        jlblStartVal.setFont(resourceMap.getFont("jlblStartVal.font")); // NOI18N
        jlblStartVal.setText(resourceMap.getString("jlblStartVal.text")); // NOI18N
        jlblStartVal.setName("jlblStartVal"); // NOI18N

        jtxtStartVal.setName("jtxtStartVal"); // NOI18N

        jbtnCreate.setText(resourceMap.getString("jbtnCreate.text")); // NOI18N
        jbtnCreate.setName("jbtnCreate"); // NOI18N
        jbtnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCreateActionPerformed(evt);
            }
        });

        jbtnCancel.setText(resourceMap.getString("jbtnCancel.text")); // NOI18N
        jbtnCancel.setName("jbtnCancel"); // NOI18N
        jbtnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jtxtChgAmt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtChgAmt.setText(resourceMap.getString("jtxtChgAmt.text")); // NOI18N
        jtxtChgAmt.setName("jtxtChgAmt"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jtxtChgDesc.setName("jtxtChgDesc"); // NOI18N

        jbtnChg.setText(resourceMap.getString("jbtnChg.text")); // NOI18N
        jbtnChg.setName("jbtnChg"); // NOI18N
        jbtnChg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnChgActionPerformed(evt);
            }
        });

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jtxtPmt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtPmt.setName("jtxtPmt"); // NOI18N

        jbtnPmt.setText(resourceMap.getString("jbtnPmt.text")); // NOI18N
        jbtnPmt.setName("jbtnPmt"); // NOI18N
        jbtnPmt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPmtActionPerformed(evt);
            }
        });

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jtxtRate.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtRate.setName("jtxtRate"); // NOI18N

        jbtnIntTrans.setText(resourceMap.getString("jbtnIntTrans.text")); // NOI18N
        jbtnIntTrans.setToolTipText(resourceMap.getString("jbtnIntTrans.toolTipText")); // NOI18N
        jbtnIntTrans.setName("jbtnIntTrans"); // NOI18N
        jbtnIntTrans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnIntTransActionPerformed(evt);
            }
        });

        jbtnLog.setText(resourceMap.getString("jbtnLog.text")); // NOI18N
        jbtnLog.setName("jbtnLog"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtChgAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtPmt, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtRate, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnPmt)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtChgDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnIntTrans))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtnLog)
                            .addComponent(jbtnChg))))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtChgAmt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtChgDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnChg))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtPmt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnPmt))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnIntTrans)
                    .addComponent(jbtnLog))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jlblTypeCd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtTypeCd, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlblAcctNm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtxtAcctNm, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlblStartVal, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtStartVal, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnCreate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtnCancel)
                        .addContainerGap(27, Short.MAX_VALUE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(54, Short.MAX_VALUE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblTypeCd)
                    .addComponent(jtxtTypeCd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblAcctNm)
                    .addComponent(jtxtAcctNm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblStartVal)
                    .addComponent(jtxtStartVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnCreate)
                    .addComponent(jbtnCancel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        jmnuAsset.setText(resourceMap.getString("jmnuAsset.text")); // NOI18N
        jmnuAsset.setName("jmnuAsset"); // NOI18N

        jmnuCK.setText(resourceMap.getString("jmnuCK.text")); // NOI18N
        jmnuCK.setName("jmnuCK"); // NOI18N
        jmnuCK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmnuCKActionPerformed(evt);
            }
        });
        jmnuAsset.add(jmnuCK);

        jmnuSV.setText(resourceMap.getString("jmnuSV.text")); // NOI18N
        jmnuSV.setName("jmnuSV"); // NOI18N
        jmnuSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmnuSVActionPerformed(evt);
            }
        });
        jmnuAsset.add(jmnuSV);

        fileMenu.add(jmnuAsset);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(accounts.AccountsApp.class).getContext().getActionMap(AccountsView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 512, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void jmnuSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmnuSVActionPerformed
        a = new Savings();
        setInputLine(true);
        jtxtTypeCd.setText(a.getTypeCd());
        jtxtAcctNm.setText("");
        jlblStartVal.setText("Initial Deposit");
        jtxtStartVal.setText("");
        jtxtStartVal.setEnabled(true);
        jtxtAcctNm.requestFocusInWindow();
    }//GEN-LAST:event_jmnuSVActionPerformed

    private void jmnuCKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmnuCKActionPerformed
        a = new Checking();
        setInputLine(true);
        jtxtTypeCd.setText(a.getTypeCd());
        jtxtAcctNm.setText("");
        jlblStartVal.setText("Initial Deposit");
        jtxtStartVal.setText("");
        jtxtStartVal.setEnabled(true);
        jtxtAcctNm.requestFocusInWindow();
    }//GEN-LAST:event_jmnuCKActionPerformed

    private void jbtnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCreateActionPerformed
       double sbal;
       NumberFormat curr = NumberFormat.getCurrencyInstance();
       statusMessageLabel.setText("");
        if(jtxtAcctNm.getText().isEmpty()){
            statusMessageLabel.setText("Missing Account Name");
            jtxtAcctNm.requestFocusInWindow();
            return;
        }
        try{
            sbal = Double.parseDouble(jtxtStartVal.getText());
        }catch (NumberFormatException e){
            statusMessageLabel.setText("Illegal/Missing start vale");
            jtxtStartVal.requestFocusInWindow();
            return;
        }
        
        if (a instanceof Checking) {
            a = new Checking(jtxtAcctNm.getText(),sbal, a.getTypeCd());
        } else if (a instanceof Savings) {
            a = new Savings(jtxtAcctNm.getText(),sbal, a.getTypeCd());
        } else {
            statusMessageLabel.setText("unknown account type");
        }
        
        jtxtDisplayName.setText(a.getName());
        jtxtAcctNo.setText(String.valueOf(a.getAcctNo()));
        jtxtAcctTypeCd.setText(a.getTypeCd());
        jtxtAcctTypeDesc.setText(a.getTypeDesc());
        jtxtBalance.setText(curr.format(a.getBalance()));
      
        setInputLine(false);
        jtxtChgAmt.requestFocusInWindow();
    }//GEN-LAST:event_jbtnCreateActionPerformed

    private void jbtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelActionPerformed
        setInputLine(false);
    }//GEN-LAST:event_jbtnCancelActionPerformed

    private void jbtnChgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnChgActionPerformed
        statusMessageLabel.setText("");
        double amt;
        NumberFormat curr = NumberFormat.getCurrencyInstance();
        try{
            amt = Double.parseDouble(jtxtChgAmt.getText());
            if (jtxtAcctTypeCd.getText().equalsIgnoreCase(a.getTypeCd())) {
                a.setCharge(amt, jtxtChgDesc.getText());
                if (a.getErrMsg().isEmpty()) {
                    statusMessageLabel.setText(a.getActionMsg());
                    jtxtBalance.setText(curr.format(a.getBalance()));
                } else {
                    statusMessageLabel.setText(a.getErrMsg());
                }
            } else {
                statusMessageLabel.setText("Unknown Account type");                
            }
           jtxtChgAmt.setText("");
           jtxtChgDesc.setText("");           
        }catch(Exception e){
            statusMessageLabel.setText("Non numeric charge amount");
        }
        jtxtChgAmt.requestFocusInWindow();
    }//GEN-LAST:event_jbtnChgActionPerformed

    private void jbtnPmtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPmtActionPerformed
        statusMessageLabel.setText("");
        double amt;
        NumberFormat curr = NumberFormat.getCurrencyInstance();
        try{
            amt = Double.parseDouble(jtxtPmt.getText());
            if (jtxtAcctTypeCd.getText().equalsIgnoreCase(a.getTypeCd())) {
                a.setPayment(amt);
                if (a.getErrMsg().isEmpty()) {
                    statusMessageLabel.setText(a.getActionMsg());
                    jtxtBalance.setText(curr.format(a.getBalance()));
                } else {
                    statusMessageLabel.setText(a.getErrMsg());
                }
            } else {
                statusMessageLabel.setText("Unknown Account type");                
            }
           jtxtPmt.setText("");          
        }catch(Exception e){
            statusMessageLabel.setText("Non numeric charge amount");
        }
        jtxtPmt.requestFocusInWindow();
    }//GEN-LAST:event_jbtnPmtActionPerformed

    private void jbtnIntTransActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnIntTransActionPerformed
        statusMessageLabel.setText("");
        double rate;
        NumberFormat curr = NumberFormat.getCurrencyInstance();
        try{
            rate = Double.parseDouble(jtxtRate.getText());
            if (jtxtAcctTypeCd.getText().equalsIgnoreCase(a.getTypeCd())) {
                a.setInterest(rate);
                if (a.getErrMsg().isEmpty()) {
                    statusMessageLabel.setText(a.getActionMsg());
                    jtxtBalance.setText(curr.format(a.getBalance()));
                } else {
                    statusMessageLabel.setText(a.getErrMsg());
                }
            } else {
                statusMessageLabel.setText("Unknown Account type");                
            }
           jtxtRate.setText("");        
        }catch(Exception e){
            statusMessageLabel.setText("Non numeric charge amount");
        }
        jtxtRate.requestFocusInWindow();
    }//GEN-LAST:event_jbtnIntTransActionPerformed
    
    private void setInputLine(boolean tf){
        jlblTypeCd.setVisible(tf);
        jtxtTypeCd.setVisible(tf);
        jtxtAcctNm.setVisible(tf);
        jlblAcctNm.setVisible(tf);
        jlblStartVal.setVisible(tf);
        jtxtStartVal.setVisible(tf);
        jbtnCreate.setVisible(tf);
        jbtnCancel.setVisible(tf);
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnChg;
    private javax.swing.JButton jbtnCreate;
    private javax.swing.JButton jbtnIntTrans;
    private javax.swing.JButton jbtnLog;
    private javax.swing.JButton jbtnPmt;
    private javax.swing.JLabel jlblAcctNm;
    private javax.swing.JLabel jlblStartVal;
    private javax.swing.JLabel jlblStartVal1;
    private javax.swing.JLabel jlblStartVal2;
    private javax.swing.JLabel jlblStartVal3;
    private javax.swing.JLabel jlblStartVal4;
    private javax.swing.JLabel jlblTypeCd;
    private javax.swing.JMenu jmnuAsset;
    private javax.swing.JMenuItem jmnuCK;
    private javax.swing.JMenuItem jmnuSV;
    private javax.swing.JTextField jtxtAcctNm;
    private javax.swing.JTextField jtxtAcctNo;
    private javax.swing.JTextField jtxtAcctTypeCd;
    private javax.swing.JTextField jtxtAcctTypeDesc;
    private javax.swing.JTextField jtxtBalance;
    private javax.swing.JTextField jtxtChgAmt;
    private javax.swing.JTextField jtxtChgDesc;
    private javax.swing.JTextField jtxtDisplayName;
    private javax.swing.JTextField jtxtPmt;
    private javax.swing.JTextField jtxtRate;
    private javax.swing.JTextField jtxtStartVal;
    private javax.swing.JTextField jtxtTypeCd;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
