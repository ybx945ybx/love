package kyf.loveapplication.data.constant;

/**
 * Created by a55 on 2017/11/2.
 */

public class ServerUrl {
    public static final int DEV  = 0;      // 开发
    public static final int PROD = 1;      // 生产

    public static int ENV_TYPE = PROD; // 环境

    static {
        if (ENV_TYPE == DEV) {
            SERVER_URL = "";

        } else {
            SERVER_URL = "";

        }
    }

    public static final String SERVER_URL;                  // 服务器
    public static final String PIC_PATH = "/.love/image/";                  // 图片缓存路径

}
