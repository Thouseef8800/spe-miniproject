pipeline {
    agent any

    environment {
        MAVEN_HOME = "/usr/share/maven"
        PATH = "$PATH:$MAVEN_HOME/bin"
        DOCKER_IMAGE = "thouseef8800/scientific-calculator"
        DOCKER_USERNAME = "thouseef8800"
        DOCKER_PASSWORD = "Sonu@8800"
        SERVER_IP = "192.168.207.115"
        SSH_KEY_PATH = "/var/lib/jenkins/.ssh/id_rsa"
    }

    stages {
        stage('Setup Permissions') {
            steps {
                script {
                    sh '''
                    echo "Granting permissions to Jenkins user.."
                    sudo usermod -aG docker jenkins
                    sudo mkdir -p /var/lib/jenkins/.ssh
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

        stage('Build') {
            steps {
            dir('calculator') {  // Navigate to the correct directory
                sh 'mvn clean install'
                }
            }
        }

        stage('Test') {
            steps {
            dir('calculator') {  // Navigate to the correct directory
                sh 'mvn test'
            }
        }
        }

        stage('Containerize Application') {
            steps {
                dir('calculator'){
                sh 'sudo docker build -t ${DOCKER_IMAGE} .'
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    sh '''
                    echo "Logging in to Docker Hub.."
                    echo "${DOCKER_PASSWORD}" | sudo docker login -u "${DOCKER_USERNAME}" --password-stdin
                    sudo docker push ${DOCKER_IMAGE}
                    '''
                }
            }
        }

        stage('Run Ansible Deployment') {
            steps {
                script {
                    sh '''
                    echo "Creating Ansible hosts.ini file..."
                    echo "[servers]" > hosts.ini
                    echo "${SERVER_IP} ansible_user=pavan ansible_ssh_private_key_file=${SSH_KEY_PATH}" >> hosts.ini

                    echo "Running Ansible Playbook..."
                    ansible-playbook -i hosts.ini deploy.yml
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
            echo "Pipeline failed! Check the logs for errors."
        }
    }
}
