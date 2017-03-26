package la.iok.finnecho.cq.util;

import java.util.List;

/**
 * 静态配置
 */
public class Config {

    public static String WSHost;// websocket的host
    public static String WSPort;// websocket的port
    public static String PHP_WS_URL;//php接口的url
    public static String COOLQ_IMAGE_PATH;//CoolQ 图片文件夹
    public static List<String> MANAGER_QQ;//管理员QQ，使用逗号分隔

    //查看QQ是否是机器人管理员
    public static boolean isFather(String qq) {
        try {
            if (qq == null || qq == "" || MANAGER_QQ == null || MANAGER_QQ.isEmpty()) {
                return false;
            }
            for (String q : MANAGER_QQ) {
                if (q.equals(qq)) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static List<String> getFathersQQ() {
        return MANAGER_QQ;
    }
}
