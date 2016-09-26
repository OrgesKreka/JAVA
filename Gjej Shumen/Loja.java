///****************************************************************************************************
/// Loja.java					Punoi : Orges Kreka
///										17 / 5 / 2016
///           Gjeneron numra te rastesishem dhe kontrollon shumen e tyre me pergjigjen e perdoruesit.
///           perdoruesi mund te jape pergjigjen duke shtypur Enter
///           pa nderfaqe grafike. . . 
///******************************************************************************************************


import java.util.*;

public class Loja 
{
    private Lojtar lojtari;
    
    private Random r;
    
    private int VESHTIRESIA;
    
    public Loja ()
    {
    	lojtari = new Lojtar ();
    	r = new Random ();
    	
    	VESHTIRESIA = 0;
    }///FUND konstruktori;
    
    
    
    public void luaj( String emri )
    {
    	lojtari.vendosEmrin( emri );
    	
    	boolean mbaroi = false;
    	
    	int n1,n2, shuma = 0;
    	
    	Scanner in = new Scanner( System.in );
    	
    	System.out.println( "----------------" +lojtari.merrEmrin () + "----------------\n\n"  );
    	
    	while( !mbaroi )
    	{
    		
    		if( lojtari.merrPiket () % 5 == 0 )
    		{
    			lojtari.shtoNivelin ();
    			
    			VESHTIRESIA += 10;
    		}
    		
    		
    		n1 = 1 + r.nextInt( VESHTIRESIA );
    		n2 = 1 + r.nextInt( VESHTIRESIA );
    		
    		System.out.print( n1 + " + " + n2 + " = " );
    		int p = in.nextInt ();
    		
    		
    		if( p == n1 + n2 )
    		{
    				System.out.println( "\nE sakte ! \n\n");
    				lojtari.shtoPiket ();
    		}
    		else
    				System.out.println( "\n E pasakte. . . ");
    		
    		System.out.println( "\nDeshironi te vazhdoni ( p / j ) ? ");
    		
    		String pergjigja = in.next ();
    		
    		if( pergjigja.equalsIgnoreCase( "J" ) )
    				mbaroi = true;
    		
    	} ///FUND while
    	
    } ///FUND luaj
    
    
    public static void main(	String[] args	)
    {
    	Loja obj = new Loja ();
    	
    	obj.luaj( "lojtari 1"  );;
    }
    
}










