import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class PurseFrameViewer extends JFrame
{
	private JMenuBar menuBar;

	private JMenu file;
	private JMenu add;


	private JMenuItem open;
	private JMenuItem exit;
	private JMenuItem save;
	private JMenuItem dhjeteshe;
	private JMenuItem qindeshe;
	private JMenuItem mijeshe;
	private JMenuItem kuleteRe;

	private Kulete kuleta;
	private JLabel id_kulete;
	private JLabel balance_kulete;

	private JPanel panel;

	public PurseFrameViewer ()
	{
		super( "Kuleta" );

		kuleta = new Kulete ();
		this.setSize( 400, 400 );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setResizable( false );

		createMenuItem ();
		krijoMenu ();
		krijoMenuBar ();
		krijoEtiketa ();
		krijoPanelin ();

        setLookAndFeel ();
		this.setVisible( true );
	} ///FUND konstruktori



	/**
		Krijon nenmenute e menuve.
	*/
	private void createMenuItem ()
	{
		open = new JMenuItem( "Open" );
		open.addActionListener( new DegjuesMenuje () );

		exit = new JMenuItem( "Exit" );
		exit.addActionListener( new DegjuesMenuje () );

		save = new JMenuItem( "Save" );
		save.addActionListener( new DegjuesMenuje () );

		dhjeteshe = new JMenuItem( "Dhjeteshe" );
		dhjeteshe.addActionListener( new DegjuesMenuje () );

		qindeshe = new JMenuItem( "Qindeshe" );
		qindeshe.addActionListener( new DegjuesMenuje () );

		mijeshe = new JMenuItem( "Mijeshe" );
		mijeshe.addActionListener( new DegjuesMenuje () );

		kuleteRe = new JMenuItem( "New" );
		kuleteRe.addActionListener( new DegjuesMenuje () );
	} ///FUND createMenuItem


	/**
		Krijon menute kryesore.
	*/
	public void krijoMenu ()
	{
		file = new JMenu( "File" );
		file.add( kuleteRe );
		file.add( open );
		file.add( save );
		file.add( exit );

		add = new JMenu( "Add" );
		add.add( dhjeteshe );
		add.add( qindeshe );
		add.add( mijeshe );
	}///FUND krijoMenu


	/**
		Krijon panelin e menuve.
	*/
	private void krijoMenuBar ()
	{
		menuBar = new JMenuBar ();
		menuBar.add( file );
		menuBar.add( add );
		super.add(menuBar, BorderLayout.NORTH );
	}


	/**
		Krijon etiketat qe do te shfaqin te dhenat ne dritare.
	*/
	private void krijoEtiketa ()
	{
		id_kulete = new JLabel ();
		id_kulete.setText( "ID: " + kuleta.merrID () );
		id_kulete.setFont( new Font( "Consolas", Font.BOLD, 26 ) );


		balance_kulete = new JLabel ();
		balance_kulete.setText( "Balanca: " + kuleta.merrBalancen () );
		balance_kulete.setFont( new Font( "Consolas", Font.BOLD, 26 ) );
	}


	/**
		Reseton etiketat.
	*/
	public void reset ()
	{
		id_kulete.setText( "ID: " + kuleta.merrID () );
		id_kulete.setFont( new Font( "Consolas", Font.BOLD, 26 ) );

		balance_kulete.setText( "Balanca: " + kuleta.merrBalancen () );
		balance_kulete.setFont( new Font( "Consolas", Font.BOLD, 26 ) );
	}



	/**
		Krijon panelin  e permbajtjes.
	*/
	public void krijoPanelin ()
	{
		panel = new JPanel ();

		panel.setLayout( new GridLayout( 2, 1 ) );
		panel.add( id_kulete );
		panel.add( balance_kulete );
		super.add( panel, BorderLayout.CENTER );
	}

/**
	vendos GUI ne Nimbus look and feel.
*/
	private void setLookAndFeel ()
	{
		try
		{
			UIManager.setLookAndFeel( "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");;
		}
		catch( Exception e  )
		{} /// i patrajtuar
	}

	///****************************************************
	/// Klasa e brendshme, degjuese e ngjarjve te menuve.
	///****************************************************
	class DegjuesMenuje implements ActionListener
	{
		/**
			Kap ngjarjen e aksionit.
			@param e, ngjarja qe ka shkaktuar therritjen e metodes.
		*/
		public void actionPerformed( ActionEvent e )
		{
			 /// Mbyll programin
			if( e.getSource ().equals( exit ) )
			{
				System.exit( 0 );
			}
			else if( e.getSource ().equals( save ) )
			{
				JFileChooser f = new JFileChooser ();

				if( f.showSaveDialog( null ) == JFileChooser.APPROVE_OPTION )
				{
					String emriSkedarit = f.getCurrentDirectory ().getPath ();

					emriSkedarit += "/" + f.getSelectedFile ().getName ();

					MenaxhuesSkedari menaxher = new MenaxhuesSkedari ();

					try
				      {
						 menaxher.hapSkedarin( emriSkedarit );
						 menaxher.shkruaj( kuleta );
						 menaxher.mbyllSkedarin ();
					   }
					   catch( IOException exc )
					   {
						   JOptionPane.showMessageDialog( null, "Problem me skedarin ! ", exc.getMessage (), JOptionPane.ERROR_MESSAGE, null );
					   } ///FUND catch
				} ///FUND if - 1
			}///FUND else if - 1

		    else if( e.getSource ().equals( open ) )
		    {
				JFileChooser f = new JFileChooser ();

				if( f.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION )
				{
					String emriSkedarit = f.getCurrentDirectory ().getPath ();

					emriSkedarit += "/" + f.getSelectedFile ().getName ();

				 MenaxhuesSkedari menaxher = new MenaxhuesSkedari ();

				 try
				 {
					 menaxher.hapSkedarin( emriSkedarit );
					 kuleta = menaxher.lexo ();

					 reset ();

					 menaxher.mbyllSkedarin ();
				 }
				 catch( IOException exc )
				 {
					JOptionPane.showMessageDialog( null, "Problem me skedarin !", exc.getMessage (), JOptionPane.ERROR_MESSAGE, null );
				 } ///FUND catch
			   } ///FUND if - 2
			} /// FUND if else - 2
			else if( e.getSource ().equals( dhjeteshe ) )
			{
				String num = JOptionPane.showInputDialog( "Sa dhjeteshe do shtoni? " );
				kuleta.shtoDhjeteshe( Integer.parseInt( num ) );

				reset ();
			} ///FUND else if - 3
			else if( e.getSource ().equals( qindeshe ) )
			{
				String num = JOptionPane.showInputDialog( "Sa qindeshe do te shtoni ? " );
				kuleta.shtoQindeshe( Integer.parseInt( num ) );

				reset  ();
			}
			else if( e.getSource ().equals( mijeshe ) )
			{
				String num = JOptionPane.showInputDialog( "Sa mijeshe do te shtoni ? " );
				kuleta.shtoMijeshe( Integer.parseInt( num ) );

				reset ();
			}
			else if( e.getSource ().equals( kuleteRe ) )
			{
				kuleta = new Kulete ();

				reset ();
			}

   	}

  }
}
