<!DOCTYPE html>
<html>
<head>
    <title> Characters Overview </title>
</head>

<body>
<form method="GET" action="/career">
    Battle Tag Name:
    <input id="battleTagName" type="text" name="battleTagName">
    </br>Battle Tag Code:
    <input id="battleTagCode" type="text" name="battleTagCode">
    </br>
    <input type="submit" id="btnSubmit" value="Submit"></br>
    </br>
</form>
<g:if test='${career}'>
Battle Tag:         '${career.getBattleTag()          }'</br>
Paragon Level       '${career.getParagonLevel()       }'</br>
Number of Kills:                                        </br>
Monsters:           '${career.getKills().getMonsters()}'</br>
Elites:             '${career.getKills().getMonsters()}'</br>
Hardcore Monsters:  '${career.getKills().getMonsters()}'</br>
Heroes:</br>
<dl>
<g:each  var="hero" in='${career.getHeroes()}'>
    <dt>
        <a href="/hero?battleID=${career.getBattleTag().replaceAll("#", "-")}&hero=${hero.getId()}">
        Name:    ${hero.getName()}</a></br>
        </dt>
        <dd> Level:  ${hero.getLevel()} </dd>
        <dd> Dead:   ${hero.isDead()?"Yes":"No"}</dd>
    </g:each>
</ul>


</g:if>

</body>
</html>


