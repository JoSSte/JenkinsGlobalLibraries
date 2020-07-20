package com.stevnsvig.jenkins.release

import org.junit.Test
import junit.framework.TestCase
import groovy.test.GroovyTestCase
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized)
class ReleaseUtilGetBranchTypeTest extends GroovyTestCase {

  final String input
  final String expected

    public ReleaseUtilGetBranchTypeTest(String input, String expected) { 
        this.input = input
        this.expected = expected
    }

    @Parameters
    public static Collection<Object[]> data() {
        def cases = new Object[6][2]
        cases[0][0] = 'development'
        cases[0][1] = ReleaseUtil.devBranchName
        cases[1][0] = 'feature/abc'
        cases[1][1] = ReleaseUtil.featureBranchName
        cases[2][0] = 'master'
        cases[2][1] = ReleaseUtil.masterBranchName
        cases[3][0] = 'production'
        cases[3][1] = ReleaseUtil.prodBranchName
        cases[4][0] = 'release/seamonkey'
        cases[4][1] = ReleaseUtil.releaseBranchName
        cases[5][0] = 'hotfix/pantsonfire'
        cases[5][1] = ReleaseUtil.hotfixBranchName
        Arrays.asList(cases)
    }

    @Test
    void testGetBranchType() {
        def outcome = ReleaseUtil.getBranchType(this.input)
        println "input: ${input} expected: ${expected} outcome: ${outcome}"
        assert outcome == this.expected
    }
}