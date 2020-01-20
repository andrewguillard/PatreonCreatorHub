/*
 * Created by JFormDesigner on Sun Jan 12 16:30:59 EST 2020
 */

package com.amg.supporttracker.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.amg.supporttracker.gui.util.*;

import com.amg.supporttracker.gui.util.STStandard;
import com.amg.supporttracker.gui.util.XMLParser;

import com.amg.supporttracker.gui.util.STUtil;
import com.amg.supporttracker.gui.util.dto.PatronDTO;
import net.miginfocom.swing.*;

/**
 * @author Andrew Guillard
 */
public class PatronScreen extends JPanel {

    private ArrayList<PatronDTO> loadedPatrons;
    private ArrayList<STTableHeader> headers;

    public PatronScreen(Dimension dim) {
        loadData();
        this.headers = generateHeaders();
        initComponents();
        this.setSize(dim);

        loadUI();
    }

    private void loadUI(){
        //Create table headers
        patronTable.setHeaders(headers);
        patronTable.setFilter("active");
        patronTable.setTableData(loadedPatrons);

        cboPatronFilter.addItem("Active");
        cboPatronFilter.addItem("Retired");
        cboPatronFilter.addItem("All");
    }

    private void loadData(){
        this.loadedPatrons = XMLParser.readPatronsFile();
//        for(PatronDTO patron : loadedPatrons){
//            System.out.println("Adding Patron: "+patron.getPatronName());
//            insertPatronToTable(patron);
//        }
    }
    
    //TODO: Make an XML Parser to just add a patron. Not search through all of them. 
    public void addPatron(PatronDTO patron){
        loadedPatrons.add(patron);
        insertPatronToTable(patron);
        saveData(loadedPatrons);
    }
    
    public void saveData(ArrayList<PatronDTO> patrons){
        //validate? Maybe? idk
        XMLParser.writePatronsFile(patrons);
    }

    public ArrayList<STTableHeader> generateHeaders(){
        ArrayList<STTableHeader> headers = new ArrayList<>();
        headers.add(new STTableHeader("patronName", "Patron Name"));
        headers.add(new STTableHeader("friendlyName", "Friendly Name", true));
        headers.add(new STTableHeader("discordName", "Discord Name", true));
        headers.add(new STTableHeader("tierNum", "Tier"));
        headers.add(new STTableHeader("pledgeAmount", "Pledge Amount"));
        headers.add(new STTableHeader("totalAmount", "Total Amount"));
        headers.add(new STTableHeader("pledgeDate", "Pledge Date"));
        headers.add(new STTableHeader("status", "Status"));

        return headers;
    }

    //Insert a patron (from PatronDTO) to the table
    //TODO: Reimplement this function
    public void insertPatronToTable(PatronDTO patron){
//        tableModel.addRow(new Object[]{
//                patron.getPatronName(),
//                patron.getFriendlyName(),
//                patron.getDiscordName(),
//                patron.getTierNum(),
//                STUtil.formatCurrency(patron.getPledgeAmount()),
//                STUtil.formatCurrency(patron.getTotalAmount()),
//                STUtil.formatDateToString(patron.getPledgeDate(), STStandard.TABLE_DATE_FORMAT),
//                STUtil.formatDateToString(patron.getDeclineDate(), STStandard.TABLE_DATE_FORMAT),
//                patron.getSource(),
//        });
    }

    private void cboPatronFilterActionPerformed(ActionEvent e) {
        System.out.println("Filtering patrons by: "+cboPatronFilter.getSelectedItem().toString());
        patronTable.doFilter(cboPatronFilter.getSelectedItem().toString());
    }

    private void btnAddPatronActionPerformed(ActionEvent e) {
        System.out.println("Manually adding a patron.");
        AddPatron addPatron = new AddPatron(this);
        addPatron.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Andrew Guillard
        patronTopPanel = new JPanel();
        btnPatreonIntegration = new JButton();
        lblPatronFilters = new JLabel();
        cboPatronFilter = new JComboBox();
        patronScrollPane = new JScrollPane();
        patronTable = new STTable(null, this.headers);
        btnAddPatron = new JButton();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder
        (0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing.border.TitledBorder.CENTER,javax.swing.border
        .TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt
        .Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void
        propertyChange(java.beans.PropertyChangeEvent e){if("\u0062ord\u0065r".equals(e.getPropertyName()))throw new RuntimeException()
        ;}});
        setLayout(new MigLayout(
            "insets 0,hidemode 3,gap 0 0",
            // columns
            "[fill]" +
            "[grow,fill]" +
            "[fill]",
            // rows
            "[60,fill]" +
            "[294,grow]" +
            "[]"));

        //======== patronTopPanel ========
        {
            patronTopPanel.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[152,fill]" +
                "[236,grow,fill]" +
                "[130,fill]" +
                "[236,grow,fill]",
                // rows
                "[]"));

            //---- btnPatreonIntegration ----
            btnPatreonIntegration.setText("Connect to Patreon");
            patronTopPanel.add(btnPatreonIntegration, "cell 0 0");

            //---- lblPatronFilters ----
            lblPatronFilters.setText("Filter Patrons");
            patronTopPanel.add(lblPatronFilters, "cell 2 0");

            //---- cboPatronFilter ----
            cboPatronFilter.addActionListener(e -> cboPatronFilterActionPerformed(e));
            patronTopPanel.add(cboPatronFilter, "cell 3 0");
        }
        add(patronTopPanel, "cell 1 0");

        //======== patronScrollPane ========
        {
            patronScrollPane.setViewportView(patronTable);
        }
        add(patronScrollPane, "cell 1 1");

        //---- btnAddPatron ----
        btnAddPatron.setText("Manually Add a Patron");
        btnAddPatron.addActionListener(e -> btnAddPatronActionPerformed(e));
        add(btnAddPatron, "cell 1 2");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Andrew Guillard
    private JPanel patronTopPanel;
    private JButton btnPatreonIntegration;
    private JLabel lblPatronFilters;
    private JComboBox cboPatronFilter;
    private JScrollPane patronScrollPane;
    private STTable patronTable;
    private JButton btnAddPatron;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
