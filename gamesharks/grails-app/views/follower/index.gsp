    <html>
    <head>
    <link rel="stylesheet" href="/assets/css/Kellys.css">
    <title>
        ${follow?follow.getName():"Follower"}
    </title>
    </head>
    <body>
    <div id='rightside'>
    <h1>
    ${follow?follow.getName():"Follower"}
    </h1>
    <g:if test="${!follow}">
    <img src="http://cdn2.gamefront.com/wp-content/uploads/2012/05/d3-26.jpg">
    </g:if>
    <g:elseif test="${follow.getName().equals('Enchantress')}">
    <img src="http://vignette1.wikia.nocookie.net/diablo/images/b/bb/Enchantress.jpg/revision/latest?cb=20110513220214">
    </g:elseif>
    <g:elseif test="${follow.getName().equals('Templar')}">
    <img src="http://vignette3.wikia.nocookie.net/diablo/images/0/02/Templar.jpg/revision/latest?cb=20110513220215">
    </g:elseif>
    <g:elseif test="${follow.getName().equals('Scoundrel')}">
    <img src="http://vignette1.wikia.nocookie.net/diablo/images/3/3c/Scoundrel.jpg/revision/latest?cb=20110513220216">
    </g:elseif>

<div id="lvl">
<form method="GET" action="/follower">
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
        </div>
    </div>
</body>
</html>

