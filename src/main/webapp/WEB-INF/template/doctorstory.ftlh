<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>История приёмов доктора</title>
    <link rel="stylesheet" href="${contextPath}/static/css/main.css">
    <script src="${contextPath}/static/js/form-manager.js"></script>
</head>
<body>
<div class="header">
    <div>
        <h1>История моих приёмов</h1>
    </div>
    <div>
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
    <#if appointments?? && appointments?size gt 0>
        <#list appointments as entity>
            <#assign appointment = entity.key>
            <#assign patient = entity.value>
            <div class="appointment-card">
                <div class="appointment-field">
                    <strong>Пациент:</strong> ${patient.name} ${patient.surname}
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
                <div class="appointment-field">
                    <strong>Рекомендации:</strong> ${appointment.recomFromDoc!''}
                </div>

                <#if appointment.documentPath?? && appointment.documentPath != ''>
                <div class="appointment-field">
                    <strong>Документ:</strong>
                    <a href="${contextPath}/download?file=${appointment.documentPath?replace('\\', '/')?url}">
                        Скачать документ
                    </a>
                </div>
                <#else>
                    <button type="button" class="btn btn-success"
                            onclick="showDocumentForm(${appointment.appId})">
                        ➕ Добавить документ
                    </button>

                    <div id="documentForm-${appointment.appId}" class="document-form">
                        <h4>Добавление документа</h4>
                        <form action="${contextPath}/doctormenu/ended" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="appointmentId" value="${appointment.appId}">
                            <input type="hidden" name="flag" value="false">
                            <div class="form-group">
                                <label>PDF документ:</label>
                                <input type="file" name="document" accept=".pdf">
                                <small>Только файлы в формате PDF (опционально)</small>
                            </div>
                            <div>
                                <button type="submit" class="btn btn-success">✅ Сохранить</button>
                                <button type="button" class="btn btn-info" onclick="hideDocumentForm(${appointment.appId})">Отмена</button>
                            </div>
                        </form>
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