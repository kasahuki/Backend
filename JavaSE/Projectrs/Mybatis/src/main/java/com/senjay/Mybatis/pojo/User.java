package com.senjay.Mybatis.pojo;
public class User {
    // 创建实体类
    private int id;
    private String name;
    private int age;
    // 要有顺序之分吗！
    private char gender;

    public User(int age, int id, String name, char gender) {
        this.age = age;
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public User() {
    }
    // 设置无参和有参构造


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
//   设置get和set方法
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
    // 反斜杠（右斜杠）是转义字符
    // 重写toString方法 (ctrl + ,)
    // 作用: 打印对象时, 输出对象的属性值

}
