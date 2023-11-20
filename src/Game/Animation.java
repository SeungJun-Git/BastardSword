package Game;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class Animation{
    private ImageControl ic;
    private CharacterDetailSetting cds;
    private Thread thread;
    private boolean finished;
    public Animation(ImageControl ic, CharacterDetailSetting cds) {
        this.ic=ic;
        this.cds = cds;
        finished = true;
    }

    public synchronized void twoWayRepeat() {
        thread = new Thread( new Runnable() {
            @Override
            public void run() {
                try {
                    while(finished) {
                        System.out.println("서있음");
                        for (int i = 0; i < ic.getImageCount(); i++) {
                            cds.setIcon(ic.getIcon()[i]);
                            Thread.sleep(70);
                        }
                        for (int i = ic.getImageCount() - 1; i >= 0; i--) {
                            cds.setIcon(ic.getIcon()[i]);
                            Thread.sleep(70);
                        }
                    }
                } catch (InterruptedException e) {
                    System.out.println("정지 종료");
                }
            }
        });
        thread.start();
    }

    public synchronized void oneWayRepeat() {
        thread = new Thread(() -> {
            try {
                while(true) {
                    System.out.println("이동중...");
                    for (int i = 0; i < ic.getImageCount(); i++) {
                        cds.setIcon(ic.getIcon()[i]);
                        Thread.sleep(70);
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("이동 종료");
            }
        });
        thread.start();
    }

    public synchronized void oneTimeExecute() {
        thread = new Thread(() -> {
            try {
                System.out.println("이동중...");
                for (int i = 0; i < ic.getImageCount(); i++) {
                    cds.setIcon(ic.getIcon()[i]);
                    Thread.sleep(70);
                }
            } catch (InterruptedException e) {
                System.out.println("실행 끝");
            }
            thread.interrupt();
        });
        thread.start();
    }

    public void stopThread() {
        thread.interrupt();
    }
}
