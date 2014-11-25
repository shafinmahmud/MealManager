package mealmanager;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author SHAFIN
 */
public class NewAccount extends javax.swing.JFrame {

    private final Connection connRefNA;
    private PreparedStatement preparedstatementNA;
    private ResultSet resultsetNA;

    private String monthyearTagNA;
    private int TempSaveFlag;
    private boolean EditFlag;
    private int editID = 0;
    private byte[] person_image = null;

    /**
     * this constructor called from temp new member insertion Creates new form
     * NewAccount
     *
     * @param val
     * @param conn
     */
    public NewAccount(String val, Connection conn) {
        initComponents();
        connRefNA = conn;
        TempSaveFlag = Integer.parseInt(val);
    }

    /**
     * constructs NewAccount form called from UI member profile
     *
     * @param id
     * @param conn
     * @param tag
     */
    public NewAccount(int id, Connection conn, String tag) {
        initComponents();
        connRefNA = conn;
        monthyearTagNA = tag;

        try {
            String sql = "SELECT firstname, lastname, email, PhoneNumber FROM [memberProfile" + monthyearTagNA + "] WHERE ID=" + id;
            preparedstatementNA = connRefNA.prepareStatement(sql);
            resultsetNA = preparedstatementNA.executeQuery();

            if (resultsetNA.next()) {
                String fname = resultsetNA.getString("firstname");
                txt_fName.setText(fname);
                txt_fName.setEditable(false);

                String lname = resultsetNA.getString("lastname");
                txt_lName.setText(lname);

                String email = resultsetNA.getString("email");
                txt_email.setText(email);

                String phoneNumber = resultsetNA.getString("PhoneNumber");
                txt_phoneNumber.setText(phoneNumber);

                ImageHandler viewImg = new ImageHandler(connRefNA);
                ImageIcon img = viewImg.retriveImageFromDb(id, "member_data");

                if (img == null) {
                    choosed_image.setIcon(null);
                    jpanel_view_image.revalidate();
                } else {
                    Image scaledImg = img.getImage().getScaledInstance(162, 162, Image.SCALE_SMOOTH);
                    choosed_image.setIcon(new ImageIcon(scaledImg));
                }
                editID = id;
                EditFlag = true;
            }
        } catch (SQLException e) {

        } finally {
            try {
                if (resultsetNA != null) {
                    resultsetNA.close();
                }
                if (preparedstatementNA != null) {
                    preparedstatementNA.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_fName = new javax.swing.JTextField();
        txt_phoneNumber = new javax.swing.JTextField();
        txt_lName = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        webCam = new javax.swing.JButton();
        cmd_upload_image = new javax.swing.JButton();
        jpanel_view_image = new javax.swing.JDesktopPane();
        choosed_image = new javax.swing.JLabel();
        cmd_newMemberConfirm = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setForeground(new java.awt.Color(240, 240, 240));

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Credential", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setText("First Name");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(240, 240, 240));
        jLabel2.setText("Last Name");

        txt_fName.setBackground(new java.awt.Color(102, 102, 102));
        txt_fName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_fName.setForeground(new java.awt.Color(240, 240, 240));

        txt_phoneNumber.setBackground(new java.awt.Color(102, 102, 102));
        txt_phoneNumber.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_phoneNumber.setForeground(new java.awt.Color(240, 240, 240));

        txt_lName.setBackground(new java.awt.Color(102, 102, 102));
        txt_lName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_lName.setForeground(new java.awt.Color(240, 240, 240));

        txt_email.setBackground(new java.awt.Color(102, 102, 102));
        txt_email.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_email.setForeground(new java.awt.Color(240, 240, 240));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(240, 240, 240));
        jLabel4.setText("Phone Number");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(240, 240, 240));
        jLabel3.setText("E-mail");

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_email)
                    .addComponent(txt_lName)
                    .addComponent(txt_fName)
                    .addComponent(txt_phoneNumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_fName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_lName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(7, 7, 7)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_phoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txt_fName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txt_phoneNumber, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txt_lName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txt_email, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane2.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Photo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        webCam.setText("WebCam");
        webCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webCamActionPerformed(evt);
            }
        });

        cmd_upload_image.setText("Upload");
        cmd_upload_image.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_upload_imageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpanel_view_imageLayout = new javax.swing.GroupLayout(jpanel_view_image);
        jpanel_view_image.setLayout(jpanel_view_imageLayout);
        jpanel_view_imageLayout.setHorizontalGroup(
            jpanel_view_imageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(choosed_image, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
        );
        jpanel_view_imageLayout.setVerticalGroup(
            jpanel_view_imageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(choosed_image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpanel_view_image.setLayer(choosed_image, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpanel_view_image)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(webCam, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmd_upload_image, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addComponent(jpanel_view_image)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(webCam)
                    .addComponent(cmd_upload_image))
                .addContainerGap())
        );
        jLayeredPane2.setLayer(webCam, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(cmd_upload_image, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jpanel_view_image, javax.swing.JLayeredPane.DEFAULT_LAYER);

        cmd_newMemberConfirm.setText("CONFIRM");
        cmd_newMemberConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_newMemberConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmd_newMemberConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(221, 221, 221))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLayeredPane2)
                    .addComponent(jLayeredPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmd_newMemberConfirm)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(606, 342));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmd_newMemberConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_newMemberConfirmActionPerformed

        String name = txt_fName.getText();
        String lastName = txt_lName.getText();
        String email = txt_email.getText();
        String phoneNumber = txt_phoneNumber.getText();
        ModifyMember memNew = new ModifyMember(connRefNA);
        
        if (EditFlag) {

            memNew.editMemberCredential(editID, lastName, email, phoneNumber);
            if (person_image != null) {
                ImageHandler editimg = new ImageHandler(connRefNA);
                editimg.saveImageToDb("member_data", editID, person_image);
                person_image = null;
            }
            editID = 0;
            EditFlag = false;

            close();
        } else if (TempSaveFlag == 2) { //this flag insert the member in the temp table
            ID i = new ID(connRefNA);
            int idNo = i.memberID();
            idNo = i.tempID(idNo);

            if (!name.equals("")) {
                memNew.tempInsertNewMember(idNo, name, lastName, email, phoneNumber, 0);
                if (person_image != null) {
                    ImageHandler tempimg = new ImageHandler(connRefNA);
                    tempimg.saveImageToDb("TempReport", idNo, person_image);
                }

                memNew.listing();

                NewMonthInitializer.txt_status.setText("New Member " + name + " is added with ID: " + idNo);
                person_image = null;
                TempSaveFlag = 0;

                close();
            }
        } else if (TempSaveFlag == 1) { //this flag insert member in the final table
            ID i = new ID(connRefNA);
            int id = i.memberID();

            memNew.insertNewMember(monthyearTagNA, id, name, lastName, email, phoneNumber, 0.0);
            if (person_image != null) {
                ImageHandler saveimg = new ImageHandler(connRefNA);
                saveimg.saveImageToDb("member_data", id, person_image);
            }

            Generate gen = new Generate(connRefNA);
            gen.NameCombo();
            gen.memberList();

            UpdateData update = new UpdateData(connRefNA, monthyearTagNA);
            update.MealTable();
            update.DepositeTable();
            update.report();
            update.totalMember();

            JOptionPane.showMessageDialog(null, "saved");
            person_image = null;
            TempSaveFlag = 0;

            close();
        }
    }//GEN-LAST:event_cmd_newMemberConfirmActionPerformed

    private void cmd_upload_imageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_upload_imageActionPerformed
        try {
            ImageHandler wcam = new ImageHandler(connRefNA);

            person_image = wcam.imgUpload();
            if (person_image != null) {
                ImageIcon imgIcon = new ImageIcon(person_image);

                Image scaledImage;
                scaledImage = imgIcon.getImage().getScaledInstance(jpanel_view_image.getWidth(), jpanel_view_image.getHeight(), Image.SCALE_SMOOTH);
                imgIcon = new ImageIcon(scaledImage);
                choosed_image.setIcon(imgIcon);
            }
        } catch (IOException ex) {
            Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmd_upload_imageActionPerformed

    private void webCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webCamActionPerformed
        ImageHandler wcam = new ImageHandler(connRefNA);
        wcam.webCam();
    }//GEN-LAST:event_webCamActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        TempSaveFlag = 0;
        EditFlag = false;
        editID = 0;
    }//GEN-LAST:event_formWindowClosing

    /**
     * Closes current form
     */
    public void close() {
        WindowEvent windowClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(windowClosingEvent);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel choosed_image;
    private javax.swing.JButton cmd_newMemberConfirm;
    private javax.swing.JButton cmd_upload_image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JDesktopPane jpanel_view_image;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_fName;
    private javax.swing.JTextField txt_lName;
    private javax.swing.JTextField txt_phoneNumber;
    private javax.swing.JButton webCam;
    // End of variables declaration//GEN-END:variables

}
