"use strict";

/* === easy alert listener === */
function showToast(toastId, toastContent, message) {
	let e = document.getElementById(toastId);
	let c = document.getElementById(toastContent);
	let toast = bootstrap.Toast.getOrCreateInstance(e);
	c.innerHTML = message;
	toast.show();
};

(function() {
	let delResponseInput = document.getElementById("delResponse");
	if (delResponseInput != null) {
		let delResponse = delResponseInput.value;
		if (delResponse != '') {
			if (delResponse == 'true') {
				showToast('deleteToast', 'deleteToastContent', 'Delete job successfully!');
			} else {
				showToast('deleteToast', 'deleteToastContent', 'Delete job failed!');
			}
		}
	}
	
	
	let addResponseInput = document.getElementById("addResponse");
	if (addResponseInput != null) {
		let addResponse = addResponseInput.value;
		if (addResponse!='') {
			if (addResponse=='true'){
				showToast('addNewToast', 'addToastContent', 'Add new job successfully!');
			} else {
				showToast('addNewToast', 'addToastContent', 'Add new job failed!');
			}
		}
	}
	
	let editResponseInput = document.getElementById("editResponse");
	if (editResponseInput != null) {
		let editResponse = editResponseInput.value;
		if (editResponse!='') {
			if(editResponse=='true') {
				showToast('editToast', 'editToastContent', 'Edit job successfully!');
			} else {
				showToast('editToast', 'editToastContent', 'Edit job failed!');
			}
		}
	}
	
})();

let currentId;
function deleteJob(id) {
	let deleteModal = new bootstrap.Modal('#deleteModal', {
		keyboard: false
	});
	deleteModal.show();
	currentId = id;
}
async function confirmDel() {

	await fetch('/admin/job/del?id='+currentId, {
		method: 'POST'
	}).then(response => {
		if (response.ok) {
			setTimeout(()=> {
				location.reload();
			},2000);
			
			showToast('deleteToast', 'deleteToastContent', 'Delete job successfully!');
		} else {
			showToast('deleteToast', 'deleteToastContent', 'Delete job failed!');
		}
	})
}

let form;

function preaddJob(fn) {
	let addModal = new bootstrap.Modal('#addModal', {
		keyboard: false
	});
	if (fn.checkValidity()) {
		fn.classList.add('was-validated');
		addModal.show();
	} else {
		fn.classList.add('was-validated');
	}
	
	form = fn;
}

async function addJob() {

	if (form) {
		form.action = "/admin/job/add";
		form.method = "POST";
		form.submit();
	}
}
function preeditJob1(fn) {
	let editModal = new bootstrap.Modal('#editModal', {
		keyboard: false
	});
	if (fn.checkValidity()) {
		fn.classList.add('was-validated');
		editModal.show();
	} else {
		fn.classList.add('was-validated');
	}
	
	form = fn;
}
function preeditJob2(fn) {
	let editModal = new bootstrap.Modal('#editModal', {
		keyboard: false
	});
	if (fn.checkValidity()) {
		fn.classList.add('was-validated');
		editModal.show();
	} else {
		fn.classList.add('was-validated');
	}
	
	form = fn;
}
function editJob() {
	if (form) {
		form.action = "/admin/job/edit";
		form.method = "POST";
		form.submit();
	}
}

function previewImage(event) {
	const input = event.target;
	const reader = new FileReader();
	reader.onload = function() {
		const dataURL = reader.result;
		const output = document.getElementById('jobImageSrc');
		output.src = dataURL;
		};
	reader.readAsDataURL(input.files[0]);
}

function removeImage() {
	const output = document.getElementById('jobImageSrc');
	output.src = "/admin/img/job.jpg";
	document.getElementById('jobImg').value = "";
}