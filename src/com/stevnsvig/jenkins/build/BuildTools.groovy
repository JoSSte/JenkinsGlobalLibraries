#!groovy
package com.stevnsvig.jenkins.build

//Jenkins Pipeline build utilities

/**@
  * Functions to ease build process
  */
class BuildTools {
    static String getModifiedConfig() {

        def changeLogSets = currentBuild.changeSets
        for (int i = 0; i < changeLogSets.size(); i++) {
            def entries = changeLogSets[i].items
            for (int j = 0; j < entries.length; j++) {
                def entry = entries[j]
                echo "${entry.commitId} by ${entry.author} on ${new Date(entry.timestamp)}: ${entry.msg}"
                def files = new ArrayList(entry.affectedFiles)
                for (int k = 0; k < files.size(); k++) {
                    def file = files[k]
                    echo "  ${file.editType.name} ${file.path}"
                }
            }
        }
    }

    /*
     * delete failed and or aborted jobs
     */
    static void deleteJobs(ArrayList<String> jobnamelist, Boolean deleteFailed = true, Boolean deleteAborted = true){
        for(String jobstring in jobnamelist) {
            def joblist = getJobList(jobstring)
            for(String jobname in joblist) {
                print("Scanning job " + jobname + " for failed and aborted builds")
                def job = Jenkins.instance.getItemByFullName(jobname) 
                job.getBuilds().each {
                    //print(it.getNumber())
                    if((deleteFailed && it.result == Result.FAILURE) || (deleteAborted && it.result == Result.ABORTED)){
                        print("Deleting job " + jobname + " run # " + it.getNumber())
                        // Delete failed job
                        it.delete()
                    }
                }
            }
        }
    }

    /*
     * get list of jobs for a pipeline
     */
    static ArrayList<String> getJobList(String pipelineName) {

        def hi = hudson.model.Hudson.instance;
        def item = hi.getItemByFullName(pipelineName);
        def jobs = item.getAllJobs();

        def arr = new ArrayList<String>();

        Iterator<?> iterator = jobs.iterator();
        while (iterator.hasNext()) {
            def job = iterator.next();
            arr.add(pipelineName + "/" + job.name);
        }
        return arr;
    }    
}