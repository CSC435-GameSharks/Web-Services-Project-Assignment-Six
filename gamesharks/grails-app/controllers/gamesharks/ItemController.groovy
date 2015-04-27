package gamesharks

import java.io.IOException;

import java.io.InputStream;
import java.net.URL;

import javax.json.*;

import models.*;

class ItemController{
    def index() {
        api();
    }
    def api(){
        Item item = makeServerAPIRequest(params.item);
        if(item!=null){
            render toJsonObject(item);
        }else{
            render(view:"/error")
        }
    }

    public static Item makeServerAPIRequest(String itemId){
        Item item = null;
        InputStream is = null;
        try{
            is = new URL("http://us.battle.net/api/d3/data/item/" + itemId).openStream();
            System.out.println("http://us.battle.net/api/d3/data/item/" + itemId);
            JsonReader jsonReader = Json.createReader(is);
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();
            item = new Item(jsonObject);
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
        return item;
    }


    private JsonObject toJsonObject(Item item){
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObjectBuilder tempObj = factory.createObjectBuilder()
            .add("id", item.getId())
            .add("name", item.getName())
            .add("icon", item.getIcon())
            .add("displayColor", item.getDisplayColor())
            .add("tooltipParams", item.getTooltipParams())
            JsonObjectBuilder attributes = factory.createObjectBuilder();
            for(String attributeName : item.getRawAttributes().keySet()){
                System.out.println("attributeName = " + attributeName);
                System.out.println("attributeValue = " + item.getRawAttributes().get(attributeName));
                attributes.add(
                    attributeName,item.getRawAttributes().get(attributeName));
            }
        tempObj.add("rawAttributes", attributes);
        return tempObj.build();
    }
}
