package bank_project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.UIManager;

public class MainFrame extends JFrame
{

	
    public MainFrame() {
		
    	WindowListener sluchacz = new Zamykanie();
        this.addWindowListener(sluchacz);
    	
	}

	class Zamykanie extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
        	try {
            int answer = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz zamkn¹æ aplikacjê ?", "Wyjœcie z aplikacji", JOptionPane.YES_NO_OPTION);
           if (answer == JOptionPane.YES_OPTION)
           {
                 System.exit(0);
           }
        	}catch (Exception ex) {
        		ex.printStackTrace();
        	}
 
        }
    }
}
