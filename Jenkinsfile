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
                sh "${tool name: 'sbt', type: 'org.jvnet.hudson.plugins.SbtPluginBuilder$SbtInstallation'}/bin/sbt clean test" 

            }
        }
        stage('Docker Publish') {
            steps {
                // Gera um Jenkinsfile .
                sh "${tool name: 'sbt', type: 'org.jvnet.hudson.plugins.SbtPluginBuilder$SbtInstallation'}/bin/sbt docker:tag"

                // Realiza a construção salvando a imagem em container docker
                script {
                    docker.withTool('docker') {
                        docker.build('insightlab:latest', 'target/docker/stage')
                    }
                }
            }
        }
    }
}
