package com.oguzbabaoglu.auto.value.querymap;

import com.google.auto.value.extension.AutoValueExtension;
import com.google.common.collect.ImmutableSet;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;

import static javax.lang.model.element.Modifier.PUBLIC;

class MapMethod {
  private static final String METHOD_NAME = "toQueryMap";
  private static final ParameterizedTypeName METHOD_RETURN_TYPE = ParameterizedTypeName
      .get(Map.class, String.class, String.class);

  static MethodSpec generateMethod(List<Property> properties) {
    MethodSpec.Builder builder = MethodSpec.methodBuilder(METHOD_NAME)
        .addModifiers(PUBLIC)
        .addAnnotation(Override.class)
        .returns(METHOD_RETURN_TYPE);

    builder.addStatement("$T queryMap = new $T<>()", METHOD_RETURN_TYPE, LinkedHashMap.class);
    for (Property p : properties) {
      if (!p.isPrimitive) builder.beginControlFlow("if ($L() != null)", p.methodName);
      builder.addStatement("queryMap.put($S, $T.valueOf($L()))", p.keyValue, String.class, p.methodName);
      if (!p.isPrimitive) builder.endControlFlow();
    }
    builder.addStatement("return queryMap");

    return builder.build();
  }

  static Set<ExecutableElement> findDeclaredMethod(AutoValueExtension.Context context) {
    TypeElement type = context.autoValueClass();

    for (ExecutableElement method : ElementFilter.methodsIn(type.getEnclosedElements())) {
      if (method.getModifiers().contains(Modifier.ABSTRACT) && method.getModifiers().contains(Modifier.PUBLIC)
          && method.getSimpleName().toString().equals(METHOD_NAME) && method.getParameters().size() == 0) {
        TypeMirror rType = method.getReturnType();
        TypeName returnType = TypeName.get(rType);
        if (returnType.equals(METHOD_RETURN_TYPE)) {
          return ImmutableSet.of(method);
        }
      }
    }

    return ImmutableSet.of();
  }
}
