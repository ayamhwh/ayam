
package SJF_Algorithm;

import java.text.DecimalFormat;
import java.util.Arrays;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class SJF_GanttChart extends javax.swing.JFrame {
    
    int numrow;
    int numcolumn;
    Object[][] tableData;
    DefaultTableModel gantt = new DefaultTableModel();  
    DefaultTableModel value = new DefaultTableModel();

    
    public SJF_GanttChart(int numrow, Object[][] tableData, int numcolumn) {
        initComponents();
        
        this.numcolumn = numcolumn;
        this.tableData = tableData;
        this.numrow = numrow;
        //to get some variable from the previous class/frame
        
        int totalExecutedTIme=0;
        double totalWT=0;
        double totalTaT=0;
        double AWT;
        double aveTaT;
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        table.setDefaultRenderer(Object.class, rightRenderer);
        
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        valueTable.setDefaultRenderer(Object.class, center);
        
        
        gantt = (DefaultTableModel) table.getModel();
        value = (DefaultTableModel) valueTable.getModel();
        gantt.addRow(tableData);
        gantt.setValueAt(0, 0, 0);
        
        Object[][] computation = new Object[numrow][6];
        // to store all the value to use in computation
                
                
            for (int row = 0; row < numrow; row++) {
                
                Object Process = tableData[row][0];
                gantt.addColumn(Process);
                //to add the processes to the gantt chanrt
                
                computation[row][0] = Process;
                // to store the process number in the computation
            }
         
            
             double[] endTime = new double[numrow];
             double[] arrivalTime = new double[numrow];
             double[] startingTime = new double[numrow];
             //array to store the values when we retrieve it from the table
             
            for (int row = 0; row < numrow; row++) {
            
                Object burstTime = tableData[row][2];
                double executedTime = Double.parseDouble(burstTime.toString());
                totalExecutedTIme += executedTime;
                //add the current process burst time to the previous total execution time
                
                endTime[row] = totalExecutedTIme;
                gantt.setValueAt(totalExecutedTIme, 0, row+1);
                //to store every end time of each process
                
                Object retriveArrivalTime = tableData[row][1];
                double arrTime = Double.parseDouble(retriveArrivalTime.toString());
                arrivalTime[row] = arrTime;
                //creating an array to retrieve the arrival time
                //storing it at the arrival time array as double data type
                
                Object retrieveStartTIme = gantt.getValueAt(0, row);
                double ST = Double.parseDouble(retrieveStartTIme.toString());
                startingTime[row] = ST;
                //creating an array to retrieve the Starting time
                //storing it at the starting time array as double data type
                
                
                computation[row][1]= retriveArrivalTime;
                computation[row][2]= ST;
                computation[row][3]= endTime[row];
                // to store the arrival time, starting time, and endtime in the computation table
                
                
                }
            
            
            for (int row = 0; row < numrow; row++) {
                double WT = startingTime[row] - arrivalTime[row];
                double TaT = endTime[row] - arrivalTime[row];
                
                totalWT += WT;
                totalTaT += TaT;
                
                // Assuming computation[row] is an array with at least 6 elements
                computation[row][4] = WT; // Store WT in the 5th column
                computation[row][5] = TaT; // Store TaT in the 6th column
                
            }

            
            Arrays.sort(computation, (row1, row2) -> {
                 String value1 = (String) row1[0];
                 String value2 = (String) row2[0];
                
                 int num1 = Integer.parseInt(value1.substring(1));
                 int num2 = Integer.parseInt(value2.substring(1));
                 return Integer.compare(num1,num2);
                 });
             //to arrange back according to process number
                        
             
            for (int i = 0; i < numrow; i++) {
                value.addRow(computation);
                for(int j = 0; j < computation[0].length;j++){
             
                    Object placing = computation[i][j];
                    
                    value.setValueAt(placing, i, j);
                }
            }
            
            AWT = totalWT/numrow;
            aveTaT = totalTaT/numrow;
            // to get the average of WT and TaT
            
            
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            
            String formateedTotalWT = decimalFormat.format(totalWT);
            String formattedAWT = decimalFormat.format(AWT);
            String formattedTaT = decimalFormat.format(totalTaT);
            String formattedaveTat = decimalFormat.format(aveTaT);
            //to limit all numerical values to 2 decimial points
            
            
            txtTotalWT.setText(String.valueOf("   "+formateedTotalWT));
            txtAverageWT.setText(String.valueOf("   "+formattedAWT));
            txtTotalTaT.setText(String.valueOf("   "+formattedTaT));
            txtAverageTaT.setText(String.valueOf("   "+formattedaveTat));
            // to display the WT AWT TaT ave TaT
            
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTotalWT = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtAverageWT = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTotalTaT = new javax.swing.JLabel();
        txtAverageTaT = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        valueTable = new javax.swing.JTable();
        reset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        table.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                ""
            }
        ));
        jScrollPane1.setViewportView(table);

        jLabel1.setFont(new java.awt.Font("Palatino Linotype", 1, 22)); // NOI18N
        jLabel1.setText("TaT = eT - AT");

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel2.setText("Total Waiting Time = ");

        txtTotalWT.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        txtTotalWT.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel3.setFont(new java.awt.Font("Palatino Linotype", 1, 22)); // NOI18N
        jLabel3.setText("WT = sT - AT");

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel4.setText("Average Waiting TIme = ");

        txtAverageWT.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        txtAverageWT.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel5.setText("Total TurnAround Time = ");

        txtTotalTaT.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        txtTotalTaT.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        txtAverageTaT.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        txtAverageTaT.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel6.setText("Average TurnAround Time = ");

        jLabel7.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 30)); // NOI18N
        jLabel7.setText("SJF Gantt Chart");

        valueTable.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        valueTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PROCESS", "ARRIVAL TIME", "STARTING TIME", "END TIME", "WAITING TIME", "TURNAROUND TIME"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(valueTable);

        reset.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        reset.setText("RESTART");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(394, 394, 394)
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 856, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1))
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtTotalWT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtAverageWT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(181, 181, 181)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtAverageTaT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtTotalTaT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(reset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(41, 41, 41)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                        .addGap(413, 413, 413))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(197, 197, 197)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtTotalWT, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtAverageWT, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtTotalTaT, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtAverageTaT, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(78, 78, 78)
                        .addComponent(reset)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        // TODO add your handling code here:
        SJF_Input sJF_Input = new SJF_Input();
        sJF_Input.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_resetActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SJF_GanttChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SJF_GanttChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SJF_GanttChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SJF_GanttChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
     
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton reset;
    private javax.swing.JTable table;
    private javax.swing.JLabel txtAverageTaT;
    private javax.swing.JLabel txtAverageWT;
    private javax.swing.JLabel txtTotalTaT;
    private javax.swing.JLabel txtTotalWT;
    private javax.swing.JTable valueTable;
    // End of variables declaration//GEN-END:variables
}
