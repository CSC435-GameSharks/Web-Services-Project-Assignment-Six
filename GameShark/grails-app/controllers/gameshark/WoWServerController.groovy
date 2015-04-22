package gameshark

import groovy.json.JsonSlurper

/**
 * Created by kirito on 4/20/15.
 */
class WoWServerController {
    def serversAll(){
        def jsonSlurper = new JsonSlurper()
        def is

        try{
            /*Get the raw json*/
            is = new URL("http://us.battle.net/api/wow/realm/status").openStream()
            String sRawJson = is.text

            /**/
            def object = new JsonSlurper().parseText(sRawJson)
            //object.realms.each{println it.name}
            render (view:"/WoWServer", model:[object: object.realms])
        }
        catch(ex){
            redner(view:"/error")
        }
    }
}
