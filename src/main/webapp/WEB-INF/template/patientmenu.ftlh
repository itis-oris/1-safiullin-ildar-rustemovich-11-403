<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>МедПортал - Личный кабинет пациента</title>
    <link rel="stylesheet" href="${contextPath}/static/css/main.css">
</head>
<body>
<div class="header">
    <div>
        <h1>МедПортал - Личный кабинет</h1>
    </div>
    <div>
        <a href="${contextPath}/main" class="btn btn-info">Главное меню</a>
        <a href="${contextPath}/logout" class="btn btn-danger">Выйти</a>
    </div>
</div>

<div class="content">
    <div class="section">
        <h2>Профиль пациента</h2>
        <div class="profile-card">
            <div class="profile-field">
                <strong>Имя:</strong> ${patient.name!''}
            </div>
            <div class="profile-field">
                <strong>Фамилия:</strong> ${patient.surname!''}
            </div>
            <div class="profile-field">
                <strong>Email:</strong> ${patient.email!''}
            </div>
            <div class="profile-field">
                <strong>Адрес:</strong> ${patient.address!''}
            </div>
            <div class="profile-field">
                <strong>Дата рождения:</strong> ${patient.dateOfBirth!''}
            </div>
        </div>

        <div class="btn-group">
            <a href="${contextPath}/patientmenu/update" class="btn btn-primary">Изменить профиль</a>
            <a href="${contextPath}/patientmenu/reception" class="btn btn-success">Записаться на приём</a>
            <a href="${contextPath}/patientmenu/story" class="btn btn-warning">История приёмов</a>
        </div>
    </div>
</div>

<div class="footer">
    <p>© 2025 МедПортал. Все права защищены.</p>
</div>
</body>
</html>