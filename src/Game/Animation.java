package Game;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Animation{
    private ImageControl ic;
    private CharacterDetailSetting cds;
    private Thread thread;
    private boolean finished;
    private int cnt;
    public Animation(ImageControl ic, CharacterDetailSetting cds) {
        this.ic=ic;
        this.cds = cds;
        finished = true;
    }

    public synchronized void oneWayRepeat() {
        thread = new Thread(() -> {
            try {
                while(true) {
                    System.out.println("이동중...");
                    for (cnt = 0; cnt < ic.getImageCount(); cnt++) {
                        cds.setIcon(ic.getIcon()[cnt]);
                        Thread.sleep(70);
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("이동 종료");
            }
        });
        thread.start();
    }

    public void changeImage(ImageControl ic) {
        this.ic = ic;
        cnt=0;
    }

    public void stopThread() {
        thread.interrupt();
    }
}
