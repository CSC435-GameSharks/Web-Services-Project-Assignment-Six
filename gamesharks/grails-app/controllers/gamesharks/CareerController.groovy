package gamesharks

import java.io.IOException;

import java.io.InputStream;
import java.net.URL;

import javax.json.*;

import models.Career;
import models.AbbreviatedHero;

class CareerController{
    def index() {
        System.out.println("Line 19");
        Career career = makeServerAPIRequest(params.battleTagName, params.battleTagCode);
        System.out.println("Line 21");
        println("About to display career view");
        render (view:"index", model: [career: career])
    }
    def api(){
        Career career = makeServerAPIRequest(params.battleTagName, params.battleTagCode);
        if(career!=null){
            render toJsonObject(career);
        }else{
            render(view:"/error")
        }
    }
    /**
     * @return A models Career using the user supplied realm and character name
     */
    private static Career makeServerAPIRequest(String  battleTagName, String battleTagCode){
        Career diabloPlayer = null;
        InputStream is = null;
        try{
            is = new URL("http://us.battle.net/api/d3/profile/" + battleTagName + "-" + battleTagCode + "/").openStream();
            JsonReader jsonReader = Json.createReader(is);
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();
            if(jsonObject!=null)
                diabloPlayer = new Career(jsonObject);
            else
                return null;
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(is!=null)
                    is.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return diabloPlayer;
    }


    /**
     * Converts a Diablo.Career to a JSON string
     * @param  career The Career to be stringified
     * @return        a JSON String representing the Diablo.Career
     */
    private JsonObject toJsonObject(Career career){
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObjectBuilder tempObj = factory.createObjectBuilder()
            .add("battleTag", career.getBattleTag())
            .add("paragonLevel", career.getParagonLevel())
            .add("paragonLevelHardcore", career.getParagonLevelHardcore())
            .add("paragonLevelSeason", career.getParagonLevelSeason())
            .add("paragonLevelSeasonHardcore", career.getParagonLevelSeasonHardcore())
            .add("lastHeroPlayed", career.getLastHeroPlayed())
            .add("lastUpdated", career.getLastUpdated())
            .add("kills", factory.createObjectBuilder()
                    .add("monsters", career.getKills().getMonsters())
                    .add("elites", career.getKills().getElites())
                    .add("hardcoreMonsters", career.getKills().getHardcoreMonsters()));
        JsonArrayBuilder heroArray = factory.createArrayBuilder();
        for(AbbreviatedHero hero : career.getHeroes()){
            heroArray.add(factory.createObjectBuilder()
                    .add("name",hero.getName())
                    .add("id",hero.getId())
                    .add("level",hero.getLevel())
                    .add("hardcore",hero.isHardcore())
                    .add("gender",hero.getGender())
                    .add("lastUpdated",hero.getLastUpdated())
                    .add("dead",hero.isDead()));
        }
        tempObj.add("heroes", heroArray);
        return tempObj.build();
    }
}
