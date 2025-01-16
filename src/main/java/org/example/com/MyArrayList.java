package org.example.com;

import java.util.Comparator;

public class MyArrayList<E> {

    private int arrayListLength; // длина списка
    private Object[] arrayList; // список
    private final Object[] emptyArrayList = {}; // пустой список


    /**
     * создает новый пустой список
     */
    public MyArrayList(){
        // конструктор создает пустой список
        arrayList = emptyArrayList;
        arrayListLength = 0;
    }

    /**
     * Добавляет элемент в конец списка
     * @param element Элемент, который будет добавлен
     */
    public void add(E element){
        int newArrayListLength = arrayListLength + 1; // длина будущего массива
        Object[] newArrayList = new Object[newArrayListLength]; // создаем новый массив с новой длиной

        if (arrayListLength >= 0) System.arraycopy(arrayList, 0, newArrayList, 0, arrayListLength); // копируем старый массив в новый

        newArrayList[arrayListLength] = element; // последний элемент заполняем переданным значением
        arrayList = newArrayList; // записываем этот новый массив в наш
        arrayListLength = newArrayListLength; // обновляем длину
    }

    /**
     * Возвращает значение по указанному индексу
     * @param index Индекс элемента
     * @return Значение по индексу
     * @throws IndexOutOfBoundsException В случае выхода за границы индекса
     */
    public E get(int index){
        if (index < 0 || index >= arrayListLength){ // проверка на границы индекса
            throw new IndexOutOfBoundsException();
        }
        return arrayList(index);
    }

    @SuppressWarnings("unchecked")
    private E arrayList(int index) {
        return (E) arrayList[index];
    }

    /**
     * Добавляет элемент по указанному индексу
     * @param index Индекс добавления
     * @param element Элемент
     * @throws IndexOutOfBoundsException В случае выхода за границы индекса
     */
    public void add(int index, E element){

        if (index >= arrayListLength || index < 0){ // следим за границами при добавлении по индексу
            throw new IndexOutOfBoundsException();
        }

        int newArrayListLength = arrayListLength + 1; // длина будущего массива
        Object[] newArrayList = new Object[newArrayListLength]; // создаем новый массив с новой длиной

        // копируем массив до индекса
        System.arraycopy(arrayList, 0, newArrayList, 0, index);

        // вставляем элемент
        newArrayList[index] = element;

        // копируем после индекса
        System.arraycopy(arrayList, index, newArrayList, index + 1, arrayListLength - index);

        arrayList = newArrayList; // обновляем наш массив
        arrayListLength = newArrayListLength; // и длину
    }

    /**
     * Добавляет переданный список в конец этого списка
     * @param arrayListForCopy Список, который будет добавлен в конец этого списка
     */
    public void add(MyArrayList<E> arrayListForCopy){

        int newArrayListLength = arrayListLength + arrayListForCopy.arrayListLength; // длина будущего массива
        Object[] newArrayList = new Object[newArrayListLength]; // новый массив

        // заполняем первую половину нового массива текущим массивом
        if (arrayListLength >= 0) System.arraycopy(arrayList, 0, newArrayList, 0, arrayListLength);

        // заполняем вторую половину нового массива полученным в аргументе массивом
        if (arrayListForCopy.arrayListLength >= 0)
            System.arraycopy(arrayListForCopy.arrayList, 0, newArrayList, arrayListLength, arrayListForCopy.arrayListLength);

        arrayList = newArrayList; // обновляем наш массив
        arrayListLength = newArrayListLength; // и длину

    }

    /**
     * Удаляет элемент по указанному индексу
     * @param index Индекс, по которому удалится элемент
     * @throws IndexOutOfBoundsException В случае выхода за границы индекса
     */
    public void remove(int index){

        if (index < 0 || index >= arrayListLength){ // следим за границами при удалении по индексу
            throw new IndexOutOfBoundsException();
        }

        int newArrayListLength = arrayListLength - 1; // длина нового массива

        Object[] newArrayList = new Object[newArrayListLength]; // новый массив

        // копируем элементы до удаляемого индекса
        System.arraycopy(arrayList, 0, newArrayList, 0, index);

        // копируем элементы после удаляемого индекса
        if (arrayListLength - (index + 1) >= 0)
            System.arraycopy(arrayList, index + 1, newArrayList, index + 1 - 1, arrayListLength - (index + 1));

        arrayList = newArrayList; // обновляем наш массив
        arrayListLength = newArrayListLength; // и длину
    }

    /**
     * Очищает список
     */
    public void clear(){
        arrayList = emptyArrayList;
        arrayListLength = 0;
    }

    /**
     * Выводит все элементы списка
     */
    public void printAll(){
        // выводит весь массив
        if (arrayListLength == 0){
            System.out.println("empty");
            return;
        }
        for (int i = 0; i < arrayListLength; i++) {
            System.out.print(arrayList[i] + " ");
        }
        System.out.println();
    }

    /**
     * Сортирует список алгоритмом быстрой сортировки
     * @param comparator контракт сравнения элементов типа Е
     */
    public void sort(Comparator<E> comparator){
        arrayList = quickSort(this, comparator).arrayList;
    }

    private MyArrayList<E> quickSort(MyArrayList<E> arr,Comparator<E> comparator){
        // реализовано как в книге Грокаем алгоритмы
        if (arr.arrayListLength < 2){
            return arr; // базовый случай - короткий список, который не надо сортировать
        }
        E pivot = arr.get(0); // опорный элемент сравнения
        MyArrayList<E> less = new MyArrayList<>();
        MyArrayList<E> more = new MyArrayList<>();

        for (int i = 1; i < arr.arrayListLength; i++) { // сравниваем каждый элемент списка с опорным, меньшие доавбляем в список less, большие в more
            if (comparator.compare(arr.get(i),pivot) < 0){
                less.add(arr.get(i));
            }else{
                more.add(arr.get(i));
            }
        }

        // результат собираем из отсортированного списка меньших элементов, базы и отсортированного списка больших
        MyArrayList<E> res = new MyArrayList<>();
        res.add(quickSort(less,comparator));
        res.add(pivot);
        res.add(quickSort(more,comparator));
        return res;
    }
}