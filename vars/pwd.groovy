static String getPWD() {
        def ret = sh(returnStdout: true, script: "git tag --sort version:refname | tail -1").trim()
        eho "currently in ${ret}"
    }