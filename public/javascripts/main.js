function showAnswer(id) {
    var answer = document.getElementById(id);
    answer.style.display = "block";
}
function checkAnswer(n){
    let realAnswers = document.getElementsByClassName("quiz__answer-reveal");
    let answers = document.getElementsByClassName("quiz__answer");

    let realAnswer = realAnswers[n].innerHTML;
    let answer = answers[n].value;

    console.log(realAnswer);

    if(realAnswer===answer){
        answers[n].style.color = "#285F17";
        answers[n].style.background = "#CEEDD0";
    }else{
        answers[n].style.color = "#9C312D";
        answers[n].style.background = "#F6C9CE";
    }
}

let currentTab = 0;
let tabs = document.getElementsByClassName("tab");
showTab(currentTab)
function showTab(n) {
    // This function will display the specified tab of the form ...
    tabs[n].style.display = "block";
    // this.fixStepIndicator(n);
    if (n === this.tabs.length - 1) {
        document.getElementById("nextBtn").innerHTML = "Home";
    } else {
        document.getElementById("nextBtn").innerHTML = "Next Question";
    }

}
function nextPrev(n) {
    // This function will figure out which tab to display
    // Exit the function if any field in the current tab is invalid:
    // if (n == 1 && !validateForm()) return false;
    // Hide the current tab:
    tabs[currentTab].style.display = "none";
    // Increase or decrease the current tab by 1:
    currentTab = currentTab + n;
    // if you have reached the end of the form... :
    if (currentTab >= tabs.length) {
        //...the form gets submitted:
        // document.getElementById("nextBtn").outerHTML = <a href="/"></a>
        return false;
    }
    // Otherwise, display the correct tab:
    showTab(currentTab);
}
function answer(){
    checkAnswer(currentTab);
}

