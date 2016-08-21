package com.oguzbabaoglu.auto.value.querymap;

import com.google.auto.value.processor.AutoValueProcessor;
import com.google.common.truth.Truth;
import com.google.testing.compile.JavaFileObjects;

import org.junit.Test;

import javax.tools.JavaFileObject;

import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;

public class AutoValueQueryMapExtensionTest {

  @Test public void testNoQueryMapMethod() throws Exception {
    JavaFileObject source = JavaFileObjects.forSourceString("test.Simple",
        "package test;\n"
            + "import com.google.auto.value.AutoValue;\n"
            + "@AutoValue\n"
            + "public abstract class Simple {\n"
            + "    public abstract String name();\n"
            + "    public abstract Long value();\n"
            + "}");

    JavaFileObject expected = JavaFileObjects.forSourceString("test/AutoValue_Simple",
        "package test;\n"
            + "\n"
            + "import javax.annotation.Generated;\n"
            + "\n"
            + "@Generated(\"com.google.auto.value.processor.AutoValueProcessor\")\n"
            + "final class AutoValue_Simple extends Simple {\n"
            + "\n"
            + "  private final String name;\n"
            + "  private final Long value;\n"
            + "\n"
            + "  AutoValue_Simple(\n"
            + "      String name,\n"
            + "      Long value) {\n"
            + "    if (name == null) {\n"
            + "      throw new NullPointerException(\"Null name\");\n"
            + "    }\n"
            + "    this.name = name;\n"
            + "    if (value == null) {\n"
            + "      throw new NullPointerException(\"Null value\");\n"
            + "    }\n"
            + "    this.value = value;\n"
            + "  }\n"
            + "\n"
            + "  @Override\n"
            + "  public String name() {\n"
            + "    return name;\n"
            + "  }\n"
            + "\n"
            + "  @Override\n"
            + "  public Long value() {\n"
            + "    return value;\n"
            + "  }\n"
            + "\n"
            + "  @Override\n"
            + "  public String toString() {\n"
            + "    return \"Simple{\"\n"
            + "        + \"name=\" + name + \", \"\n"
            + "        + \"value=\" + value\n"
            + "        + \"}\";\n"
            + "  }\n"
            + "\n"
            + "  @Override\n"
            + "  public boolean equals(Object o) {\n"
            + "    if (o == this) {\n"
            + "      return true;\n"
            + "    }\n"
            + "    if (o instanceof Simple) {\n"
            + "      Simple that = (Simple) o;\n"
            + "      return (this.name.equals(that.name()))\n"
            + "           && (this.value.equals(that.value()));\n"
            + "    }\n"
            + "    return false;\n"
            + "  }\n"
            + "\n"
            + "  @Override\n"
            + "  public int hashCode() {\n"
            + "    int h = 1;\n"
            + "    h *= 1000003;\n"
            + "    h ^= this.name.hashCode();\n"
            + "    h *= 1000003;\n"
            + "    h ^= this.value.hashCode();\n"
            + "    return h;\n"
            + "  }\n"
            + "\n"
            + "}");

    Truth.assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoValueProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expected);
  }

  @Test public void testWithoutAnnotations() throws Exception {
    JavaFileObject source = JavaFileObjects.forSourceString("test.Simple",
        "package test;\n"
            + "import java.util.Map;\n"
            + "import com.google.auto.value.AutoValue;\n"
            + "@AutoValue\n"
            + "public abstract class Simple {\n"
            + "    public abstract String name();\n"
            + "    public abstract Long value();\n"
            + "    public abstract int primitive();\n"
            + "    public abstract Map<String, String> toQueryMap();\n"
            + "}");

    JavaFileObject expected = JavaFileObjects.forSourceString("test/AutoValue_Simple",
        "package test;\n"
            + "\n"
            + "import java.lang.Long;\n"
            + "import java.lang.Override;\n"
            + "import java.lang.String;\n"
            + "import java.util.LinkedHashMap;\n"
            + "import java.util.Map;\n"
            + "\n"
            + "final class AutoValue_Simple extends $AutoValue_Simple {\n"
            + "  AutoValue_Simple(String name, Long value, int primitive) {\n"
            + "    super(name, value, primitive);\n"
            + "  }\n"
            + "\n"
            + "  @Override\n"
            + "  public Map<String, String> toQueryMap() {\n"
            + "    Map<String, String> queryMap = new LinkedHashMap<>();\n"
            + "    if (name() != null) {\n"
            + "      queryMap.put(\"name\", String.valueOf(name()));\n"
            + "    }\n"
            + "    if (value() != null) {\n"
            + "      queryMap.put(\"value\", String.valueOf(value()));\n"
            + "    }\n"
            + "    queryMap.put(\"primitive\", String.valueOf(primitive()));\n"
            + "    return queryMap;\n"
            + "  }\n"
            + "}");

    Truth.assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoValueProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expected);
  }

  @Test public void testWithAnnotations() throws Exception {
    JavaFileObject source = JavaFileObjects.forSourceString("test.Simple",
        "package test;\n"
            + "import java.util.Map;\n"
            + "import com.google.auto.value.AutoValue;\n"
            + "import com.oguzbabaoglu.auto.value.querymap.Param;\n"
            + "@AutoValue\n"
            + "public abstract class Simple {\n"
            + "    @Param(\"nameKey\") public abstract String name();\n"
            + "    @Param(\"valueKey\") public abstract Long value();\n"
            + "    @Param(\"primitiveKey\") public abstract int primitive();\n"
            + "    public abstract Map<String, String> toQueryMap();\n"
            + "}");

    JavaFileObject expected = JavaFileObjects.forSourceString("test/AutoValue_Simple",
        "package test;\n"
            + "\n"
            + "import java.lang.Long;\n"
            + "import java.lang.Override;\n"
            + "import java.lang.String;\n"
            + "import java.util.LinkedHashMap;\n"
            + "import java.util.Map;\n"
            + "\n"
            + "final class AutoValue_Simple extends $AutoValue_Simple {\n"
            + "  AutoValue_Simple(String name, Long value, int primitive) {\n"
            + "    super(name, value, primitive);\n"
            + "  }\n"
            + "\n"
            + "  @Override\n"
            + "  public Map<String, String> toQueryMap() {\n"
            + "    Map<String, String> queryMap = new LinkedHashMap<>();\n"
            + "    if (name() != null) {\n"
            + "      queryMap.put(\"nameKey\", String.valueOf(name()));\n"
            + "    }\n"
            + "    if (value() != null) {\n"
            + "      queryMap.put(\"valueKey\", String.valueOf(value()));\n"
            + "    }\n"
            + "    queryMap.put(\"primitiveKey\", String.valueOf(primitive()));\n"
            + "    return queryMap;\n"
            + "  }\n"
            + "}");

    Truth.assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoValueProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expected);
  }
}