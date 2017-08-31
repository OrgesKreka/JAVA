package ushtrimdiplome;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.JComponent;

/**
 *
 * @author Orges
 */
public class KomponenteVije extends JComponent
{
    // Vija zhvendoset me 15px majtas apo djathtas
    private final int ZHVENDOSJA = 15;
    

    
    private Vija vijaObj;
    private Line2D.Double vija;
    
    public KomponenteVije( int x1, int y1, int gjatesia )
    {   
        super();
                
        vijaObj = new Vija( x1, y1, x1 + gjatesia , y1 );
        
        vija = new Line2D.Double( vijaObj.getX1(), vijaObj.getY1(), vijaObj.getX2(), vijaObj.getY2()   );
        

    }
    
    @Override
    public void paintComponent( Graphics g )
    {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        g2.setColor(Color.blue);
        g2.draw( vija );
    }
    
    public void zhvendos( String drejtimi )
    {   
        int x1Ri = vijaObj.getX1();
        int y1Ri = vijaObj.getY1();
        
        int x2Ri = vijaObj.getX2();
        int y2Ri = vijaObj.getY2();
        
        switch( drejtimi )
        {
            case "M": 
                // Zbret nga cdo skaj ne menyre qe te mos ndryshoje gjatesia e vijes
                x1Ri -= ZHVENDOSJA;
                x2Ri -= ZHVENDOSJA;
                
                // Objekt i ri vija dhe komponente 2D 
                vijaObj = new Vija( x1Ri, y1Ri, x2Ri, y2Ri );
                vija = new Line2D.Double( vijaObj.getX1(), vijaObj.getY1(), vijaObj.getX2(), vijaObj.getY2()   );
                break;
                
            case "D":
                // Shton nga cdo skaj ne menyre qe te mos ndryshoje gjatesia e vijes
                x1Ri += ZHVENDOSJA;
                x2Ri += ZHVENDOSJA;
                
                // Objekt i ri vija dhe komponente 2D 
                vijaObj = new Vija( x1Ri, y1Ri, x2Ri, y2Ri );
                vija = new Line2D.Double( vijaObj.getX1(), vijaObj.getY1(), vijaObj.getX2(), vijaObj.getY2()   );
                break;
        }//FUND switch
        
        // Ne cdo rast, rivizatohet dritarja
        this.repaint();
    }//FUND zhvendos
    
    @Override
    public String toString()
    {
        return "x1 = " + vijaObj.getX1() + " | y1 = " +  vijaObj.getY1() + " | x2 = " +  vijaObj.getX2() + " | y2" +  vijaObj.getY2() ;
    }
}//FUND KomponenteVije
