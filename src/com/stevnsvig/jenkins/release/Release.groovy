#!groovy
package com.stevnsvig.jenkins.release

//Jenkins Pipeline release utilities

/**
  * Functions to ease Release process
  */
class Release {

    def get_branch_type(String branchName) {
        def dev_pattern = '.*development'
        def release_pattern = '.*release/.*'
        def feature_pattern = '.*feature/.*'
        def hotfix_pattern = '.*hotfix/.*'
        def master_pattern = '.*master'
        if (branchName =~ dev_pattern) {
            return 'dev'
        } else if (branchName =~ release_pattern) {
            return 'release'
        } else if (branchName =~ master_pattern) {
            return 'master'
        } else if (branchName =~ feature_pattern) {
            return 'feature'
        } else if (branchName =~ hotfix_pattern) {
            return 'hotfix'
        } else {
            return ''
        }
    }

    def get_branch_deployment_environment(String branchType) {
        if (branchType == 'dev') {
            return 'dev'
        } else if (branchType == 'release') {
            return 'staging'
        } else if (branchType == 'master') {
            return 'prod'
        }
        return ''
    }

}
