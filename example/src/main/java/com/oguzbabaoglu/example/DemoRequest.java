package com.oguzbabaoglu.example;

import com.google.auto.value.AutoValue;
import com.oguzbabaoglu.auto.value.querymap.Param;

import java.util.Map;

@AutoValue
public abstract class DemoRequest {

  @Param("customKey") public abstract int annotatedField();
  public abstract String normalField();
  @Nullable public abstract Object nullField();

  public abstract Map<String, String> toQueryMap();

  public static DemoRequest create(int annotatedField, String normalField, Object nullField) {
    return new AutoValue_DemoRequest(annotatedField, normalField, nullField);
  }
}
