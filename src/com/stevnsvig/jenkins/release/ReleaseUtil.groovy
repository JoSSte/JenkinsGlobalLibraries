#!groovy
package com.stevnsvig.jenkins.release

//Jenkins Pipeline release utilities

/**@
  * Functions to ease Release process
  */
class ReleaseUtil {

    // branches
    static String devBranchName = 'dev'
    static String releaseBranchName = 'release'
    static String masterBranchName = 'master'
    static String prodBranchName = 'production'
    static String testBranchName = 'test'
    static String hotfixBranchName = 'hotfix'
    static String featureBranchName = 'feature'

    //build environments
    static String devEnv = 'development'
    static String testEnv = 'test'
    static String sandboxEnv = 'sandbox'
    static String prodEnv = 'production'

    static String getBranchType(String branchName) {
        def devPattern = '.*development'
        def testPattern = '.*test'
        def releasePattern = '.*release/.*'
        def featurePattern = '.*feature/.*'
        def hotfixPattern = '.*hotfix/.*'
        def masterPattern = '.*master'
        def prodPattern = '.*production'
        if (branchName.toLowerCase() =~ devPattern) {
            return ReleaseUtil.devBranchName
        } else if (branchName.toLowerCase() =~ testPattern) {
            return ReleaseUtil.testBranchName
        } else if (branchName.toLowerCase() =~ releasePattern) {
            return ReleaseUtil.releaseBranchName
        } else if (branchName.toLowerCase() =~ prodPattern) {
            return ReleaseUtil.prodBranchName
        } else if (branchName.toLowerCase() =~ masterPattern) {
            return ReleaseUtil.masterBranchName
        } else if (branchName.toLowerCase() =~ featurePattern) {
            return ReleaseUtil.featureBranchName
        } else if (branchName.toLowerCase() =~ hotfixPattern) {
            return ReleaseUtil.hotfixBranchName
        }
        return ''
    }

    static String getBranchDeploymentEnvironment(String branchType) {
        switch(branchType) {
            default:
                return ReleaseUtil.devEnv
            break
            case ReleaseUtil.devBranchName:
                return ReleaseUtil.devEnv
            break
            case ReleaseUtil.releaseBranchName:
                return ReleaseUtil.testEnv
            break
            case ReleaseUtil.testBranchName:
            case ReleaseUtil.featureBranchName:
                return ReleaseUtil.sandboxEnv
            break
            case ReleaseUtil.hotfixBranchName:
            case ReleaseUtil.masterBranchName:
            case ReleaseUtil.prodBranchName:
                return ReleaseUtil.prodEnv
            break
        }
    }

    static String getGitTag() {
        def stdout = "git tag --sort version:refname | tail -1".execute()
        return stdout.in.text
    }

    static String getPWD() {
        def stdout = "pwd".execute()
        return stdout.in.text
    }

    /*
    returns the base name of an ant version string
    i.e. if input is 'Release-1.0' 'release' is returned
    */
    static String getBaseName(String tagOrBranch) {
        def matcher = tagOrBranch =~ /(?i)^(?<name>[a-z]*)(?:-(?<version>\d{1,3}\.\d{1,3}))?/
        def result = ''
        try { 
            matcher.matches()
            result = matcher.group("name")
        } catch(IllegalStateException ise) {
            return ''
        }
        
        return result.toLowerCase()
    }
}
