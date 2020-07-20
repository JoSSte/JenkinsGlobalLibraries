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
class ReleaseUtilGetBranchDeployEnvTest extends GroovyTestCase {

  final String input
  final String expected

    public ReleaseUtilGetBranchDeployEnvTest(String input, String expected) { 
        this.input = input
        this.expected = expected
    }

    @Parameters
    public static Collection<Object[]> data() {
        def cases = new Object[6][2]
        cases[0][0] = ReleaseUtil.devBranchName
        cases[0][1] = ReleaseUtil.devEnv
        cases[1][0] = ReleaseUtil.featureBranchName
        cases[1][1] = ReleaseUtil.sandboxEnv
        cases[2][0] = ReleaseUtil.masterBranchName
        cases[2][1] = ReleaseUtil.prodEnv
        cases[3][0] = ReleaseUtil.prodBranchName
        cases[3][1] = ReleaseUtil.prodEnv
        cases[4][0] = ReleaseUtil.releaseBranchName
        cases[4][1] = ReleaseUtil.testEnv
        cases[5][0] = ReleaseUtil.hotfixBranchName
        cases[5][1] = ReleaseUtil.prodEnv
        Arrays.asList(cases)
    }

    @Test
    void testGetBranchDeploymentEnvironment() {
        def outcome = ReleaseUtil.getBranchDeploymentEnvironment(this.input)
        println "input: ${input} expected: ${expected} outcome: ${outcome}"
        assert outcome == this.expected
    }
}