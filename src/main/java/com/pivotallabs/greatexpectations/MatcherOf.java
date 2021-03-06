package com.pivotallabs.greatexpectations;

@java.lang.annotation.Documented
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE})
public @interface MatcherOf {
  Class[] value();
  boolean directObject() default false;
}
