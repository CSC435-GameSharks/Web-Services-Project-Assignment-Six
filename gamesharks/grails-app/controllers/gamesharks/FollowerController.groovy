package gamesharks

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.*;
//
import models.Follower;
import models.*;
//import play.mvc.Controller;
//import play.mvc.Result;
//import views.html.diabloFollower;

class FollowerController{
    def index() {
        System.out.println("Line 19");
        Follower follower = makeServerAPIRequest(params.follower);
        System.out.println("Line 21");
        //if(follower==null){
        //    System.out.println("follower was null");
        //    render(view:"/error")
        //}else{
            println("About to display follower view");
            render (view:"index", model: [follow: follower])
        //}

    }
    def api(){
        String follower = params.follower;
        Follower myFollower = makeServerAPIRequest(follower);
        if(myFollower!=null){
            render toJsonObject(myFollower);
        }else{
            render(view:"/error")
            //render "There is no follower " + follower;
        }
    }
    private static Follower makeServerAPIRequest(String strFollower){
        Follower diabloFollower = null;
        InputStream is = null;
        JsonObject jsonObject;
        try{
            is = new URL("http://us.battle.net/api/d3/data/follower/" + strFollower).openStream();
            if(is){
                JsonReader jsonReader = Json.createReader(is);
                jsonObject = jsonReader.readObject();
                jsonReader.close();
            }
            diabloFollower = new Follower(jsonObject);
        } catch(FileNotFoundException fnfe){
            System.out.println(fnfe);
        }finally {
            try {
                if(is)
                    is.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("about to return diablo follower");
        return diabloFollower;
    }
    private JsonObject toJsonObject(Follower follower){
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObjectBuilder tempObj = factory.createObjectBuilder()
            .add("slug", follower.getSlug())
            .add("name", follower.getName())
            .add("realName", follower.getRealName())
            .add("portrait", follower.getPortrait());
        JsonArrayBuilder skills = factory.createArrayBuilder();
        //for(ActiveSkill skill : follower.getActiveSkills()){
        //    skills.add(factory.createObjectBuilder()
        //            .add("slug",skill.getSlug())
        //            .add("name",skill.getName())
        //            .add("icon",skill.getIcon())
        //            .add("level",skill.getLevel())
        //            .add("tooltipUrl",skill.getTooltipUrl())
        //            .add("description",skill.getDescription())
        //            .add("skillCalcId",skill.getSkillCalcId()));
        //}
        //tempObj.add("skills", skills);
        return tempObj.build();

    }
}



//class FollowerController {
//    def index() { }
//}
