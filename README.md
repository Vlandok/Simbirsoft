# Программа обучения Android-разработчика
###### Туториал по изучению теоретических материалов:
Обозначение    | Уровень важности
--------|-------------------------------------------------------------------------------
**(\*\*\*\*)**   | Без изучения этого материала невозможно успешное прохождение темы
**(\*\*\*)**     | Материал, без которого сложно успешно завершить изучение темы
**(\*\*)**       | Важный материал, рекомендуемый к изучению
**(\*)**         | Полезная литература, улучшающая навыки
---

### Прохождение программы обучения
 Программа обучения разделена на секции. Каждая секция состоит из
 + Теоретической части;
 + Практической части;
 + Теста;

 Каждая секция начинается с выполнения практического задания. Теоретический материал изучается по мере необходимости для выполнения практики. После завершения практического задания необходимо в github создать merge request на ментора, чтобы он смог проверить задание. Если задание выполнено успешно, то ментор предоставляет тест по пройденной секции. Для успешного прохождения теста в большинстве случаев достаточно знаний, полученных в ходе выполнения практического задания и прочтения необходимой для него теории.

 Стоит отметить, что ментор в силу различных обстоятельств не всегда может оперативно проверять merge request'ы и предоставлять тесты. Поэтому, если ментор вам говорит, что сможет проверить задание/предоставить тест только через несколько часов - приступайте к выполнению следующей секции программы обучения.
 **Важно** одновременно непроверенным может быть не более одной секции программы обучения. То есть, чтобы приступить к 5ой секции, Ваше практическое задание по 3ей секции должно быть одобрено, а тест пройден.


 В случае возникновения вопросов во время выполнения практического задания, можно просить помощи у ментора. Однако не стоит подходить к ментору с недекомпозированной задачей из разряда "Я не понимаю, как сверстать экран". Декомпозируйте задачу, чтобы задать ментору более конкретный вопрос. Также не стоит сразу же спрашивать ментора, как только возникла трудность. Для начала попробуйте самостоятельно найти ответ на свой вопрос в интернете.

---
## I. Основные принципы разработки. Git. Flow проектов
---
### Теоретическая часть

**1. ООП:**
+ [Основные принципы](https://progstudy.ru/index.php/sm/article/ob-ektno-orientirovannoe-programmirovanie)  **(\*\*\*\*)**

**2. SOLID**
+ [Принципы SOLID](https://ru.wikipedia.org/wiki/SOLID_(%D0%BE%D0%B1%D1%8A%D0%B5%D0%BA%D1%82%D0%BD%D0%BE-%D0%BE%D1%80%D0%B8%D0%B5%D0%BD%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%BD%D0%BE%D0%B5_%D0%BF%D1%80%D0%BE%D0%B3%D1%80%D0%B0%D0%BC%D0%BC%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5))  **(\*\*\*)**
+ [SOLID в Java](http://blog.gauffin.org/2012/05/solid-principles-with-real-world-examples/) **(\*)**

**3. Работа с Git, gitflow**
+ [Основные команды](https://git-scm.com/book/ru/v2) : init, clone, add, status, stash, commit (-m, -am, --amend), fetch, pull, push, branch, checkout, merge **(\*\*\*\*)**
+ Что такое [git flow](https://habr.com/post/106912/) **(\*\*\*\)**
+ [Первоначальная настройка](https://git-scm.com/book/ru/v1/%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5-%D0%9F%D0%B5%D1%80%D0%B2%D0%BE%D0%BD%D0%B0%D1%87%D0%B0%D0%BB%D1%8C%D0%BD%D0%B0%D1%8F-%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0-Git): конфигурация username и email **(\*\*\*\)**

**4. Создание проекта, среда разработки Android Studio**
+ [Установка Android Studio] (https://developer.android.com/studio/preview/index.html) **(\*\*\*\*)**
+ [Создание нового проекта](https://developer.android.com/training/basics/firstapp/index.html) **(\*\*\*\*)**
+ [Основы интерфейса Android Studio](https://developer.android.com/studio/intro/index.html) **(\*\*\*\*)**

**5. Gradle**
+ [Gradle](https://developer.android.com/studio/build/index.html) **(\*\*\*\)**


### Практическое задание
1. В GitHub создать новый репозиторий и следуя инструкциям, склонировать его к себе на компьютер. В настройках репозитория дать доступ ментору.
2.  В глобальных конфигурациях git прописать корректное имя пользователя и e-mail, которые будут использоваться для подписи коммитов.
3. Добавить `.gitignore`. Содержание файла можно взять с ресурса: https://www.gitignore.io/api/androidstudio.  Cделать коммит и запушить изменения на remote-сервер в `master` ветку
4. Переключиться на новую ветку `develop`.
5. Создать новый android-проект (Phone and Tablet -> Empty Activity).
6. Добавить в gradle-файл библиотеку retrofit http://square.github.io/retrofit/
7. Запустить проект на телефоне/симуляторе
8. Cделать коммит и запушить изменения на remote-сервер в `develop` ветку
9. После завершения работ над задением отправить ментору количество затраченного времени на практическое выполнение задачи.

---
## II. Java. Часть 1
---
### Теоретическая часть

**Основы:**  
+ [Java-платформа] (https://docs.oracle.com/javase/tutorial/getStarted/intro/definition.html) **(\*\*)**
+ [Типы данных и переменные](https://metanit.com/java/tutorial/2.1.php) **(\*\*\*\*)**
+ [Преобразования базовых типов данных] (https://metanit.com/java/tutorial/2.2.php) **(\*\*\*\*)**
+ [Операции языка Java] (https://metanit.com/java/tutorial/2.3.php) **(\*\*\*\*)**
+ [Массивы] (https://metanit.com/java/tutorial/2.4.php) **(\*\*\*)**
+ [Условные конструкции] (https://metanit.com/java/tutorial/2.5.php) **(\*\*\*\*)**
+ [Циклы] (https://metanit.com/java/tutorial/2.6.php) **(\*\*\*\*)**
+ [Методы] (https://metanit.com/java/tutorial/2.7.php) **(\*\*\*\*)**
+ [Рекурсивные функции] (https://metanit.com/java/tutorial/2.8.php) **(\*\*\*)**
+ [Введение в обработку исключений] (https://metanit.com/java/tutorial/2.10.php) **(\*\*)**
+ [Java Code Conventions - Oracle] (http://www.oracle.com/technetwork/java/codeconventions-150003.pdf) **(\*\*\*\*)**


### Практическое задание
Все задачи должны быть реализованы в одном файле и разделены комментариями, указывающими на номер или текст задания.

***Важно! Весь код должен быть написан по предоставленному Java Code Conventions***
1. Определить две константы `a` и `b` типа `Double`, присвоить им любые значения. Вычислить среднее значение и сохранить результат в переменную `average`.
2. Создать класс, и задать два любых строковых значения с названиями `firstName` и `lastName`. Далее необходимо вывести в консоль строку в формате "Full name: [firstName] [lastName]".
3. Напишите программу для вывода первых 15 чисел последовательности Фибоначчи
4. Напишите программу для сортировки массива, использующую метод пузырька. Сотрировка должна происходить в отдельной функции, принимающей на вход исходный массив.
5. Напишите программу, решающую задачу: есть входящая строка формата "abc123", где сначала идет любая последовательность букв, потом число. Необходимо получить новую строку, в конце которой будет число на единицу больше предыдущего, то есть "abc124".
6. Завершить task и отправить ментору затраченное время

---
## III. Java. Часть 2
---
### Теоретическая часть

**1. Классы:**
+ [Классы и объекты] (https://metanit.com/java/tutorial/3.1.php)  **(\*\*\*\*)**
+ [Пакеты] (https://metanit.com/java/tutorial/3.2.php)  **(\*\*\*\*)**
+ [Модификаторы доступа и инкапсуляция] (https://metanit.com/java/tutorial/3.3.php)  **(\*\*\*\*)**
+ [Статические члены и модификатор static] (https://metanit.com/java/tutorial/3.4.php)  **(\*\*\*\*)**
+ [Объекты как параметры методов] (https://metanit.com/java/tutorial/3.14.php)  **(\*\*\*\*)**
+ [Наследование, полиморфизм и ключевое слово super] (https://metanit.com/java/tutorial/3.5.php)  **(\*\*\*\*)**
+ [Абстрактные классы] (https://metanit.com/java/tutorial/3.6.php)  **(\*\*\*\*)**
+ [Иерархия наследования и преобразование типов] (https://metanit.com/java/tutorial/3.10.php)  **(\*\*\*\*)**
+ [Внутренние классы] (https://metanit.com/java/tutorial/3.12.php)  **(\*\*\*\*)**
+ [Интерфейсы] (https://metanit.com/java/tutorial/3.7.php)  **(\*\*\*\*)**
+ [Интерфейсы в механизме обратного вызова] (https://metanit.com/java/tutorial/3.16.php)  **(\*\*\*)**
+ [Перечисления enum] (https://metanit.com/java/tutorial/3.8.php)  **(\*\*\*\*)**
+ [Класс Object и его методы] (https://habrahabr.ru/post/168195/)  **(\*\*\*)**
+ [Обобщенные типы и методы] (https://metanit.com/java/tutorial/3.11.php)  **(\*\*\*)**
+ [Наследование и обобщения] (https://metanit.com/java/tutorial/3.15.php)  **(\*\*\*)**
+ [Ссылочные типы и клонирование объектов] (https://metanit.com/java/tutorial/3.13.php) **(\*\*\*)**

**2. Обработка исключений:**  
+ [Оператор throws] (https://metanit.com/java/tutorial/4.1.php) **(\*\*\*\*)**
+ [Классы исключений] (https://metanit.com/java/tutorial/4.2.php) **(\*\*\*\*)**
+ [try-with-resources] (https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html) **(\*\*\*\*)**
+ [Создание своих классов исключений] (https://metanit.com/java/tutorial/4.3.php) **(\*\*)**

**3. Коллекции:**  
+ [Введение в коллекции в Java] (https://metanit.com/java/tutorial/5.1.php) **(\*\*\*\*)**
+ [Класс ArrayList и интерфейс List] (https://metanit.com/java/tutorial/5.2.php) **(\*\*\*\*)**
+ [Класс LinkedList] (https://metanit.com/java/tutorial/5.3.php) **(\*\*)**
+ [Класс HashSet] (https://metanit.com/java/tutorial/5.4.php) **(\*\*\*\*)**
+ [Класс TreeSet] (https://metanit.com/java/tutorial/5.5.php) **(\*\*\*)**
+ [Интерфейсы Comparable и Comporator. Сортировка] (https://metanit.com/java/tutorial/5.6.php) **(\*\*\**)**
+ [Очереди и класс ArrayDeque] (https://metanit.com/java/tutorial/5.7.php) **(\*\*)**
+ [Отображения и класс HashMap] (https://metanit.com/java/tutorial/5.8.php) **(\*\*\*\*)**
+ [Класс TreeMap] (https://metanit.com/java/tutorial/5.9.php) **(\*\*)**
+ [Итераторы] (https://metanit.com/java/tutorial/5.10.php) **(\*\*\)**

**4. Работа со строками:**  
+ [Введение в строки. Класс String] (https://metanit.com/java/tutorial/7.1.php) **(\*\*\*\*)**
+ [Основные операции со строками] (https://metanit.com/java/tutorial/7.2.php) **(\*\*\*\*)**
+ [StringBuffer и StringBuilder] (https://metanit.com/java/tutorial/7.3.php) **(\*\*\*)**
+ [Регулярные выражения] (https://metanit.com/java/tutorial/7.4.php) **(\*\*)**

**5. Лямбда-выражения:**  
+ [Введение в лямбда-выражения] (https://metanit.com/java/tutorial/9.1.php) **(\*\*\*\*)**
+ [Лямбды как параметры методов и ссылки на методы] (https://metanit.com/java/tutorial/9.2.php) **(\*\*\*\*)**
+ [Встроенные функциональные интерфейсы] (https://metanit.com/java/tutorial/9.3.php) **(\*\*)**
  

### Практическое задание
Все задачи должны быть реализованы в одном файле и разделены комментариями, указывающими на номер или текст задания.

**Важно! Весь код должен быть написан по предоставленному Java Code Conventions**
1. Настроить проект для java 8 https://developer.android.com/guide/platform/j8-jack.html?hl=ru
2. Написать простое лямба-выражение в переменной `myClosure`, лямба-выражение должно выводить в консоль фразу "I love Java". Вызвать это лямба-выражение. Далее написать функцию, которая будет запускать заданное лямба-выражение заданное количество раз. Объявить функцию так: `public void repeatTask (int times, Runnable task)`. Функция должна запускать `times` раз лямба-выражение `task` . Используйте эту функцию для печати «I love Java 10 раз.
3. Условия: есть начальная позиция на двумерной плоскости, можно осуществлять последовательность шагов по четырем направлениям up, down, left, right. Размерность каждого шага равна 1. Задание: 
  1. Создать enum `Directions` с возможными направлениями движения
  2. Создать метод, принимающий координаты и одно из направлений и возвращающий координату после перехода по направлению
  3. Создать метод, осуществлящий несколько переходов от первоначальной координаты и выводящий координату после каждого перехода. Для этого внутри метода следует определить переменную `location` с начальными координатами (0,0) и  массив направлений, содержащий элементы [up, up, left, down, left, down, down, right, right, down, right], и програмно вычислить какие будут координаты у переменной `location` после выполнения этой последовательности шагов. Для вычисленеия результата каждого перемещения следует использовать созданный ранее метод перемещения на один шаг.
4. Создать интерфейс Shape с двумя методами perimeter и area, выводящими периметр и площадь фигуры соответственно, после чего реализовать и использовать для вывода периметра и площади следующие классы, реализующие интерфейс Shape:
  1. `Rectangle` - прямоугольник с двумя свойствами: ширина и длина
  2. `Square` - квадрат с одним свойством: длина стороны
  3. `Circle` - круг с одним свойством: диаметр круга
5. Завершить task и отправить ментору затраченное время

 ---
## IV. Верстка
---
### Теоретическая часть

В случае если по ссылке встречается пошаговый гайд - рекомендуется его выполнить в отдельном проекте.

**1. Начало разработки под Android**
+ [Начало разаработки](https://developer.android.com/training/index.html) **(\*\*\*\*)**

**2. Верстка**
+ [Уроки верстки из курсов](http://startandroid.ru/ru/uroki/vse-uroki-spiskom.html) **(\*\*\)**
+ [Создание макетов в XML и View groups](https://developer.android.com/guide/topics/ui/declaring-layout.html) **(\*\*\*)**

**3. Типы layout'ов**
+ [Frame Layout](http://developer.alexanderklimov.ru/android/layout/framelayout.php) **(\*\*\)**
+ [Linear Layout](https://developer.android.com/guide/topics/ui/layout/linear.html) **(\*\*\)**
+ [Relative Layout](https://developer.android.com/guide/topics/ui/layout/relative.html) **(\*\*\)**

**4. Табы**
+ [Обзор](https://developer.android.com/training/implementing-navigation/lateral.html) **(\*\*\*)**

**5. BottomNavigationView**
+ [Обзор](https://developer.android.com/reference/android/support/design/widget/BottomNavigationView.html) **(\*\*\*)**

**6. Constraint Layout**
+ [Документация](https://developer.android.com/reference/android/support/constraint/ConstraintLayout.html) **(\*\*\*\*)**
+ [Работа с различными свойствами](https://habrahabr.ru/company/touchinstinct/blog/326814/) **(\*\*\*\*)**

**7. Списки**
+ [ListView](http://developer.alexanderklimov.ru/android/views/listview.php) **(\*\*\)**
+ [RecyclerView и Adapter](https://developer.android.com/training/material/lists-cards.html) **(\*\*\)**
+ [DiffUtils](https://medium.com/@iammert/using-diffutil-in-android-recyclerview-bdca8e4fbb00) **(\*\*\)**

**8. Ресурсы**
+ [Шрифты в XML](https://developer.android.com/guide/topics/ui/look-and-feel/fonts-in-xml.html) **(\*\*\)**
+ [Загружаемые шрифты](https://developer.android.com/guide/topics/ui/look-and-feel/downloadable-fonts.html) **(\*\*\)**
+ [Поддержка разных экранов](https://developer.android.com/guide/practices/screens_support.html) **(\*\*\)**
+ [Zeplin](https://habrahabr.ru/company/uteam/blog/315542/) **(\*\*\)**

**Важно** В компании при разработке любого мобильного приложения считается правилом хорошего тона придерживаться нефункциональных требований, описанных в [данной статье](http://kb.simbirsoft/nonfunctional-support/)

### Практическое задание
Работа должна производится в созданном ранее проекте.

Все изменения должны быть закоммичены, а названия коммитов должны коротко и исчерпывающе описывать содержащие изменения. Каждый коммит должен быть рабочим, отправка некомпилирующегося кода недопустима. Для работы над этим заданием необходимо переключится на ветку `layouts` и все изменения пушить в нее. После завершения работы над задачей в gitlab необходимо создать merge request в ветку `develop`.
Код должен быть читабельным и написан согласно code-style. В системе PS также необходимо создать созвучную задачу, в которую после завершения будет залогировано время.

1. Добавить иконки приложения и сделать на экране отображаемое название приложения "Хочу помочь". Ресурсы иконок [тут](https://zpl.io/2jkoMOp).
2. Реализовать Splash Screen согласно [макету](https://zpl.io/2jlk3Mm).
3. Создать экран "Категории помощи" приложения согласно [макету](https://zpl.io/b6lYE9d).
 - Экран "Категории помощи" должен быть показан после Splash Screen. По нажатию по стрелки назад, приложение закрывается.
 - Необходимо реализовать нижний элемент навигации с помощью самописного решения согласно макету (только верстку без реализации логики навигации)
 - Экран должен представлять из себя activity с `RecyclerView`.
 - Верстка должна быть реализована в xml.
 - Верстка должна быть выполнена с учетом "pixel perfect" - когда все элементы дизайна расположены и имеют размеры абсолютно идентичные макету для экрана с теми же размерами что и макет и адекватно масштабироваться для других размеров и разрешений.
 - Все переиспользуемые размеры в xml должны быть вынесены в dimes, цвета в colors, а строки в strings.
 - Никаких "магических чисел", все должно иметь понятные названия
4. После завершения работ над задением отправить ментору количество затраченного времени на практическое выполнение задачи.

---
## V. Android OS. Activity. Fragments
---
### Теоретическая часть

**1. Android OS:** 
+ [История Android](https://www.android.com/history/#/marshmallow) **(\*\*)**
+ [Архитектура Android](https://source.android.com/devices/architecture/) **(\*\*)**

**2. Application:**  
+ [Application](https://developer.android.com/reference/android/app/Application.html)  **(\*\*\*)**
+ [Context](https://possiblemobile.com/2013/06/context/)  **(\*\*\*)**
+ [Файл Manifest](https://developer.android.com/guide/topics/manifest/manifest-intro.html) **(\*\*\*\*)**

**3. Activity:**  
+ [Activity - основы](https://developer.android.com/guide/components/activities.html) **(\*\*\*\*)**
+ [Task и Back Stack](https://habrahabr.ru/post/186434/) **(\*\*)**
+ [Передача данных между Activity](https://developer.android.com/guide/components/activities/parcelables-and-bundles.html) **(\*\*\*\*)**
+ [Управление жизненным циклом Activity](https://developer.android.com/training/basics/activity-lifecycle/index.html) **(\*\*\*\*)**

**4. Fragment:**  
+ [Fragment - основы](https://developer.android.com/guide/components/fragments.html) **(\*\*\*\*)**
+ [Диалоговые окна](https://developer.android.com/guide/topics/ui/dialogs.html) **(\*\*\*\*)**
+ [Обработка изменений конфигурации экрана](https://developer.android.com/guide/topics/resources/runtime-changes.html?hl=ru) **(\*\*\*\*)**
+ [Target fragment](https://habrahabr.ru/post/259805/) **(\*\*)**


**5. Работа со сторонними приложениями и permissions:**  
+ [Run-time permissions](https://developer.android.com/training/permissions/requesting.html)**(\*\*\*\*)**
+ [Intent и фильтры](https://developer.android.com/guide/components/intents-filters.html?hl=ru)**(\*\*\*\*)**
+ [Взаимодействие с другими приложениями](https://developer.android.com/training/basics/intents/index.html )**(\*\*\*)**

**6. BroadcastReceiver:**  
+ [BroadcastReceiver - основы](http://codetheory.in/android-broadcast-receivers/) **(\*\*\*\*)**
+ [Изменения работы с BroadcastReceiver с Android 8.0](https://developer.android.com/guide/components/broadcast-exceptions.html) **(\*\*)**

### Практическое задание
Работа должна производится в созданном ранее проекте.

Все изменения должны быть закоммичены, а названия коммитов должны коротко и исчерпывающе описывать содержащие изменения. Каждый коммит должен быть рабочим, отправка некомпилирующегося кода недопустима. Для работы над этим заданием необходимо переключится на ветку `fragments` и все изменения пушить в нее. После завершения работы над задачей в gitlab необходимо создать merge request в ветку `develop`.
Код должен быть читабельным и написан согласно code-style.

1. Реализовать экран профиль согласно [макету](https://zpl.io/b6lQpZq)
2. Реализовать диалог согласно [макету](https://zpl.io/brkmRYX)
 - Диалог должен открываться при нажатии на изображение пользователя на экране профиля 
3. Реализовать экран поиска согласно [макету](https://zpl.io/bAGAPj8)
 - Экран должен быть построен с использованием `ViewPager` и фрагментов
 - Должна быть реализована возможность изменять выбранную вкладку перелистыванием с плавной анимацией. 
 - В качестве названий для результатов должны использоваться произвольные случайные строки 
 - Данные для отображения результата должны генерироваться случайным образом при каждом перелистывании `ViewPager`. 
4. После завершения работ над задением отправить ментору количество затраченного времени на практическое выполнение задачи.

---
## VI. Структуры данных. Работа с файлами
---
### Теоретическая часть

**1. Работа с файлами в java. Сериализация:**  
+ [Потоки ввода-вывода](https://metanit.com/java/tutorial/6.1.php)  **(\*\*\*\*)**
+ [Закрытие потоков](https://metanit.com/java/tutorial/6.2.php)  **(\*\*\*\*)**
+ [Чтение и запись файлов. FileInputStream и FileOutputStream](https://metanit.com/java/tutorial/6.3.php)  **(\*\*\*\*)**
+ [Классы ByteArrayInputStream и ByteArrayOutputStream](https://metanit.com/java/tutorial/6.4.php)  **(\*\*)**
+ [Буферизуемые потоки. Классы BufferedInputStream и BufferedOuputStream](https://metanit.com/java/tutorial/6.5.php)  **(\*\*\*\*)**
+ [Чтение и запись текстовых файлов. FileReader и FileWriter](https://metanit.com/java/tutorial/6.8.php)  **(\*\*)**
+ [Буферизируемые символьные потоки. BufferedReader и BufferedWriter](https://metanit.com/java/tutorial/6.9.php)  **(\*\*)**
+ [Сериализация объектов](https://metanit.com/java/tutorial/6.10.php)  **(\*\*)**
+ [Класс File. Работа с файлами и каталогами](https://metanit.com/java/tutorial/6.11.php)  **(\*\*\*\*)**
+ [Работа с ZIP-архивами](https://metanit.com/java/tutorial/6.12.php)  **(\*\*)**

**2. JSON:**
+ [Понятие](https://ru.wikipedia.org/wiki/JSON)  **(\*\*\*\*)**
+ [Парсинг в Android](https://metanit.com/java/android/13.3.php)  **(\*\*\*)**
+ [Gson](https://habrahabr.ru/company/naumen/blog/228279/) **(\*\*\*\*)**

**3. Сохранение данных в файловую систему Android:**  
+ [SharedPreferences](https://developer.android.com/training/basics/data-storage/shared-preferences.html?hl=ru#GetSharedPreferences) **(\*\*\*\*)**
+ [Настройки через Preferences](https://developer.android.com/guide/topics/ui/settings.html?hl=ru) **(\*\*)**
+ [Android data storage](https://developer.android.com/training/basics/data-storage/files.html) **(\*\*\*\*)**
+ [FileProvider](https://developer.android.com/reference/android/support/v4/content/FileProvider.html) **(\*\*\*)**

**3. Работа с датой и временем:**  
+ [Date, Calendar](http://developer.alexanderklimov.ru/android/java/date.php) **(\*\*\*\*)**
+ [Date в Java 8](http://www.baeldung.com/java-8-date-time-intro) **(\*\*\*\*)**
+ [Работа со временем в java ](https://habrahabr.ru/post/274811/) **(\*\*)**

### Практическое задание
Работа должна производится в созданном ранее проекте.

Все изменения должны быть закоммичены, а названия коммитов должны коротко и исчерпывающе описывать содержащие изменения. Каждый коммит должен быть рабочим, отправка некомпилирующегося кода недопустима. Для работы над этим заданием необходимо переключится на ветку `data_structures` и все изменения пушить в нее. После завершения работы над задачей в gitlab необходимо создать merge request в ветку `develop`.
Код должен быть читабельным и написан согласно code-style. Верстка экранов должна быть выполнена по принципу pixel-perfect.

1. Подключить к проекту [ThreeTenABP](https://github.com/JakeWharton/ThreeTenABP). Все операции с датой и временем должны быть реализованы через классы данной библиотеки.
2. Сверстать экран "Благотворительных событий" согласно [макету](https://zpl.io/brkm3we). Переход на этот экран осуществляется при выборе любой категории на экране "Категории помощи".
3. Сверстать экран "Детальное описание события" согласно [макету](https://zpl.io/adA93Z5). Переход на этот экран осуществляется при выборе любого события из списка, данный экран должен получить информацию о том, какое событие было выбрано на предыдущем шаге.
4. Необходимо создать два json'а. Содержащих в себе массивы категорий и благотворительных событий. Информация об объектах должна быть достаточной для формирования отображений на экранах, а также для корректного разделения по категориям помощи. Каждый объект должен обладать уникальным (среди объектов своего типа) идентификатором. Проверить корректность созданных json-ов через [online-parser](http://json.parser.online.fr/). Записать их в 2 файла и поместить в папку assets проекта.
5. Необходимо создать сущности соответствующие понятиям Категория и Событие.
6. Создать класс, который будет читать созданные json из файлов, парсить их и преобразовывать в массивы.
7. Наполнить экраны полученными данными. В сервисе учесть возможность фильтрации по категориям.
8. После завершения работ над задением отправить ментору количество затраченного времени на практическое выполнение задачи.

---
## VII. Многопоточность
---
### Теоретическая часть

**1. Базовые понятия:**
+ [Определение](https://ru.wikipedia.org/wiki/%D0%9C%D0%BD%D0%BE%D0%B3%D0%BE%D0%BF%D0%BE%D1%82%D0%BE%D1%87%D0%BD%D0%BE%D1%81%D1%82%D1%8C)  **(\*\*\*\*)**
+ [Мьютекс](https://ru.wikipedia.org/wiki/%D0%9C%D1%8C%D1%8E%D1%82%D0%B5%D0%BA%D1%81)  **(\*\*\)**
+ [Семафор](https://ru.wikipedia.org/wiki/%D0%A1%D0%B5%D0%BC%D0%B0%D1%84%D0%BE%D1%80_(%D0%B8%D0%BD%D1%84%D0%BE%D1%80%D0%BC%D0%B0%D1%82%D0%B8%D0%BA%D0%B0))  **(\*\*\)**
+ [Дэдлок](https://ru.wikipedia.org/wiki/Deadlock)  **(\*\*\*\)**
+ [Starvation and Livelock] (https://docs.oracle.com/javase/tutorial/essential/concurrency/starvelive.html)  **(\*\*\)**

**2. Многопоточность в java:**
+ [Thread](https://habrahabr.ru/post/164487/) **(\*\*\*\*)**
+ [Синхронизация потоков. Оператор synchronized](https://metanit.com/java/tutorial/8.3.php) **(\*\*\*\*\)**
+ [Синхронизированные коллекции](https://habrahabr.ru/company/luxoft/blog/157273/)  **(\*\*\*)**
+ [Executors](http://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/) **(\*\*\*\*)**

**3. Фоновая работа в Android:**
+ [Looper, Handler, and HandlerThread](https://blog.mindorks.com/android-core-looper-handler-and-handlerthread-bd54d69fe91a). [Видео](https://www.youtube.com/watch?v=gDvjU8HSuYE)  **(\*\*\*)**
+ [Loader](https://habrahabr.ru/company/e-Legion/blog/265405/) **(\*\*)**
+ [AsyncTask](https://developer.android.com/reference/android/os/AsyncTask.html)  **(\*\*\*)**
		
**4. Service:**
+ [Service - основы](https://developer.android.com/guide/components/services.html)  **(\*\*\*\*)**
+ [IntentService](http://developer.alexanderklimov.ru/android/theory/intentservice.php)  **(\*\*\*\*)**
+ [Job Scheduler](http://ticketmastermobilestudio.com/blog/how-to-use-androids-job-scheduler) **(\*\*\*\*)**
+ [Background Execution Limits Android 8.0+](https://developer.android.com/about/versions/oreo/background.html) **(\*\*)**


### Практическое задание
Работа должна производится в созданном ранее проекте.

Все изменения должны быть закоммичены, а названия коммитов должны коротко и исчерпывающе описывать содержащие изменения. Каждый коммит должен быть рабочим, отправка некомпилирующегося кода недопустима. Для работы над этим заданием необходимо переключится на ветку `concurrency` и все изменения пушить в нее. После завершения работы над задачей в gitlab необходимо создать merge request в ветку `develop`.
Код должен быть читабельным и написан согласно code-style.

1. В рамках предыдущего задания было реализовано чтение из файла. Перенести чтение из файла, парсинг, фильтрацию в background-поток. При повороте экрана не должно происходить повторное чтение из файла.
2. Реализовать Activity Indicator на экранах Категорий и Событий. Индикатор должен показываться с момента запроса данных до момента их отображения на экране. **Внимание!** Все действия c UI должны совершаться в главном потоке. 
3. После завершения работ над задением отправить ментору количество затраченного времени на практическое выполнение задачи.

---
## VIII. Базы данных. Content Provider
---
### Теоретическая часть

**1. Android SQLite:**
+ [Работа с SQLite средствами Android SDK](https://developer.android.com/training/data-storage/sqlite.html)  **(\*\*\)**
+ [ORM](https://habrahabr.ru/company/yotadevices/blog/242559/)  **(\*\*\)**

**2. Основы Realm:**
+ [Документация по Realm](https://realm.io/docs/java/latest/)  **(\*\*\*\*)**
+ [DatabaseProvider в androidbase](http://gitlab.simbirsoft/mobile/AndroidBase/blob/master/app/src/main/java/com/simbirsoft/baseplatform/data/db/DatabaseProvider.java)  **(\*\*)**

**3. Content Provider:**
+ [Основы](https://developer.android.com/guide/topics/providers/content-provider-basics.html?hl=ru) **(\*\*)**

### Практическое задание
Работа должна производится в созданном ранее проекте.

Все изменения должны быть закоммичены, а названия коммитов должны коротко и исчерпывающе описывать содержащие изменения. Каждый коммит должен быть рабочим, отправка некомпилирующегося кода недопустима. Для работы над этим заданием необходимо переключится на ветку `data_base` и все изменения пушить в нее. После завершения работы над задачей в gitlab необходимо создать merge request в ветку `develop`.
Код должен быть читабельным и написан согласно code-style. В системе PS также необходимо создать созвучную задачу, в которую после завершения будет залогировано время.

1. Необходимо создать базу данных с использованием Realm для хранения сущностей Категории и Благотворительных событий.
2. Реализовать на старте приложения чтение из файла и сохранение в БД.
 - Все "тяжелые" операции долны быть реализованы в фоновом потоке
 - На время выполнения фоновых операций пользователю должен быть показан Activity Indicator
3. Наполнение экранов Категории и Благотворительных событий должно происходить из созданной базы данных
 - Все "тяжелые" операции долны быть реализованы в фоновом потоке
 - На время выполнения фоновых операций пользователю должен быть показан Activity Indicator
4. После завершения работ над задением отправить ментору количество затраченного времени на практическое выполнение задачи.

---
## IX. Работа с сетью
---
### Теоретическая часть
**1. Базовые понятия:**
+ [HTTP](https://ru.wikipedia.org/wiki/HTTP) **(\*\*\*)**
+ [HTTP codes](https://ru.wikipedia.org/wiki/Список_кодов_состояния_HTTP/) **(\*\*\*)**
+ [RESTful](https://habrahabr.ru/company/hexlet/blog/274675/) **(\*\*\*)**
+ [RESTful API — ложь](https://habrahabr.ru/post/265845/) **(\*)**
+ [WebSocket](https://stfalcon.com/ru/blog/post/android-websocket) **(\*\*)**

**2. Библиотеки:**
+ [OkHttp](http://square.github.io/okhttp/) **(\*\*\*\*)**
+ [Retrofit](http://square.github.io/retrofit/) **(\*\*\*\*)**
+ [OkHttp. Interceptors](https://github.com/square/okhttp/wiki/Interceptors) **(\*\*\*)**
+ [OkHttp which ignores all SSL errors](https://gist.github.com/chalup/8706740) **(\*\*\*)**

**3. Firebase:**
+ [Документация к проекту](https://firebase.google.com/docs/database/)  **(\*\*)**

**4. Отладка. Перехват и подмена трафика мобильных устройств:**
+ [Fiddler](https://learn.javascript.ru/fiddler)  **(\*\*)**
+ [Postman](https://habr.com/ru/company/kolesa/blog/351250/)  **(\*\*)**
+ [Charles](http://wormiks.ru/faq_po_programmam_wormix/11-charles_instrukcija_polzovatelja_i_faq.html)  **(\*\*)**

### Практическое задание
Работа должна производится в созданном ранее проекте.

Все изменения должны быть закоммичены, а названия коммитов должны коротко и исчерпывающе описывать содержащие изменения. Каждый коммит должен быть рабочим, отправка некомпилирующегося кода недопустима. Для работы над этим заданием необходимо переключится на ветку `networking
` и все изменения пушить в нее. После завершения работы над задачей в gitlab необходимо создать merge request в ветку `develop`.
Код должен быть читабельным и написан согласно code-style.

1. Необходимо реализовать транспортный слой приложения, который будет осуществалять загрузку данных с сервера. Для этого требуется использовать firebase. Документация по работе запросов [тут](https://firebase.google.com/docs/android/setup).
 - Все "тяжелые" операции долны быть реализованы в фоновом потоке
 - На время выполнения фоновых операций пользователю должен быть показан Activity Indicator
2. Заменить загрузку из файла на старте приложения на загрузку с сервера. В случае если ответ от сервера ошибочен - загрузить данные из файла.
 - Все "тяжелые" операции долны быть реализованы в фоновом потоке
 - На время выполнения фоновых операций пользователю должен быть показан Activity Indicator
