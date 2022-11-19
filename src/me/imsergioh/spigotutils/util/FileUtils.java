package me.imsergioh.spigotutils.util;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    public static void setupFileAndParents(File file){
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e){e.printStackTrace();}
        }
    }

}
