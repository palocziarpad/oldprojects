import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class AboutBox extends JDialog
{
	private JButton	closeButton;
	
	public AboutBox()
	{
		initComponents();
		getRootPane().setDefaultButton(closeButton);
	}
	
	public void closeAboutBox()
	{
		dispose();
	}
	
	private void initComponents()
	{
		this.setSize(300, 150);
		this.setResizable(false);
		this.setTitle("About...");
		/*
		 * closeButton =new JButton(); closeButton.setText("Close");
		 * closeButton.setSize(100, 50);
		 */
		JPanel panel = new JPanel();
		add(panel);
		JTextArea ta = new JTextArea();
		ta.setEditable(false);
		ta.setSize(250, 100);
		
		//ta.add(areaScrollPane);
		ta.setText("Created by Saznetro Soft."
				+ "\n\nGraphics by Mira web: tnelly.daportfolio.com/");
		ta.setLineWrap(true);
		//ta.add(areaScrollPane);
		//panel.add(ta);
		JScrollPane areaScrollPane = new JScrollPane(ta);
		areaScrollPane.setVerticalScrollBarPolicy(
		                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(250, 250));
		panel.setLayout(new BorderLayout());
		panel.add(areaScrollPane,BorderLayout.CENTER);
		//this.pack();
		//ta.setVisible(true);
		// panel.add(closeButton);
		
	}
	
	public JButton getCloseButton()
	{
		return closeButton;
	}
	
}
