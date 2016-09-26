import javax.swing.*;

public class Aplikacioni extends JFrame
{

	public static void main( 	String[] args	)
	{
		
		/// Vendos layoutin ne look and feel

		 try 
		    { 
		        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
		    } 
		    catch(Exception e){ 
		    }
	   
	        
			String emri = JOptionPane.showInputDialog( "Emri Lojtarit: " );
			
			
			LojaGUI obj = new LojaGUI ( emri );

		
	
	}///FUND main 

} ///FUND KLASA
