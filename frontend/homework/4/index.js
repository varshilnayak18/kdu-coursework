const button = document.getElementById("btn");

button.addEventListener("click", addTask);

function addTask() {
  const div = document.createElement("div");
  div.classList.add('list-task');

  const checkbox = document.createElement("input");
  checkbox.type = "checkbox";
  checkbox.classList.add('checkbox');

  const del = document.createElement("button");
  del.innerText = "Delete";
  del.classList.add('btn');

  const edit = document.createElement("button");
  edit.innerText = "Edit";
  edit.classList.add('btn');

  const span = document.createElement("span");
  span.innerText = document.getElementById("input").value;

  if (span.textContent === "") {

  } else {
    div.append(checkbox);
    div.append(span);
    div.append(edit);
    div.append(del);
    div.classList.add('list-item');

    del.addEventListener("click", (e) => {
      e.target.parentElement.remove(div);
    });

    edit.addEventListener("click", (e) => {
        const text = span.textContent;

        const newText = document.createElement("input");
        newText.type = "text";
        newText.value = text;

        div.replaceChild(newText, span);
        newText.addEventListener("keyup", (e) => {
            if (e.key === "Enter") {
                span.textContent = newText.value;
                div.replaceChild(span, newText);
            }
        });
    })

    const parentDiv = document.getElementById("list-tasks");
    parentDiv.appendChild(div);
  }
}
