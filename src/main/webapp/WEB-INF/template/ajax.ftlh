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