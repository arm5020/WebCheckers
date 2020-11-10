<!DOCTYPE html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <meta http-equiv="refresh" content="10">
  <title>Web Checkers | ${title}</title>
  <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
<div class="page">

  <h1>Web Checkers | ${title}</h1>

  <!-- Provide a navigation bar -->
  <#include "nav-bar.ftl" />

  <div class="body">
    <#if currentUser??>
        Online players |
        <#if opponents??>
            <#list opponents as opponent>
                <form action="/game" method="post">
                    <input type="submit" value="${opponent}" name="whitePlayer">
                </form>
            </#list>
        <#else>
            There are no other online players
        </#if>

    <#else>
        There are ${playerAmount} player(s) online
    </#if>

    <#if currentUser??>
        <#include "message.ftl" />
    <#else>

    </#if>
  </div>

</div>
</body>

</html>