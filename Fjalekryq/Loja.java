import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Loja extends JFrame
{

   private JTextField[][] fushat;
   private ArrayList< Enigma > enigmat;
   private ArrayList< String > pergjigjet;

   private int pI;
   private int pJ;

   private int i;
   private int j;

   private boolean vertikalisht;

   private JButton dil;
   private JButton ndihme;
   private JButton kontrollo;
   private JLabel mesazh;


	public Loja ()
	{
		super( "F J A L E K R Y Q" );
		this.setLayout( new BorderLayout () );

        fushat = new JTextField[ 13 ][ 13 ];
        enigmat = new ArrayList<>();
        pergjigjet = new ArrayList<>();

         pI = 1;
         pJ = 1;

         i = 0;
         j = 0;

            this.add( paneliPyetjeve (), BorderLayout.EAST );
            this.add( paneliButonave (), BorderLayout.SOUTH );
			this.add( inicializoFushen (), BorderLayout.CENTER );
			this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			this.setSize( 580, 580 );
			//this.relativeTo( null );
			this.setVisible( true );

	}



	private JPanel inicializoFushen ()
	{
		JPanel panel = new JPanel( new GridLayout( 13, 13 ) );

		    for( int i = 0; i < 13; i++ )
				for( int j = 0; j < 13; j++ )
					{
						fushat[ i ][ j ] = new JTextField( 1 );
						fushat[ i ][ j ].addFocusListener( new DegjuesTasti () );
						fushat[ i ][ j ].setFont( new Font( "Consolas", Font.BOLD, 26 ) );
					    panel.add( fushat[ i ][ j ] );
			        }

                   this.fushaPaPerdorim ();
                   this.numratEFushave ();
			        return panel;
	}


	private void fushaPaPerdorim ()
	{
	     Scanner input;

	     try
	     {
			 File f = new File( "fushaPaPerdorim.txt" );
			 input = new Scanner( f );

			 while( input.hasNextInt () )
			 {
				 int i = input.nextInt ();
				 int j = input.nextInt ();

				 fushat[ i ][ j ].setBackground( Color.BLACK );
				 fushat[ i ][ j ].setEditable( false );
			 } ///FUND while

			 input.close ();
		 }
		 catch( Exception e )
		 {
			 System.out.println( e.getMessage () );
		 }

	}


	private void numratEFushave ()
	{
		Scanner in;

		try
		{
			File f = new File( "numratEFushave.txt" );

			in = new Scanner( f );

			while( in.hasNextInt () )
			{
				int i = in.nextInt ();
				int j = in.nextInt ();
				int v = in.nextInt ();


			}///FUND while

			in.close ();
		}
		catch( Exception e )
		{
			System.out.println( e.getMessage () );
		}
	}


	private JPanel paneliPyetjeve ()
	{
		JPanel p = new JPanel( new GridLayout( 2, 1 ) );

		JLabel l1 = new JLabel( "Horizontal" );
		l1.setFont( new Font( "Consolas", Font.BOLD, 18 ) );


		this.lexoSkedarin ( "enigmatVertikal.txt" );
		this.lexoSkedarin( "enigmatHorizontal.txt" );


		JTextArea t1 = new JTextArea();

		JTextArea t2 = new JTextArea ();

		Enigma tmp;

		for( int i = 0; i < enigmat.size (); i++ )
		{
			tmp = enigmat.get( i );

			if( tmp.merrOrientimin ().equals( "horizontal" ) )

				t1.append( tmp.merrPozicionin () + "  " + tmp.merrPyetjen () + " ( "  + tmp.gjatesia () + " )" +  "\n" );
			else

				 t2.append( tmp.merrPozicionin () + "  " + tmp.merrPyetjen () + " ( " + tmp.gjatesia () + " )"  +  "\n" );
		} ///FUND for


         t1.setFont( new Font( "Arial", Font.PLAIN, 16 ) );
         t2.setFont( new Font( "Arial", Font.PLAIN, 16 ) );

         t1.setEditable( false );
         t2.setEditable( false );

		JLabel l2 = new JLabel( "Vertikalisht" );
		l2.setFont( new Font( "Consolas", Font.BOLD, 18 ) );


		JPanel p1 = new JPanel( new BorderLayout () );
		p1.add( l1,BorderLayout.NORTH );
		p1.add( t1, BorderLayout.CENTER );

       JPanel p2 = new JPanel( new BorderLayout () );
       p2.add( l2, BorderLayout.NORTH );
       p2.add( t2, BorderLayout.CENTER );

       p.add( p1 );
       p.add( p2 );
		return p;

	}


	private void lexoSkedarin (String emri )
	{
		Scanner in;
		Enigma tmp;

	    try
	    {
			File f = new File( emri );

			in = new Scanner( f );

			while( in.hasNextLine () )
			{
				int pozicioni = in.nextInt ();
				String orientimi = in.next ();
				String pyetja = in.next ();
				String pergjigja = in.next ();
                int gjatesia = in.nextInt ();
				enigmat.add( new Enigma( pozicioni, orientimi, pyetja, pergjigja, gjatesia ) );
			}

			in.close ();
		}
		catch( IOException e )
		{
             e.printStackTrace ();
			///System.exit( 0 );
		}
		catch( Exception e )
		{
			System.out.println( e.getMessage () );
		}


	}



	private JPanel paneliButonave ()
	{
		JPanel p = new JPanel ();

		dil = new JButton( " Dil " );
		kontrollo = new JButton( " Kontrollo " );
		ndihme = new JButton( " Ndihme " );
	    mesazh = new JLabel( "" );

	    p.add( dil );
	    p.add( kontrollo );
	    p.add( ndihme );
	    p.add( mesazh );

	    return p;

	}



	///*********************************************************
	///
	///
	///*********************************************************
	private class DegjuesTasti implements FocusListener
	{

			public void focusGained( FocusEvent e )
			{
				if( Loja.this.i > 12 || Loja.this.i > 12 )
				{
					Loja.this.i = 0;
					Loja.this.i = 0;
				}

                  for( int i = 0; i < 13; i++ )
                  {
                  	for( int j = 0; j < 13; j++ )

                  	if( e.getSource () == fushat[ i ][ j ] )
                    {
                  		Loja.this.i = i;
                  		Loja.this.j = j;
					}
			 	   }

                  	if( fushat[ pI + 1 ][ pJ ] == ( fushat[ Loja.this.i ][ Loja.this.j ] ) )

                  		fushat[ Loja.this.i ][ Loja.this.j ].setBackground( Color.CYAN );
                  	else
                  		fushat[ Loja.this.i ][ Loja.this.j ].setBackground( Color.CYAN );
			}

			public void focusLost( FocusEvent e )
			{
		        fushat[ i ][ j ].setBackground( Color.WHITE );
			    pI = i;
			    pJ = j;
			}

	}


	public static void main(	String[] args	)
	{
			try
				{
					UIManager.setLookAndFeel( "javax.swing.plaf.nimbus.NimbusLookAndFeel" );
				}
				catch( Exception e  )
				{} /// i patrajtuar
		Loja obj = new Loja ();
	}

}