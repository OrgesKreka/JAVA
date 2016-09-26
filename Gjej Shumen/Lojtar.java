
public class Lojtar
{
     private String emri;
     private int piket;
     private int niveli;
     
     
     public Lojtar ()
     {
    	 emri = "";
    	 piket = 0;
    	 niveli = 0;
     } ///FUND konstruktori
     
     
     public void shtoPiket ()
     {
    	 piket++;
     }
     
     
     public void shtoNivelin ()
     {
    	 niveli++;
     }
     
     
     public void vendosEmrin( String emri )
     {
    	 this.emri = emri;
     }
     
     
     public String merrEmrin ()
     {
    	 return emri;
     }
     
     
     public int merrPiket ()
     {
    	 return piket;
     }
     
     
     public void vendosNivelin( int n )
     {
        niveli  = n;	 
     }
     
     public int merrNivelin ()
     {
    	 return niveli;
     }
} ///FUND klasa









