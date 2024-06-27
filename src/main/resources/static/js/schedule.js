"use strict";

function smallAcademicTranscript() {
	var items = document.querySelectorAll('.academicTranscript .item');
    items.forEach(function(item) {
        item.classList.toggle('trans-none');
    });
}