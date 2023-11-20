package Game;

import java.io.File;

public class FileCount {
    private String path;
    //경로를 받아서 문자열로 저장
    public FileCount(String path) {
        this.path = path;
    }

    public int countImageFile() {
        //디렉토리에 있는 파일을 files에 저장
        File dir = new File(path);
        File[] files = dir.listFiles();

        int fileCnt = 0;

        for (int i = 0; i < files.length; i++) {
            File file = files[i];

            //파일일 경우 +1
            if (file.isFile()) {
                fileCnt++;// 파일 개수
            }
        }
        return fileCnt;
    }
}
