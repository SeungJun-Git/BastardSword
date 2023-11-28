package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class BackgroundPlayerService implements Runnable{
    private Character character;
    private BufferedImage image;

    private final int CHAR_WIDTH = 110;
    private final int CHAR_HEIGHT = 140;
    private int leftX;
    private int topY;

    public BackgroundPlayerService(Character character) {
        this.character=character;
        //350 : 캐릭터 왼쪽 끝
        leftX=character.getCds().getX()+350;
        //260: 캐릭터 바로 위
        topY=character.getCds().getY()+260;

        try {
            image= ImageIO.read(new File("images/background_server.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true) {
            Color leftColor = new Color(image.getRGB(leftX,topY+CHAR_HEIGHT));
            Color rightColor = new Color(image.getRGB(leftX + CHAR_WIDTH, topY+CHAR_HEIGHT));
            int bottomColor = image.getRGB(leftX,topY+CHAR_HEIGHT+3)+image.getRGB(leftX + CHAR_WIDTH, topY+CHAR_HEIGHT+3);

            if(bottomColor != -2) {
                character.getCds().setStatus(2,CharacterStat.NONE);
            }
        }
    }
}
