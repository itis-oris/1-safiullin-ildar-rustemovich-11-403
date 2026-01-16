<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>МедПортал - Главная</title>
    <link rel="stylesheet" href="${contextPath}/static/css/main.css">
</head>
<body>

<div class="header">
    <div>
        <h1>МедПортал</h1>
    </div>
    <div>
        <#if patient?? || doctor?? || admin??>
            <div class="user-menu">
                <#if patient??>
                    <a href="${contextPath}/patientmenu" class="btn btn-primary">Личный кабинет пациента</a>
                    <span>Привет, ${patient.name!''}!</span>
                <#elseif doctor??>
                    <a href="${contextPath}/doctormenu" class="btn btn-primary">Личный кабинет врача</a>
                    <span>Привет, ${doctor.name!''}!</span>
                <#elseif admin??>
                    <a href="${contextPath}/admin/show" class="btn btn-primary">Личный кабинет</a>
                    <span>Привет, Админ</span>
                </#if>
                <a href="${contextPath}/logout" class="btn btn-danger">Выйти</a>
            </div>
        <#else>
            <div class="btn-group">
                <a href="${contextPath}/register" class="btn btn-success">Зарегистрироваться</a>
                <a href="${contextPath}/login" class="btn btn-primary">Войти</a>
            </div>
        </#if>
    </div>
</div>

<div class="hero">
    <div class="hero-overlay">
        <h2>Добро пожаловать в МедПортал</h2>
        <p>Современная медицина для вашего здоровья</p>

        <a href="${contextPath}/doctors" class="btn btn-success">👨‍⚕️ Наши врачи 👩‍⚕️</a>

        <#if patient?? || doctor??>
            <#if patient??>
                <p style="margin-top: 15px;"><strong>Добро пожаловать, ${patient.name!''} ${patient.surname!''}!</strong></p>
            <#elseif doctor??>
                <p style="margin-top: 15px;"><strong>Добро пожаловать, ${doctor.name!''} ${doctor.surname!''}!</strong></p>
            </#if>
        </#if>
    </div>
</div>

<div class="content">
    <div class="section">
        <h2>О нашей клинике</h2>
        <p>Мы предоставляем качественные медицинские услуги с заботой о каждом пациенте.</p>
    </div>

    <div class="section">
        <h3>Наши услуги</h3>
        <ul>
            <li>Консультации специалистов</li>
            <li>Диагностика</li>
            <li>Стационарное лечение</li>
            <li>Хирургические операции</li>
            <li>Реабилитация</li>
        </ul>
    </div>

    <div class="section">
        <h3>Контакты</h3>
        <p>Адрес: г. Казань, ул. Сафиуллина, д. 12</p>
        <p>Телефон: +7 (777) 999-42-94</p>
        <p>Email: info@medportal.ru</p>
    </div>
</div>

<div class="footer">
    <p>© 2025 МедПортал. Все права защищены.</p>
</div>
</body>
</html>