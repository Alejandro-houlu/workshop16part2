package sg.edu.nus.iss.workshop16Part2.Repo;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.workshop16Part2.Models.Game;

@Repository
public class GameRedisRepository {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public JsonObject findById(String gid){

        String gameId = (String)redisTemplate.opsForHash().get("Game:" + gid, "gid"); 
        
        return
        Json.createObjectBuilder()
            .add("gid", gameId)
            .add("name", (String)redisTemplate.opsForHash().get("Game:" + gid, "name"))
            .build();
      
    }

    public Set<String> searchPattern(String pattern){

        String query = "*%s*".formatted(pattern);

        Set<String> result = redisTemplate.keys(query);
        Set<String> formatResult = new HashSet<>();
        result.stream().map(x->x.substring(5)).forEach(x->formatResult.add(x));

        return formatResult;

    }


}
