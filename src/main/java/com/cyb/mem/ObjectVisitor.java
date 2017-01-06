package com.cyb.mem;


public interface ObjectVisitor<T> {
  Traversal visit(Chain chain);
  T result();
  enum Traversal { EXPLORE, SKIP }
}
