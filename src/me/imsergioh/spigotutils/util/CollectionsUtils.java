package me.imsergioh.spigotutils.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectionsUtils {

    public static List<String> list(String... lines){
        return new ArrayList<>(Arrays.asList(lines));
    }

}
