function ConfirmDelete() {
    const answer = confirm("Are you sure?")
    return answer
}
function updateEndDate() {
    const startDtValue = document.getElementById("startDate").value
    document.getElementById("endDate").setAttribute("value", startDtValue)
    document.getElementById("endDate").setAttribute("min", startDtValue)
}
function initialDatesBinding() {
    date = new Date();
    year = date.getFullYear();
    month = date.getMonth() + 1;
    day = date.getDate();
    if (month.toString().length == 1) {
        month = 0 + month.toString();
    }
    if (day.toString().length == 1) {
        day = 0 + day.toString();
    }
    const fullDate = year + "-" + month + "-" + day;
    const maxDate = year + 1 + "-" + month + "-" + day;
    document.getElementById("startDate").setAttribute("min", fullDate);
    document.getElementById("startDate").setAttribute("max", maxDate)
    document.getElementById("startDate").setAttribute("value", fullDate)
    document.getElementById("endDate").setAttribute("min", fullDate);
    document.getElementById("endDate").setAttribute("value", fullDate);
}