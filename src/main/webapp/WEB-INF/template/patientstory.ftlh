<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>История приёмов</title>
    <link rel="stylesheet" href="${contextPath}/static/css/main.css">

</head>
<body>
<div class="header">
    <div>
        <h1>История моих приёмов</h1>
    </div>
    <div>
        <a href="${contextPath}/main" class="btn btn-info">Главное меню</a>
        <a href="${contextPath}/patientmenu" class="btn btn-info">Назад</a>
    </div>
</div>

<div class="content">
    <#if appointments?? && appointments?size gt 0>
        <#list appointments as entity>
            <#assign appointment = entity.key>
            <#assign doctor = entity.value>
            <div class="appointment-card">
                <div class="appointment-field">
                    <strong>Доктор:</strong> ${doctor.name} ${doctor.surname}
                </div>
                <div class="appointment-field">
                    <strong>Дата и время:</strong> ${appointment.scheduledTime!''}
                </div>
                <div class="appointment-field">
                    <strong>Жалобы:</strong> ${appointment.complaints!''}
                </div>
                <div class="appointment-field">
                    <strong>Статус:</strong> ${appointment.status!''}
                </div>
                <#if appointment.status != 'запланирован'>
                    <div class="appointment-field">
                        <strong>Рекомендации врача:</strong> ${appointment.recomFromDoc!''}
                    </div>
                </#if>
                <#if appointment.documentPath?? && appointment.documentPath != ''>
                    <div class="appointment-field">
                        <strong>Документ:</strong>
                        <a href="${contextPath}/download?file=${appointment.documentPath?replace('\\', '/')?url}">
                            Скачать документ
                        </a>
                    </div>
                </#if>
            </div>
        </#list>
    <#else>
        <p>У вас пока нет завершённых приёмов</p>
    </#if>
</div>

<div class="footer">
    <p>© 2025 МедПортал. Все права защищены.</p>
</div>
</body>
</html>