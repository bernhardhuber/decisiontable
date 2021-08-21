/*
 * Copyright 2021 berni3.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.huberb.decisiontable;

import java.util.Arrays;
import java.util.List;

//----
public class Person {

    /**
     * Conditions of the person.
     */
    public enum ConditionsEnum {
        c1, c2;
    }

    /**
     * Rules of the person.
     */
    public enum RulesEnum {
        r1, r2, r3;
    }

    public enum Gender {
        female, male;
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

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Person{"
                + "name=" + name
                + ", gender=" + gender
                + ", age=" + age
                + ", weight=" + weight
                + ", height=" + height + '}';
    }

    public static List<Person> tenPersons() {
        final List<Person> persons = Arrays.asList(
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
