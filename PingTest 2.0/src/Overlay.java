import java.awt.*;
import java.awt.Window.Type;

import javax.swing.*;

public class Overlay {
	private JLabel text = new JLabel("Pinging...");
	private JFrame frame = new JFrame("Ping tester");

    public Overlay() {
    	text.setFont(new Font("Roboto", Font.BOLD, 14));
        text.setForeground(Color.GREEN);
        frame.setUndecorated(true);
        frame.setBackground(new Color(0, 0, 0, 0));
        frame.setLocation(0, 1020);
        frame.setAlwaysOnTop(true);
        //starting systray
        frame.setType(Type.UTILITY);
        this.sysTray();
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(text, BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.pack();
        
        
    }
    
    public void setPing(String string){
    	
    	double n = Double.parseDouble(string);
    	
    	if(n <= 0.06) {
    		text.setForeground(Color.GREEN);
    		
    	}else if( n > 0.1) {
    		text.setForeground(Color.RED);
    		
    	}else if ((n > 0.06 ) && ( n <= 0.1)) {
    		text.setForeground(Color.ORANGE);
    		
    	}
    	text.setText(string+"s");
  
    	text.updateUI();
    }
    
    
    private void sysTray() {
    	
    	
    	ImageIcon icon = new ImageIcon("ping.png");
    	Image image = icon.getImage();
    	TrayIcon trayIcon = new TrayIcon(image,"PingTest 3.0");
    	SystemTray tray = SystemTray.getSystemTray();
    	
    	PopupMenu menu = new PopupMenu();
    	MenuItem btnQuitter = new MenuItem("Quitter");
    	btnQuitter.addActionListener(e -> {
    		System.exit(0);
    	});
    	
    	menu.add(btnQuitter);
    	
    	trayIcon.setPopupMenu(menu);
    	
    	
    	try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}