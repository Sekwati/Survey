/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.survey;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;


/**
 *
 * @author Sekwa
 */
public class SurveyForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SurveyForm.class.getName());
    private java.util.List<RatingRow> ratingRows;

    private String[] questions = {
        "I like to watch movies",
        "I like to listen to radio",
        "I like to eat out",
        "I like to watch TV"
    };

    private String[] columns = {"Strongly Agree", "Agree", "Neutral", "Disagree", "Strongly Disagree"};

    private JRadioButton[][] buttons;


    /**
     * Creates new form SurveyForm
     */
    public SurveyForm() {
        initComponents();
        DatabaseHelper.createTables();
        
        fillOutSurveyLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        viewResultsLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        fillOutSurveyLabel.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        //Show message
        JOptionPane.showMessageDialog(SurveyForm.this, "You're already on the survey page!");
        }
    });

        viewResultsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        //Open the Results Page
        ResultsPage resultsPage = new ResultsPage();
        resultsPage.setVisible(true);
        }
    });


        //Layout for ratingsContainer
        int rows = questions.length + 1; // +1 for header
        int cols = columns.length + 1;   // +1 for question text
        ratingsContainer.setLayout(new GridLayout(rows, cols));
        
        //Header row (empty cell + column headers)
        ratingsContainer.add(new JLabel(""));
        for (String col : columns) {
            JLabel header = new JLabel(col, SwingConstants.CENTER);
            header.setOpaque(true);
            header.setBackground(new Color(230,230,230)); // Light grey
            header.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            ratingsContainer.add(header);
        }
        
        //Question rows with radio buttons
        ButtonGroup[] groups = new ButtonGroup[questions.length];

        buttons = new JRadioButton[questions.length][columns.length];

        
        for (int i = 0; i < questions.length; i++) {
            JLabel qLabel = new JLabel(questions[i]);
            
            qLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 2),
            BorderFactory.createEmptyBorder(5, 5, 5, 5) // top, left, bottom, right padding
            ));
            
            ratingsContainer.add(qLabel);

            groups[i] = new ButtonGroup();
            for (int j = 0; j < columns.length; j++) {
                JRadioButton rb = new JRadioButton();
                rb.setHorizontalAlignment(SwingConstants.CENTER);
                rb.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                groups[i].add(rb);
                buttons[i][j] = rb;
                ratingsContainer.add(rb);
            }
        }
        ratingsContainer.revalidate();
        ratingsContainer.repaint();
        
    }
    
private boolean validateInput() {
    if (fullNames.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Full Name is required.");
        return false;
    }
    if (email.getText().trim().isEmpty() || !email.getText().contains("@")) {
        JOptionPane.showMessageDialog(this, "Please enter a valid email address.");
        return false;
    }
    if (dateOfBirth.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Date of Birth is required.");
        return false;
    }
    if (contactNumber.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Contact Number is required.");
        return false;
    }
    if (!pizzaChkBox.isSelected() && !pastaChkBox.isSelected() &&
        !papAndWorsChkBox.isSelected() && !otherChkBox.isSelected()) {
        JOptionPane.showMessageDialog(this, "Please select at least one favorite food.");
        return false;
    }

    // Ratings
    for (int i = 0; i < questions.length; i++) {
        boolean selected = false;
        for (int j = 0; j < columns.length; j++) {
            if (buttons[i][j].isSelected()) {
                selected = true;
                break;
            }
        }
        if (!selected) {
            JOptionPane.showMessageDialog(this, "Please select a rating for: " + questions[i]);
            return false;
        }
    }

    return true;
}

    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fullNames = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        dateOfBirth = new javax.swing.JTextField();
        contactNumber = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pizzaChkBox = new javax.swing.JCheckBox();
        pastaChkBox = new javax.swing.JCheckBox();
        papAndWorsChkBox = new javax.swing.JCheckBox();
        otherChkBox = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        submitBtn = new javax.swing.JButton();
        ratingsContainer = new javax.swing.JPanel();
        fillOutSurveyLabel = new javax.swing.JLabel();
        viewResultsLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.white);

        jLabel1.setText("Personal Details : ");

        jLabel2.setText("Full Names");

        fullNames.setMargin(new java.awt.Insets(2, 6, 1, 6));
        fullNames.setName(""); // NOI18N

        jLabel3.setText("Email");

        email.setMargin(new java.awt.Insets(2, 6, 1, 6));

        jLabel4.setText("Date of Birth (yyyy-mm-dd)");

        dateOfBirth.setMargin(new java.awt.Insets(2, 6, 1, 6));

        contactNumber.setMargin(new java.awt.Insets(2, 6, 1, 6));
        contactNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactNumberActionPerformed(evt);
            }
        });

        jLabel5.setText("Contact Number");

        jLabel6.setText("What is your favourite food?");

        pizzaChkBox.setText("Pizza");

        pastaChkBox.setText("Pasta");

        papAndWorsChkBox.setText("Pap and Wors");

        otherChkBox.setText("Other");

        jLabel7.setText("Please rate your level of agreement on a scale from 1 to 5, with 1 being \"strongly agree\" and 5 being \"strongly disagree.\"");

        submitBtn.setBackground(new java.awt.Color(213, 228, 243));
        submitBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        submitBtn.setText("SUBMIT");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ratingsContainerLayout = new javax.swing.GroupLayout(ratingsContainer);
        ratingsContainer.setLayout(ratingsContainerLayout);
        ratingsContainerLayout.setHorizontalGroup(
            ratingsContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );
        ratingsContainerLayout.setVerticalGroup(
            ratingsContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );

        fillOutSurveyLabel.setForeground(new java.awt.Color(0, 153, 153));
        fillOutSurveyLabel.setText("FILL OUT SURVEY");

        viewResultsLabel.setForeground(new java.awt.Color(0, 153, 153));
        viewResultsLabel.setText("VIEW SURVEY RESULTS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(pizzaChkBox)
                                        .addGap(18, 18, 18)
                                        .addComponent(pastaChkBox)
                                        .addGap(18, 18, 18)
                                        .addComponent(papAndWorsChkBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(otherChkBox))
                                    .addComponent(fullNames, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(contactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel7)
                            .addComponent(ratingsContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(389, 389, 389)
                        .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(149, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(fillOutSurveyLabel)
                .addGap(72, 72, 72)
                .addComponent(viewResultsLabel)
                .addGap(64, 64, 64))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fillOutSurveyLabel)
                    .addComponent(viewResultsLabel))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fullNames, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(310, 310, 310)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(pizzaChkBox)
                            .addComponent(pastaChkBox)
                            .addComponent(papAndWorsChkBox)
                            .addComponent(otherChkBox))))
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(ratingsContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        fullNames.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void contactNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contactNumberActionPerformed

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        if (!validateInput()) {
            return;
        }

        Map<String, Integer> ratingsMap = new HashMap<>();
        for (int i = 0; i < questions.length; i++) {
            int selected = -1;
            for (int j = 0; j < columns.length; j++) {
                if (buttons[i][j].isSelected()) {
                    selected = j + 1; // 1 = Strongly Agree
                    break;
                }
            }
            if (selected == -1) {
                JOptionPane.showMessageDialog(this, "Please select a rating for: " + questions[i]);
                return;
            }
            ratingsMap.put(questions[i], selected);
        }
        
        try (Connection conn = DatabaseHelper.connect()) {
            String sql = """
                INSERT INTO survey (
                    full_name, email, dob, contact,
                    pizza, pasta, pap_wors, other,
                    movie_rating, radio_rating, eatout_rating, tv_rating
                ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, fullNames.getText());
                stmt.setString(2, email.getText());
                stmt.setString(3, dateOfBirth.getText());
                stmt.setString(4, contactNumber.getText());
                stmt.setInt(5, pizzaChkBox.isSelected() ? 1 : 0);
                stmt.setInt(6, pastaChkBox.isSelected() ? 1 : 0);
                stmt.setInt(7, papAndWorsChkBox.isSelected() ? 1 : 0);
                stmt.setInt(8, otherChkBox.isSelected() ? 1 : 0);

                stmt.setInt(9, ratingsMap.get("I like to watch movies"));
                stmt.setInt(10, ratingsMap.get("I like to listen to radio"));
                stmt.setInt(11, ratingsMap.get("I like to eat out"));
                stmt.setInt(12, ratingsMap.get("I like to watch TV"));

                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Survey submitted successfully!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        }

    // Clear text fields
    fullNames.setText("");
    email.setText("");
    dateOfBirth.setText("");
    contactNumber.setText("");

    // Uncheck food checkboxes
    pizzaChkBox.setSelected(false);
    pastaChkBox.setSelected(false);
    papAndWorsChkBox.setSelected(false);
    otherChkBox.setSelected(false);

    // Clear radio buttons
    for (int i = 0; i < questions.length; i++) {
        for (int j = 0; j < columns.length; j++) {
            buttons[i][j].setSelected(false);
        }
    }

    }//GEN-LAST:event_submitBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new SurveyForm().setVisible(true));
    }
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField contactNumber;
    private javax.swing.JTextField dateOfBirth;
    private javax.swing.JTextField email;
    private javax.swing.JLabel fillOutSurveyLabel;
    private javax.swing.JTextField fullNames;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JCheckBox otherChkBox;
    private javax.swing.JCheckBox papAndWorsChkBox;
    private javax.swing.JCheckBox pastaChkBox;
    private javax.swing.JCheckBox pizzaChkBox;
    private javax.swing.JPanel ratingsContainer;
    private javax.swing.JButton submitBtn;
    private javax.swing.JLabel viewResultsLabel;
    // End of variables declaration//GEN-END:variables
}
