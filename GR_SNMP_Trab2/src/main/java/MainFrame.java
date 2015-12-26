/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import SNMP.InAndOut;
import SNMP.SNMPManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import java.util.Random;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/**
 *
 * @author Rafael
 */
public class MainFrame extends javax.swing.JFrame {

    private InAndOut snmp;
    private ScheduledExecutorService exec;
    private boolean isRunning;
    private static TimeSeries dataIN;
    private static TimeSeries dataOUT;
    private double lastIn, lastOut;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();

        this.snmp = new InAndOut();
        this.isRunning = false;
        this.lastIn = 0;
        this.lastOut = 0;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        b_start = new javax.swing.JToggleButton();
        l_in = new javax.swing.JLabel();
        t_ip = new javax.swing.JTextField();
        t_port = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        l_out = new javax.swing.JLabel();
        b_stop = new javax.swing.JButton();
        l_status = new javax.swing.JLabel();
        panel_graphic = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        b_start.setText("Start");
        b_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_startActionPerformed(evt);
            }
        });

        t_ip.setText("127.0.0.1");

        t_port.setText("161");

        jLabel1.setText("IP:");

        jLabel2.setText("Port:");

        jLabel3.setText("In:");

        jLabel4.setText("Out:");

        b_stop.setText("Stop");
        b_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_stopActionPerformed(evt);
            }
        });

        l_status.setText("Stoped");

        javax.swing.GroupLayout panel_graphicLayout = new javax.swing.GroupLayout(panel_graphic);
        panel_graphic.setLayout(panel_graphicLayout);
        panel_graphicLayout.setHorizontalGroup(
            panel_graphicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_graphicLayout.setVerticalGroup(
            panel_graphicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 421, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(t_ip)
                    .addComponent(t_port, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_start, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_status)
                        .addContainerGap(324, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_stop, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_in, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_out, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_graphic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(t_ip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(l_status)
                                .addComponent(b_start)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(b_stop)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(l_in, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(l_out, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(panel_graphic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_startActionPerformed

        if (isRunning) {
            return;
        }
        isRunning = true;

        dataIN = new TimeSeries("In");
        dataOUT = new TimeSeries("Out");

        chartUpdate chartUp = new chartUpdate();
        new Thread(chartUp).start();

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(dataIN);
        dataset.addSeries(dataOUT);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                null, // title
                "Time", // x-axis graf
                "PDU's", // y-axis graf
                dataset, // data
                true, // create legend?
                false, // generate tooltips?
                false // generate URLs?
        );

        final XYPlot plot = chart.getXYPlot();
        ValueAxis axis = plot.getDomainAxis();
        axis.setAutoRange(true);
        axis.setFixedAutoRange(900000.0);
        //900 000 milisegundos = 15 min
        //300 000 milisegundos = 5 min
        // 60 000 milisegundos = 1 min

        ChartPanel graf = new ChartPanel(chart);
        panel_graphic.setLayout(new java.awt.BorderLayout());
        panel_graphic.add(graf, BorderLayout.CENTER);
        panel_graphic.validate();

        l_status.setText("Reading Values...");

    }//GEN-LAST:event_b_startActionPerformed

    private void b_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_stopActionPerformed
        try {
            exec.shutdown();
            isRunning = false;
            l_status.setText("Stoped");

            panel_graphic.repaint();
        } catch (NullPointerException e) {

        }
    }//GEN-LAST:event_b_stopActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });

    }

    class chartUpdate implements Runnable {

        public void run() {
            exec = Executors.newSingleThreadScheduledExecutor();
            exec.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    try {
                        snmp.loadValues(t_ip.getText(), t_port.getText());

                        l_in.setText(Double.toString(snmp.getIn() - lastIn));
                        l_out.setText(Double.toString(snmp.getOut() - lastOut));

                        //System.out.println("Loaded Value!");
                        dataIN.addOrUpdate(new Second(), snmp.getIn()  - lastIn);
                        dataOUT.addOrUpdate(new Second(), snmp.getOut()  - lastOut);
                        
                        lastIn=snmp.getIn();
                        lastOut=snmp.getOut();
                        
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(new Frame(), "Could not Connect!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }, 0, 60, TimeUnit.SECONDS);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton b_start;
    private javax.swing.JButton b_stop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel l_in;
    private javax.swing.JLabel l_out;
    private javax.swing.JLabel l_status;
    private javax.swing.JPanel panel_graphic;
    private javax.swing.JTextField t_ip;
    private javax.swing.JTextField t_port;
    // End of variables declaration//GEN-END:variables
}
