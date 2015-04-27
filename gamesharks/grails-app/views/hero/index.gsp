    <html>
    <head>
    <link rel="stylesheet" href="/assets/css/Kellys.css">
    <title>
        ${hero?hero.getName():"Hero"}
    </title>
    </head>
    <body>
    <g:if test='${hero}'>
    <div id='rightside'>
    <h1>  ${hero?hero.getName():"Hero"}</h1>
    <img src="http://t3.gstatic.com/images?q=tbn:ANd9GcSJuCdFXM4vv1NdFSNKUkxSF6YWxXNmyxd_sgJ7EHvyzO8XV4GWig">
    <table id="stats">
    <tr>
    <td>Health: <span class="labelText" id='vitality'>${hero.getStats().get("vitality")}</span></td></tr>
    <tr>
    <td>Health Regen: <label class="labelText" id='healing'>${hero.getStats().get("healing")}</label></td></tr>
    <tr>
    <td>Mana: <label class="labelText" id='intelligence'>${hero.getStats().get("intelligence")}</label></td></tr>
    <tr>
    <td>Armor: <label class="labelText" id='armor'>${hero.getStats().get("armor")}</label></td></tr>
    <tr>
    <td>Dexterity: <label class="labelText" id='dexterity'>${hero.getStats().get("dexterity")}</label></td></tr>
    <tr>
    <td>Physical Resistance: <label class="labelText" id='physicalResist'>${hero.getStats().get("physicalResist")}</  label></td></tr>
    <tr>
    <td>Thorns: <label class="labelText" id='thorns'>${hero.getStats().get("thorns")}</label></td> </tr>
    <tr>
    <td>Attack Damage: <label class="labelText" id='damage'>${hero.getStats().get("damage")}</label></td></tr>
    <tr>
    <td>Attack Speed: <label class="labelText" id='attackSpeed'>${hero.getStats().get("attackSpeed")}</label></td></  tr>
    <tr>
    <td>Crit Chance: <label class="labelText" id='critChance'>${hero.getStats().get("critChance")}</label></td></tr>
    <tr>
    <td>Crit Damage: <label class="labelText" id='critDamage'>${hero.getStats().get("critDamage")}</label></td></tr>
    <tr>
    <td>Damage Increase: <label class="labelText" id='damageIncrease'>${hero.getStats().get("damageIncrease")}</      label></td></tr>
    <tr>
    <td>Damage Reduction: <label class="labelText" id='damageReduction'>${hero.getStats().get("damageReduction")}</   label></td></tr>
    <tr>
    <td>Life Steal: <label class="labelText" id='lifeSteal'>${hero.getStats().get("lifeSteal")}</label></td></tr>
    </table>
    </div>
    </g:if>
    <div id="section">
    <div id="skillsection">
    <g:each var="skill" in='${hero.getActiveSkills()}'>
        <div class='skillImages' ><img src='${skill.IMAGE_URL_PREFIX}${skill.getIcon()}.png'>
            <span><br><b>${skill.getName()}</b>
            <p>${skill.getLevel()}</p>
            </div>
    </g:each>
    <g:each var="skill" in='${hero.getPassiveSkills()}'>
        <div class='skillImages' ><img src='${skill.IMAGE_URL_PREFIX}${skill.getIcon()}.png'>
            <span><br><b>${skill.getName()}</b>
            <p>${skill.getLevel()}</p>
            </div>
    </g:each>
</div>
<div>
</body>
</html>
