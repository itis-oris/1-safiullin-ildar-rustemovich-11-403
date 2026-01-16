<!DOCTYPE html>
<html>
<head>
    <title>Регистрация пациента - MedPortal</title>
    <link rel="stylesheet" href="${contextPath}/static/css/main.css">
</head>
<body>
<div class="header">
    <div>
        <h1>МедПортал - Регистрация пациента</h1>
    </div>
    <div>
        <a href="${contextPath}/main" class="btn btn-secondary">На главную</a>
    </div>
</div>

<div class="content">
    <div class="section">
        <div>
            <#if error??>
                <div style="color: red;">
                    ${error}
                </div>
            </#if>
        </div>

        <form action="${contextPath}/register/patient" method="post">
            <div class="form-group">
                <label>Имя:</label>
                <input type="text" name="name" required>
            </div>

            <div class="form-group">
                <label>Фамилия:</label>
                <input type="text" name="surname" required>
            </div>

            <div class="form-group">
                <label>Email:</label>
                <input type="email" name="email" required>
            </div>

            <div class="form-group">
                <label>Пароль:</label>
                <input type="password" name="password" required>
            </div>

            <div class="form-group">
                <label>Дата рождения:</label>
                <input type="date" name="dateOfBirth" required>
            </div>

            <div class="form-group">
                <label>Адрес:</label>
                <input type="text" name="address" required>
            </div>

            <div class="btn-group">
                <button type="submit" class="btn btn-success">Зарегистрироваться</button>
                <a href="${contextPath}/main" class="btn btn-secondary">Отмена</a>
            </div>
        </form>
    </div>
</div>

<div class="footer">
    <p>© 2025 МедПортал. Все права защищены.</p>
</div>
</body>
</html>