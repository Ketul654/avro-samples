package com.ketul.avro.reflection;

import org.apache.avro.reflect.AvroDefault;
import org.apache.avro.reflect.Nullable;

public class ReflectedEmployee {

    private String firstName;

    @Nullable
    private String lastName;

    private int age;
    private float salary;

    @AvroDefault("true")
    private boolean isPermanent;

    public ReflectedEmployee(){}

    public ReflectedEmployee(String firstName, String lastName, int age, float salary, boolean isPermanent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
        this.isPermanent = isPermanent;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public boolean isPermanent() {
        return isPermanent;
    }

    public void setPermanent(boolean permanent) {
        isPermanent = permanent;
    }

    @Override
    public String toString() {
        return "ReflectedEmployee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", isPermanent=" + isPermanent +
                '}';
    }
}
