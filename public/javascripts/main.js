function showAnswer(id) {
    var answer = document.getElementById(id);
    answer.style.display = "block";
}

function nextQuestion(){
    return false;
}

function checkAnswer(id){
    var realAnswer = document.getElementById(id).innerHTML;
    var answer = document.getElementById("text-"+id).value;
    if(realAnswer===answer){
        alert("Correct");
    }else{
        alert("False");
    }
}
let currentTab = 0;
let tabs = document.getElementsByClassName("tab");
showTab(currentTab)
function showTab(n) {
    // This function will display the specified tab of the form ...
    tabs[n].style.display = "block";
    // this.fixStepIndicator(n);
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
        document.getElementById("nextBtn").type = "submit";
        return false;
    }
    // Otherwise, display the correct tab:
    showTab(currentTab);
}



