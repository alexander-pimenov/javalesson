<!--Пока к travis и codecov, решил не подключаться. Сделаю это позже.-->
<!--[![Build Status](https://travis-ci.org/alexander-pimenov/job4j.svg?branch=master)](https://travis-ci.org/alexander-pimenov/job4j)
    [![codecov](https://codecov.io/gh/alexander-pimenov/job4j/branch/master/graph/badge.svg)](https://codecov.io/gh/alexander-pimenov/job4j)
-->
<!--Пример подключения к travis и codecov от Владимира Жданова-->
<!--[![Build Status](https://travis-ci.org/VladimirZhdanov/job4j.svg?branch=master)](https://travis-ci.org/VladimirZhdanov/job4j)-->
<!--[![codecov](https://codecov.io/gh/VladimirZhdanov/job4j/branch/master/graph/badge.svg)](https://codecov.io/gh/VladimirZhdanov/job4j)-->

## Репозиторий Александра Пименова.
Репозиторий Александра Пименова [javalessons][].

Разбираем примеры и уроки по Java. Некоторые из книг, некоторые из видеоуроков.

>Ниже находятся наиболее интересные проекты, которые я разбирал для лучшего обучения.


- [x] this is a complete item
- [ ] this is an incomplete item
- [x] @mentions, #refs, [links][],
**formatting**, and <del>tags</del> ~~ddd~~
supported
- [x] list syntax required (any
unordered or ordered list
supported)

<!-- Пример создания таблицы -->
First Header | Second Header
------------ | -------------
Content cell 1 | Content cell 2
Content column 1 | Content column 2

## Topic and Queue JMS
Simple implementation async 
- point-to-point (queue)  
- provider-subscriber (topic)


### Helper recourses:
- [5 Minutes or Less: ActiveMQ with JMS Queues and Topics](https://www.tomitribe.com/blog/5-minutes-or-less-activemq-with-jms-queues-and-topics/)
- [JMS: Введение в технологию и установка ActiveMQ сервера.](https://onedeveloper.javadev.ru/article%3Fid=12.html)

#### Список продуктов (пример маркерных вложенных списков):
- овощи
  - картошка
  - морковь
  - лук
- мясо
  - свиниа
    - карбонад
    - окорок
  - говядина
  - баранина
- рыбные продукты
  - консервы рыбные
  - свежая рыба
    - окунь
    - камбала
    - горбуша
- молочные продукты



### Double Calendar Props

- **start_date** `[date YYYY-MM-DD]`
  - The date to start the selection on for the calendar
- **end_date** `[date YYYY-MM-DD]`
  - The date to end the selection on for the calendar
- **same_day_range** `[boolean]`
  - Allow a range selection of a single day
- **format** `[preset key in format object] // see above`
  - The double calendar adds the `preset` key to the format object for formatting the preset dates in the preset dropdown
- **presets** `[boolean] or [object]`
  - If you don't want to show the preset link just set this to `false` otherwise the default is true which will just give you a basic preset of.. yep.. presets. BOOM!
  - Otherwise, if you want to customize it up you can include an array of preset objects. Something like:
  ```js
  presets: [
    {
      label: "Last month",
      start: moment()
        .subtract(1, "month")
        .startOf("month"),
      end: moment()
        .subtract(1, "month")
        .endOf("month")
    },
    {
      label: "Last year",
      start: moment()
        .subtract(1, "year")
        .startOf("year"),
      end: moment()
        .subtract(1, "year")
        .endOf("year")
    }
  ];
  ```
  
  ```java
  public String getName() {
          return name;
      }
  ```
  **REMEMBER _elementName_ is the only required prop and it should be different for each datepicker in your component**
  
  ```html
  <template>
    <div id="app">
      <Calendar
        @rangeEdit="processOutput"
        type="double"
        elementName="otherRangePicker"
      />
  
      <Calendar
        @dateEdit="processOutput"
        type="single"
        elementName="primaryRangePicker"
      />
    </div>
  </template>
  
  <script>
    import Calendar from "./components/Calendar";
    export default {
      components: {
        Calendar
      },
      methods: {
        processOutput(output) {
          console.log(output);
        }
      }
    };
  </script>
  ```
  
  



[]: https://github.com/alexander-pimenov/javalesson