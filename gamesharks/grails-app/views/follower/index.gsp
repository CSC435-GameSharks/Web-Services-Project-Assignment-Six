<%@ page import="java.util.ArrayList">
<%@ page import="models.Follower">
    <html>
    <head>
    <link rel="stylesheet" href="/assets/css/Kellys.css">
    <title>
    "${follower.getName()}"
    </title>
    </head>
    <body>
    <div id='rightside'>
    <h1> "${follower.getName()}" </h1>
    <g:if "${follower}">
        <img src="http://cdn2.gamefront.com/wp-content/uploads/2012/05/d3-26.jpg">
    <g:if/>
    <g:elseif ${follower.getName().equals("Enchantress")}>
        <img src="http://vignette1.wikia.nocookie.net/diablo/images/b/bb/Enchantress.jpg/revision/latest?cb=20110513220214">
    <g:elseif/>
    <g:elseif ${follower.getName().equals("Templar")}>
        <img src="http://vignette3.wikia.nocookie.net/diablo/images/0/02/Templar.jpg/revision/latest?cb=20110513220215">
    <g:elseif/>
    <g:elseif ${follower.getName().equals("Scoundrel")}>
        <img src="http://vignette1.wikia.nocookie.net/diablo/images/3/3c/Scoundrel.jpg/revision/latest?cb=20110513220216">
    <g:elseif/>
<div id="lvl">
<form method="GET" action="/diablofollower">
<select name="follower">
<option value="enchantress">Enchantress</option>
<option value="templar">Templar</option>
<option value="scoundrel">Scoundrel</option>
</select>
<input type="submit">
</form>
</div>
</div>
    <div id="section">
        <div id="skillsection">
        <g:each in={follower.getActiveSkills()} var="skill">
            <div class='skillImages' ><img src='${skill.IMAGE_URL_PREFIX}${skill.getIcon()}.png'>
                <span><br><b>${skill.getName()}</b>
                <p>${skill.getLevel()}</p>
                </div>
        <g:each/>
    </div>
        </div>
</body>
</html>

