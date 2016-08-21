package com.oguzbabaoglu.auto.value.querymap;

import com.google.auto.service.AutoService;
import com.google.auto.value.extension.AutoValueExtension;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

import java.util.List;
import java.util.Set;

import javax.lang.model.element.ExecutableElement;

import static javax.lang.model.element.Modifier.ABSTRACT;
import static javax.lang.model.element.Modifier.FINAL;

@AutoService(AutoValueExtension.class)
public class AutoValueQueryMapExtension extends AutoValueExtension {

  @Override public boolean applicable(Context context) {
    return MapMethod.findDeclaredMethod(context).size() > 0;
  }

  @Override public Set<ExecutableElement> consumeMethods(Context context) {
    return MapMethod.findDeclaredMethod(context);
  }

  @Override public String generateClass(Context context, String className, String classToExtend, boolean isFinal) {
    List<Property> properties = Property.readProperties(context.properties());

    TypeSpec.Builder subclass = TypeSpec.classBuilder(className)
        .superclass(TypeVariableName.get(classToExtend))
        .addMethod(ConstructorMethod.generateConstructor(properties))
        .addMethod(MapMethod.generateMethod(properties));

    if (isFinal) {
      subclass.addModifiers(FINAL);
    } else {
      subclass.addModifiers(ABSTRACT);
    }

    return JavaFile.builder(context.packageName(), subclass.build()).build().toString();
  }
}
