<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>МедПортал - Изменение профиля врача</title>
    <link rel="stylesheet" href="${contextPath}/static/css/main.css">
</head>
<body>
<div class="header">
    <div>
        <h1>МедПортал - Изменение профиля врача</h1>
    </div>
    <div>
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
        <form action="${contextPath}/doctormenu/update" method="post">
            <div class="form-group">
                <label>Имя:</label>
                <input type="text" name="name" value="${doctor.name!''}" required>
            </div>

            <div class="form-group">
                <label>Фамилия:</label>
                <input type="text" name="surname" value="${doctor.surname!''}" required>
            </div>

            <div class="form-group">
                <label>Email:</label>
                <input type="email" name="email" value="${doctor.email!''}" required>
            </div>

            <div class="form-group">
                <label>Специализации:</label>
                <select name="specializations" multiple required style="width: 100%; height: 120px;">
                    <#list specializations as spec>
                        <option value="${spec.specId}"
                                <#if doctor?? && doctor.specializations??>
                                    <#list doctor.specializations as docSpec>
                                        <#if docSpec.specId == spec.specId>selected</#if>
                                    </#list>
                                </#if>
                        >${spec.name}</option>
                    </#list>
                </select>
                <small>Для выбора нескольких специализаций удерживайте Ctrl</small>
            </div>

            <div class="form-group">
                <label>Онлайн консультации:</label>
                <label class="radio-group">
                    <input type="radio" name="onlineConsultations" value="да"
                           <#if doctor.onlineConsultations?? && doctor.onlineConsultations>checked</#if>>
                    Да
                </label>
                <label class="radio-group">
                    <input type="radio" name="onlineConsultations" value="нет"
                           <#if !doctor.onlineConsultations?? || !doctor.onlineConsultations>checked</#if>>
                    Нет
                </label>
            </div>


            <div class="form-group">
                <label>О себе:</label>
                <textarea name="aboutMe" rows="4">${doctor.aboutMe!''}</textarea>
            </div>

            <div class="form-group">
                <label>Новый пароль:</label>
                <label>(или введите старый)</label>
                <input type="password" name="password" required>
            </div>

            <div class="btn-group">
                <button type="submit" class="btn btn-primary">Сохранить изменения</button>
                <a href="${contextPath}/doctormenu" class="btn btn-secondary">Отмена</a>
            </div>
        </form>
    </div>
</div>

<div class="footer">
    <p>© 2025 МедПортал. Все права защищены.</p>
</div>
</body>
</html>