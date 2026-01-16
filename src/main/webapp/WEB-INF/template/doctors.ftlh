<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>МедПортал - Все врачи</title>
    <link rel="stylesheet" href="${contextPath}/static/css/main.css">
</head>
<body>
<div class="header">
    <div>
        <h1>МедПортал - Наши врачи</h1>
    </div>
    <div>
        <a href="${contextPath}/main" class="btn btn-primary">Главное меню</a>
    </div>
</div>

<div class="content">
    <div class="section">
        <h2>Список врачей</h2>

        <#if doctors?? && doctors?size gt 0>
            <#list doctors as doctor>
                <div class="doctor-card">
                    <div class="doctor-field">
                        <strong>${doctor.name!''} ${doctor.surname!''}</strong>
                    </div>
                    <div class="doctor-field">
                        <strong>Специализации:</strong>
                        <#if doctor.specializations?? && doctor.specializations?size gt 0>
                            <#list doctor.specializations as spec>${spec.name}<#if spec_has_next>, </#if></#list>
                        <#else>
                            Не указано
                        </#if>
                    </div>
                    <div class="doctor-field">
                        <strong>Контакты:</strong> ${doctor.email!''}
                    </div>
                    <div class="doctor-field">
                        <strong>Онлайн консультации:</strong>
                        <#if doctor.onlineConsultations?? && doctor.onlineConsultations>
                            <span class="online-badge">Доступны</span>
                        <#else>
                            <span class="offline-badge">Не доступны</span>
                        </#if>
                    </div>
                    <div class="doctor-field">
                        <strong>О себе:</strong> ${doctor.aboutMe!''}
                    </div>
                </div>
            </#list>
        <#else>
            <p>Врачи не найдены</p>
        </#if>
    </div>
</div>

<div class="footer">
    <p>© 2025 МедПортал. Все права защищены.</p>
</div>
</body>
</html>