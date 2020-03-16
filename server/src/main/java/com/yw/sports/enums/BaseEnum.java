package com.yw.sports.enums;


import java.util.Set;

public interface BaseEnum<E extends Enum<E>, T> {
    T getValue();
    String getMessage();

}
