pipelineJob('terraform/nonprod/all') {

  parameters {
    choiceParam('ACTION', ['', 'APPLY', 'DESTROY'],'Pick something')
  }

  configure { flowdefinition ->
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://github.com/chandralek/pipelines.git')
            'credentialsId'('GitUserPass')
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('*/master')
          }
        }
      }
      'scriptPath'('Jenkinsfile_All')
      'lightweight'(true)
    }
  }
}
