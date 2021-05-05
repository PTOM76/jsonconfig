package ml.pkom.jsonconfig.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ObjectDumper {

    private static final int INDENT = 2;

    public static String dump(Map<String, Object> obj) {
        List<String> buffer = new ArrayList<String>();
        dumpMap(obj, 0, buffer);
        return String.join("\n", buffer);
    }

    private static void dumpMap(Map<String, Object> obj, int depth, List<String> buffer) {
        for (Map.Entry<String, Object> entry : obj.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof List) {
                buffer.add(line(entry.getKey(), value, depth));
                dumpList((List<Object>) value, depth + 1, buffer);
            } else if (value instanceof Map) {
                buffer.add(line(entry.getKey(), value, depth));
                dumpMap((Map<String, Object>) value, depth + 1, buffer);
            } else {
                buffer.add(line(entry.getKey(), value, depth));
            }
        }
    }

    private static void dumpList(List<Object> array, int depth, List<String> buffer) {
        for (Object element : array) {
            if (element instanceof List) {
                buffer.add(line(element, depth));
                dumpList((List<Object>) element, depth + 1, buffer);
            } else if (element instanceof Map) {
                buffer.add(line(element, depth));
                dumpMap((Map<String, Object>) element, depth + 1, buffer);
            } else {
                buffer.add(line(element, depth));
            }
        }
    }

    private static String line(String key, Object value, int depth) {
        if (value == null) {
            return StringUtils.repeat(" ", INDENT * depth) + key + ": " + "null";
        } else if (value instanceof Map) {
            return StringUtils.repeat(" ", INDENT * depth) + key + ": " + "(" + value.getClass().getName() + ")";
        } else if (value instanceof List) {
            return StringUtils.repeat(" ", INDENT * depth) + key + ": " + "(" + value.getClass().getName() + ")";
        } else {
            return StringUtils.repeat(" ", INDENT * depth) + key + ": " + value.toString() + " (" + value.getClass().getName() + ")";
        }
    }

    private static String line(Object value, int depth) {
        if (value == null) {
            return StringUtils.repeat(" ", INDENT * depth) + "null";
        } else if (value instanceof Map) {
            return StringUtils.repeat(" ", INDENT * depth) + "(" + value.getClass().getName() + ")";
        } else if (value instanceof List) {
            return StringUtils.repeat(" ", INDENT * depth) + "(" + value.getClass().getName() + ")";
        } else {
            return StringUtils.repeat(" ", INDENT * depth) + value.toString() + " (" + value.getClass().getName() + ")";
        }
    }
}