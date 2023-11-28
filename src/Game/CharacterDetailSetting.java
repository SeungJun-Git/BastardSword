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
    private final int ISDOWN = 3;

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

        status = new CharacterStat[4];

        initCharacterStatus();

        System.out.println(selectClass);
        x=80;
        y=500;
        setSize(width,height);
        setLocation(x,y);
        //시작을 서있는 자세로 세팅

        image = new ImageControl(selectClass,"Stay_R","idle_",width,height);
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
        if(status[CHARACTERWAY]==CharacterStat.WAY_RIGHT) {
            image = new ImageControl(selectClass, "Stay_R", "idle_", width, height);
        } else {
            image = new ImageControl(selectClass, "Stay_L", "idle_", width, height);
        }
        ani.changeImage(image);
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
        image = new ImageControl(selectClass,"MoveRight","run_",width,height);
        ani.changeImage(image);
        status[MOVEMENT] = CharacterStat.MOVE_RIGHT;
        status[CHARACTERWAY] = CharacterStat.WAY_RIGHT;

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
        image = new ImageControl(selectClass,"MoveLeft","run_",width,height);
        ani.changeImage(image);
        status[MOVEMENT] = CharacterStat.MOVE_LEFT;
        status[CHARACTERWAY] = CharacterStat.WAY_LEFT;
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
        if(status[CHARACTERWAY]==CharacterStat.WAY_RIGHT) {
            image = new ImageControl(selectClass, "Attack_1_R", "1_atk_", width, height);
        } else {
            image = new ImageControl(selectClass, "Attack_1_L", "1_atk_", width, height);
        }
        ani.changeImage(image);
        status[ACTION] = CharacterStat.ATTACK;
        status[MOVEMENT] = CharacterStat.NONE;

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
        if(status[CHARACTERWAY]==CharacterStat.WAY_RIGHT) {
            image = new ImageControl(selectClass, "Attack_2_R", "2_atk_", width, height);
        } else {
            image = new ImageControl(selectClass, "Attack_2_L", "2_atk_", width, height);
        }
        ani.changeImage(image);
        status[ACTION] = CharacterStat.ATTACK;
        status[MOVEMENT] = CharacterStat.NONE;

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
        if(status[CHARACTERWAY]==CharacterStat.WAY_RIGHT) {
            image = new ImageControl(selectClass, "Attack_3_R", "3_atk_", width, height);
        } else {
            image = new ImageControl(selectClass, "Attack_3_L", "3_atk_", width, height);
        }
        ani.changeImage(image);
        status[ACTION] = CharacterStat.ATTACK;
        status[MOVEMENT] = CharacterStat.NONE;

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
        if(status[CHARACTERWAY]==CharacterStat.WAY_RIGHT) {
            image = new ImageControl(selectClass, "Jump_up_R", "j_up_", width, height);
        } else {
            image = new ImageControl(selectClass, "Jump_up_L", "j_up_", width, height);
        }
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
        if(status[CHARACTERWAY]==CharacterStat.WAY_RIGHT) {
            image = new ImageControl(selectClass, "Jump_down_R", "j_down_", width, height);
        } else {
            image = new ImageControl(selectClass, "Jump_down_L", "j_down_", width, height);
        }
        ani.changeImage(image);
        status[ISDOWN] = CharacterStat.DOWNTRUE;

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
            stayMotion();
        }).start();
    }

    public void roll() {
        if(status[CHARACTERWAY]==CharacterStat.WAY_RIGHT) {
            image = new ImageControl(selectClass, "Roll_R", "roll_", width, height);
        } else {
            image = new ImageControl(selectClass, "Roll_L", "roll_", width, height);
        }
        ani.changeImage(image);
        status[ACTION] = CharacterStat.ROLL;

        new Thread(()->{
            for(int i=0; i<130/JUMPSPEED; i++) {
                if(x>250&&x<490) {
                    y=y-(SPEED);
                }
                if(status[CHARACTERWAY]==CharacterStat.WAY_RIGHT) {
                    x = x + SPEED * 2;
                } else {
                    x = x - SPEED * 2;
                }
                setLocation(x,y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            status[ACTION] = CharacterStat.NONE;
            stayMotion();
        }).start();
    }

    public void takeHit() {
        if(status[CHARACTERWAY]==CharacterStat.WAY_RIGHT) {
            image = new ImageControl(selectClass, "Take_hit_R", "take_hit_", width, height);
        } else {
            image = new ImageControl(selectClass, "Take_hit_L", "take_hit_", width, height);
        }
        ani.changeImage(image);
        status[MOVEMENT] = CharacterStat.TAKE_HIT;
        status[ACTION] = CharacterStat.TAKE_HIT;

        new Thread(()->{
            try {
                Thread.sleep(image.getImageCount()*70);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            status[ACTION] = CharacterStat.NONE;
            stayMotion();
        }).start();
    }

    public void setStatus(int num, CharacterStat stat) {
        status[num] = stat;
    }
}
