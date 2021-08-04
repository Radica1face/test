                var countOfFields = 2; // Текущее число полей

                function deleteField(a) {
                  if (countOfFields > 2)
                  {
                 // Получаем доступ к ДИВу, содержащему поле
                 var contDiv = a.parentNode;
                 // Удаляем этот ДИВ из DOM-дерева
                 contDiv.parentNode.removeChild(contDiv);
                 // Уменьшаем значение текущего числа полей
                 countOfFields--;
                 }
                 // Возвращаем false, чтобы не было перехода по сслыке
                 return false;
                }
                function addField() {
                 // Увеличиваем текущее значение числа полей
                 countOfFields++;
                 // Создаем элемент ДИВ
                 var div = document.createElement("div");
                 // Добавляем HTML-контент с помощью свойства innerHTML
                 div.innerHTML = "<div class=\"form-group\"><input name=\"answerOption\" type=\"text\" class=\"form-control\"/><a style=\"color:red;size=.3em\" onclick=\"return deleteField(this)\" href=\"#\">Удалить</a></div>";
                 // Добавляем новый узел в конец списка полей
                 document.getElementById("parentId").appendChild(div);
                 // Возвращаем false, чтобы не было перехода по сслыке
                 return false;
                }