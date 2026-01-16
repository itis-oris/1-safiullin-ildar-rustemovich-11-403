<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>МедПортал - Все приёмы</title>
    <link rel="stylesheet" href="${contextPath}/static/css/main.css">
    <script src="${contextPath}/static/js/form-manager.js"></script>
</head>
<body>
<div class="header">
    <div>
        <h1>МедПортал - Все приёмы</h1>
    </div>
    <div>
        <a href="${contextPath}/doctormenu/addworkinghour" class="btn btn-success">➕ Добавить рабочий час</a>
        <a href="${contextPath}/main" class="btn btn-info">Главное меню</a>
        <a href="${contextPath}/doctormenu" class="btn btn-info">Назад</a>
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
        <h2>Список приёмов</h2>


        <#if appointmentMap?? && appointmentMap?size gt 0>
            <#list appointmentMap as appointment, patient>
                <div class="appointment-card">
                    <div class="appointment-field">
                        <strong>Дата и время:</strong> ${appointment.scheduledTime!''}
                    </div>
                    <div class="appointment-field">
                        <strong>Статус:</strong>
                        <span class="status-${appointment.status!'pending'}">${appointment.status!'pending'}</span>
                    </div>

                    <#if patient??>
                        <div class="appointment-field">
                            <strong>Пациент:</strong> ${patient.name!''} ${patient.surname!''}
                        </div>
                        <div class="appointment-field">
                            <strong>Email пациента:</strong> ${patient.email!''}
                        </div>
                        <div class="appointment-field">
                            <strong>Жалобы пациента:</strong> ${appointment.complaints!''}
                        </div>
                        <button type="button" class="btn btn-success"
                            onclick="showEndForm(${appointment.appId})">
                            ✅ Завершить приём
                        </button>

                        <div id="endForm-${appointment.appId}" class="document-form">
                            <h4>Завершение приёма</h4>
                            <form action="${contextPath}/doctormenu/ended" method="post" enctype="multipart/form-data">
                                <input type="hidden" name="appointmentId" value="${appointment.appId}">

                                <div class="form-group">
                                    <label>Рекомендации:</label>
                                    <textarea name="recomFromDoc" rows="3" placeholder="Назначения и рекомендации..."></textarea>
                                </div>

                                <div class="form-group">
                                    <label>PDF документ:</label>
                                    <input type="file" name="document" accept=".pdf">
                                    <small>Только файлы в формате PDF (опционально)</small>
                                </div>

                                <div>
                                    <button type="submit" class="btn btn-success">✅ Подтвердить</button>
                                    <button tbtnype="button" class="btn btn-secondary" onclick="hideEndForm(${appointment.appId})">Отмена</button>
                                </div>
                            </form>
                        </div>
                    <#else>
                        <div class="appointment-field">
                            <strong>Пациент:</strong> Не назначен
                        </div>
                    </#if>
                </div>
            </#list>
        <#else>
            <p>Приёмов не найдено</p>
        </#if>
    </div>
</div>

<div class="footer">
    <p>© 2025 МедПортал. Все права защищены.</p>
</div>
</body>
</html>
