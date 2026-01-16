<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>МедПортал - Вход</title>
    <link rel="stylesheet" href="${contextPath}/static/css/main.css">
</head>
<body>
<div class="header">
    <div>
        <h1>МедПортал - Вход</h1>
    </div>
</div>

<div class="content">
    <div class="section">
        <form action="${contextPath}/login" method="post">
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>

            <div class="form-group">
                <label for="password">Пароль:</label>
                <input type="password" id="password" name="password" required>
            </div>

            <div class="btn-group">
                <button type="submit" class="btn btn-success">Войти</button>
            </div>
        </form>

        <div style="margin-top: 20px;">
            <#if errormessage??>
                <div style="color: red;">
                    ${errormessage}
                </div>
            </#if>
        </div>

        <div style="margin-top: 20px;">
            <p>Нет аккаунта? <a href="${contextPath}/register" class="btn btn-primary btn-small">Зарегистрироваться</a></p>
        </div>
    </div>
</div>

<div class="footer">
    <p>© 2025 МедПортал. Все права защищены.</p>
</div>
</body>
</html>