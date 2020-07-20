import org.jenkinsci.plugins.workflow.steps.durable_task.ShellStep


static String getPWD() {
        def ret = ShellStep.sh(returnStdout: true, script: "git tag --sort version:refname | tail -1").trim()
        eho "currently in ${ret}"
    }