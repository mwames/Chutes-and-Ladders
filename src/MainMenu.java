import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.GridLayout;
import java.awt.BorderLayout;


public class MainMenu extends JPanel 
{
	JCheckBox cbComputer1, cbComputer2, cbComputer3;
	int computerPlayers = 0;
	
	public MainMenu() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblWelcomeTo = new JLabel("Welcome to:");
		lblWelcomeTo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblWelcomeTo);
		
		JLabel lblNewLabel = new JLabel("Chutes and Ladders");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		panel_1.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(2, 4, 0, 0));
		
		JButton btnPlayer = new JButton("1 player");
		btnPlayer.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				computerPlayers = cmptCount();
				GameManager.setNumberOfPlayers(1, cmptCount());
				GameManager.switchScreen();
			}
		});
		panel.add(btnPlayer);
		
		JButton btnPlayers = new JButton("2 players");
		btnPlayers.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				computerPlayers = cmptCount();
				GameManager.setNumberOfPlayers(2, cmptCount());
				GameManager.switchScreen();
			}
		});
		panel.add(btnPlayers);
		
		JButton btnPlayers_1 = new JButton("3 players");
		btnPlayers_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				GameManager.setNumberOfPlayers(3, cmptCount());
				GameManager.switchScreen();
			}
		});
		panel.add(btnPlayers_1);
		
		JButton btnPlayers_2 = new JButton("4 players");
		btnPlayers_2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				GameManager.setNumberOfPlayers(4, cmptCount());
				GameManager.switchScreen();
			}
		});
		panel.add(btnPlayers_2);
		
		cbComputer1 = new JCheckBox("1 Computer playing");
		cbComputer1.addItemListener(new ItemListener ()
		{
			
			public void itemStateChanged(ItemEvent e) 
			{
				
				if (cbComputer1.isSelected())
					btnPlayers_2.setEnabled(false);
				
				if(!cbComputer1.isSelected() && !cbComputer2.isSelected() && !cbComputer3.isSelected())
					btnPlayers_2.setEnabled(true);
			}
		});
		panel.add(cbComputer1);
		
		cbComputer2 = new JCheckBox("2 Computers playing");
		cbComputer2.addItemListener(new ItemListener ()
		{
			
			public void itemStateChanged(ItemEvent e) 
			{
				
				if (cbComputer2.isSelected())
				{
					btnPlayers_2.setEnabled(false);
					btnPlayers_1.setEnabled(false);

				}
					
				
				if(!cbComputer2.isSelected() && !cbComputer3.isSelected())
				{
					btnPlayers_1.setEnabled(true);
					if(!cbComputer1.isSelected())
						btnPlayers_2.setEnabled(true);
				}
					
			}
		});
		panel.add(cbComputer2);	
		
		cbComputer3 = new JCheckBox("3 Computers playing");
		cbComputer3.addItemListener(new ItemListener ()
		{
			
			public void itemStateChanged(ItemEvent e) 
			{
				
				if (cbComputer3.isSelected())
				{
					btnPlayers_2.setEnabled(false);
					btnPlayers_1.setEnabled(false);
					btnPlayers.setEnabled(false);
				}
					
				
				if(!cbComputer3.isSelected())
				{
					btnPlayers.setEnabled(true);					
					if(!cbComputer2.isSelected())
					{
						btnPlayers_1.setEnabled(true);
						if(!cbComputer1.isSelected())
						
							btnPlayers_2.setEnabled(true);									
					}
	
				}
					
			}
		});
		panel.add(cbComputer3);
		
				
	}
	
	public int cmptCount()
	{
		if(cbComputer3.isSelected())
			computerPlayers = 3;
		else if (cbComputer2.isSelected())
			computerPlayers = 2;
		else if(cbComputer1.isSelected())
			computerPlayers = 1;
		else
			computerPlayers = 0;
		
		return computerPlayers;
	}

	
}