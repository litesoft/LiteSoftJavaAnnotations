package org.litesoft.annotations.expectations;

public interface Expectation {
  String DEFAULT_ON_INSIGNIFICANT = ".!N/A!.";

  /**
   * Process (either record or throw an Exception) the <code>Expectation</code>
   * not being met with the <code>FailedValue</code> from the <code>Name</code>d
   * source.
   *
   * @param pName of the source (normally a parameter name) (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
   * @param pFailedValue Nullable
   * @param pExpectation that was not met (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
   */
  void unmet( String pName, Object pFailedValue, String pExpectation );
}
