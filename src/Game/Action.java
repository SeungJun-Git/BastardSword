package Game;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Action extends JFrame {
    static final int SIZE = 300;
    ImageIcon img[]=new ImageIcon[8];
    ImageIcon changeImg[]=new ImageIcon[8];
    Image image, changeImage;
    private BufferedImage buffer;
    private LineBorder b = new LineBorder(Color.black, 1,true);
    int num;
    JLabel character;
    public Action() {
        setSize(1920,1080);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for(int i=0;i<8;i++) {
            img[i]=new ImageIcon("img/idle_"+(i+1)+".png");
            image = img[i].getImage();
            changeImage = image.getScaledInstance(400,200,Image.SCALE_SMOOTH);
            changeImg[i] = new ImageIcon(changeImage);
        }

        character = new JLabel(changeImg[0]);
        character.setSize(SIZE,SIZE);
        character.setBorder(b);
        add(character);

        animated();

        setVisible(true);
    }

    public BufferedImage crop(int x, int y, int width, int height) {
        BufferedImage dest = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g = dest.createGraphics();
        g.setComposite(AlphaComposite.Src);
        g.drawImage(buffer, 0, 0, width, height, x, y, x + width, y + height,null);
        g.dispose();
        return dest;
    }


    public static void main(String[] args) {
        new Action();
    }

    public void animated() {
        new Thread(()->{
            int i=0;
            boolean isIncrease=true;

            while(true) {
                character.setIcon(changeImg[i]);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if( i>=0 && i<8 ){
                    if(i==0) {
                        isIncrease=true;
                    } else if(i==7) {
                        isIncrease=false;
                    }
                    if(isIncrease) {
                        i++;
                    } else {
                        i--;
                    }
                } else {
                    isIncrease=false;
                    i=0;
                }
            }
        }).start();
    }
}
