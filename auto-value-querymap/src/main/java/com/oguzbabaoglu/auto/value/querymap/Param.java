package com.oguzbabaoglu.auto.value.querymap;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Annotation to indicate that this field should be added to the parameter map with
 * the provided name value as its key name.
 */
@Retention(SOURCE)
@Target(METHOD)
public @interface Param {

  /**
   * @return the desired key of the field in the map
   */
  String value();
}
