package org.reproducer;

import java.util.NoSuchElementException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@ApplicationScoped
public class FactoryBean {

  /* Adding <?> solves the issue:
   * https://docs.jboss.org/cdi/spec/2.0/cdi-spec.html#legal_bean_types
   * "A parameterized type that contains a wildcard type parameter is not a legal bean type" */
  private Instance<ParameterizedAbstractClass> parameterizedAbstractClasses;

  @Inject
  public FactoryBean(/* Adding <?> solves the issue */
      Instance<ParameterizedAbstractClass> parameterizedAbstractClasses) {
    this.parameterizedAbstractClasses = parameterizedAbstractClasses;
  }

  public ParameterizedAbstractClass getElement() {
    return parameterizedAbstractClasses.stream()
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException("No parameterized abstract class found"));
  }
}
