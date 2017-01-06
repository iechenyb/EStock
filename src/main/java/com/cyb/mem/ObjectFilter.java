package com.cyb.mem;

public interface ObjectFilter<T> {
  boolean apply(T input);
}
