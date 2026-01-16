<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>МедПортал - Изменение профиля пациента</title>
    <link rel="stylesheet" href="${contextPath}/static/css/main.css">
</head>
<body>
<div class="header">
    <div>
        <h1>МедПортал - Изменение профиля пациента</h1>
    </div>
    <div>
        <a href="${contextPath}/patientmenu" class="btn btn-info">Назад</a>
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
        <form action="${contextPath}/patientmenu/update" method="post">
            <div class="form-group">
                <label>Имя:</label>
                <input type="text" name="name" value="${patient.name!''}" required>
            </div>

            <div class="form-group">
                <label>Фамилия:</label>
                <input type="text" name="surname" value="${patient.surname!''}" required>
            </div>

            <div class="form-group">
                <label>Email:</label>
                <input type="email" name="email" value="${patient.email!''}" required>
            </div>

            <div class="form-group">
                <label>Адрес:</label>
                <input type="text" name="address" value="${patient.address!''}" required>
            </div>

            <div class="form-group">
                <label>Дата рождения:</label>
                <input type="date" name="dateOfBirth" value="${patient.dateOfBirth!''}" required>
            </div>

            <div class="form-group">
                <label>Новый пароль:</label>
                <label>(или введите старый)</label>
                <input type="password" name="password" required>
            </div>

            <div class="btn-group">
                <button type="submit" class="btn btn-primary">Сохранить изменения</button>
                <a href="${contextPath}/patientmenu" class="btn btn-secondary">Отмена</a>
            </div>
        </form>
    </div>
</div>

<div class="footer">
    <p>© 2025 МедПортал. Все права защищены.</p>
</div>
</body>
</html>