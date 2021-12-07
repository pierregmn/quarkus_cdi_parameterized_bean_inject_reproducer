package org.reproducer;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ApplicationTest {

  @Inject
  FactoryBean factoryBean;

  @Test
  public void test() {
    factoryBean.getElement();
  }
}
