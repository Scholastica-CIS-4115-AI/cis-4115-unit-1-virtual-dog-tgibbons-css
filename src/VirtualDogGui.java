// AI Virtual Dog Assignment for CIS 4115 AI
// Student:  ........Put student name here
// Student should not change this file, but should change the dog.java file
//=========================================================
// based on LunarPhases from http://java.sun.com/docs/books/tutorial/uiswing/learn/example5.html
// modified for virtual dog by Tom Gibbons

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;


public class VirtualDogGui implements ActionListener {
    final static int NUM_IMAGES = 8;
    final static int START_INDEX = 0;

    ImageIcon[] images = new ImageIcon[NUM_IMAGES];
    JPanel mainPanel, selectPanel, displayPanel;

    JComboBox actionChoices = null;
    JLabel actionIconLabel = null;
    JLabel dogstateLabel, dogactionLabel;

    dog fido = new dog();
    
    

    public VirtualDogGui() {
        //Create the action selection and display panels.
        selectPanel = new JPanel();
        displayPanel = new JPanel();

        //Add various widgets to the sub panels.
        addWidgets();

        //Create the main panel to contain the two sub panels.
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        //Add the select and display panels to the main panel.
        mainPanel.add(selectPanel);
        mainPanel.add(displayPanel);
    }

    /*
     * Get the images and set up the widgets.
     */
    private void addWidgets() {
        //Get the images and put them into an array of ImageIcons.
        for (int i = 0; i < NUM_IMAGES; i++) {
        	images[i] = createImageIcon("/images/image" + i + ".gif");
        	//images[i] = createImageIcon("image" + i + ".gif");
        }

        /*
         * Create a label for displaying the moon action images and
         * put a border around it.
         */
        actionIconLabel = new JLabel();
        actionIconLabel.setHorizontalAlignment(JLabel.CENTER);
        actionIconLabel.setVerticalAlignment(JLabel.CENTER);
        actionIconLabel.setVerticalTextPosition(JLabel.CENTER);
        actionIconLabel.setHorizontalTextPosition(JLabel.CENTER);
        actionIconLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLoweredBevelBorder(),
            BorderFactory.createEmptyBorder(5,5,5,5)));

        actionIconLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(0,0,10,0),
            actionIconLabel.getBorder()));

        //Create a combo box with lunar action choices.
        actionChoices = new JComboBox(fido.getActions());
        actionChoices.setSelectedIndex(START_INDEX);

        //Display the first image.
        actionIconLabel.setIcon(images[START_INDEX]);
        actionIconLabel.setText("");

        //Add a border around the select panel.
        selectPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Action for the dog"), 
            BorderFactory.createEmptyBorder(5,5,5,5)));

        //Add a border around the display panel.
        displayPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Dog status"), 
            BorderFactory.createEmptyBorder(5,5,5,5)));

        //Add moon actions combo box to select panel and image label.
        displayPanel.add(actionIconLabel);
        selectPanel.add(actionChoices);

		  // Add label for dog state
        dogstateLabel = new JLabel(fido.getStateLabel(), SwingConstants.LEFT);
        dogstateLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        displayPanel.add(dogstateLabel);

		  // Add label for dog action
        dogactionLabel = new JLabel("", SwingConstants.LEFT);
        dogactionLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        displayPanel.add(dogactionLabel);


        //Listen to events from the combo box.
        actionChoices.addActionListener(this);
    }  
    
    public void actionPerformed(ActionEvent event) {
        if ("comboBoxChanged".equals(event.getActionCommand())) {
            //Update the icon to display the new action.
            // actionIconLabel.setIcon(images[actionChoices.getSelectedIndex()]);
				
        	fido.do_action(actionChoices.getSelectedIndex());
        	actionIconLabel.setIcon(images[fido.getStateAsInt()]);
        	dogstateLabel.setText(fido.getDogStatus());
        }
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imageURL = VirtualDogGui.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource not found: "
                               + path);
            return null;
        } else {
            return new ImageIcon(imageURL);
        }
    }



    /**
     * Create the GUI and show it.  For thread safety, this method should be invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create a new instance of virtual_dog_1.
        VirtualDogGui dogGui = new VirtualDogGui();

        //Create and set up the window.
        JFrame virtualDogFrame = new JFrame("Virtual Dog");
        virtualDogFrame.setPreferredSize(new Dimension(600, 600));
        virtualDogFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        virtualDogFrame.setContentPane(dogGui.mainPanel);

        //Display the window.
        virtualDogFrame.pack();
        virtualDogFrame.setVisible(true);
    }



	// ======================================================
	// Main program: DO NOT CHANGE
	// =====================================================

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
