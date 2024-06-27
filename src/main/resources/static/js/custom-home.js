"use strict";

const account = document.querySelector('.account');
const accountBtn = document.querySelector('.account-btn');
(function() {
    /* Show account toggle */
    accountBtn.addEventListener('click', function(event) {
        event.preventDefault();
        account.classList.toggle('show');
    });

    /* Hide account menu when clicking outside */
    document.addEventListener('click', function(event) {
    // Check if the click is outside the account menu and the account button
        if (!account.contains(event.target) && !accountBtn.contains(event.target)) {
        account.classList.remove('show');
        }
    });
})();