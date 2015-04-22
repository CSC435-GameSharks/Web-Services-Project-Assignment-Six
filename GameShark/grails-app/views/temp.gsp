<%--
  Created by IntelliJ IDEA.
  User: kirito
  Date: 4/20/15
  Time: 1:28 PM
--%>

<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="test"/>
    <title>I am a test page</title>
    <link type="text/css" href="${resource(dir: 'assets/stylesheets', file: 'WoWStyle.css')}" />
</head>

<body>
    <div style="padding-top: 10px;">
        <h3>
            Server Status
        </h3>
        <div>
            <table class="serverTable">
                <caption>
                    WoW Server Status
                </caption>
                <tr>
                    <th>
                        Name
                    </th>
                    <th>
                        Status
                    </th>
                </tr>
                <g:each in="${object}" var="servers">
                    <tr>
                        <td>
                            ${servers.name}
                        </td>
                        <td>
                            ${servers.status}
                        </td>
                    </tr>
                </g:each>
            </table>
        </div>
    </div>
</body>
</html>