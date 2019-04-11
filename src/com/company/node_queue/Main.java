package com.company.node_queue;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

        Queue<String> queue = new Queue<>();
        queue.addByNewStyle("Peter");
        queue.addByNewStyle("Moshe");
        queue.addByNewStyle("Anna");
        queue.addByNewStyle("Sarah");
        queue.addByNewStyle("Jacob");

        System.out.println(queue.toString());

        System.out.println(" ------- ");
        for (String name : queue) {
            System.out.println(name);
        }

        System.out.println();
        System.out.println(" ------- ");
        System.out.println();

        Person peter = new Person("peter");
        Person moshe = new Person("moshe");
        Person anna = new Person("anna");
        Person sarah = new Person("sarah");

        Queue<Person> people = new Queue<>();
        people.addMultiple(peter, moshe, anna, sarah);

        for (Person person : people) {
            System.out.println(person);
        }

//        m1();
        System.out.println();
        System.out.println();
        System.out.println();
//        varArgs("word", "word2", "word3");
    }

//    static void m1(String [] params) { }

//    static void varArgs(String...params) { //var args = array (dynamic method param)
//        for (Object p : params) {
//            System.out.println(p);
//        }
//    }
}

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Queue<T> implements Iterable<T> {
    private Node<T> first = null;
    private Node<T> last;

    //Peter -> Moshe -> Anna
    public void add(T data) { //Moshe
        if (first == null) { //Peter

            first = new Node<>();
            first.data = data;
            last = first;
        } else {

            Node<T> newNode = new Node<>();
            newNode.data = data; //Sarah -> null

            //Peter -> Moshe
            //Moshe -> Anna
            //Anna -> Sarah
            final Node previous = last; //last == previous | Anna

            //anna.connected = Sarah
            previous.connectedNode = newNode;

            //last = anna
            last = newNode;

            //first - last
            //peter

            //previous = peter
            //peter.connected = moshe
        }
    }

    //Peter -> moshe -> anna
    public void addByNewStyle(T data) { //anna
        final Node<T> previous = last; //moshe

        Node<T> newInfo = new Node<>(); //anna
        newInfo.data = data;

        last = newInfo; //last = anna

        if (previous == null) {
            first = newInfo; //first = peter
        } else {
            //moshe.connected = anna
            previous.connectedNode = newInfo;
        }
    }

    public void addMultiple(T...data) {
        for (T param: data) {
            addByNewStyle(param);
        }
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        //peter -> moshe -> anna -> null
        Node<T> current = first;

        builder.append(current.data); //peter

        while (current.connectedNode != null) { //peter -> moshe -> anna
            current = current.connectedNode; //current = moshe
            builder.append(" -> ");
            builder.append(current.data);   //append (moshe)
        }
        return builder.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator<>(first);
    }

    public class QueueIterator<T> implements Iterator<T> {
        //Peter -> null
        private Node<T> first;

        public QueueIterator(Node<T> first) {
            this.first = first;
        }

        @Override
        public boolean hasNext() {

            boolean noData = first == null;

            return !noData;
        }

        @Override
        public T next() {
            //              Peter = Peter
            final Node<T> nodeToShow = first;

            //first = peter.contact
            first = first.connectedNode; //null

            //Peter.name
            return nodeToShow.data;
        }
    }
}

class Node<T> { //Person
    Node<T> connectedNode;
    T data;
}