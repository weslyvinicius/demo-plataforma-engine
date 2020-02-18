package br.com.itau.co8.Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class ConvertUtils {

    public static File convert(MultipartFile file ) {

        File convFile = new File(file.getOriginalFilename());
        try {

            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return convFile;
    }

    public static void deleteFile(File file ) {
        file.deleteOnExit();
    }
}
