package SecondLessonHomework;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread extends Thread {

    public void run(){

        for (;;){
            try {

                String accessToken = "******************************************************************";
                DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
                DbxClientV2 client = new DbxClientV2(config, accessToken);

                DateFormat dateFormat = new SimpleDateFormat("YYYYMMdd_HHmmss");
                String currentDate = dateFormat.format(new Date());

                Rectangle screenshot = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                BufferedImage bImage = new Robot().createScreenCapture(screenshot);

                ByteArrayOutputStream image = new ByteArrayOutputStream();
                ImageIO.write(bImage, "png", new File("C://Users//polya//Desktop//screenshot.png"));

                InputStream in = new FileInputStream("C://Users//polya//Desktop//screenshot.png");
                FileMetadata metadata;
                sleep(5000);
                metadata = client.files().uploadBuilder("/" + currentDate + ".png")
                        .uploadAndFinish(in);
            } catch (Exception ex){
                ex.printStackTrace();
            }

        }

    }


}

