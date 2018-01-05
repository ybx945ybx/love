package kyf.loveapplication.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a55 on 2017/11/2.
 */

public class LoveUtils {


    public static int getSize(ArrayList arrayList) {
        return arrayList == null ? 0 : arrayList.size();
    }

    public static int getSize(List arrayList) {
        return arrayList == null ? 0 : arrayList.size();
    }

    public static int getSize(String[] list) {
        return list == null ? 0 : list.length;
    }

    public static int getSize(String string) {
        return string == null ? 0 : string.length();
    }

}
