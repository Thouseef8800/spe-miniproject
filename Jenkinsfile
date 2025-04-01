pipeline {
    agent any

    environment {
        MAVEN_HOME = "/usr/share/maven"
        PATH = "$PATH:$MAVEN_HOME/bin"
        DOCKER_IMAGE = "thouseef8800/scientific-calculator"
        SERVER_IP = "192.168.207.115"
        SSH_KEY_PATH = "/var/lib/jenkins/.ssh/id_rsa"
    }

    stages {
        stage('Configure Access Rights') {
            steps {
                script {
                    sh '''
                    echo "Setting up permissions to Jenkins user.."
                    sudo usermod -aG docker jenkins
                    sudo chown -R jenkins:jenkins /var/lib/jenkins/.ssh
                    sudo chmod 700 /var/lib/jenkins/.ssh
                    '''
                }
            }
        }

        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/Thouseef8800/spe-miniproject.git'
            }
        }

        stage('Cpmpile Project') {
            
            steps {
                dir('calculator'){
                sh 'mvn clean package'
                }
            }
        }
        
        stage('Run Unit Tests') {
            steps {
                 dir('calculator'){
                sh 'mvn test'
            }
        }
        }

        stage('Build Docker Image') {
            steps {
                sh 'sudo docker build -t ${DOCKER_IMAGE} .'
            }
        }

        stage('Upload to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: '1995720d-5a7d-48d3-9f64-f3321ab90224',
                                                  usernameVariable: 'DOCKER_USER',
                                                  passwordVariable: 'DOCKER_PASS')]) {
                    sh '''
                    echo "log into dockerhub"
                    echo "${DOCKER_PASS}" | sudo docker login -u "${DOCKER_USER}" --password-stdin
                    sudo docker push ${DOCKER_IMAGE}
                    '''
                    
                }
            }
        }

        stage('Executing Ansible Playbook') {
            steps {
                script {
                    sh '''
                    

                    echo "Executing Ansible Deployement..."
                    export LC_ALL=en_US.UTF-8
                    export LANG=en_US.UTF-8
                    export LANGUAGE=en_US.UTF-8
                    locale
                    ansible-playbook -i host.ini deploy.yml
                    '''
                }
            }
        }
    }

    post {
        success {
            echo "Pipeline executed successfully! The Scientific Calculator is deployed."
        }
        failure {
            echo "Pipeline failed! Check the logs for errors..."
        }
    }
}
