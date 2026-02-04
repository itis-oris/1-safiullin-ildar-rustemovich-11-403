<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Управление специализациями</title>
    <link rel="stylesheet" href="${contextPath}/static/css/main.css">
    <script src="${contextPath}/static/js/form-manager.js"></script>
</head>
<body>
<div class="header">
    <div>
        <h1>Управление специализациями</h1>
    </div>
    <div class="user-menu">
        <a href="${contextPath}/main" class="btn btn-info">Главное меню</a>
        <a href="${contextPath}/logout" class="btn btn-danger">Выйти</a>
    </div>
</div>

<div class="content">
    <div class="btn-group">
        <button onclick="showDocumentForm('add')" class="btn btn-success">➕ Добавить специализацию</button>
    </div>

    <div id="documentForm-add" class="document-form">
        <form action="${contextPath}/admin/add" method="post">
            <div class="form-group">
                <label for="newName">Название специализации:</label>
                <input type="text" id="newName" name="name" required
                       placeholder="Введите название специализации">
            </div>

            <div class="form-group">
                <label for="newDescription">Описание:</label>
                <textarea id="newDescription" name="description" rows="4"
                          placeholder="Введите описание специализации"></textarea>
            </div>

            <div class="btn-group">
                <button type="submit" class="btn btn-success">💾 Сохранить</button>
                <button type="button" onclick="hideDocumentForm('add')" class="btn btn-secondary">❌ Отмена</button>
            </div>
        </form>
    </div>

    <div class="section">
        <h2>Список специализаций</h2>

        <#if specializations?? && specializations?size gt 0>
            <#list specializations as spec>
                <div class="profile-card">
                    <div class="profile-field">
                        <strong>Название:</strong> ${spec.name!''}
                    </div>
                    <div class="profile-field">
                        <strong>Описание:</strong> ${spec.description!''}
                    </div>

                    <div class="btn-group">
                        <button onclick="showDocumentForm('edit-${spec.specId}')"
                                class="btn btn-warning btn-small">✏️ Изменить</button>

                        <form action="${contextPath}/admin/delete" method="post" style="display: inline;">
                            <input type="hidden" name="specId" value="${spec.specId}">
                            <button type="submit" class="btn btn-danger btn-small">🗑️ Удалить</button>
                        </form>
                    </div>
                </div>

                <div id="documentForm-edit-${spec.specId}" class="document-form">
                    <form action="${contextPath}/admin/update" method="post">
                        <input type="hidden" name="specId" value="${spec.specId}">

                        <div class="form-group">
                            <label for="name-${spec.specId}">Название специализации:</label>
                            <input type="text" id="name-${spec.specId}" name="name" required
                                   value="${spec.name!''}"
                                   placeholder="Введите название специализации">
                        </div>

                        <div class="form-group">
                            <label for="description-${spec.specId}">Описание:</label>
                            <textarea id="description-${spec.specId}" name="description" rows="4"
                                      placeholder="Введите описание специализации">${spec.description!''}</textarea>
                        </div>

                        <div class="btn-group">
                            <button type="submit" class="btn btn-success">💾 Сохранить изменения</button>
                            <button type="button" onclick="hideDocumentForm('edit-${spec.specId}')"
                                    class="btn btn-secondary">❌ Отмена</button>
                        </div>
                    </form>
                </div>
            </#list>
        <#else>
            <p>Нет специализаций</p>
        </#if>
    </div>
</div>

<div class="footer">
    <p>© 2025 МедПортал. Административная панель</p>
</div>
</body>
</html>