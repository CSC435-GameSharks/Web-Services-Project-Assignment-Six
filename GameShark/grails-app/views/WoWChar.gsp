<%--
  Created by IntelliJ IDEA.
  User: kirito
  Date: 4/21/15
  Time: 2:02 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="test"/>
    <title>WoW Character</title>
    <link type="text/css" href="${resource(dir: 'assets/stylesheets', file: 'WoWStyle.css')}" />
</head>

<body>
<h3>
    Player Character Info
</h3>
<div>
    <form action="WoWCharController">
        <table>
            <tr>
                <td>
                    Character Name:
                </td>
                <td>
                    <input type="text" id="txtName" name="name"></br>
                </td>
            </tr>
            <tr>
                <td>
                    Realm Name:
                </td>
                <td>
                    <input type="text" id="txtRealm" name="realm"></br>
                </td>
            </tr>
            <tr colspan="2">
                <td colspan="2">
                    <input type="submit" id="btnSubmit" value="Submit"></br>
                </td>
            </tr>
        </table>
    </form>
</div>
<g:if test="${name != ""}">
    <div class="mainDiv" >
        <h2 class="characterTitle">
            Character Profile
        </h2>
        <table class="charTable">
            <tr>
                <td class="mainDivPhoto" rowspan="5">

                </td>
            </tr>
            <tr>
                <td class="nameCell">

                </td>
            </tr>
            <tr>
                <td>
                    Level:&#160;
                </td>
            </tr>
            <tr>
                <td>
                    Realm:&#160;
                </td>
            </tr>
            <tr>
                <td>
                    Battlegroup:&#160;
                </td>
            </tr>
            <tr>
                <td>
                    Honurable Kills:&#160;
                </td>
            </tr>
            <tr>
                <td>
                    Achievement Points:&#160;
                </td>
            </tr>
        </table>
    </div>
</g:if>
<g:else>
    <div>
        <span>No User Character Data</span>
    </div>
</g:else>
</body>
</html>