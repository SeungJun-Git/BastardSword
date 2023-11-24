package Game;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class CharacterDetailSetting extends JLabel implements CharacterStatus{
    private final int SPEED = 4;
    private final int JUMPSPEED = 6;
    private final int MOVEMENT = 0;
    private final int ACTION = 1;
    private final int CHARACTERWAY=2;

    private int x, y;
    private int charX, charY;
    private int width, height;
    private String selectClass;

    private CharacterStat[] status;

    private MainFrame frame;
    private ImageControl image;
    private Animation ani;

    public CharacterDetailSetting(MainFrame frame, String selectClass, int width, int height) {
        this.frame = frame;
        this.selectClass = selectClass;
        this.width = width;
        this.height = height;

        status = new CharacterStat[3];

        initCharacterStatus();

        System.out.println(selectClass);
        x=80;
        y=500;
        setSize(width,height);
        setLocation(x,y);
        //시작을 서있는 자세로 세팅

        image = new ImageControl(selectClass,"Stay","idle_",width,height);
        ani = new Animation(image, this);
        ani.oneWayRepeat();

        stayMotion();
    }
    public void initCharacterStatus() {
        //시작시 가만히 서있는 상태
        status[MOVEMENT] = CharacterStat.STAY;
        status[ACTION] = CharacterStat.NONE;
        status[CHARACTERWAY] = CharacterStat.WAY_RIGHT;
    }
    @Override
    public void stayMotion() {
        //이미지 객체 생성후 사이즈 조절
        image = new ImageControl(selectClass,"Stay","idle_",width,height);
        ani.setIc(image);
        status[MOVEMENT] = CharacterStat.STAY;
        new Thread(()->{
            while(status[MOVEMENT] == CharacterStat.STAY) {
                try {
                    Thread.sleep(100);
                    System.out.println("아직 진행중...");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    @Override
    public void moveRight() {
        //이미지 객체 생성후 사이즈 조절
        image = new ImageControl(selectClass,"MoveRight","idle_",width,height);
        ani.changeImage(image);
        status[MOVEMENT] = CharacterStat.MOVE_RIGHT;
        new Thread(()->{
            while(status[MOVEMENT] == CharacterStat.MOVE_RIGHT) {
                if(x>250&&x<490) {
                    y=y-(SPEED-1);
                }
                x=x+SPEED;
                setLocation(x,y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    @Override
    public void moveLeft() {
        //이미지 객체 생성후 사이즈 조절
        image = new ImageControl(selectClass,"MoveLeft","idle_",width,height);
        ani.changeImage(image);
        status[MOVEMENT] = CharacterStat.MOVE_LEFT;
        new Thread(()->{
            while(status[MOVEMENT] == CharacterStat.MOVE_LEFT) {
                if(x>250&&x<490) {
                    y=y+(SPEED-1);
                }
                x=x-SPEED;
                setLocation(x,y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    @Override
    public void attack1() {
        image = new ImageControl(selectClass,"Attack_1","1_atk_",width,height);
        ani.changeImage(image);
        status[ACTION] = CharacterStat.ATTACK;

        new Thread(()->{
            try {
                Thread.sleep(image.getImageCount()*70);
                status[ACTION] = CharacterStat.NONE;
                stayMotion();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    @Override
    public void attack2() {
        image = new ImageControl(selectClass,"Attack_2","2_atk_",width,height);
        ani.changeImage(image);
        status[ACTION] = CharacterStat.ATTACK;

        new Thread(()->{
            try {
                Thread.sleep(image.getImageCount()*70);
                status[ACTION] = CharacterStat.NONE;
                stayMotion();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }

    @Override
    public void attack3() {
        image = new ImageControl(selectClass,"Attack_3","3_atk_",width,height);
        ani.changeImage(image);
        status[ACTION] = CharacterStat.ATTACK;

        new Thread(()->{
            try {
                Thread.sleep(image.getImageCount()*70);
                status[ACTION] = CharacterStat.NONE;
                stayMotion();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    @Override
    public void jumpCharacter() {
        image = new ImageControl(selectClass,"Jump_up","jump_up_",width,height);
        ani.changeImage(image);
        status[ACTION] = CharacterStat.UP;

        new Thread(()->{
            for(int i=0; i<130/JUMPSPEED; i++) {
                y=y-JUMPSPEED;
                setLocation(x,y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            status[ACTION] = CharacterStat.NONE;
            down();
        }).start();
    }

    public void down() {
        image = new ImageControl(selectClass,"Jump_down","jump_down_",width,height);
        ani.changeImage(image);
        status[ACTION] = CharacterStat.DOWN;

        new Thread(()->{
            for(int i=0; i<130/JUMPSPEED; i++) {
                y=y+JUMPSPEED;
                setLocation(x,y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            status[ACTION] = CharacterStat.NONE;
            status[MOVEMENT] = CharacterStat.STAY;
        }).start();
    }

    public void roll() {
        image = new ImageControl(selectClass,"Roll","roll_",width,height);
        ani.changeImage(image);
        status[ACTION] = CharacterStat.ROLL;

        new Thread(()->{
            for(int i=0; i<130/JUMPSPEED; i++) {
                x=x+SPEED*2;
                setLocation(x,y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            stayMotion();
        }).start();
    }
}
