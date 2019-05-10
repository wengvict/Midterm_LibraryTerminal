package co.grandcircus;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LibraryGUI extends JFrame {
	
	JButton buttonDisplayBookInv;
	JButton buttonCheckOut;
	JButton buttonSearchTitle;
	JButton buttonSearchAuthor;
	JButton buttonQuit;
	JTextField searchFieldTitle;
	JTextField searchFieldAuthor;
	int buttonClicked;
	
	
	public static void main(String[] args) {
		
		initGUI();
		
		//new LibraryGUI();		
	}
	
	public LibraryGUI() {
		
		// sets size of screen
		//this.setSize(400,400);
		
		// instantiates toolkit to allow further positioning of window on screen
		Toolkit tk = Toolkit.getDefaultToolkit();
		// holds width/height for window
		Dimension dim = tk.getScreenSize();
		
		// defines width of screen, divides by 2 to find center
		int xPos = (dim.width/ 2) - (this.getWidth() /2);
		// same but for height
		int yPos = (dim.height/ 2) - (this.getHeight() /2);
		this.setLocation(xPos, yPos);

		// quit by hitting close button
		// if this isn't called, Java requires manual close is system tray
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Library GUI");
		
		// panel is where you put things in the window
		// label prints text to user
		JPanel thePanel = new JPanel();
		JLabel label1 = new JLabel("Welcome to the library!");
		thePanel.add(label1);
		label1.setToolTipText("Mouseover message here");
		
		// Display book catalogue
		JButton buttonDisplayBookInv = new JButton("Display Book Catalogue");
		//buttonDisplayBookInv.setBorderPainted(false);
		buttonDisplayBookInv.setContentAreaFilled(true);
		buttonDisplayBookInv.setToolTipText("See our list of the books");
		thePanel.add(buttonDisplayBookInv);
		
		// creates listener object for clicking button
		ListenForButton listenDisplayBookInv = new ListenForButton();
		buttonDisplayBookInv.addActionListener(listenDisplayBookInv);
		
		// Check out book button
		JButton buttonCheckOut = new JButton("Check Out Book");
		//buttonDisplayBookInv.setBorderPainted(false);
		buttonCheckOut.setContentAreaFilled(true);
		buttonCheckOut.setToolTipText("Check out your books");
		thePanel.add(buttonCheckOut);
		
		// Search by title button, or should we just have the search bar?
		JButton buttonSearchTitle = new JButton("Search by Book Title");
		//buttonDisplayBookInv.setBorderPainted(false);
		buttonSearchTitle.setContentAreaFilled(true);
		buttonSearchTitle.setToolTipText("Search titles");
		thePanel.add(buttonSearchTitle);
		
		// Search by author button, or should we just have the search bar?
		JButton buttonSearchAuthor = new JButton("Search by Author");
		//buttonDisplayBookInv.setBorderPainted(false);
		buttonSearchAuthor.setContentAreaFilled(true);
		buttonSearchAuthor.setToolTipText("Search authors");
		thePanel.add(buttonSearchAuthor);
		
		// Search by author button, or should we just have the search bar?
		JButton buttonQuit = new JButton("Quit");
		//buttonDisplayBookInv.setBorderPainted(false);
		buttonQuit.setContentAreaFilled(true);
		thePanel.add(buttonQuit);
		buttonQuit.addActionListener(new CloseListener());
		
		
		// search field by title
		JTextField searchFieldTitle = new JTextField("Search by title", 15);
		searchFieldTitle.setColumns(10);
		thePanel.add(searchFieldTitle);
		
		
		
		// search field by author
		JTextField searchFieldAuthor = new JTextField("Search by author", 15);
		searchFieldAuthor.setColumns(10);
		thePanel.add(searchFieldAuthor);
		
		
		
		this.add(thePanel);
	
		// centers window instead of placing at (0,0)
		//this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		// auto-highlights upon opening window
		// searchFieldTitle.requestFocus();
	}
	
	public static void initGUI() {
		JFrame libFrame = new JFrame("Library System Name");
		//libFrame.setTitle("Library App Name Here");
		libFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		libFrame.setLayout(new BorderLayout());
		
		JPanel topCenterPanel = new JPanel(new GridBagLayout());
		topCenterPanel.setBorder(BorderFactory.createTitledBorder("Library App Name Here"));
		
		GridBagConstraints labelConstraints = new GridBagConstraints();
		
		labelConstraints.anchor = GridBagConstraints.NORTH;
		labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.weightx = 0.5;
        labelConstraints.weighty = 1;
        labelConstraints.insets = new Insets(5, 10, 5, 10);
		
		JPanel leftPanel = new JPanel(new GridBagLayout());
		libFrame.add(leftPanel);
//		leftPanel.setBorder(BorderFactory.createTitledBorder("Library App Name Here"));
		labelConstraints.anchor = GridBagConstraints.WEST;
		labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.weightx = 0.5;
        labelConstraints.weighty = 1;
        labelConstraints.insets = new Insets(5, 10, 5, 10);
        GridBagConstraints fieldConstraints = new GridBagConstraints();
        
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.fill = GridBagConstraints.HORIZONTAL;
        buttonConstraints.insets = new Insets(10, 10, 10, 10);
        buttonConstraints.weighty = 1;
        buttonConstraints.gridy = 0;
        
//        fieldConstraints.anchor = GridBagConstraints.WEST;
//        fieldConstraints.gridx = 1;
//        fieldConstraints.gridy = 0;
//        fieldConstraints.weightx = 0.5;
//        fieldConstraints.weighty = 1;
//        fieldConstraints.insets = new Insets(5, 10, 5, 10);
        //topLeftPanel.add(new JLabel("Type"), labelConstraints);
        
        JButton reset = new JButton("Reset");
        leftPanel.add(reset, buttonConstraints);
        
        // Title search fields on 
        leftPanel.add(new JLabel("Search By Title: "), labelConstraints);
        JTextField titleField = new JTextField(20);
        fieldConstraints.gridy++;
        leftPanel.add(titleField, fieldConstraints);
        labelConstraints.gridy++;
        
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBorder(BorderFactory.createEmptyBorder());
        libFrame.add(rightPanel, BorderLayout.EAST);
        
        
        
        // // Author search field
        rightPanel.add(new JLabel("Search By Author: "), labelConstraints);
        JTextField authorField = new JTextField(20);
        fieldConstraints.gridy++;
        rightPanel.add(authorField, fieldConstraints);
        labelConstraints.gridy++;
        
		
		libFrame.pack();
		libFrame.setVisible(true);
		
	}
	
	private class ListenForButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == buttonDisplayBookInv) {
				buttonClicked++;
				// do things, maybe output array list?
			}
		}
	}
	
	private class CloseListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	
}
	
	
//	private JTextField tf;
//	private JButton displayIndex;
//	private JButton searchByTitle;
//	private JButton searchByAuthor;
//	private JButton quit;
//	
//	public showUI() {
//		createAndShow();
//		
//	private void createAndShow() {
//		JFrame mainFrame = new JFrame("Launch Library System");
//		addComponents(mainframe.get)
//		
//	}

//		JFrame f = new JFrame("Library");
//		final JTextField tf = new JTextField();
//		tf.setBounds(50, 50, 150, 20);
//		JButton b = new JButton("Search");
//		b.setBounds(50, 100, 95, 30);
//		b.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				tf.setText("Try searching by title or author name");
//			}
//		});
//		f.add(b);
//		f.add(tf);
//		f.setSize(400, 400);
//		f.setLayout(null);
//		f.setVisible(true);

