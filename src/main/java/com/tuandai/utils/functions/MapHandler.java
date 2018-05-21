package com.tuandai.utils.functions;

import java.util.Map;

/**
 * Created by 肖文 on 2018/5/19
 */
public interface MapHandler<K,V> {

    String handler(Map<K, V> map);
}
