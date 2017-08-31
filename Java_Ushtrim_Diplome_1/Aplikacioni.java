package ushtrimdiplome;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 * @author Orges
 */
public class Aplikacioni extends JFrame 
{   
    // Permasat e dritares
    private final int GJATESIA = 500;
    private final int GJERESIA = 700;
    
    // 100px gjatesia e vijes
    private final int GJATESIA_VIJES = 100;
    
    // Konstante per te percaktuar kahun e levizjes
    private final String MAJTAS   = "M";
    private final String DJATHTAS = "D";
    
    // Konstante per te percaktuar veprimin aktual
    private final  int ZHVENDOS = 1;
    private final int  SHKRUAJ  = 2;
    
    // Folderi ku gjendet skedari leviz.txt
    private String folderiSkedarit = System.getProperty("user.home");
    private String emriSkedarit  = "leviz.txt";
    
    
    private JMenu fileMenu;
    private JMenu exitMenu;
    
    private JButton majtasButton;
    private JButton djathtasButton;
    private JButton ruajZhvendosjen;
    
    private JLabel mesazh;
    
    private KomponenteVije vija;
    
    private ArrayList< String > levizjetMajtas;
    private ArrayList< String > levizjetDjathtas;
    
    private PrintWriter out;
    private String output;
    
    private int veprimi;
    
    private boolean isDebug;
    
    
    public Aplikacioni()
    {   
        //this.setDefaultLookAndFeelDecorated(true);
        this.setTitle( "Ushtrimi 1-a + 1-b" );
        this.setSize( GJERESIA, GJATESIA );
        
        this.setLocationRelativeTo( null );   
        this.setResizable( false );
        this.setVisible( true );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        // Dritarja ndahet ne dy pjese kryesore
        this.setLayout( new GridLayout( 1, 2 ) );
        
        
        levizjetMajtas = new ArrayList<>();
        levizjetDjathtas = new ArrayList<>();
        
        out = null;
        output = "";
        
        veprimi = 0;

       isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("jdwp") >= 0;
        
        
        this.setJMenuBar( shtoMenuBar() );
        
        this.add( ndertoVijen(), 0, 0 );
        this.add( paneliKontrollit(), 0, 1 );


    }//FUND konstruktori
    
    /***
     *  Krijon menute kryesore File dhe Te tjera
     *  Shton nenmenute dhe eventet e tyre
     * @return MenuBari
     */
    private JMenuBar shtoMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        
        fileMenu = new JMenu( "File" );
        
        // Nese skedari ekziston, lexon permbajtjen e tij
        // Ne te kundert shfaq nje mesazh
        JMenuItem lexoSkedar = new JMenuItem( new AbstractAction("Lexo Skedarin")
        {
            
            @Override
            public void actionPerformed( ActionEvent e )
            {   
                // Fillimisht ngarko ne arraylista permbajtjen e skedarit
                lexoSkedarin();
                veprimi = ZHVENDOS;
            }
        });

        // Nese rryma eshte ne rregull dhe ka input shkruan dhe ose krijon skedari
        // Ne te kundert shfaq nje mesazh
        JMenuItem shkruajNeSkedar = new JMenuItem( new AbstractAction( "Shkruaj ne Skedar")
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {   
                veprimi = SHKRUAJ; 
            }
        });
        
        fileMenu.add( lexoSkedar );
        fileMenu.add( shkruajNeSkedar );
        
        
        exitMenu = new JMenu( "Te Tjera " );
        
        // Shfaq nje dialog
        // Nese zgjidhet ok
        // Aplikacioni mbyllet
        JMenuItem dil = new JMenuItem( new AbstractAction( "Dil" )
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int p = JOptionPane.showConfirmDialog(null, "E sigurt qe do e mbyllni? " );
                
                if( p == JOptionPane.OK_OPTION )
                {
                    System.exit( 0 );
                }
            }

        });
        
        
        // Shfaq nje mesazh te krijuar nga shefi
        JMenuItem about = new JMenuItem(  new AbstractAction( "About") 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog( null , "Ushtrimi 1-a dhe 1-b i javes ne provimin e diplomes \n te dy se bashku kane 25 pike \n Autor: Orges Kreka  \n \t 31-08-2017" );
            }
            
        });
        
        exitMenu.add( dil );
        exitMenu.add( about );
        
        menuBar.add( fileMenu );
        menuBar.add( exitMenu );
        
        return menuBar;
    }
    
    /**
     *  Lexon skedarin <ref: emriSkedarit>
     *  Nese skedari nuk ekziston ose nuk mund te aksesohet, shfaqet nje dritare me gabimin perkates
     * 
     */
    private void lexoSkedarin()
    {
                String pathiSkedarit = folderiSkedarit + File.separator + emriSkedarit;
                
                File skedari = new File(  pathiSkedarit );
                Scanner in = null;
                
                String foo = "";
                
                if( skedari.exists() && ! skedari.isDirectory()) 
                {   
                    try
                    {
                         in = new Scanner( skedari );
                         
                        while(  in.hasNextLine() )
                        {
                            String drejtim = in.nextLine();
                           
                            
                            if( drejtim.equalsIgnoreCase( MAJTAS ) )
                            {
                                levizjetMajtas.add( MAJTAS );
                            }
                            else
                            {
                                levizjetDjathtas.add( DJATHTAS );
                            }
                            
                            foo += drejtim;
                        }
                        
                        if( isDebug ) System.out.println( foo );
                    }
                     catch( Exception exc )
                     {
                         JOptionPane.showMessageDialog( null , "Dicka shkoi gabim.. " + System.getProperty("line.separator") + exc.getMessage() , "E R R O R", JOptionPane.ERROR_MESSAGE);
                         
                         //todo - bej panelin disable
                     }
                    finally
                    {
                        in.close();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog( null , emriSkedarit + " nuk ekziston, krijojeni, shtoni disa vlera dhe provoni perseri " , "Warning", JOptionPane.WARNING_MESSAGE );
                    
                    // todo - bej panelin disable
                }
    }
    
    /***
     *  Hap rrjedhat per te shkruar ne skedar
     *  out, rrjedha qe krijon skedarin nese nuk ekziston, ose per te mbishkruar permbajtjen e tij
     *  Ne skedar komandat ruhen ne formen
     * M ose D 
     * rresht i ri komanda e rradhes
     * M - majtas dhe D - djathtas
     */
    private void shkruajNeSkedar( String text )
    {
         String pathiSkedarit = folderiSkedarit + File.separator + emriSkedarit;
                
          File skedari = new File(  pathiSkedarit );
          
              try
              {
                  out = new PrintWriter( pathiSkedarit );
                  out.write(text);
                   
              }
              catch( Exception exc )
              {
                   JOptionPane.showMessageDialog( null , "Dicka shkoi gabim.. " + System.getProperty("line.separator") + exc.getMessage() , "E R R O R", JOptionPane.ERROR_MESSAGE);
                   
                   return;
              }
              finally
              {
                  out.close();
              }
              
    }//FUND shkruaj ne skedar

    private KomponenteVije ndertoVijen()
    {
         // Vlera hardcoded
         // Mund te behen llogari me permasat e dritares per ti bere me dinamike...
         vija = new KomponenteVije( 50, 200, 200 );
         
         return vija;
    }

    private Component paneliKontrollit()
    {
      JPanel p = new JPanel( new BorderLayout() );
      
      majtasButton = new JButton( "Majtas" );
      majtasButton.addActionListener( new DegjuesButoni1() );
      
      djathtasButton = new JButton( "Djathtas");
      djathtasButton.addActionListener( new DegjuesButoni2() );
      
      
      ruajZhvendosjen = new JButton(  "Ruaj Zhvendosjen" );
      ruajZhvendosjen.addActionListener( new DegjuesButoni3() );
      
      
      JPanel p1 = new JPanel( new FlowLayout(FlowLayout.CENTER) );
      
      if( veprimi ==  ZHVENDOS  )
      {
          mesazh = new JLabel( "----- Kliko butonat per te zhvendosur vijen me 15 px -----" );
      }
      else if( veprimi ==  SHKRUAJ  )
      {
           mesazh = new JLabel( "----Kliko butonat per te percaktuar zhvendosjen e  vijes----" );
      }
      else
      {
          mesazh = new JLabel( "---------- Zgjidhni nje veprim nga menuja File. ----------   \n\n " );
      }
      
      p1.add( mesazh );
      p1.add( majtasButton );
      p1.add( djathtasButton );
      
      JPanel p2 = new JPanel();
      p2.add( ruajZhvendosjen );
      
      p.add( p2, BorderLayout.SOUTH );
           
      p.add( p1, BorderLayout.CENTER );
      p.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      return p;
    }

    private  class DegjuesButoni3 implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if( veprimi == SHKRUAJ )
            {   
                if( output.length() > 0 )
                {
                     Aplikacioni.this.shkruajNeSkedar( output );
                     
                      JOptionPane.showMessageDialog( null , "Drejtimet u ruajten" , "OK", JOptionPane.INFORMATION_MESSAGE  );
                }
                else
                {
                     JOptionPane.showMessageDialog( null , "Klikoni butonat e drejtimeve, per te ruajtur drejtimet ne skedar" , "Info", JOptionPane.INFORMATION_MESSAGE );
                }
                
            }
            else
            {
                 JOptionPane.showMessageDialog( null , "Ky buton mund te perdoret vetem kur duhet te ruhen vlerat ne skedar" , "Info", JOptionPane.INFORMATION_MESSAGE );
            }
           
        }
    }

    private class DegjuesButoni2 implements ActionListener
    {
        @Override
        public void actionPerformed( ActionEvent e )
        {
            if( veprimi == ZHVENDOS )
           {
               if( levizjetDjathtas.size() > 0 )
               {
                   vija.zhvendos( DJATHTAS );
                   levizjetDjathtas.remove( levizjetDjathtas.size() - 1 );
                   
                   if( isDebug ) System.out.println( "D: " + vija.toString() );
                   
                   
               }
               else
               {
                 JOptionPane.showMessageDialog( null , "Nuk ka me levizje djathtas" , "Info", JOptionPane.INFORMATION_MESSAGE );   
               }
               
           }
           else if( veprimi == SHKRUAJ)
           { 
             output += DJATHTAS + System.getProperty("line.separator");
           }
           else
           {
               JOptionPane.showMessageDialog( null , "Zgjidhni nje veprim nga menuja File" , "Info", JOptionPane.INFORMATION_MESSAGE );   
               
           }
        }
    }

    private  class DegjuesButoni1 implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
           
           if( veprimi == ZHVENDOS )
           {
               if( levizjetMajtas.size() > 0 )
               {
                   vija.zhvendos( MAJTAS );
                   levizjetMajtas.remove( levizjetMajtas.size() - 1 );
                   
                     if( isDebug ) System.out.println( "M: " + vija.toString() );
               }
               else
               {
                 JOptionPane.showMessageDialog( null , "Nuk ka me levizje majtas" , "Info", JOptionPane.INFORMATION_MESSAGE );   
               }
               
           }
           else if( veprimi == SHKRUAJ)
           { 
             output += MAJTAS + System.getProperty("line.separator");
           }
           else
           {
               JOptionPane.showMessageDialog( null , "Zgjidhni nje veprim nga menuja File" , "Info", JOptionPane.INFORMATION_MESSAGE );   
               
           }
        }
        
    }
    
}
