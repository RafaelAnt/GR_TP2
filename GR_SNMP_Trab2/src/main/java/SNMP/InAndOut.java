/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SNMP;

import java.awt.Frame;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.snmp4j.smi.OID;

/**
 *
 * @author rafael
 */
public class InAndOut {

    private double in;
    private double out;

    public InAndOut() {
        this.in = 0;
        this.out = 0;
    }

    public InAndOut(String in, String out) {
        this.in = Double.parseDouble(in);
        this.out = Double.parseDouble(out);
    }

    public InAndOut(double in, double out) {
        this.in = in;
        this.out = out;
    }

    /*public void run() {
        System.out.println("timer working");
    }**/

    public double getIn() {
        return this.in;
    }

    public void setIn(double in) {
        this.in = in;
    }

    public void setIn(String in) {
        this.in = Double.parseDouble(in);
    }

    public double getOut() {
        return out;
    }

    public void setOut(double out) {
        this.out = out;
    }

    public void setOut(String out) {
        this.out = Double.parseDouble(out);
    }

    public void loadValues(String IP, String port) throws IOException {

        String IPin = null;
        String IPout = null;

        //try {

            String host = IP.concat("/").concat(port);
            SNMPManager client = new SNMPManager(host);
            client.start();

            //String sysDescr = client.getAsString(new OID(".1.3.6.1.2.1.1.1.0"));
            IPin = client.getAsString(new OID("1.3.6.1.2.1.4.31.1.1.3.1"));
            IPout = client.getAsString(new OID("1.3.6.1.2.1.4.31.1.1.30.1"));

        /*} catch (Exception ex) {
            JOptionPane.showMessageDialog(new Frame(), "Fatal Error!", "Fatal Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }*/

        this.setIn(IPin);
        this.setOut(IPout);
    }

}
