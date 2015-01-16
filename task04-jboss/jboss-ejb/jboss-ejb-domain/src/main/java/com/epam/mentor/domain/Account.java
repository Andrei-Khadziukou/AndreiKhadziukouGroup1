package com.epam.mentor.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * jboss-ejb class
 *
 * Date: Sep 11, 2014
 *
 * @author Aliaksandr_Shynkevich
 */
public class Account {
    private String serialNumber;
    private String name;
    private Integer age;

    public Account() {
    }

    public Account(String serialNumber, String name, Integer age) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(serialNumber)
                .append(name)
                .append(age)
                .build();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !getClass().equals(obj.getClass())) {
            return false;
        }
        Account acc = (Account) obj;
        return new EqualsBuilder()
                .append(serialNumber, acc.serialNumber)
                .append(name, acc.serialNumber)
                .append(age, acc.age)
                .isEquals();
    }
}
