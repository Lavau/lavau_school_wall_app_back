package top.leeti.util;//package edu.cust.util;
//
//import edu.cust.miniprogram.model.IndexModel;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class RedisUtil {
//
//    @Autowired
//    private RedisTemplate<String, IndexModel> redisTemplate;
//
//    public List<IndexModel> get(String key, int i1, int i2){
//        return redisTemplate.opsForList().range(key, i1, i2);
//    }
//
//    public void set(){
//
//    }
//}
