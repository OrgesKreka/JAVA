///****************************************************************************************************
/// LojaGUI.java					Punoi : Orges Kreka
///										17 / 5 / 2016
///           Gjeneron numra te rastesishem dhe kontrollon shumen e tyre me pergjigjen e perdoruesit.
///           perdoruesi mund te jape pergjigjen duke klikuar butonin llogarit 
///           ose duke shtypur Enter.. . . 
///******************************************************************************************************

import javax.swing.*;
import javax.swing.UIManager.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class LojaGUI extends JFrame
{
     private JButton dil;
     private JButton fillimi;     ///Butonat 
     private JButton llogarit;

	 
	 private JLabel nivelil;
	 private JLabel emril;     /// te dhenat e lojtarit
	 private JLabel piketl;
	 
	 
	 private JLabel mesazhi;   /// pergjigja 
	 
	 
	 private JLabel v1;
	 private JLabel v2;
     private JTextField shuma; /// merr vleren e perdoruesit
	 
	 private int n1; /// vlera 1 
	 private int n2; /// vlera 2
	 
	 
	 
	 private Lojtar lojtari;
	 

	 
	 private Random r; /// gjeneruesi i numrave
	 
	 private int VESHTIRESIA; /// percakton rangun e numrave ne varesi te veshtiresise
	 
	 
	 ///Kontruktori
	public LojaGUI( String emri )
	{
		super( "Gjej Shumën" ); 
		this.setLayout( new BorderLayout () );
		
		lojtari = new Lojtar ();
		lojtari.vendosEmrin( emri );
		
		r = new Random ();
		
		
		VESHTIRESIA = 10; /// numrat e pare jane nga 1 - 10
		
		n1 =  1 + r.nextInt( VESHTIRESIA );
		n2 = 1 + r.nextInt( VESHTIRESIA );
		
		
		JLabel titulli = new JLabel( "Gjej Shumën v1.0.0", SwingConstants.CENTER );
		titulli.setFont( new Font( "Courier New", Font.BOLD, 26 ) );
		titulli.setBorder( new TitledBorder( new EtchedBorder (), "" ) );
		this.add( titulli, BorderLayout.NORTH );
		
		this.add( paneliLojes (), BorderLayout.CENTER ); /// nderton panelin e lojes
		this.add( paneliLojtarit (), BorderLayout.EAST ); /// nderton panelin e lojtarit
        this.add( paneliButonave (), BorderLayout.SOUTH ); /// nderton panelin e butonave
		
		
		this.setSize( 700, 500 );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		this.setVisible( true );
		this.setResizable( false );
		this.setLocationRelativeTo( null );
	} ///FUND konstruktori
	
	
	
	//// nderton panelin e lojes
	private JPanel paneliLojes ()
	{
		JPanel panel = new JPanel( new GridLayout( 1, 4 ) );
		JPanel p = new JPanel( new BorderLayout () );
		
		
		v1 = new JLabel ( n1+"" );
		v2 = new JLabel (  n2+"" );
		
		JPanel paneliShumes = new JPanel ( new GridLayout ( 3, 1 ) );
		shuma = new JTextField( "" );
        shuma.addActionListener( new Llogarit () );
        
        
		paneliShumes.add( new JLabel( " " ) );
		paneliShumes.add( new JLabel( " " ) );
		paneliShumes.add( new JLabel( " " ) );
		paneliShumes.add( shuma );
		
		JLabel plus = new JLabel( " + ");
		JLabel baraz = new JLabel( " = ");
		
		v1.setFont( new Font( "Consolas", Font.BOLD, 38 ) );
		v2.setFont( new Font( "Consolas", Font.BOLD, 38 ) );
		plus.setFont( new Font( "Consolas", Font.BOLD, 38 ) );
		baraz.setFont( new Font( "Consolas", Font.BOLD, 38 ) );
		shuma.setFont( new Font( "Consolas", Font.BOLD, 38 ) );
		
		panel.add( v1 );
		panel.add( plus );
		panel.add( v2 );
		panel.add( baraz );
		panel.add( paneliShumes );
		
		mesazhi = new JLabel( "" );
		
		
		p.add( panel, BorderLayout.CENTER );
		p.add( mesazhi, BorderLayout.SOUTH );
	
		return p;
	} ///FUND paneliLojes
	
	
	
	/// nderton panelin e butonave
	private JPanel paneliButonave ()
	{
		JPanel panel = new JPanel ();
		
		dil = new JButton( " Dil " );
		dil.addActionListener( new Dil () );
		
		fillimi = new JButton( " Rifillo " );
		fillimi.addActionListener( new Fillimi () );
		
		llogarit = new JButton( " Llogarit " );
		llogarit.addActionListener( new Llogarit () );
		
		
         panel.add( dil );
         panel.add( fillimi );
         panel.add( llogarit );
         
		panel.setBorder( new TitledBorder( new EtchedBorder (), "" ) );
		
		return panel;
	} ///FUND paneliButonave
	
	
	
	/// nderton panelin qe shfaq te dhenat e lojtarit
	private JPanel paneliLojtarit ()
	{
		JPanel panel = new JPanel(  new GridLayout( 3, 1 ) );
		
	    emril = new JLabel( "Lojtari:   " + lojtari.merrEmrin () + "          ");
	    piketl = new JLabel( "Piket:   " + lojtari.merrPiket () + "          " );
	    nivelil = new JLabel( "Niveli:  " + lojtari.merrNivelin () + "          ");
		
		
		JPanel p1 = new JPanel ();
		JPanel p2 = new JPanel ();
		JPanel p3 = new JPanel ();
		
		p1.add( emril );
		p2.add( piketl );
		p3.add( nivelil );
     
		
		panel.add( p1  );
		panel.add( p2 );
		panel.add( p3 );
		
		panel.setBorder( new TitledBorder( new EtchedBorder(), "" ) );
		
		return panel;
	} ///FUND paneliLojtarit
	
	
	///**********************************************************************
	/// klasa degjuese qe merr input dhe percakton pergjigjen.
    ///**********************************************************************
	private class Llogarit implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			int sh = 0;
			
			try
			{
			   sh = Integer.parseInt( shuma.getText() );
			}
			catch( Exception exc )
			{   
				mesazhi.setText( "Input i G A B U A R " );
				mesazhi.setFont( new Font( "Consolas", Font.BOLD, 36 ) );
				return; /// te shfaqet gabimi
			}
			
			
			
			if( sh == n1 + n2 )
			{
				mesazhi.setText( "E sakte ! " );
				
				lojtari.shtoPiket ();
			}
			else
				mesazhi.setText( "E pasakte !\n "+ n1 + "  + "+ n2 + " = " + ( n1 + n2 ) );
			
			
		   mesazhi.setFont( new Font( "Consolas", Font.BOLD, 36 ) );
		   
		   reset( lojtari.merrEmrin (),  lojtari.merrNivelin (), lojtari.merrPiket () );
		   
		   
		   LojaGUI.this.luaj ();
		} ///FUND actionEvent
	} ///FUND klasa 
	
	
	
	///**********************************************************************************
	/// klasa degjuese qe mbyll aplikacionin.
	///**********************************************************************************
	private class Dil implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			System.exit( 0 );
		} 
	}///FUND klasa
	
	
	///********************************************************************************
	/// klasa degjuese qe e kthen ne fillimi lojen.
	///********************************************************************************
	private class Fillimi implements ActionListener
	{
		public void actionPerformed( ActionEvent e)
		{
			VESHTIRESIA = 10;
			lojtari.vendosNivelin( 0 );
			mesazhi.setText( "" );
			reset( lojtari.merrEmrin (), 0, lojtari.merrPiket ());
			LojaGUI.this.luaj ();
			
	
		}
	} ///FUND klasa
	
	
	
	/// Mdryshon fushat e lojtarit sipas ndryshimeve te fundit.
	private void reset( String emri, int niveli, int piket )
	{
	    emril.setText( "Lojtari:   " + emri + "          ");
	    piketl.setText( "Piket:   " + piket + "          " );
	    nivelil.setText( "Niveli:  " + niveli + "          ");
	    
	    this.repaint ();
	} ///FUND reset
	
	
	//// gjeneron numrat dhe ndryshon nivelin
	private void luaj ()
	{
		n1 = 1 + r.nextInt( VESHTIRESIA );
		n2 = 1 + r.nextInt( VESHTIRESIA );
		
		v1.setText( n1+"" );
		v2.setText( n2+"" );
		
		shuma.setText( "" );
		
		
		if( lojtari.merrPiket () % 5 == 0 )
		{
			lojtari.shtoNivelin ();
			nivelil.setText( "Niveli:  " + lojtari.merrNivelin () + "          " );
			VESHTIRESIA += 10;
		}
		
		
		this.repaint ();
		
	} ///FUND luaj
} ///FUND KLASA
