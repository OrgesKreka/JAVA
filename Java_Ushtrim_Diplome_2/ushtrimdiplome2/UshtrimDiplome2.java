/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ushtrimdiplome2;

import javax.swing.UIManager;

/**
 *
 * @author Orges
 */
public class UshtrimDiplome2
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
          try 
        { 
             UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
        } catch (Exception ex) 
        { }
        
       Aplikacioni ushtrimi1 = new Aplikacioni();
    }
    
}
