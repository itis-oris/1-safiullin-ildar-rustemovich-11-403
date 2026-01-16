<!DOCTYPE html>
<html>
<head>
    <title>Регистрация врача - MedPortal</title>
    <link rel="stylesheet" href="${contextPath}/static/css/main.css">
</head>
<body>
<div class="header">
    <div>
        <h1>МедПортал - Регистрация врача</h1>
    </div>
    <div>
        <a href="${contextPath}/main" class="btn btn-secondary">На главную</a>
    </div>
</div>

<div class="content">
    <div class="section">
        <div style="margin-top: 20px;">
            <#if error??>
                <div style="color: red;">
                    ${error}
                </div>
            </#if>
        </div>

        <form action="${contextPath}/register/doctor" method="post">
            <div class="form-group">
                <label>Имя:</label>
                <input type="text" name="name" required>
            </div>

            <div class="form-group">
                <label>Фамилия:</label>
                <input type="text" name="surname" required>
            </div>

            <div class="form-group">
                <label>Email:</label>
                <input type="email" name="email" required>
            </div>

            <div class="form-group">
                <label>Пароль:</label>
                <input type="password" name="password" required>
            </div>

            <div class="form-group">
                <label>Специализации:</label>
                <select name="specializations" multiple required style="width: 100%; height: 120px;">
                    <#list specializations as spec>
                        <option value="${spec.specId}">${spec.name}</option>
                    </#list>
                </select>
                <small>Для выбора нескольких специализаций удерживайте Ctrl </small>
            </div>

            <div class="form-group">
                <label>О себе:</label>
                <textarea name="aboutMe" rows="4"></textarea>
            </div>

            <div class="form-group">
                <label>Онлайн консультации:</label>
                <select name="onlineConsultations">
                    <option value="да">Да</option>
                    <option value="нет">Нет</option>
                </select>
            </div>

            <div class="btn-group">
                <button type="submit" class="btn btn-success">Зарегистрироваться</button>
                <a href="${contextPath}/main" class="btn btn-secondary">Отмена</a>
            </div>
        </form>
    </div>
</div>

<div class="footer">
    <p>© 2025 МедПортал. Все права защищены.</p>
</div>
</body>
</html>