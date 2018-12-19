import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestDemo {

    @Test
    public void test01() {
        // 创建集合对象
        Map<String, String> map = new HashMap<String, String>();

        // 创建元素并添加到集合
        map.put("杨过", "小龙女");
        map.put("郭靖", "黄蓉");
        map.put("杨康", "穆念慈");
        map.put("陈玄风", "梅超风");

        // 获取所有键值对对象的集合
        Set<Map.Entry<String, String>> set = map.entrySet();
        // 遍历键值对对象的集合，得到每一个键值对对象是map接口的子类对象。
      for (Map.Entry<String, String> me:set){

        }


    }
}
