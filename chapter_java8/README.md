# java8
Java 8 features

Differences between Java 8 Map() Vs flatMap() :

map() | flatMap() | 
--- | --- |  
It processes stream of values. | It processes stream of stream of values. 
Он обрабатывает поток значений. | Он обрабатывает поток значений. 
It does only mapping. | It performs mapping as well as flattening.
Он выполняет только сопоставление. | Он выполняет как отображение, так и сглаживание.
It’s mapper function produces single value for each input value. | It’s mapper function produces multiple values for each input value. 
Его функция отображения выдает одно значение для каждого входного значения. | Его функция отображения выдает несколько значений для каждого входного значения.
It is a One-To-One mapping. | It is a One-To-Many mapping. 
Это сопоставление "Один к одному". | Это сопоставление "Один ко многим".
Data Transformation : From Stream<T> to Stream<R> | Data Transformation : From Stream<Stream<T> to Stream<R> 
Преобразование данных: Из Stream<T> в Stream<R> | Преобразование данных: Из Stream<Stream<T> в Stream<R>
Use this method when the mapper function is producing a single value for each input value. | Use this method when the mapper function is producing multiple values for each input value. 
Используйте этот метод, когда функция отображения выдает одно значение для каждого входного значения. | Используйте этот метод, когда функция отображения выдает несколько значений для каждого входного значения. 

