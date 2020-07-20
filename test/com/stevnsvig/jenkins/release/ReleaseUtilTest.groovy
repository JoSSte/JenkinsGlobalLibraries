package com.stevnsvig.jenkins.release

import org.junit.Test
import junit.framework.TestCase
import groovy.test.GroovyTestCase
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

// http://dzone.com/articles/writing-unit-tests-using-groov
// https://groovy-lang.org/testing.html
// https://stackoverflow.com/questions/27855989/running-groovy-tests-with-maven
@RunWith(Parameterized)
class ReleaseUtilTest extends GroovyTestCase {

  final String input
  final String expected

    public ReleaseUtilTest(String input, String expected) { 
        this.input = input
        this.expected = expected
    }

    @Parameters
    public static Collection<Object[]> data() {
        def cases = new Object[2][2]
        cases[0][0] = 'dev'
        cases[0][1] = 'development'
        cases[1][0] = 'feature'
        cases[1][1] = 'sandbox'
        Arrays.asList(cases)
    }

    @Test
    void testGetBranchDeploymentEnvironment() {
        println "input = ${input} expected= ${expected}"
        def outcome = ReleaseUtil.getBranchDeploymentEnvironment(this.input)
        assert outcome == this.expected
    }
}