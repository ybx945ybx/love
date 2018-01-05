package kyf.loveapplication;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDexApplication;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import java.util.Iterator;
import java.util.List;

import kyf.loveapplication.ui.view.LoveTextView;

/**
 * 全局Application
 * Created by a55 on 2017/10/31.
 */

public class LoveApplication extends MultiDexApplication {

    private static Context mContext;
    private static int activityCount = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        // 初始化Stetho
        Stetho.initializeWithDefaults(this);
        // 初始化Logger
        Logger.init("love").logLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE);
        // 初始化环信
        initEchat();
        // 初始化fresco
        Fresco.initialize(this);
        // 初始化字体
        LoveTextView.init(this);
    }

    private void initEchat() {
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回

        if (processAppName == null ||!processAppName.equalsIgnoreCase(mContext.getPackageName())) {
            Logger.d("enter the service process!");

            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }

        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
//        options.setAutoTransferMessageAttachments(true);
        // 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
//        options.setAutoDownloadThumbnail(true);
        //初始化
        EMClient.getInstance().init(mContext, options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);

        Logger.d("初始化环信");
    }

    private String getAppName(int pID) {
        String          processName = null;
        ActivityManager am          = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List            l           = am.getRunningAppProcesses();
        Iterator        i           = l.iterator();
        PackageManager  pm          = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }

    public static int getAppCount() {
        return activityCount;
    }

    public static Context getContext() {
        return mContext;
    }
}
