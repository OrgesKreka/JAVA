public class Kulete
{
	private static  int curID = 0;

	private double balanceKorente;

	private int idKulete;

	public Kulete ()
	{
		this.balanceKorente = 0;
		this.idKulete = curID;

		curID++;
	} ///FUND konstruktori1


	public Kulete( int id, double balance )
	{
		balanceKorente = balance;
		idKulete = id;
	} ///FUND kostruktori2


	/**
	  *	Shton qindeshet ne kulete.
	  * @param num, vlera qe do te shtohet.
	   ************************************/
	   public void shtoQindeshe( int num )
	   {
		   balanceKorente += num * 100.0;
	   }


	   /**
	   		Shton mijeshet ne kulete.
	   		@param num, sasia qe do te shtohet.
	   	*/
	   	public void shtoMijeshe( int num )
	   	{
			balanceKorente += num * 1000.0;
		}


		/**
			Shton dhjeteshe ne kulete.
			@param num, sasia qe do te shtohet.
		*/
		public void shtoDhjeteshe( int num )
		{
			balanceKorente += num * 10.0;
		}


		/**
			Kthen sasine e parave ne kulete.
			@return balanceKorente
		*/
		public double merrBalancen ()
		{
			return this.balanceKorente;
		}


		/**
			Kthen id e kuletes.
			@return idKulete.
		*/
		public int merrID ()
		{
			return this.idKulete;
		}

} ///FUND Kulete