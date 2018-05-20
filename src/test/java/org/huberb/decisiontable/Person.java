/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.huberb.decisiontable;

import java.util.Arrays;
import java.util.List;

//----
class Person {

    /**
     * Conditions of the person.
     */
    enum ConditionsEnum {

        c1, c2
    }

    /**
     * Rules of the person.
     */
    enum RulesEnum {

        r1, r2, r3
    }

    enum Gender {
        female, male
    }
    String name;
    Gender gender;
    int age;
    int weight;
    int height;

    public Person(String name, Gender gender, int age, int weight, int height) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    static List<Person> tenPersons() {
        List<Person> persons = Arrays.asList(
                new Person("Peter", Gender.male, 15, 50, 170),
                new Person("Manuela", Gender.female, 21, 55, 185),
                new Person("Thomas", Gender.male, 26, 70, 160),
                new Person("Maria", Gender.female, 30, 90, 172),
                new Person("Hermann", Gender.male, 34, 80, 196),
                new Person("Daniela", Gender.female, 38, 60, 165),
                new Person("Gustav", Gender.male, 42, 70, 179),
                new Person("Viktoria", Gender.female, 55, 83, 184),
                new Person("Klaus", Gender.male, 62, 62, 166),
                new Person("Eva", Gender.female, 73, 70, 183));
        return persons;
    }

}
