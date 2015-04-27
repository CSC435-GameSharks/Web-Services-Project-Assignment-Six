package gamesharks

import java.io.IOException;

import java.io.InputStream;
import java.net.URL;

import javax.json.*;

import models.*;

class HeroController{
    def index() {
        Hero hero = makeServerAPIRequest(params.battleID, params.hero);
        System.out.println("hero = " + hero);
        if(hero!=null){
            render (view:"index", model: [hero: hero])
        }else{
            render(view:"/error")
        }
    }
    def api(){
        Hero hero = makeServerAPIRequest(params.battleID, params.hero);
        if(hero!=null){
            render toJsonObject(hero);
        }else{
            render(view:"/error")
        }
    }

    private JsonObject toJsonObject(Hero hero){
        Map<String, Object> properties = new HashMap<String, Object>();
        //properties.put("javax.json.stream.JsonGenerator.prettyPrinting", Boolean.valueOf(true));
        //properties.put(JsonGenerator.PRETTY_PRINTING, true);

        JsonBuilderFactory factory = Json.createBuilderFactory(properties);
        JsonObjectBuilder tempObj = factory.createObjectBuilder()
            .add("id", hero.getId())
            .add("name", hero.getName())
            .add("className", hero.getClassName())
            .add("gender", hero.getGender())
            .add("level", hero.getLevel())
            .add("hardcore", hero.isHardcore())
            .add("lastUpdated", hero.getLastUpdated());
        JsonArrayBuilder activeSkillArray = factory.createArrayBuilder();
        for(Skill skill : hero.getActiveSkills()){
            activeSkillArray.add(factory.createObjectBuilder()
                    .add("slug",skill.getSlug())
                    .add("name",skill.getName())
                    .add("icon",skill.getIcon())
                    .add("level",skill.getLevel())
                    .add("tooltipUrl",skill.getTooltipUrl())
                    .add("description",skill.getDescription())
                    .add("skillCalcId",skill.getSkillCalcId()));
        }
        tempObj.add("activeSkills", activeSkillArray);
        JsonArrayBuilder passiveSkillArray = factory.createArrayBuilder();
        for(Skill skill : hero.getPassiveSkills()){
            passiveSkillArray.add(factory.createObjectBuilder()
                    .add("slug",skill.getSlug())
                    .add("name",skill.getName())
                    .add("icon",skill.getIcon())
                    .add("level",skill.getLevel())
                    .add("tooltipUrl",skill.getTooltipUrl())
                    .add("description",skill.getDescription())
                    .add("skillCalcId",skill.getSkillCalcId()));
        }
        tempObj.add("passiveSkills", passiveSkillArray);

        JsonObjectBuilder itemsBuilder = factory.createObjectBuilder();
        Map<String, Item> items = hero.getItems();
        for(String itemName : items.keySet()){
            Item item = items.get(itemName);
            itemsBuilder.add(itemName, factory.createObjectBuilder()
                    .add("id", item.getId())
                    .add("name", item.getName())
                    .add("icon", item.getIcon())
                    .add("displayColor", item.getDisplayColor())
                    .add("tooltipParams", item.getTooltipParams())
                    //Add raw attributes here.
                    );
        }
        tempObj.add("items", itemsBuilder);

        JsonObjectBuilder statsBuilder = factory.createObjectBuilder();
        Map<String, Double> stats = hero.getStats();
        for(String statName : stats.keySet()){
            statsBuilder.add(statName, stats.get(statName));
        }
        tempObj.add("stats", statsBuilder);
        return tempObj.build();
    }



    /**
     * @return A models Career using the user supplied realm and character name
     */
    private static Hero makeServerAPIRequest(String battleID, String strHero){
        Hero diabloHero = null;
        InputStream is = null;
        try{
            is = new URL("http://us.battle.net/api/d3/profile/" + battleID + "/hero/" + strHero).openStream();
            System.out.println("http://us.battle.net/api/d3/profile/" + battleID + "/hero/" + strHero);
            JsonReader jsonReader = Json.createReader(is);
            println("created json Reader.");
            JsonObject jsonObject = jsonReader.readObject();
            println("created json object.");
            jsonReader.close();
            println("closed json reader");
            System.out.println("About to create diabloHero");
            diabloHero = new Hero(jsonObject);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(is!=null)
                    is.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return diabloHero;
    }
}
