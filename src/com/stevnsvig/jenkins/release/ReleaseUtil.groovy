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
    static String prodBranchName = 'master'
    static String hotfixBranchName = 'hotfix'
    static String featureBranchName = 'feature'

    //build environments
    static String devEnv = 'development'
    static String testEnv = 'test'
    static String sandboxEnv = 'sandbox'
    static String prodEnv = 'production'

    static String getBranchType(String branchName) {
        def devPattern = '.*development'
        def releasePattern = '.*release/.*'
        def featurePattern = '.*feature/.*'
        def hotfixPattern = '.*hotfix/.*'
        def masterPattern = '.*master'
        def prodPattern = '.*production'
        if (branchName =~ devPattern) {
            return ReleaseUtil.devBranchName
        } else if (branchName =~ releasePattern) {
            return ReleaseUtil.releaseBranchName
        } else if (branchName =~ masterPattern) {
            return ReleaseUtil.prodBranchName
        } else if (branchName =~ masterPattern) {
            return ReleaseUtil.prodBranchName
        } else if (branchName =~ featurePattern) {
            return ReleaseUtil.featureBranchName
        } else if (branchName =~ hotfixPattern) {
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
            case ReleaseUtil.featureBranchName:
                return ReleaseUtil.sandboxEnv
            break
            case ReleaseUtil.masterBranchName:
                return ReleaseUtil.prodEnv
            break
        }
    }

    static String getGitTag() {
        def stdout = "git tag --sort version:refname | tail -1".execute()
        return stdout.in.text
    }
}
