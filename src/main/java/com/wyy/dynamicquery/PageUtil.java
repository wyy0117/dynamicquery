package com.wyy.dynamicquery;

import java.util.List;

/**
 * @author wyy
 * @date 18-6-21
 * @time 下午2:39
 */
public class PageUtil<T> {

    public List<T> page(List<T> list, int start, int end) {
        if (start == -1 || end == -1) {
            return list;
        }
        start = Math.min(start, list.size());
        end = Math.min(end, list.size());
        return list.subList(start, end);
    }
}
