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
    public Animation(ImageControl ic, CharacterDetailSetting cds) {
        this.ic=ic;
        this.cds = cds;
    }

    public void twoWayRepeat() {
        thread = new Thread(() -> {
            try {
                while(true) {
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
                System.out.println("시간 초과");
            }
        });
        thread.start();
    }

    public void oneWayRepeat() {
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
                System.out.println("쓰레드 종료");
            }
        });
        thread.start();
    }

    public void oneTimeExecute() {
        thread = new Thread(() -> {
            try {
                System.out.println("이동중...");
                for (int i = 0; i < ic.getImageCount(); i++) {
                    cds.setIcon(ic.getIcon()[i]);
                    Thread.sleep(70);
                }
            } catch (InterruptedException e) {
                System.out.println("쓰레드 종료");
            }
            thread.interrupt();
        });
        thread.start();
    }

    public void stopThread() {
        thread.interrupt();
    }
}
