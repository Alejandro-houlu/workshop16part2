package sg.edu.nus.iss.workshop16Part2.Models;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Game")
public class Game implements Serializable {
    private String id;
    private Integer gid;
    private String name; 
    private Integer year;
    private Integer ranking;
    private Integer users_rated;
    private String url;
    private String image;

    public Integer getGid() {
        return gid;
    }
    public void setGid(Integer gid) {
        this.gid = gid;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public Integer getRanking() {
        return ranking;
    }
    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
    public Integer getUsers_rated() {
        return users_rated;
    }
    public void setUsers_rated(Integer users_rated) {
        this.users_rated = users_rated;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String setUniqueId(){
        
        String uniqueId = UUID.randomUUID().toString();
        return uniqueId.substring(0,8);
    }

    
    @Override
    public String toString() {
        return "Game [gid=" + gid + ", name=" + name + "]";
    }

    public Game(int gid, String name, int year, int ranking, int users_rated, String url, String image){
        this.gid = gid;
        this.name = name;
        this.year = year;
        this.ranking = ranking;
        this.users_rated = users_rated;
        this.url = url;
        this.image = image;
        this.id = setUniqueId();
    }

    public Game(){}
    
}
