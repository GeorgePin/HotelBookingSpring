<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" th:href="@{/header-style.css}" type="text/css"/>
</head>
<header>
    <a th:href="@{/requests/all}"> <img
            th:src="@{/hotel_logo.png}" alt="hotel logo" id="hotel-logo"></a>
    <div class="navigation-bar">
        <a th:href="@{/rooms}">rooms
        </a> | <a th:href="@{/requests/all}">requests</a> | <a
            th:href="@{/clients}">clients
    </a> |
        <form th:action="@{/logout}" method="POST" name="logoutForm"
              id="logout">
            <input id="logout-btn" type="submit" value="logout"/>
        </form>
    </div>
    </a>
</header>
</html>