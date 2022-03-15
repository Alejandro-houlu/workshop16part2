package sg.edu.nus.iss.workshop16Part2.Services;

import java.io.FileInputStream;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonString;
import sg.edu.nus.iss.workshop16Part2.Models.Game;
import sg.edu.nus.iss.workshop16Part2.Repo.GameRepository;

@Service
public class GameService {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    GameRepository gRepo;
    
    public Game findByGidJson(String gid){
        
       var game = gRepo.findById(gid); 

       Game game1 = new Game();

       game.ifPresent(x->{
           game1.setGid(x.getGid());
           game1.setId(x.getId());
           game1.setName(x.getName());
           game1.setRanking(x.getRanking());
           game1.setUrl(x.getUrl());
           game1.setUsers_rated(x.getUsers_rated());
           game1.setYear(x.getYear());
           game1.setImage(x.getImage());
           
       }); 

        return game1;
    }

}
