package com.oguzbabaoglu.auto.value.querymap;

import com.squareup.javapoet.TypeName;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

class Property {
  final String methodName;
  final String humanName;
  final TypeName type;
  final String keyValue;
  final boolean isPrimitive;

  Property(String humanName, ExecutableElement element) {
    this.methodName = element.getSimpleName().toString();
    this.humanName = humanName;
    this.type = TypeName.get(element.getReturnType());

    String definedKey = getKeyFromAnnotation(element);
    this.keyValue = definedKey == null ? humanName : definedKey;

    this.isPrimitive = element.getReturnType().getKind().isPrimitive();
  }

  private String getKeyFromAnnotation(ExecutableElement element) {
    List<? extends AnnotationMirror> annotations = element.getAnnotationMirrors();
    for (AnnotationMirror annotation : annotations) {
      if (Param.class.getCanonicalName().equals(
          ((TypeElement) annotation.getAnnotationType().asElement()).getQualifiedName().toString())) {
        return (String) annotation.getElementValues().values().iterator().next().getValue();
      }
    }
    return null;
  }

  static List<Property> readProperties(Map<String, ExecutableElement> properties) {
    List<Property> values = new LinkedList<>();
    for (Map.Entry<String, ExecutableElement> entry : properties.entrySet()) {
      values.add(new Property(entry.getKey(), entry.getValue()));
    }
    return values;
  }
}
