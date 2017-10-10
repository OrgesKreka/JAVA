package ushtrimdiplome2;

/**
 *
 * @author Orges
 */
public class Klient 
{
    private static int numerKlienti = 0;
    private String emri;
    private String numri;
    private boolean eshteAnetar;
    
    public Klient()
    {
        numerKlienti++;
        
        emri = "";
        numri = "0123456789";
        eshteAnetar = false;
    }

    public Klient( String emri, String numri, boolean anetar )
    {
        numerKlienti++;
        this.emri = emri;
        this.numri = numri;
        eshteAnetar = anetar;
        
    }
    
    public int merrNumerKlienti()
    {
        return numerKlienti;
    }
    
    
    public void vendosEmrin( String emri )
    {
        this.emri = emri;
    }
    
    public String getEmri()
    {
        return this.emri;
    }
    
    public void vendosNumrin( String numri )
    {
        this.numri = numri;
    }
    
    public String merrNumrin()
    {
        return this.numri;
    }
    
    public void vendosAnetaresine( boolean anetar )
    {
        this.eshteAnetar = anetar;
    }
    
    public boolean getAnetaresine()
    {
        return this.eshteAnetar;
    }
    
    @Override
    public String toString()
    {
        return this.emri + " ka id: " + numerKlienti + " ka numer tel: " + numri + (  eshteAnetar  ? " eshte anetar" : " nuk eshte anetar" );
    }
}
