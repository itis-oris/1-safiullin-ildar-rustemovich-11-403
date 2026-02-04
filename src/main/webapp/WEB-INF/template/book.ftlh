<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>МедПортал - Запись на приём</title>
    <link rel="stylesheet" href="${contextPath}/static/css/main.css">
</head>
<body>
<div class="header">
    <div>
        <h1>МедПортал - Запись на приём</h1>
    </div>
    <div>
        <a href="${contextPath}/patientmenu/reception" class="btn btn-secondary">Назад</a>
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
        <h2>Заполните данные для записи</h2>

        <#if appointmentId??>

            <form action="${contextPath}/patientmenu/book" method="post">
                <input type="hidden" name="appointmentId" value="${appointmentId!''}">

                <div class="form-group">
                    <label for="complaints">Жалобы и симптомы:</label>
                    <textarea id="complaints" name="complaints"
                              placeholder="Опишите ваши жалобы, симптомы, историю заболевания..."
                              required></textarea>
                </div>

                <div>
                    <button type="submit" class="btn btn-success">✅ Подтвердить запись</button>
                    <a href="${contextPath}/patientmenu/reception" class="btn btn-secondary">Отмена</a>
                </div>
            </form>
        <#else>
            <p>Ошибка: время приёма не выбрано</p>
            <a href="${contextPath}/doctor/reception" class="btn btn-secondary">Вернуться к расписанию</a>
        </#if>
    </div>
</div>

<div class="footer">
    <p>© 2025 МедПортал. Все права защищены.</p>
</div>
</body>
</html>