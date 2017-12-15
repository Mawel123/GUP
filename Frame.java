
import java.awt.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mawel
 */
public class Frame extends javax.swing.JFrame implements PropertyChangeListener {

    protected Simulation simulation;
    protected String eventFlag = "";

    /**
     * Creates new form Field
     */
    public Frame() {
        super();
        initComponents();

        simulation = new Simulation(animation);
        animation.setPaintClient(simulation);
        simulation.addPropertyChangeListener(this);
        numberfieldVelRunner.setText(String.format("%6.2f", simulation.stepLengthRunner * 100));
        numberfieldVelChaserBlue.setText(String.format("%6.2f", simulation.stepLengthChaserBlue * 100));
        numberfieldVelChaserRed.setText(String.format("%6.2f", simulation.stepLengthChaserRed * 100));

        sliderVelRunner.setValue(10);
        sliderVelChaserBlue.setValue(10);
        sliderVelChaserRed.setValue(10);

        numberfieldScale.setText(String.format("%6.2f", simulation.animationScale));
        animation.repaint();
        simulation.init();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //on hit
        if (evt.getPropertyName().equals("hit")) {
            simulation.stop();
            buttonOnOff.setEnabled(false);
            buttonReset.setEnabled(true);
            if (buttonOnOff.getText().equals("STOP")) {
                buttonOnOff.setText("START");
            }
        }

        Vector v;
        if (evt.getPropertyName().equals("nextRunner")) {
            v = (Vector) evt.getNewValue();
            numberfieldPosRunnerX.setText("" + String.format("%6.2f", v.x));
            numberfieldPosRunnerY.setText("" + String.format("%6.2f", v.y));
        }

        if (evt.getPropertyName().equals("nextChaserBlue")) {
            v = (Vector) evt.getNewValue();
            numberfieldPosChaserBlueX.setText("" + String.format("%6.2f", v.x));
            numberfieldPosChaserBlueY.setText("" + String.format("%6.2f", v.y));
        }

        if (evt.getPropertyName().equals("nextChaserRed")) {
            v = (Vector) evt.getNewValue();
            numberfieldPosChaserRedX.setText("" + String.format("%6.2f", v.x));
            numberfieldPosChaserRedY.setText("" + String.format("%6.2f", v.y));
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
        java.awt.GridBagConstraints gridBagConstraints;

        Field = new javax.swing.JPanel();
        animation = new JMath();
        ui = new javax.swing.JPanel();
        buttonReset = new javax.swing.JButton();
        buttonOnOff = new javax.swing.JButton();
        checkboxChaserBlue = new javax.swing.JCheckBox();
        checkboxChaserRed = new javax.swing.JCheckBox();
        buttonPosRnner = new javax.swing.JButton();
        buttonPosChaserBlue = new javax.swing.JButton();
        buttonPosChaserRed = new javax.swing.JButton();
        numberfieldPosRunnerX = new JNumberField();
        numberfieldPosRunnerY = new JNumberField();
        numberfieldPosChaserBlueX = new JNumberField();
        numberfieldPosChaserBlueY = new JNumberField();
        numberfieldPosChaserRedX = new JNumberField();
        numberfieldPosChaserRedY = new JNumberField();
        jLabel1 = new javax.swing.JLabel();
        numberfieldVelRunner = new JNumberField();
        numberfieldVelChaserBlue = new JNumberField();
        numberfieldVelChaserRed = new JNumberField();
        comboBoxForm = new javax.swing.JComboBox<>();
        numberfieldScale = new JNumberField();
        jLabel4 = new javax.swing.JLabel();
        sliderVelRunner = new javax.swing.JSlider();
        sliderVelChaserBlue = new javax.swing.JSlider();
        sliderVelChaserRed = new javax.swing.JSlider();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSlider1 = new javax.swing.JSlider();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Field.setLayout(new java.awt.BorderLayout());

        animation.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Field.add(animation, java.awt.BorderLayout.CENTER);

        ui.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ui.setLayout(new java.awt.GridBagLayout());

        buttonReset.setText("RESET");
        buttonReset.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonReset.setEnabled(false);
        buttonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonResetActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        ui.add(buttonReset, gridBagConstraints);

        buttonOnOff.setText("START");
        buttonOnOff.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonOnOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOnOffActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        ui.add(buttonOnOff, gridBagConstraints);

        checkboxChaserBlue.setSelected(true);
        checkboxChaserBlue.setIconTextGap(0);
        checkboxChaserBlue.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkboxChaserBlueItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        ui.add(checkboxChaserBlue, gridBagConstraints);

        checkboxChaserRed.setSelected(true);
        checkboxChaserRed.setIconTextGap(0);
        checkboxChaserRed.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkboxChaserRedItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        ui.add(checkboxChaserRed, gridBagConstraints);

        buttonPosRnner.setText("Ziel");
        buttonPosRnner.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Position", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        buttonPosRnner.setIconTextGap(0);
        buttonPosRnner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPosRnnerActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 5;
        ui.add(buttonPosRnner, gridBagConstraints);

        buttonPosChaserBlue.setText("Verfolger Blau");
        buttonPosChaserBlue.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Position", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        buttonPosChaserBlue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPosChaserBlueActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 5;
        ui.add(buttonPosChaserBlue, gridBagConstraints);

        buttonPosChaserRed.setText("Verfolger Rot");
        buttonPosChaserRed.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Position", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        buttonPosChaserRed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPosChaserRedActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 5;
        ui.add(buttonPosChaserRed, gridBagConstraints);

        numberfieldPosRunnerX.setEditable(false);
        numberfieldPosRunnerX.setColumns(4);
        numberfieldPosRunnerX.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.insets = new java.awt.Insets(1, 2, 0, 2);
        ui.add(numberfieldPosRunnerX, gridBagConstraints);

        numberfieldPosRunnerY.setEditable(false);
        numberfieldPosRunnerY.setColumns(4);
        numberfieldPosRunnerY.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.insets = new java.awt.Insets(1, 2, 0, 2);
        ui.add(numberfieldPosRunnerY, gridBagConstraints);

        numberfieldPosChaserBlueX.setEditable(false);
        numberfieldPosChaserBlueX.setColumns(4);
        numberfieldPosChaserBlueX.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.insets = new java.awt.Insets(1, 2, 0, 2);
        ui.add(numberfieldPosChaserBlueX, gridBagConstraints);

        numberfieldPosChaserBlueY.setEditable(false);
        numberfieldPosChaserBlueY.setColumns(4);
        numberfieldPosChaserBlueY.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.insets = new java.awt.Insets(1, 2, 0, 2);
        ui.add(numberfieldPosChaserBlueY, gridBagConstraints);

        numberfieldPosChaserRedX.setEditable(false);
        numberfieldPosChaserRedX.setColumns(4);
        numberfieldPosChaserRedX.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.insets = new java.awt.Insets(1, 2, 0, 2);
        ui.add(numberfieldPosChaserRedX, gridBagConstraints);

        numberfieldPosChaserRedY.setEditable(false);
        numberfieldPosChaserRedY.setColumns(4);
        numberfieldPosChaserRedY.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.insets = new java.awt.Insets(1, 2, 0, 2);
        ui.add(numberfieldPosChaserRedY, gridBagConstraints);

        jLabel1.setText("Geschwindigkeit:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        ui.add(jLabel1, gridBagConstraints);

        numberfieldVelRunner.setEditable(false);
        numberfieldVelRunner.setColumns(4);
        numberfieldVelRunner.setFocusable(false);
        numberfieldVelRunner.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                numberfieldVelRunnerFocusLost(evt);
            }
        });
        numberfieldVelRunner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberfieldVelRunnerActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.insets = new java.awt.Insets(1, 2, 0, 7);
        ui.add(numberfieldVelRunner, gridBagConstraints);

        numberfieldVelChaserBlue.setEditable(false);
        numberfieldVelChaserBlue.setColumns(4);
        numberfieldVelChaserBlue.setFocusable(false);
        numberfieldVelChaserBlue.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                numberfieldVelChaserBlueFocusLost(evt);
            }
        });
        numberfieldVelChaserBlue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberfieldVelChaserBlueActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.insets = new java.awt.Insets(1, 2, 0, 7);
        ui.add(numberfieldVelChaserBlue, gridBagConstraints);

        numberfieldVelChaserRed.setEditable(false);
        numberfieldVelChaserRed.setColumns(4);
        numberfieldVelChaserRed.setFocusable(false);
        numberfieldVelChaserRed.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                numberfieldVelChaserRedFocusLost(evt);
            }
        });
        numberfieldVelChaserRed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberfieldVelChaserRedActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.insets = new java.awt.Insets(1, 2, 0, 7);
        ui.add(numberfieldVelChaserRed, gridBagConstraints);

        comboBoxForm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gerade", "Sinus", "Kreis" }));
        comboBoxForm.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxFormItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        ui.add(comboBoxForm, gridBagConstraints);

        numberfieldScale.setColumns(4);
        numberfieldScale.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                numberfieldScaleFocusLost(evt);
            }
        });
        numberfieldScale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberfieldScaleActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 5);
        ui.add(numberfieldScale, gridBagConstraints);

        jLabel4.setText("Maßstab:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 8, 0, 0);
        ui.add(jLabel4, gridBagConstraints);

        sliderVelRunner.setMajorTickSpacing(10);
        sliderVelRunner.setMinorTickSpacing(1);
        sliderVelRunner.setPaintTicks(true);
        sliderVelRunner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderVelRunnerStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        ui.add(sliderVelRunner, gridBagConstraints);

        sliderVelChaserBlue.setMajorTickSpacing(10);
        sliderVelChaserBlue.setMinorTickSpacing(1);
        sliderVelChaserBlue.setPaintTicks(true);
        sliderVelChaserBlue.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderVelChaserBlueStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        ui.add(sliderVelChaserBlue, gridBagConstraints);

        sliderVelChaserRed.setMajorTickSpacing(10);
        sliderVelChaserRed.setMinorTickSpacing(1);
        sliderVelChaserRed.setPaintTicks(true);
        sliderVelChaserRed.setToolTipText("");
        sliderVelChaserRed.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        sliderVelChaserRed.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderVelChaserRedStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        ui.add(sliderVelChaserRed, gridBagConstraints);

        jLabel5.setText("x:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        ui.add(jLabel5, gridBagConstraints);

        jLabel8.setText("y:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        ui.add(jLabel8, gridBagConstraints);

        jSeparator1.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        ui.add(jSeparator1, gridBagConstraints);

        jSlider1.setMajorTickSpacing(10);
        jSlider1.setMaximum(50);
        jSlider1.setMinimum(1);
        jSlider1.setMinorTickSpacing(1);
        jSlider1.setPaintTicks(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        ui.add(jSlider1, gridBagConstraints);

        Field.add(ui, java.awt.BorderLayout.SOUTH);

        getContentPane().add(Field, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void uiSetRunning() {
        buttonReset.setEnabled(false);
        buttonPosRnner.setEnabled(false);
        buttonPosChaserBlue.setEnabled(false);
        buttonPosChaserRed.setEnabled(false);
        comboBoxForm.setEnabled(false);
        sliderVelRunner.setEnabled(false);
        sliderVelChaserBlue.setEnabled(false);
        sliderVelChaserRed.setEnabled(false);
        numberfieldScale.setEnabled(false);
    }

    public void uiSetNotRunning() {
        if (simulation.dataRunner.size() < 2) {
            buttonReset.setEnabled(false);
            buttonPosRnner.setEnabled(true);
            comboBoxForm.setEnabled(true);
            sliderVelRunner.setEnabled(true);
            sliderVelChaserBlue.setEnabled(true);
            sliderVelChaserRed.setEnabled(true);
            numberfieldScale.setEnabled(true);
            if (checkboxChaserBlue.isSelected()) {
                buttonPosChaserBlue.setEnabled(true);
            } else {
                buttonPosChaserBlue.setEnabled(false);
            }
            if (checkboxChaserRed.isSelected()) {
                buttonPosChaserRed.setEnabled(true);
            } else {
                buttonPosChaserRed.setEnabled(false);
            }
        } else if (simulation.dataRunner.size() == 1) {
            comboBoxForm.setEnabled(true);
            sliderVelRunner.setEnabled(true);
            sliderVelChaserBlue.setEnabled(true);
            sliderVelChaserRed.setEnabled(true);
            numberfieldScale.setEnabled(false);
        } else {
            buttonReset.setEnabled(true);
            buttonPosRnner.setEnabled(false);
            buttonPosChaserBlue.setEnabled(false);
            buttonPosChaserRed.setEnabled(false);
            comboBoxForm.setEnabled(false);
            sliderVelRunner.setEnabled(false);
            sliderVelChaserBlue.setEnabled(false);
            sliderVelChaserRed.setEnabled(false);
            numberfieldScale.setEnabled(false);
        }
        buttonOnOff.setEnabled(true);
    }

    protected Vector frameToMath(Vector coordinates) {
        double x = coordinates.x;
        double y = coordinates.y;
        double mx = (x * ((x - (animation.getWidth() / 2)) / x)) / animation.getUnit();
        double my = -(y * ((y - (animation.getHeight() / 2)) / y)) / animation.getUnit();

        return new Vector(mx, my);
    }

    private void buttonOnOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOnOffActionPerformed
        if (simulation.next == false) {
            if (buttonOnOff.getText().equals("START")) {
                buttonOnOff.setText("STOP");
                uiSetRunning();
            }
            if (simulation.dataRunner.size() < 2) {
                simulation.init();
            }
            simulation.start();
        } else if (simulation.next == true) {
            simulation.stop();
            if (buttonOnOff.getText().equals("STOP")) {
                buttonOnOff.setText("START");
                uiSetNotRunning();
            }
        }
    }//GEN-LAST:event_buttonOnOffActionPerformed

    private void buttonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonResetActionPerformed
        simulation.reset();
        simulation.init();
        sliderVelRunner.setValue(10);
        sliderVelChaserBlue.setValue(10);
        sliderVelChaserRed.setValue(10);
        uiSetNotRunning();
        buttonReset.setEnabled(false);
    }//GEN-LAST:event_buttonResetActionPerformed

    private void buttonPosRnnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPosRnnerActionPerformed
        eventFlag = "pos_r";
        createMouseListener();
    }//GEN-LAST:event_buttonPosRnnerActionPerformed

    private void buttonPosChaserBlueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPosChaserBlueActionPerformed
        eventFlag = "pos_cb";
        createMouseListener();
    }//GEN-LAST:event_buttonPosChaserBlueActionPerformed

    private void buttonPosChaserRedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPosChaserRedActionPerformed
        eventFlag = "pos_cr";
        createMouseListener();
    }//GEN-LAST:event_buttonPosChaserRedActionPerformed

    private void checkboxChaserBlueItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkboxChaserBlueItemStateChanged
        if (checkboxChaserBlue.isSelected()) {
            simulation.chaserBlueEnabled = true;
            if (simulation.dataChaserRed.size() < 2) {
                buttonPosChaserBlue.setEnabled(true);
            } else {
                buttonPosChaserBlue.setEnabled(false);
            }
        } else {
            simulation.chaserBlueEnabled = false;
            buttonPosChaserBlue.setEnabled(false);
        }
        animation.repaint();
    }//GEN-LAST:event_checkboxChaserBlueItemStateChanged

    private void checkboxChaserRedItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkboxChaserRedItemStateChanged
        if (checkboxChaserRed.isSelected()) {
            simulation.chaserRedEnabled = true;
            if (simulation.dataChaserRed.size() < 2) {
                buttonPosChaserRed.setEnabled(true);
            } else {
                buttonPosChaserRed.setEnabled(false);
            }
        } else {
            simulation.chaserRedEnabled = false;
            buttonPosChaserRed.setEnabled(false);
        }
        animation.repaint();
    }//GEN-LAST:event_checkboxChaserRedItemStateChanged

    private void comboBoxFormItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxFormItemStateChanged
        simulation.runnerMode = comboBoxForm.getSelectedItem().toString();
    }//GEN-LAST:event_comboBoxFormItemStateChanged

    private void numberfieldVelRunnerFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numberfieldVelRunnerFocusLost
        if (Math.abs(Double.parseDouble(numberfieldVelRunner.getText())) < 1E-7) {
            numberfieldVelRunner.setText(String.format("%6.2f", 0.01));
        }
        simulation.stepLengthRunner = Double.parseDouble(numberfieldVelRunner.getText()) * 0.01;
    }//GEN-LAST:event_numberfieldVelRunnerFocusLost

    private void numberfieldVelRunnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberfieldVelRunnerActionPerformed
        if (Math.abs(Double.parseDouble(numberfieldVelRunner.getText())) < 1E-7) {
            numberfieldVelRunner.setText(String.format("%6.2f", 0.01));
        }
        simulation.stepLengthRunner = Double.parseDouble(numberfieldVelRunner.getText()) * 0.01;
    }//GEN-LAST:event_numberfieldVelRunnerActionPerformed

    private void numberfieldVelChaserBlueFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numberfieldVelChaserBlueFocusLost
        if (Math.abs(Double.parseDouble(numberfieldVelChaserBlue.getText())) < 1E-7) {
            numberfieldVelChaserBlue.setText(String.format("%6.2f", 0.01));
        }
        simulation.stepLengthChaserBlue = Double.parseDouble(numberfieldVelChaserBlue.getText()) * 0.01;
    }//GEN-LAST:event_numberfieldVelChaserBlueFocusLost

    private void numberfieldVelChaserBlueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberfieldVelChaserBlueActionPerformed
        if (Math.abs(Double.parseDouble(numberfieldVelChaserBlue.getText())) < 1E-7) {
            numberfieldVelChaserBlue.setText(String.format("%6.2f", 0.01));
        }
        simulation.stepLengthChaserBlue = Double.parseDouble(numberfieldVelChaserBlue.getText()) * 0.01;
    }//GEN-LAST:event_numberfieldVelChaserBlueActionPerformed

    private void numberfieldVelChaserRedFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numberfieldVelChaserRedFocusLost
        if (Math.abs(Double.parseDouble(numberfieldVelChaserRed.getText())) < 1E-7) {
            numberfieldVelChaserRed.setText(String.format("%6.2f", 0.01));
        }
        simulation.stepLengthChaserRed = Double.parseDouble(numberfieldVelChaserRed.getText()) * 0.01;
    }//GEN-LAST:event_numberfieldVelChaserRedFocusLost

    private void numberfieldVelChaserRedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberfieldVelChaserRedActionPerformed
        if (Math.abs(Double.parseDouble(numberfieldVelChaserRed.getText())) < 1E-7) {
            numberfieldVelChaserRed.setText(String.format("%6.2f", 0.01));
        }
        simulation.stepLengthChaserRed = Double.parseDouble(numberfieldVelChaserRed.getText()) * 0.01;
    }//GEN-LAST:event_numberfieldVelChaserRedActionPerformed

    private void numberfieldScaleFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numberfieldScaleFocusLost
        if (Math.abs(Double.parseDouble(numberfieldScale.getText())) < 1E-7) {
            numberfieldScale.setText(String.format("%6.2f", 0.01));
        }
        simulation.animationScale = Double.parseDouble(numberfieldScale.getText());
        simulation.init();
        buttonReset.setEnabled(true);
    }//GEN-LAST:event_numberfieldScaleFocusLost

    private void numberfieldScaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberfieldScaleActionPerformed
        if (Math.abs(Double.parseDouble(numberfieldScale.getText())) < 1E-7) {
            numberfieldScale.setText(String.format("%6.2f", 0.01));
        }
        simulation.animationScale = Double.parseDouble(numberfieldScale.getText());
        simulation.init();
        buttonReset.setEnabled(true);

    }//GEN-LAST:event_numberfieldScaleActionPerformed

    private void sliderVelChaserRedStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderVelChaserRedStateChanged
        simulation.stepLengthChaserRed = sliderVelChaserRed.getValue() * 0.1 * 0.01;
        numberfieldVelChaserRed.setText(String.format("%6.2f", (sliderVelChaserRed.getValue() * 0.1)));
        buttonReset.setEnabled(true);
    }//GEN-LAST:event_sliderVelChaserRedStateChanged

    private void sliderVelChaserBlueStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderVelChaserBlueStateChanged
        simulation.stepLengthChaserBlue = sliderVelChaserBlue.getValue() * 0.1 * 0.01;
        numberfieldVelChaserBlue.setText(String.format("%6.2f", (sliderVelChaserBlue.getValue() * 0.1)));
        buttonReset.setEnabled(true);
    }//GEN-LAST:event_sliderVelChaserBlueStateChanged

    private void sliderVelRunnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderVelRunnerStateChanged
        simulation.stepLengthRunner = sliderVelRunner.getValue() * 0.1 * 0.01;
        numberfieldVelRunner.setText(String.format("%6.2f", (sliderVelRunner.getValue() * 0.1)));
        buttonReset.setEnabled(true);
    }//GEN-LAST:event_sliderVelRunnerStateChanged

    private void createMouseListener() {
        animation.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setStartingPosition(evt);
            }
        });
    }

    private void setStartingPosition(final MouseEvent evt) {
        Vector coordinate = new Vector(evt.getX(), evt.getY());
        if (eventFlag.equals("pos_r")) {
            addVectorToList(simulation.dataRunner, coordinate);
            Vector trashV1 = frameToMath(coordinate);

//            simulation.runnerDirectionLine  = new Line2D.Double(trashV1.getX(), trashV1.getY(),trashV1.getX(), trashV1.getY());
            simulation.runnerDirectionLine = new Line2D.Double(-4, -2, 4, 2);
            if (comboBoxForm.getSelectedItem().toString().equals("Gerade")) {
                simulation.paintRunnerDirection = true;
                MouseListener[] mouseListeners = animation.getMouseListeners();
                Arrays.asList(animation.getMouseListeners()).removeIf(i -> true);
                animation.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseMoved(MouseEvent e) {
                        Vector trashV2 = frameToMath(new Vector(e.getX(), e.getY()));
                        simulation.runnerDirectionLine.x2 = trashV2.getX();
                        simulation.runnerDirectionLine.y2 = trashV2.getY();
                        animation.repaint();
                    }
                });
            }
        } else if (eventFlag.equals("pos_cb")) {
            addVectorToList(simulation.dataChaserBlue, coordinate);
        } else if (eventFlag.equals("pos_cr")) {
            addVectorToList(simulation.dataChaserRed, coordinate);
        }

        simulation.fireCoordinateChange();

        buttonReset.setEnabled(true);
        eventFlag = "";
    }

    private void addVectorToList(ArrayList<Vector> vectorList, Vector coordinate) {
        if (vectorList.isEmpty()) {
            vectorList.add(frameToMath(coordinate));
        } else {
            vectorList.set(0, frameToMath(coordinate));
        }
    }

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
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Field;
    private JMath animation;
    private javax.swing.JButton buttonOnOff;
    private javax.swing.JButton buttonPosChaserBlue;
    private javax.swing.JButton buttonPosChaserRed;
    private javax.swing.JButton buttonPosRnner;
    private javax.swing.JButton buttonReset;
    private javax.swing.JCheckBox checkboxChaserBlue;
    private javax.swing.JCheckBox checkboxChaserRed;
    private javax.swing.JComboBox<String> comboBoxForm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSlider jSlider1;
    private JNumberField numberfieldPosChaserBlueX;
    private JNumberField numberfieldPosChaserBlueY;
    private JNumberField numberfieldPosChaserRedX;
    private JNumberField numberfieldPosChaserRedY;
    private JNumberField numberfieldPosRunnerX;
    private JNumberField numberfieldPosRunnerY;
    private JNumberField numberfieldScale;
    private JNumberField numberfieldVelChaserBlue;
    private JNumberField numberfieldVelChaserRed;
    private JNumberField numberfieldVelRunner;
    private javax.swing.JSlider sliderVelChaserBlue;
    private javax.swing.JSlider sliderVelChaserRed;
    private javax.swing.JSlider sliderVelRunner;
    private javax.swing.JPanel ui;
    // End of variables declaration//GEN-END:variables

}
