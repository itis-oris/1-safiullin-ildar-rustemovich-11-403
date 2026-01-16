function showDocumentForm(appointmentId) {
    document.querySelectorAll('.document-form').forEach(form => {
        form.style.display = 'none';
    });
    document.getElementById('documentForm-' + appointmentId).style.display = 'block';
}

function hideDocumentForm(appointmentId) {
    document.getElementById('documentForm-' + appointmentId).style.display = 'none';
}

function showEndForm(appointmentId) {
    document.querySelectorAll('.end-form').forEach(form => {
        form.style.display = 'none';
    });
    document.getElementById('endForm-' + appointmentId).style.display = 'block';
}

function hideEndForm(appointmentId) {
    document.getElementById('endForm-' + appointmentId).style.display = 'none';
}