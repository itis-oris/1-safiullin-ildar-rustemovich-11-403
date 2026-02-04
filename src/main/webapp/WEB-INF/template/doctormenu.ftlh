<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>МедПортал - Личный кабинет врача</title>
    <link rel="stylesheet" href="${contextPath}/static/css/main.css">
</head>
<body>
<div class="header">
    <div>
        <h1>МедПортал - Личный кабинет врача</h1>
    </div>
    <div>
        <a href="${contextPath}/main" class="btn btn-info">Главное меню</a>
        <a href="${contextPath}/logout" class="btn btn-danger">Выйти</a>
    </div>
</div>

<div class="content">
    <div class="section">
        <h2>Профиль врача</h2>
        <div class="profile-card">
            <div class="profile-field">
                <strong>Имя:</strong> ${doctor.name!''}
            </div>
            <div class="profile-field">
                <strong>Фамилия:</strong> ${doctor.surname!''}
            </div>
            <div class="profile-field">
                <strong>Email:</strong> ${doctor.email!''}
            </div>
            <div class="profile-field">

            </div>
        </div>

        <div class="btn-group">
            <a href="${contextPath}/doctormenu/update" class="btn btn-primary">Изменить профиль</a>
            <a href="${contextPath}/doctormenu/workinghours" class="btn btn-success">Часы работы</a>
            <a href="${contextPath}/doctormenu/story" class="btn btn-warning">История приёмов</a>
        </div>
    </div>
</div>

<div class="footer">
    <p>© 2025 МедПортал. Все права защищены.</p>
</div>
</body>
</html>