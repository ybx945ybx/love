package kyf.loveapplication.data.net;

/**
 * Created by a55 on 2017/11/2.
 */

public class ApiModel<T> {
    public String msg;

    public StatBean stat;

    public int code;

    public T data;

    public static class StatBean{
        public String cid;
        public long   systime;
    }

    public T getData() {
        return data;
    }
}
