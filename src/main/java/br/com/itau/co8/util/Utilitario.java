package br.com.itau.co8.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class Utilitario {

    public static File converteMultipartFileParaFile(MultipartFile multipartFile ) {

        File file = new File(multipartFile.getOriginalFilename());
        try {

            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return file;
    }

}
