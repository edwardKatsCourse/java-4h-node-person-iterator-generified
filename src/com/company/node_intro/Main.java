package com.company.node_intro;

public class Main {

    public static void main(String[] args) {
        //John -> Mary -> Peter -> Moshe

        Person moshe = new Person();
        moshe.name = "Moshe";


        Person peter = new Person();
        peter.name = "Peter";
        peter.contactPerson = moshe;


        Person marry = new Person();
        marry.name = "Marry";
        marry.contactPerson = peter;



	    Person john = new Person();
	    john.name = "John";
	    john.contactPerson = marry;

	    printContact(john);


        System.out.println(" ------------ ");


	    Person wife = new Person();
	    wife.name = "Sarah";

	    Person husband = new Person();
	    husband.name = "Jim";


	    husband.contactPerson = wife;
	    wife.contactPerson = husband;

	    printContact(husband);

    }

    static void printContact(final Person person) {

        Person currentPerson = person;

        System.out.println(currentPerson.name); //John

        //Anna

        while (currentPerson.contactPerson != null) { //John -> Marry -> Anna -> Peter -> Moshe
            currentPerson = currentPerson.contactPerson; //Marry -> Peter -> Moshe

            System.out.println(currentPerson.name); //Marry -> Peter -> Moshe
        }
    }
}

//Person -> other Person -> other person
//Person -> Node
class Person {
    String name;
    Person contactPerson;
}