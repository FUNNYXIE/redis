package demo;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;

public class test {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        System.out.println("连接成功");
        Artick artick =new Artick();
        artick.setTitle("fastjson");
        artick.setAuthor("author");
        artick.setContent("cc");
        artick.setTime("2019");
        Long id=save(artick,jedis);
        System.out.println(id);
        System.out.println("保存成功");
        Artick artick1=get(jedis,id);
        System.out.println(artick1);
        System.out.println("查找成功");
        artick.setTime("2017");
        Long id1=upd(artick,jedis,id);
        Artick artick2=get(jedis,id1);
        System.out.println(artick2);
    }
    public static Long save(Artick artick,Jedis jedis){
        Long id=jedis.incr("posts");
        String stu= JSON.toJSONString(artick);
        jedis.set("post:"+id+":data",stu);
        return id;
    }
    public static Artick get(Jedis jedis,Long id){
        String post = jedis.get("post:"+id+":data");
        Artick artick=JSON.parseObject(post,Artick.class);
        return artick;
    }
    public static Long del(Jedis jedis,Long id){
        jedis.del("post:"+id+":data");
        return id;
    }
    public static Long upd(Artick artick,Jedis jedis,Long id){
        Long pid=id;
        String stu= JSON.toJSONString(artick);
        jedis.set("post:"+pid+":data",stu);
        return pid;
    }
}
