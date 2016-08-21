# AutoValue: QueryMap Extension

An extension for Google's [AutoValue](https://github.com/google/auto) that implements a "toQueryMap" method for Retrofit's [QueryMap](https://square.github.io/retrofit/2.x/retrofit/index.html?retrofit2/http/QueryMap.html).

## Usage

Include auto-value-querymap in your project and add a `public abstract Map<String, String> toQueryMap()` method to your `@AutoValue` 
annotated class.  

You can also annotate your properties using `@Param` to define an alternate key name in the `QueryMap`.

```java
@AutoValue public abstract class Foo {
  abstract String bar();                  // Will be mapped as "bar":"<value of bar>"
  @Param("Bazoo") abstract String baz();  // Will be mapped as "Bazoo":"<value of baz>"

  // This method is what auto-value-querymap will implement.
  public abstract Map<String, String> toQueryMap();
  
  // Retrofit will convert this to "?bar=<value of bar>&Bazoo=<value of baz>"
}
```

Now build your project and auto-value-querymap will implement the method.

- `null` values will not be added to the map (Not allowed by `QueryMap`)
- all values will be converted to `String` using `String.valueOf()`
- the map will preserve order of decleration of the fields

## Download

### Android
Add a Gradle dependency to the `apt` and `provided` configuration.

```groovy
apt 'com.oguzbabaoglu:auto-value-querymap:1.0'
provided 'com.oguzbabaoglu:auto-value-querymap:1.0'
```

(Using the [android-apt](https://bitbucket.org/hvisser/android-apt) plugin)

### Java
Add a Gradle dependency to the `apt` and `compileOnly` configuration.

```groovy
apt 'com.oguzbabaoglu:auto-value-querymap:1.0'
compileOnly 'com.oguzbabaoglu:auto-value-querymap:1.0'
```

(Using the [gradle-apt-plugin](https://github.com/tbroyer/gradle-apt-plugin) plugin)

## Credit
Based on the existing [auto-value-map](https://github.com/cynnyx/auto-value-map) extension by Cynny.

## License

```
Copyright 2016 Oguz Babaoglu.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
