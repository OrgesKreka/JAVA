package ushtrimdiplome2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Orges
 */
public class Aplikacioni extends JFrame
{
    // Permasat e dritares
    private final int GJATESIA = 300;
    private final int GJERESIA = 600;
    
    private JTextField emriKlientitTextField;
    private JTextField numriKlientitTextField;
    private JCheckBox eshteAnetarCheckBox;
    
    private JButton ruajButton;
    private JButton pastroButton;
    private JButton shfaqButton;
    
    // Lista e klienteve qe do shtohen
    private ArrayList< Klient > klientet;
    
    private JTextArea afishoTextArea;
    public Aplikacioni()
    {
        this.setTitle( "Ruaj Kliente - Forme" );
        this.setSize( GJERESIA, GJATESIA );

        klientet = new ArrayList< Klient >();
        
        // Dritarja ndahet ne dy pjese kryesore
        this.setLayout( new GridLayout( 1, 2 ) );
        
        afishoTextArea = new JTextArea();
        afishoTextArea.setEditable( false );
        this.add( afishoTextArea, 0, 0 );
        
        this.add( ndertoPanelinEShtimit() );
        this.setLocationRelativeTo( null );   
        this.setResizable( false );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setVisible( true );
    }
    
    /**
     * Nderton panelin me elementet e tjera per te shtuar nje klient
     * 
     * @return kthen panelin me komponentet ne te
     */
    private JPanel ndertoPanelinEShtimit()
    {
        JPanel paneli = new JPanel( new BorderLayout() );
        
        JPanel p = new JPanel( new FlowLayout() );
        p.add( new JLabel( "Emri Klientit:" ) );
        emriKlientitTextField = new JTextField( 20 );
        p.add( emriKlientitTextField);
        
        p.add( new JLabel( "Numri Klientit:" ) );
        numriKlientitTextField = new JTextField( 20 );
        p.add( numriKlientitTextField);
        
        JPanel tmp = new JPanel( new FlowLayout() );
        tmp.add( new JLabel( "Anetar" ) );
        eshteAnetarCheckBox = new JCheckBox();
        tmp.add(eshteAnetarCheckBox );
        
        p.add( tmp );
        
        paneli.add( p, BorderLayout.CENTER );
        
        ruajButton = new JButton( "Ruaj" );
        ruajButton.addActionListener( new DegjuesRuaj() );
        
        pastroButton = new JButton( "Pastro" );
        pastroButton.addActionListener( new DegjuesPastro() );
        
        shfaqButton = new JButton( "Shfaq" );
        shfaqButton.addActionListener( new DegjuesShfaq() );
        
        JPanel tmp2 = new JPanel( new FlowLayout() );
        tmp2.add( ruajButton );
        tmp2.add( pastroButton );
        tmp2.add( shfaqButton );
        
        paneli.add( tmp2, BorderLayout.SOUTH );        
        return paneli;
    }
    
    /**
     * Kthen komponentet ne default duke hequr tekstin
     * dhe duke uncheckuar checkboxin
     */
    private void pastro()
    {
        emriKlientitTextField.setText( "" );
        numriKlientitTextField.setText( "" );
        eshteAnetarCheckBox.setSelected( false );
    }
    
    /***
     * Klasa qe implementon degjuesin e butonit te ruajtjes
     * kontrollon nese emri dhe numri nuk jane bosh
     * dhe i shton ne arraylist
     * per cdo rast shfaq nje mesazh
     */
    private class DegjuesRuaj implements ActionListener 
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            String emri = emriKlientitTextField.getText();
            String numri = numriKlientitTextField.getText();
            boolean eshteAnetar = eshteAnetarCheckBox.isSelected();
            
            // Jane te dyja fushat me vlera
            if( emri != null && !emri.trim().isEmpty() && numri != null && !numri.trim().isEmpty() )
            {
                Klient klientTmp = new Klient( emri, numri, eshteAnetar );
                
                klientet.add( klientTmp );
                
                pastro();
                
                 JOptionPane.showMessageDialog( null, "Klienti u ruajt", "I N F O", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog( null, "Fushat jane te detyruara te plotesohen te dyja", "E R R O R", JOptionPane.ERROR_MESSAGE);
            }
                
        } 
    }
    
    
    /***
     * Klasa qe implementon degjuesin e butonit te pastrimit
     * therret metoden pastro
     * dhe heq tekstin nga textarea
     */
    private class DegjuesPastro implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
          pastro();
          afishoTextArea.setText( "" );
        }
        
    }
    
    /**
     * Klasa qe implementon degjuesin e butonit te shfaqjes
     * Per cdo objekt Klient ne arraylist
     * e shfaq ne textarea duke therritur metoden toString
     * Nese arrayList eshte bosh shfaq nje mesazh
     */
    private class DegjuesShfaq implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if( klientet.size() > 0 )
            {  // Per cdo klient
                for( Klient k : klientet )
                {
                    afishoTextArea.append( k.toString() + "\n" );
                }
            }
            else
            {
                 JOptionPane.showMessageDialog( null, "Shtoni nje klient qe ta shihni", "I N F O", JOptionPane.INFORMATION_MESSAGE);
            } 
        }   
    }
}
