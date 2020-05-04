pipelineJob('terraform/nonprod/rdsmysql') {
  configure { flowdefinition ->
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://gitlab.com/batch46/robo-shop/user.git')
            'credentialsId'('GitLab')
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('*/master')
          }
        }
      }
      'scriptPath'('Jenkinsfile')
      'lightweight'(true)
    }
  }
}
