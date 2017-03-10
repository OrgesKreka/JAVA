import java.io.IOException;
import java.io.RandomAccessFile;

public class MenaxhuesSkedari
{
	private RandomAccessFile skedar;

	/// konstantet e rekordeve
	public static final int INT_SIZE = 4;
	public static final int DOUBLE_SIZE = 8;
	public static final int RECORD_SIZE = 12;

	public MenaxhuesSkedari ()
	{
		skedar = null;
	} ///FUND konstruktori


	/**
		Hap skedarin qe do te procedohet.
		@param emri, emri i skedari qe do te hapet.
	*/
	public void hapSkedarin( String emri ) throws IOException
	{
		skedar = new RandomAccessFile( emri, "rw" );
	}


	/**
		Kthen madhesine e skedarit te hapur.
		@return skedar.length.
	*/
	public int madhesia () throws IOException
	{
		return ( int ) skedar.length() / RECORD_SIZE;
	}

	/**
		Mbyll skedarin.
	*/
	public void mbyllSkedarin () throws IOException
	{
		if( skedar != null )
			skedar.close ();

		skedar = null;
	}


	/**
		Lexon nje kulete nga skedari.
		@return kuleta e lexuar.
	*/
	public Kulete lexo () throws IOException
	{
		int id = skedar.readInt ();
		double balanca = skedar.readDouble ();

		return new Kulete( id, balanca );
	}

	/**
		Shkruan nje kulete ne skedar.
		@param njeKulete, kuleta qe do te shkruhet.
	*/
	public void shkruaj( Kulete njeKulete ) throws IOException
	{
		skedar.writeInt( njeKulete.merrID () );
		skedar.writeDouble(njeKulete.merrBalancen () );
	}

}