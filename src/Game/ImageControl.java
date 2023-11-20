package Game;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class ImageControl {
    private ImageIcon icon[];
    //임시로 사용할 img
    private Image tmpImage;
    //이미지 개수 저장
    private int imageCount;
    //배경 이미지
    public ImageControl(int status, int x, int y) {
        //파일 안에 이미지가 몇개 있는지 세고 그 크기만큼 배열 생성
        imageCount = new FileCount("./Images/BackGround/").countImageFile();
        System.out.println(imageCount);
        icon = new ImageIcon[imageCount];

        //icon 배열에 이미지 저장
        for(int i=0;i<imageCount;i++) {
            icon[i] = new ImageIcon("./Images/BackGround/background_"+(i+1)+".png");
            System.out.println("./Images/BackGround/background_"+(i+1)+".png");
            tmpImage = icon[i].getImage();
            tmpImage = tmpImage.getScaledInstance(x, y, Image.SCALE_SMOOTH);
            icon[i] = new ImageIcon(tmpImage);
        }
    }
    //캐릭터 이미지
    public ImageControl(String character, String status, String path, int x, int y) {
        //파일 안에 이미지가 몇개 있는지 세고 그 크기만큼 배열 생성
        imageCount = new FileCount("./Images/Character/"+character+"/"+status).countImageFile();
        icon = new ImageIcon[imageCount];

        //icon 배열에 이미지 저장 후 크기 조절
        for(int i=0;i<imageCount;i++) {
            icon[i] = new ImageIcon("./Images/Character/"+character+"/"+status+"/"+path+(i+1)+".png");
            tmpImage = icon[i].getImage();
            tmpImage = tmpImage.getScaledInstance(x, y, Image.SCALE_SMOOTH);
            icon[i] = new ImageIcon(tmpImage);
        }
    }

    //함수 호출 시 크기 조절
    public ImageIcon[] changeImageSize(int x, int y) {
        ImageIcon resizedImage[] = new ImageIcon[imageCount];
        for(int i=0; i<icon.length; i++) {
            tmpImage = icon[i].getImage();
            tmpImage = tmpImage.getScaledInstance(x, y, Image.SCALE_SMOOTH);
            resizedImage[i] = new ImageIcon(tmpImage);
        }
        return resizedImage;
    }

}
