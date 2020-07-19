#!groovy
package com.stevnsvig.jenkins.release

//Jenkins Pipeline release utilities

/**@
  * Functions to ease Release process
  */
class Release {

    // branches
    String devBranchName = 'dev'
    String releaseBranchName = 'release'
    String masterBranchName = 'master'
    String hotfixBranchName = 'hotfix'
    String featureBranchName = 'feature'

    //build environments
    String devEnv = 'development'
    String testEnv = 'test'
    String sandboxEnv = 'sandbox'
    String prodEnv = 'production'

    static String get_branch_type(String branchName) {
        def devPattern = '.*development'
        def releasePattern = '.*release/.*'
        def featurePattern = '.*feature/.*'
        def hotfixPattern = '.*hotfix/.*'
        def masterPattern = '.*master'
        if (branchName =~ devPattern) {
            return Release.devBranchName
        } else if (branchName =~ releasePattern) {
            return Release.releaseBranchName
        } else if (branchName =~ masterPattern) {
            return Release.masterBranchName
        } else if (branchName =~ featurePattern) {
            return Release.featureBranchName
        } else if (branchName =~ hotfixPattern) {
            return Release.hotfixBranchName
        }
        return ''
    }

    static String get_branch_deployment_environment(String branchType) {
        switch(branchType) {
            default:
                return Release.devEnv
            break
            case Release.devBranchName:
                return Release.devEnv
            break
            case Release.releaseBranchName:
                return Release.testEnv
            break
            case Release.masterBranchName:
                return Release.prodEnv
            break
        }
    }
}
