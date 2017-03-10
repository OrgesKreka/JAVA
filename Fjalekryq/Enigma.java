public class Enigma
{
	private int pozicioni;
	private String pyetja;
	private String pergjigja;
    private String orientimi;
    private int gjatesia;

	public Enigma( int p, String ori,  String pyet, String per, int gjat )
	{
		pozicioni = p;
		pyetja = pyet;
		pergjigja = per;
		orientimi = ori;
		gjatesia = gjat;
	}


	public int merrPozicionin ()
	{
		return this.pozicioni;
	}

	public String merrPergjigjen ()
	{
		return this.pergjigja;
	}

	public String merrPyetjen ()
	{
		return this.pyetja;
	}


	public void vendosPozicionin( int p )
	{
		pozicioni = p;
	}

	public void vendosPyetjen( String p )
	{
		pyetja = p;
	}

	public void vendosPergjigjen( String p )
	{
		pergjigja = p;
	}

    public int gjatesia ()
    {
		return gjatesia;
	}

	public void vendosOrientimin( String o )
	{
		this.orientimi = o;
	}

	public String merrOrientimin ()
	{
		return this.orientimi;
	}

}