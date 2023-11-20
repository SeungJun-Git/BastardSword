package Game;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class CharacterDetailSetting extends JLabel implements CharacterStatus{
    private static final int SPEED = 4;
    private static final int JUMPSPEED = 6;

    private boolean left, right, stayStatus, attack, up, down, roll;
    private int x, y;
    private int width, height;
    private String selectClass;

    private CharacterWay cWay;

    private MainFrame frame;
    private ImageControl image;
    private Animation ani;
    public CharacterDetailSetting(MainFrame frame, String selectClass, int width, int height) {
        this.frame = frame;
        this.selectClass = selectClass;
        this.width = width;
        this.height = height;

        initCharacterStatus();

        System.out.println(selectClass);
        x=80;
        y=500;
        setSize(width,height);
        setLocation(x,y);
        //시작을 서있는 자세로 세팅
        //stayMotion();
    }
    public void initCharacterStatus() {
        //시작시 가만히 서있는 상태
        stayStatus = true;
        left = false;
        right = false;
        attack = false;
        cWay = cWay.RIGHT;
    }
    @Override
    public void stayMotion() {
        //이미지 객체 생성후 사이즈 조절
        image = new ImageControl(selectClass,"Stay","idle_",width,height);
        ani = new Animation(image, this);
        stayStatus = true;
        new Thread(()->{
            ani.twoWayRepeat();
            while(stayStatus) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            stayStatus = false;
            ani.stopThread();
        }).start();
    }

    @Override
    public void moveRight() {
        //이미지 객체 생성후 사이즈 조절
        image = new ImageControl(selectClass,"MoveRight","idle_",width,height);
        ani = new Animation(image, this);
        right = true;
        stayStatus = false;
        new Thread(()->{
            ani.oneWayRepeat();
            while(right) {
                x=x+SPEED;
                setLocation(x,y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            right = false;
            ani.stopThread();
        }).start();
    }

    @Override
    public void moveLeft() {
        //이미지 객체 생성후 사이즈 조절
        image = new ImageControl(selectClass,"MoveLeft","idle_",width,height);
        ani = new Animation(image, this);
        left = true;
        stayStatus = false;
        new Thread(()->{
            ani.oneWayRepeat();
            while(left) {
                x=x-SPEED;
                setLocation(x,y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            left = false;
            ani.stopThread();
        }).start();
    }

    @Override
    public void attack1() {
        image = new ImageControl(selectClass,"Attack_1","1_atk_",width,height);
        ani = new Animation(image, this);
        attack = true;
        right = false;
        left = false;

        ani.oneTimeExecute();
        new Thread(()->{
            try {
                Thread.sleep(image.getImageCount()*70);
                attack = false;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    @Override
    public void attack2() {
        image = new ImageControl(selectClass,"Attack_2","2_atk_",width,height);
        ani = new Animation(image, this);
        attack = true;

        ani.oneTimeExecute();
        new Thread(()->{
            try {
                Thread.sleep(image.getImageCount()*70);
                attack = false;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    @Override
    public void attack3() {
        image = new ImageControl(selectClass,"Attack_3","3_atk_",width,height);
        ani = new Animation(image, this);
        attack = true;

        ani.oneTimeExecute();
        new Thread(()->{
            try {
                Thread.sleep(image.getImageCount()*70);
                attack = false;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    @Override
    public void jumpCharacter() {
        image = new ImageControl(selectClass,"Jump_up","jump_up_",width,height);
        ani = new Animation(image, this);
        up = true;

        new Thread(()->{
            ani.oneTimeExecute();
            for(int i=0; i<130/JUMPSPEED; i++) {
                y=y-JUMPSPEED;
                setLocation(x,y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            up = false;
            down();
        }).start();
    }

    public void down() {
        image = new ImageControl(selectClass,"Jump_down","jump_down_",width,height);
        ani = new Animation(image, this);
        down = true;

        new Thread(()->{
            ani.oneTimeExecute();
            for(int i=0; i<130/JUMPSPEED; i++) {
                y=y+JUMPSPEED;
                setLocation(x,y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            down = false;
        }).start();
    }

    public void roll() {
        image = new ImageControl(selectClass,"Roll","roll_",width,height);
        ani = new Animation(image, this);
        roll = true;
        new Thread(()->{
            ani.oneTimeExecute();
            for(int i=0; i<130/JUMPSPEED; i++) {
                x=x+SPEED*2;
                setLocation(x,y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            roll = false;
        }).start();
    }
}
