
//Switch Theme


function toggleDarkTheme(){
    document.getElementById("menu").classList.toggle("md-dark");
    document.getElementById("dark_icon").classList.toggle("md-dark");

    document.body.classList.toggle("dark-theme");
    document.getElementById("menu").classList.toggle("md-light");
    document.getElementById("dark_icon").classList.toggle("md-light");
}
function toggleLightTheme(){
    document.getElementById("menu").classList.toggle("md-light");
    document.getElementById("dark_icon").classList.toggle("md-light");

    document.body.classList.toggle("light-theme");
    document.getElementById("menu").classList.toggle("md-dark");
    document.getElementById("dark_icon").classList.toggle("md-dark");
}
