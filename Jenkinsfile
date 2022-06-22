
// #!/usr/bin/env groovy
// library identifier: "msp-jenkins-lib@${env.BRANCH_NAME}", retriever: modernSCM(github(traits: [gitHubPullRequestDiscovery(2)], credentialsId: 'gh-svc-nable-logicnow', repository: 'msp-jenkins-lib', repoOwner: 'logicnow'))

// // Loading PECN DEV cluster details
// HashMap<String,String> cluster_details = cluster.clusterDetails("clusters/pecn/dev/01-uswe2")

pipeline {
    agent none
    options {
        ansiColor('xterm')
        #disableConcurrentBuilds()
    }
    stages {
        stage('Unit Tests') {
            agent {
                kubernetes {
                    cloud "jenkins-cloud"
                    #namespace 'jenkins'
                    defaultContainer 'gradle'
                    yamlFile 'build.yaml'
                }
            }
            environment {
                GRADLE_OPTS = "-Xmx2048m -Dorg.gradle.daemon=false"
            }
            steps{
                sh './gradlew test'
            }
        }
//         stage("Build PECN DEV integration pipeline") {
//             when {
//                 beforeAgent true
//                 branch 'master'
//             }
//             agent {
//                 kubernetes {
//                     cloud "slr"
//                     namespace 'build-pecn'
//                     defaultContainer 'skaffold'
//                     label "shared-library-validation"
//                     yaml skaffold.envWithIRSA('serviceAccountName': 'pecn-dev')
//                 }
//             }
//             steps{
//                 build(job: 'SLR/clusters/pecn/dev/slr-pecn-dev-01-uswe2-integration', parameters: [
//                         string(name: 'branch_name', value: env.BRANCH_NAME),
//                         string(name: 'cluster_path', value: cluster_details.cluster_path)],
//                         wait: true,
//                         propagate: true
//                 )
//             }
//         }
    }
}
