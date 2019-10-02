pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo "Compilando..."
                sh "${tool name: 'sbt', type: 'org.jvnet.hudson.plugins.SbtPluginBuilder$SbtInstallation'}/bin/sbt compile"
            }
        }
        stage('Testes Unitários') {
            steps {
                echo "Testando ..."
#                sh "${tool name: 'sbt', type: 'org.jvnet.hudson.plugins.SbtPluginBuilder$SbtInstallation'}/bin/sbt coverage 'test-only * -- -F 4'"
                sh "${tool name: 'sbt', type: 'org.jvnet.hudson.plugins.SbtPluginBuilder$SbtInstallation'}/bin/sbt coverageReport"
                sh "${tool name: 'sbt', type: 'org.jvnet.hudson.plugins.SbtPluginBuilder$SbtInstallation'}/bin/sbt scalastyle || true"
            }
        }
        stage('Docker Publish') {
            steps {
                // Gera um Jenkinsfile .
                sh "${tool name: 'sbt', type: 'org.jvnet.hudson.plugins.SbtPluginBuilder$SbtInstallation'}/bin/sbt docker:stage"

                // Realiza a construção salvando a imagem em container docker
                script {
                    docker.withTool('docker') {
                        docker.build('my-app:latest', 'target/docker/stage')
                    }
                }
            }
        }
    }
}
