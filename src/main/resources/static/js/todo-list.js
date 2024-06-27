document.querySelectorAll(".todo-priority").forEach(function (span) {
  if (span.textContent.trim() === "Medium") {
    span.classList.add("medium-priority");
  } else if (span.textContent.trim() === "High") {
    span.classList.add("high-priority");
  }
});

document.querySelectorAll(".todo-category").forEach(function (span) {
  if (span.textContent.trim() === "Other") {
    span.classList.add("other-category");
  } else if (span.textContent.trim() === "High") {
    span.classList.add("high-priority");
  }
});

const showPopup = document.querySelector(".show-edit");
const popupContainer = document.querySelector(".edit-container");
const close = document.querySelector(".close");
const popupBox = document.querySelector(".edit-modal");

popupBox.addEventListener("click", function (event) {
  // Ngăn chặn sự kiện click từ lan ra edit-container
  event.stopPropagation();
});

function showModal(itemTodo) {
  const rect = itemTodo.getBoundingClientRect();
  popupBox.style.top = `${rect.top + window.scrollY - 38}px`;
  popupBox.style.left = `${rect.right + 10}px`; // 10px để tạo khoảng cách giữa item-todo và edit-modal
  // popupBox.style.display = 'flex';
}

document.querySelectorAll(".show-edit").forEach((editButton) => {
  editButton.addEventListener("click", function () {
    popupContainer.classList.add("active");
    const itemTodo = this.closest(".item-todo");
    showModal(itemTodo);
  });
});

popupContainer.onclick = () => {
  popupContainer.classList.remove("active");
};

document.addEventListener("DOMContentLoaded", function () {
  const editButtons = document.querySelectorAll(".edit-item button");
  const editTitles = document.querySelectorAll(".edit-title");

  editButtons.forEach((button, index) => {
    button.addEventListener("click", function () {
      const editTitle = editTitles[index];

      // Kiểm tra trạng thái hiện tại của editTitle
      if (editTitle.style.display === "block") {
        editTitle.style.display = "none";
      } else {
        // Ẩn tất cả các edit-title khác
        editTitles.forEach((title) => (title.style.display = "none"));

        // Hiển thị edit-title tương ứng
        editTitle.style.display = "block";
      }
    });
  });

  // Thêm sự kiện click cho nút lưu trong mỗi edit-title
  editTitles.forEach((title) => {
    const saveButton = title.querySelector(".btn-primary");
    saveButton.addEventListener("click", function () {
      title.style.display = "none";
    });
  });
});

// JavaScript để hiển thị modal chỉnh sửa và lưu ID của todo
document.addEventListener("DOMContentLoaded", (event) => {
  var editButtons = document.querySelectorAll(".show-edit");

  var todoIdField = document.getElementById("todo-id");
  var userIdField = document.getElementById("user-id");

  var labelIdField = document.getElementById("label-id");
  var priorityIdField = document.getElementById("priority-id");
  var categoryIdField = document.getElementById("category-id");
  var dateIdField = document.getElementById("date-id");
  var statusIdField = document.getElementById("status-id");
  var deleteIdField = document.getElementById("delete-id");

  var userIdLabelField = document.getElementById("user-id-label");
  var userIdPriorityField = document.getElementById("user-id-priority");
  var userIdCategoryField = document.getElementById("user-id-category");
  var userIdDateField = document.getElementById("user-id-date");
  var userIdStatusField = document.getElementById("user-id-status");
  var userIdDeleteField = document.getElementById("user-id-delete");

  editButtons.forEach((button) => {
    button.addEventListener("click", function () {
      var todoId = this.getAttribute("data-id");
      var userId = this.getAttribute("data-user-id");

      todoIdField.value = todoId;
      userIdField.value = userId;

      labelIdField.value = todoId;
      priorityIdField.value = todoId;
      categoryIdField.value = todoId;
      dateIdField.value = todoId;
      statusIdField.value = todoId;
      deleteIdField.value = todoId;

      userIdLabelField.value = userId;
      userIdPriorityField.value = userId;
      userIdCategoryField.value = userId;
      userIdDateField.value = userId;
      userIdStatusField.value = userId;
      userIdDeleteField.value = userId;

      console.log("Editing todo with ID:", todoId);
      console.log("Editing userId with ID:", userId);
    });
  });
});
