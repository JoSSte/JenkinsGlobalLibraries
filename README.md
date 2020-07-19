# JenkinsGlobalLibraries

Libraries I use in some of my Jenkins pipelines to make life easier

The functions so far are heavily inspired by [this repository](https://github.com/alexguzun/jenkins-pipeline-gitflow-maven/blob/master/Jenkinsfile), although I use it mainly in a multi-branch pipeline setup, so I have read [the jenkins documentation about shared libraries](https://www.jenkins.io/doc/book/pipeline/shared-libraries/) and come up with this, to try to keep my Pipeline scripts [DRY](http://en.wikipedia.org/wiki/Don't_repeat_yourself).


## Setup

Refer to [the Jenkins book](https://www.jenkins.io/doc/book/pipeline/shared-libraries/#using-libraries) for reference on setting up a scm repository as a source of your Jenkins Shared Libraries

## Importing the library in your Jenkinsfile

The import is as follows:

```
@Library('JoSSteJenkinsGlobalLibraries')
import com.stevnsvig.jenkins.release.ReleaseUtil
...
```
Note that this is all that is required for the [ReleaseUtil class](/src/com/stevnsvig/jenkins/release/ReleaseUtil.groovy) since all the methods and variables are static.

You can refer to [the Jenkins book](https://www.jenkins.io/doc/book/pipeline/shared-libraries/#accessing-steps) about non-static imports.


## (Re-)Using your functions
Using the functions defined is as easy as calling `ReleaseUtil.*`

For instance, here is an example of sending the globals to the script:

```groovy
def branch_type = ReleaseUtil.get_branch_type "${env.BRANCH_NAME}"
def branch_deployment_environment = ReleaseUtil.get_branch_deployment_environment branch_type
```

... and then you can use the variables to check for what to do either scripted:

```groovy
if (branch_deployment_environment) {
    stage('deploy') {
        if (branch_deployment_environment == ReleaseUtil.prodEnv) {
            timeout(time: 1, unit: 'DAYS') {
                input "Deploy to ${branch_deployment_environment} ?"
            }
        }
        node {
            echo "Deploying to ${branch_deployment_environment}"
        }
    }

    if (branch_deployment_environment != ReleaseUtil.prodEnv) {
        stage('integration tests') {
            node {
                echo "Running integration tests in ${branch_deployment_environment}"
                //TODO do the actual tests
            }
        }
    }
}
```

... or in a Declarative Pipeline:

```groovy
pipeline {
    stages {
        stage ('Integration Tests') {
            when {
                expression { branch_deployment_environment == ReleaseUtil.testEnv }
            }
            steps {
                echo "integration tests go here!"            
            }
        }
    }
}
```
