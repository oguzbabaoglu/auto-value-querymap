package com.oguzbabaoglu.example;

import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DemoRequestTest {

  @Test public void testQueryMap() throws Exception {
    DemoRequest demoRequest = DemoRequest.create(2, "test", null);

    Map<String, String> queryMap = demoRequest.toQueryMap();
    assertThat(queryMap).hasSize(2);
    assertThat(queryMap.get("customKey")).isEqualTo("2");
    assertThat(queryMap.get("normalField")).isEqualTo("test");
  }

}