<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>МедПортал - Расписание врача</title>
    <link rel="stylesheet" href="${contextPath}/static/css/main.css">
</head>
<body>
<div class="header">
    <div>
        <h1>МедПортал - Расписание врача</h1>
    </div>
    <div>
        <a href="${contextPath}/main" class="btn btn-info">Главное меню</a>
        <a href="${contextPath}/patientmenu/reception" class="btn btn-info">Назад</a>
    </div>
</div>

<div class="content">
    <div class="section">
        <h2>Рабочие часы врача</h2>

        <#if appointments?? && appointments?size gt 0>
            <#list appointments as appointment>
                <form action="${contextPath}/patientmenu/book" method="get" class="form-group">
                    <input type="hidden" name="appointmentId" value="${appointment.appId!''}">
                    <button type="submit" class="appointment-item">
                        <strong>📅 Дата и время:</strong> ${appointment.scheduledTime!''}
                    </button>
                </form>
            </#list>
        <#else>
            <p>Рабочих часов не найдено</p>
        </#if>
    </div>
</div>

<div class="footer">
    <p>© 2025 МедПортал. Все права защищены.</p>
</div>
</body>
</html>