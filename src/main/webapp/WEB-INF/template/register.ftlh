<!DOCTYPE html>
<html>
<head>
    <title>Выбор роли - MedPortal</title>
    <link rel="stylesheet" href="${contextPath}/static/css/main.css">
</head>
<body>
<div class="header">
    <div>
        <h1>МедПортал - Выбор роли</h1>
    </div>
    <div>
        <a href="${contextPath}/main" class="btn btn-secondary">На главную</a>
    </div>
</div>

<div class="content">
    <div class="section">
        <h2>Регистрация в MedPortal</h2>
        <p>Выберите тип аккаунта:</p>

        <form action="${contextPath}/register" method="post">
            <div class="appointment-card">
                <div class="radio-group">
                    <input type="radio" name="role" value="doctor" id="doctor">
                    <label for="doctor"><strong>Я врач</strong></label>
                </div>
                <div class="appointment-field">Регистрация для медицинских специалистов</div>
            </div>

            <div class="appointment-card">
                <div class="radio-group">
                    <input type="radio" name="role" value="patient" id="patient">
                    <label for="patient"><strong>Я пациент</strong></label>
                </div>
                <div class="appointment-field">Регистрация для пациентов клиники</div>
            </div>

            <div class="btn-group">
                <button type="submit" class="btn btn-success">Продолжить</button>
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