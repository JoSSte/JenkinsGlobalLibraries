package com.stevnsvig.jenkins.release

import org.junit.Test
import junit.framework.TestCase
import groovy.test.GroovyTestCase
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized)
class ReleaseUtilGetBaseNameTest extends GroovyTestCase {

  final String input
  final String expected

    public ReleaseUtilGetBaseNameTest(String input, String expected) { 
        this.input = input
        this.expected = expected
    }

    @Parameters
    public static Collection<Object[]> data() {
        def cases = new Object[8][2]
        cases[0][0] = 'test-1.0'
        cases[0][1] = 'test'
        cases[1][0] = 'sandbox-1.0'
        cases[1][1] = 'sandbox'
        cases[2][0] = 'release-0.1'
        cases[2][1] = 'release'
        cases[3][0] = 'Release-2.0'
        cases[3][1] = 'release'
        cases[4][0] = 'uat'
        cases[4][1] = 'uat'
        cases[5][0] = '1.0.0'
        cases[5][1] = ''
        cases[6][0] = 'feature/something-1.0'
        cases[6][1] = ''
        cases[7][0] = 'test-2.0.0'
        cases[7][1] = ''

        Arrays.asList(cases)
    }

    @Test
    void testGetBaseName() {
        def outcome = ReleaseUtil.getBaseName(this.input)
        println "input: ${input} expected: ${expected} outcome: ${outcome}"
        assert outcome == this.expected
    }

    
}