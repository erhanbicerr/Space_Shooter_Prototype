
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.xml.catalog.CatalogResolver;

/*
 * How are you today sir ?
 * Do you need anything?
 * 
 */

/**
 *
 * @author erhan
 */


    class Ates{
        private int x;
        private int y;

    public Ates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
   
    }

    public class Oyun extends JPanel implements KeyListener, ActionListener{
        
    Timer timer = new Timer(1,this);
    private int gecenSure = 0;
    private int harcananAtes = 0;  
    private BufferedImage image;
    private BufferedImage image2;
    
    ArrayList<Ates> atesler = new ArrayList<Ates>();
    
    private int atesdirY = 1;
    private int topX = 0;
    private int topdirX = 3;
    private int uzaygemisiX=0;
    private int uzaydirX=20;
    public int hp=100;
    
    public int kontrolEt(int x){
        
        for(Ates ates : atesler){
            if(new Rectangle(ates.getX(),ates.getY(),10,20).intersects(new Rectangle(topX,0,40,40))){
                return x-=20;
            }
        }
return x;
    }
        
        
    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        
        
        g.setColor(Color.RED);
        
        
        g.drawImage(image2, topX, 0, 40, 40, this);
        
        g.drawImage(image, uzaygemisiX, 490, image.getWidth() / 16,image.getHeight() / 16,this);
        
        for(Ates ates: atesler){
            if(ates.getY()<=0){
                atesler.remove(ates);
            }
        }
        
        g.setColor(Color.BLACK);
        
        for(Ates ates: atesler){
            
            g.fillRect(ates.getX(), ates.getY(), 10, 20);
            
        }
        
        hp=kontrolEt(hp);
        if(hp==0){
            timer.stop();
            String message = "Kazandınız\n"+
                    "Harcanan ates: "+harcananAtes+"\n"
                    +"Gecen sure: "+gecenSure/300.0+" sn";
            
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
        
    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }
     
    public Oyun() {
        
        try {
            image = ImageIO.read(new FileImageInputStream(new File("spaceship.png")));
                 
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            image2 = ImageIO.read(new FileImageInputStream(new File("PixelArt.png")));
                 
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        setBackground(Color.DARK_GRAY);
        
        timer.start();

    }
        

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if(c == KeyEvent.VK_LEFT){
            if(uzaygemisiX<=0){
                uzaygemisiX=0;
            }
            else{
                uzaygemisiX-=uzaydirX;
                        
            }
        }
        else if(c == KeyEvent.VK_RIGHT){
            if(uzaygemisiX>=720){
                uzaygemisiX=720;
            }
            else{
                uzaygemisiX+=uzaydirX;
                        
            }
        }
        else if (c == KeyEvent.VK_SPACE){
            atesler.add(new Ates(uzaygemisiX+25,470));
            //atesler.add(new Ates(uzaygemisiX+50,470)); çift mermi
            harcananAtes++;
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gecenSure+=1;
        for(Ates ates: atesler){
            ates.setY(ates.getY()-atesdirY);
        }
        
        topX+=topdirX;
        
        if(topX>=750){
            topdirX=-topdirX;
        }
        
        if(topX<=0){
            topdirX=-topdirX;
        }
      
        repaint();
    }
    
    
    
    
}
