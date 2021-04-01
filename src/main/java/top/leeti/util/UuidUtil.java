package top.leeti.util;

import java.util.UUID;

public class UuidUtil {

    /**
     * 获取 uuid
     * @return String
     *         返回 uuid
     */
    public static String acquireUuid(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
        System.out.println(acquireUuid());
    }
}
