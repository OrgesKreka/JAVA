package ushtrimdiplome;

/**
 *
 * @author Orges
 */
public class Vija 
{
    // Skaji majtas
    private int x1;
    private int y1;
    
    // Skaji djathtas
    private int x2;
    private int y2;
    
    
    public Vija( int _x1, int _y1, int _x2, int _y2 )
    {
        this.x1 = _x1;
        this.y1 = _y1;
        
        this.x2 = _x2;
        this.y2 = _y2;
    }
    
    
    public Vija()
    {
        this.x1 = this.y1 = this.x2 = this.y2 = 0;
    }
    
    // get, set piken majtas
    public void setX1( int x )
    {
        this.x1 = x;
    }
    
    public int getX1()
    {
        return this.x1;
    }
    
    public void setY1( int y )
    {
        this.y1 = y;
    }
    
    public int getY1()
    {
        return this.y2;
    }
    
    // get, set piken djathtas
    public void setX2( int x )
    {
        this.x2 = x;
    }
    
    public int getX2()
    {
        return this.x2;
    }
    
    public void setY2( int y )
    {
        this.y2 = y;
    }
    
    public int getY2()
    {
        return this.y2;
    }
    
    /**
     * Llogarit gjatesine e vijes duke gjetur largesen midis dy pikave ne plan
     * @return gjatesia ne double
     */
    public double gjatesia()
    {
        return Math.sqrt( Math.pow( x2 - x1, 2 )  + Math.pow(y2 - y1, 2 ) );
    }
     
    @Override
    public String toString()
    {
        String toReturn = "Pika majtas : x1 = " + x1 + " | y1 = " + y1 + System.getProperty("line.separator") + "Pika djathtas: x2 = " + x2 + " | y2 = " + y2 + System.getProperty("line.separator") + "Gjatesia = " + gjatesia() ;
        
        return toReturn;
    }
}//FUND Vija
