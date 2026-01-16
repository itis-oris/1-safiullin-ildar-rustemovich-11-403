<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>МедПортал - Добавить рабочий час</title>
    <link rel="stylesheet" href="${contextPath}/static/css/main.css">
</head>
<body>
<div class="header">
    <div>
        <h1>МедПортал - Добавить рабочий час</h1>
    </div>
    <div>
        <a href="${contextPath}/doctormenu/workinghours" class="btn btn-secondary">Назад</a>
    </div>
</div>

<div style="margin-top: 20px;">
    <#if error??>
        <div style="color: red;">
            ${error}
        </div>
    </#if>
</div>


<div class="content">
    <div class="section">
        <form action="${contextPath}/doctormenu/addworkinghour" method="post">
            <div class="form-group">
                <label>Дата и время приёма:</label>
                <input type="datetime-local" name="scheduledTime" required>
            </div>

            <div class="btn-group">
                <button type="submit" class="btn btn-primary">Добавить рабочий час</button>
                <a href="${contextPath}/doctormenu" class="btn btn-secondary">Отмена</a>
            </div>
        </form>
    </div>
</div>

<div class="footer">
    <p>© 2025 МедПортал. Все права защищены.</p>
</div>
</body>
</html>