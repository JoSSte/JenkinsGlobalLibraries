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
            return this.devBranchName
        } else if (branchName =~ releasePattern) {
            return this.releaseBranchName
        } else if (branchName =~ masterPattern) {
            return this.masterBranchName
        } else if (branchName =~ featurePattern) {
            return this.featureBranchName
        } else if (branchName =~ hotfixPattern) {
            return this.hotfixBranchName
        }
        return ''
    }

    static String get_branch_deployment_environment(String branchType) {
        if (branchType == this.devBranchName) {
            return this.devBranchName
        } else if (branchType == this.releaseBranchName) {
            return 'staging'
        } else if (branchType == this.masterBranchName) {
            return 'prod'
        }
        return ''
    }

}
