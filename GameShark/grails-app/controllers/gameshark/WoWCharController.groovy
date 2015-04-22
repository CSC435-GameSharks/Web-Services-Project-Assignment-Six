package gameshark

/**
 * Created by kirito on 4/21/15.
 */
class WoWCharController {
    def findChar(){
        def checkTime = false

        if(params.name != null){
            session.setAttribute("char", params.name)
        }else{
            checkTime = true
        }

        if(params.realm != null){
            session.setAttribute("realm", params.realm)
            session.setAttribute("timestamp", new Date().toString())
        }else{
            checkTime = true
        }

        if(session.getAttribute("timestamp") != null){
            Date timeStamp = new Date(session.getAttribute("timestamp"));
            Date currentTime = new Date();
            Long timeDiff = (currentTime.getTime() - timeStamp.getTime()) / 1000;
            if(timeDiff > 20){
                session.invalidate();
                //name = null;
                //realm = null;
            }

        }

        if(session.getAttribute("char") == null){
            session.setAttribute("char", "")
        }

        println session.getAttribute("char")
        render (view:"/WoWChar", model:[name: session.getAttribute("char")])
    }

}
