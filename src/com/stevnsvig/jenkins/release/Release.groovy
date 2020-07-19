#!groovy
package com.stevnsvig.jenkins.release

//Jenkins Pipeline release utilities

/**@
  * Functions to ease Release process
  */
class Release {

    String devBranchName = 'dev'
    String releaseBranchName = 'release'
    String masterBranchName = 'master'
    String hotfixBranchName = 'hotfix'
    String featureBranchName = 'feature'


    String  get_branch_type(String branchName) {
        def devPattern = '.*development'
        def releasePattern = '.*release/.*'
        def featurePattern = '.*feature/.*'
        def hotfixPattern = '.*hotfix/.*'
        def masterPattern = '.*master'
        if (branchName =~ devPattern) {
            return devBranchName
        } else if (branchName =~ releasePattern) {
            return relBranchName
        } else if (branchName =~ masterPattern) {
            return masterBranchName
        } else if (branchName =~ featurePattern) {
            return featureBranchName
        } else if (branchName =~ hotfixPattern) {
            return  hotfixBranchName
        }
        return ''
    }

    String  get_branch_deployment_environment(String branchType) {
        if (branchType == devBranchName) {
            return devBranchName
        } else if (branchType == relBranchName) {
            return 'staging'
        } else if (branchType == masterBranchName) {
            return 'prod'
        }
        return ''
    }

}
