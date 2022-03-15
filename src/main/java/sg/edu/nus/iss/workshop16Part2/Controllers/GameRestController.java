package sg.edu.nus.iss.workshop16Part2.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonString;
import sg.edu.nus.iss.workshop16Part2.Models.Game;
import sg.edu.nus.iss.workshop16Part2.Repo.GameRedisRepository;
import sg.edu.nus.iss.workshop16Part2.Repo.GameRepository;
import sg.edu.nus.iss.workshop16Part2.Services.GameService;

@RestController
@RequestMapping(path="/game", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameRestController {

    @Autowired
    GameService gService;
    
    @Autowired
    GameRedisRepository gRedisRepo;

    @Autowired
    GameRepository gRepo;

    @GetMapping("/{gid}")
    public ResponseEntity<String> getGameById(
        @PathVariable String gid){

           //Game game = gService.findByGidJson(gid); 

           JsonObject game = gRedisRepo.findById(gid);
                        

            return ResponseEntity
                .ok()
                .body(game.toString());


        }


    @GetMapping("/search")
    public ResponseEntity<String> searchPattern(@RequestParam(name="pattern") String pattern){


        Set<String> gids = gRedisRepo.searchPattern(pattern);

        

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        gids.stream()
            .sorted()
            .map(gid -> {
                return "/game/%s".formatted(gid);
            })
            .forEach(url -> {
                arrBuilder.add(url);
            });


        return ResponseEntity.ok(arrBuilder.build().toString());
    }
}

