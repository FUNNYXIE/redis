package demo;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class HestDemo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        System.out.println("连接成功");
        Artick artick =new Artick();
        artick.setTitle("fastjson");
        artick.setAuthor("author");
        artick.setContent("cc");
        artick.setTime("2019");
        Long id=save(artick,jedis);
        System.out.println("保存成功");
        Artick artick1=get(id,jedis);
        System.out.println(artick1);
        System.out.println("查找成功");
        artick.setTime("2017");
        Long id1=upd(artick,jedis,id);
        Artick artick2=get(id1,jedis);
        System.out.println(artick2);
    }
    static Long save(Artick artick,Jedis jedis){
        Long id=jedis.incr("posts");
        Map<String,String> blog =new HashMap<String, String>();
        blog.put("title",artick.getTitle());
        blog.put("author",artick.getAuthor());
        blog.put("content",artick.getContent());
        blog.put("time",artick.getTime());
        jedis.hmset("post:"+id+":data",blog);
        return id;
    }
    static Artick get(Long id,Jedis jedis){
        Map<String,String> blog =jedis.hgetAll("post:"+id+":data");
        Artick artick=new Artick();
        artick.setTitle(blog.get("title"));
        artick.setAuthor(blog.get("author"));
        artick.setContent(blog.get("content"));
        artick.setTime(blog.get("time"));
        return artick;

    }
    public static Long del(Jedis jedis,Long id){
        jedis.hdel("post:"+id+":data");
        return id;
    }
    public static Long upd(Artick artick,Jedis jedis,Long id){
        Long pid=id;
        Map<String,String> blog =new HashMap<String, String>();
        blog.put("title",artick.getTitle());
        blog.put("author",artick.getAuthor());
        blog.put("content",artick.getContent());
        blog.put("time",artick.getTime());
        jedis.hmset("post:"+id+":data",blog);
        return pid;
    }
}
