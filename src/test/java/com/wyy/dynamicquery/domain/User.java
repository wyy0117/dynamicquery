package com.wyy.dynamicquery.domain;

import java.util.Date;

/**
 * @author wyy
 * @date 18-10-16
 * @time 下午4:52
 */
public class User {

    private String name;
    private long age;
    private double height;
    private Date birthday;

    public User(String name, long age, double height, Date birthday) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public long getAge() {
        return age;
    }

    public User setAge(long age) {
        this.age = age;
        return this;
    }

    public double getHeight() {
        return height;
    }

    public User setHeight(double height) {
        this.height = height;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public User setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", birthday=" + birthday +
                '}';
    }
}
