package com.epam.mentor.repository.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Aliaksandr_Shynkevich on 11/22/14
 */
public final class RepositoryUtils {
    private RepositoryUtils() {
    }

    public static <K, T> List<T> getObjectList(Map<K, T> map) {
        List<T> list = new ArrayList<>();
        for (Map.Entry<K, T> entry: map.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }
}
