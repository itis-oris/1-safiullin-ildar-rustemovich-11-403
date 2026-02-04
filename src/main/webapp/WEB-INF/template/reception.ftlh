<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>МедПортал - Выбор врача</title>
    <link rel="stylesheet" href="${contextPath}/static/css/main.css">
</head>
<body>
<div class="header">
    <div>
        <h1>МедПортал - Выбор врача</h1>
    </div>
    <div>
        <a href="${contextPath}/main" class="btn btn-info">Главное меню</a>
        <a href="${contextPath}/patientmenu" class="btn btn-info">Назад</a>
    </div>
</div>

<div class="content">
    <div class="section">
        <h2>Выберите врача</h2>

        <div class="form-group">
            <label for="doctorSelect">Врач:</label>
            <select id="doctorSelect" name="doctorId" required>
                <option value="">-- Выберите врача --</option>
                <#list doctors as doctor>
                    <option value="${doctor.id}">
                        ${doctor.surname} ${doctor.name} -
                        <#if doctor.specializations?? && doctor.specializations?size gt 0>
                            <#list doctor.specializations as spec>${spec.name}<#if spec_has_next>, </#if></#list>
                        </#if>
                    </option>
                </#list>
            </select>
        </div>

        <div id="scheduleContainer" style="display: none;">
            <h3>Расписание врача</h3>
            <div id="appointmentsList"></div>
        </div>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('doctorSelect').addEventListener('change', function() {
            var doctorId = this.value;

            if (doctorId) {
                document.getElementById('appointmentsList').innerHTML = '<p>Загрузка расписания...</p>';
                document.getElementById('scheduleContainer').style.display = 'block';

                fetch('${contextPath}/patientmenu/reception', {
                    method: 'POST',
                    body: 'doctorId=' + encodeURIComponent(doctorId),
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                })
                    .then(response => response.text())
                    .then(html => {
                        document.getElementById('appointmentsList').innerHTML = html;
                    })
                    .catch(error => {
                        document.getElementById('appointmentsList').innerHTML = '<p>Ошибка загрузки расписания</p>';
                    });
            } else {
                document.getElementById('scheduleContainer').style.display = 'none';
            }
        });
    });
</script>

<div class="footer">
    <p>© 2025 МедПортал. Все права защищены.</p>
</div>
</body>
</html>