package com.oguzbabaoglu.auto.value.querymap;

import com.google.common.collect.Lists;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;

import java.util.ArrayList;
import java.util.List;

class ConstructorMethod {
  static MethodSpec generateConstructor(List<Property> properties) {
    List<ParameterSpec> params = Lists.newArrayList();
    List<String> keySet = new ArrayList<>();
    for (Property p : properties) {
      params.add(ParameterSpec.builder(p.type, p.humanName).build());
      keySet.add(p.humanName);
    }

    MethodSpec.Builder builder = MethodSpec.constructorBuilder()
        .addParameters(params);

    StringBuilder superFormat = new StringBuilder("super(");
    for (int i = properties.size(); i > 0; i--) {
      superFormat.append("$N");
      if (i > 1) superFormat.append(", ");
    }
    superFormat.append(")");
    builder.addStatement(superFormat.toString(), keySet.toArray());

    return builder.build();
  }
}
