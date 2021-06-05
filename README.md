# SPBU Homework. Programming course 3. Kotlin.

* [Programmin course 1. C](https://github.com/EmirVildanov/BasicProgramming) 
* [Programming course 2. Kotlin](https://github.com/EmirVildanov/BasicProgramming2) 
* You are here
* [Programming course 4. Python](https://github.com/EmirVildanov/BasicProgramming4)


### Homework №1
1. Смоделировать работу локальной сети: <br/>
a) в сети есть несколько компьютеров, связанных друг с другом (каким образом - можно задавать, например, матрицей смежности); <br/>
b) на каждом компе стоит ОС (Windows, Linux, etc...); <br/>
c) в сети гуляют вирусы, так что для каждой машины есть ненулевая вероятность заразиться (вероятность зависит от типа ОС), заражаются компьютеры, непосредственно соединённые с заражёнными; <br/>
d) заражения (и проверки, заразился компьютер или нет) происходят дискретно - по ходам. <br/>
Требуется периодически выводить состояние сети. Нужны тесты, проверяющие правильность процесса заражения.

### Homework №2
1. Нарисовать диаграмму классов для игры "реверси", с несколькими видами "ботов", которые могли бы играть вместо человека. Диаграмма должна быть довольно подробным проектом системы. Реализовывать этот проект не нужно.

2. Нарисовать диаграмму состояний для класса `MicrowaveOven`, реализующего ПО СВЧ-печи.

### Homework №3
1. Есть автостоянка с ограниченным количеством мест, на стоянку могут заезжать машины и со стоянки могут уезжать машины. Надо реализовать класс, который бы сообщал машине при въезде, есть ли свободные места, и регистрировал уезжающие машины. На парковку есть несколько въездов, на каждом из которых планируется поставить такой автомат и сделать так, чтобы автоматы синхронизировались друг с другом. Реализация должна быть lock-free.

### Homework №4
1. Реализовать Android-приложение "Калькулятор". Пользователь должен вводить выражение, которое состоит из вещественных чисел, операций "+-*/" и скобочек. По нажатию на равно должно показываться значение выражения. [[Link]](https://github.com/EmirVildanov/AndroidCalculator)

### Homework №7
1. Разработайте простое web-приложение с использованием Ktor. Выберите тему на свой вкус, например, показ случайной цитаты с баша. Приложение должно быть простым, не более 5 роутингов. После его реализации, упакуйте исходный код в контейнер. При этом, при запуске контейнера, должно запускаться само приложение. <br/> <br/>
Прикрепите Dockerfile к pull request. В качестве 1 слоя контейнера используйте alpine. Для определения "точки запуска" рекомендуется установить `ENTRYPOINT [/bin/bash]` и `CMD [<args>]`, где `args` — команды для запуска вашего сервера. Более подробно можно почитать, например, [тут](https://www.ctl.io/developers/blog/post/dockerfile-entrypoint-vs-cmd). <br/> <br/>
[[Link]](https://github.com/EmirVildanov/KtorDockerProject)
