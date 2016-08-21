package com.oguzbabaoglu.auto.value.querymap;

import com.google.common.collect.ImmutableSet;
import com.squareup.javapoet.TypeName;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.ExecutableElement;

class Property {
  final String methodName;
  final String humanName;
  final ExecutableElement element;
  final TypeName type;
  final ImmutableSet<String> annotations;

  Property(String humanName, ExecutableElement element) {
    this.methodName = element.getSimpleName().toString();
    this.humanName = humanName;
    this.element = element;

    type = TypeName.get(element.getReturnType());
    annotations = buildAnnotations(element);
  }

  private ImmutableSet<String> buildAnnotations(ExecutableElement element) {
    ImmutableSet.Builder<String> builder = ImmutableSet.builder();

    List<? extends AnnotationMirror> annotations = element.getAnnotationMirrors();
    for (AnnotationMirror annotation : annotations) {
      builder.add(annotation.getAnnotationType().asElement().getSimpleName().toString());
    }

    return builder.build();
  }

  static List<Property> readProperties(Map<String, ExecutableElement> properties) {
    List<Property> values = new LinkedList<>();
    for (Map.Entry<String, ExecutableElement> entry : properties.entrySet()) {
      values.add(new Property(entry.getKey(), entry.getValue()));
    }
    return values;
  }
}
