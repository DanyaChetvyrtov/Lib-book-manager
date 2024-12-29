# Book Manager
Практический мини-проект для закрепления материала по spring MVC

---

## Проделанная работа
* Настройка проекта и DispatcherServlet'а через java-классы и аннотации
* Взаимодействие с бд(использовалась PostgreSQL) через Spring Data JPA
* Реализованы CRUD операции для моделей person, book и author
* Базовая валидация форм с использованием элементарных регулярных выражений.(дата, номер, ФИО)
* Возможность назначить книгу за читателем и вернуть её
* Пагинация + сортировка
* Обработка ошибок с использованием ControllerAdvice

## Структура БД

![](https://drive.google.com/uc?export=view&id=1YtIs0LOfOiPJhv1FFlXaXzNWeNifRsoB)

## Главная страница

![](https://drive.google.com/uc?export=view&id=1YbPur-oca4B6DojxPf3rmMByVjJzOlg2)

## Валидация

<div align="center">
  <a href="https://github.com/JosManoel">
    <img src="https://drive.google.com/uc?export=view&id=12fUbAgvds99vKpcrpCnObKsaK3Yh4Nz0" width="412px"/> 
  </a>
  <a href="https://github.com/JosManoel">
    <img src="https://drive.google.com/uc?export=view&id=108IeGkRecGN1rZLPlp8N6KZDNKX5y5ul" width="412px"/>
  </a>
</div>

## Список всех доступных книг

![](https://drive.google.com/uc?export=view&id=1fFMMkaCrK3Y-KOpxsYUsMXtxIr88X_t4)

## Взаимодействие с книгой
В приложении есть возможность закрепить книгу за определённым читателем. 
После чего соответствующая информация будет выводится как на странице книге, так и на странице
самого читателя.

<div align="center">
  <a href="https://github.com/JosManoel">
    <img src="https://drive.google.com/uc?export=view&id=17GpWQpWsf0jNM7qYZ8r5V4pOyKVWTE1w" width="412px"/> 
  </a>
  <a href="https://github.com/JosManoel">
    <img src="https://drive.google.com/uc?export=view&id=1gyPgdRTVW8cV4CLB-NsgkpGcNJFYeP-8" width="412px"/>
  </a>
</div>

### Отображение закреплённых за читателем книг

![](https://drive.google.com/uc?export=view&id=1eturDlGTwa4O6cUDbPFe83-gyXxngkcO)
