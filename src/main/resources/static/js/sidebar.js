document.addEventListener("DOMContentLoaded", function() {
    const sidebar = document.querySelector(".sidebar");
    const content = document.querySelector(".content");
    const toggleButton = document.querySelector(".toggle-sidebar");

    toggleButton.addEventListener("click", function() {
        sidebar.classList.toggle("hidden");
        content.classList.toggle("shifted");
    });
});