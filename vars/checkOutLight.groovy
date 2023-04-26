def call(String repoUrl, String branchName, String checkoutUser){
	log.info("Chekout Repo: ${repoUrl}")
	log.info("Chekout Branch: ${branchName}")
	checkout(
		changelog: true,
		poll: false, 
		scm: [
		       $class: 'GitSCM',
		       branches: [[name: '*/'+branchName]],
		       doGenerateSubmoduleConfigurations: false,
		       extensions: [
			       [$class: 'WipeWorkspace'],
			       [$class: 'CloneOption', timeout: 30 ,honorRefspec: true, noTags: true, reference: '', shallow: false]
		       ], 
		       submoduleCfg: [], 
		       userRemoteConfigs: [[url: repoUrl,
			   refspec: '+refs/heads/'+branchName+':refs/remotes/origin/'+branchName, 
			   credentialsId: "${checkoutUser}"]]
		])
	log.info("Light Checkout Complete")
}
