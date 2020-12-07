package top.lavau.util;

import java.util.Hashtable;
import java.util.Map;

public class TypeUtil {

    /**
     * TYPEID_TYPESTR：将数据库中的 type_id（数字） 映射为具体的字符串
     */
    private static final Map<Integer, String> TYPEID_TYPESTR = new Hashtable<>();
    private static final Map<Integer, String> TYPEID_TABLESTR = new Hashtable<>();

    static {
        // 宣传
         TYPEID_TYPESTR.put(1, "personalPublicity");
        TYPEID_TYPESTR.put(2, "officialPublicity");
        // 感谢或吐槽
        //TYPEID_TYPESTR.put(3, "thankOrRidicule");
        TYPEID_TYPESTR.put(3, "感谢或吐槽");
        // 失物招领
        // TYPEID_TYPESTR.put(4, "lostAndFound");
        TYPEID_TYPESTR.put(4, "失物招领");
        // 求助
        // TYPEID_TYPESTR.put(5, "seekHelp");
        TYPEID_TYPESTR.put(5, "求助");
        // 脱单
        // TYPEID_TYPESTR.put(6, "single");
        TYPEID_TYPESTR.put(6, "脱单");
        // 一卡通
        TYPEID_TYPESTR.put(7, "ecard");

        TYPEID_TABLESTR.put(1, "publicity");
        TYPEID_TABLESTR.put(2, "publicity");
        TYPEID_TABLESTR.put(3, "thank_or_ridicule");
        TYPEID_TABLESTR.put(4, "lost_and_found");
        TYPEID_TABLESTR.put(5, "seek_help");
        TYPEID_TABLESTR.put(6, "single");
    }

    /**
     * 给出 typeId，返回（转换）为 typeString
     * @param id
     *        typeId
     * @return
     *        返回为 typeString。如果 id 错误，返回 null
     */
    public static String getTypeString(Integer id){
        return TYPEID_TYPESTR.getOrDefault(id, null);
    }
    
    /**
     * 给出 typeId，返回（转换）为 tableString
     * @param id
     *        typeId
     * @return
     *        返回为 tableString。如果 id 错误，返回 null
     */
    public static String getTableString(Integer id){
        return TYPEID_TABLESTR.getOrDefault(id, null);
    }
}
