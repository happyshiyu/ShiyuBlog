package cn.shiyu.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiu12
 * 实现将POJO类转换成Map
 */
public class POJOUtil {
	public static Map<String, Object> getMap(Object request) {
        Map<String, Object> params = new HashMap<>();
        if (request == null) {
            return params;
        }

        Field fields[] = request.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object obj = field.get(request);
                String name = field.getName();
                if("serialVersionUID".equals(name))
                	continue;
                params.put(name, obj);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return params;
    }
}
