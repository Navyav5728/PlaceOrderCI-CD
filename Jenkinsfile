pipeline{
	//pipeline is the outer block of a Jenkins Declarative Pipeline. Jenkins says all valid Declarative Pipelines must be inside a pipeline block.
	agent any//I used agent any so Jenkins can pick any available executor to run my automation suite.
	tools {
        jdk 'JDK17'
        maven 'Maven3'
    }
    parameters {
        choice(name: 'ENV', choices: ['qa', 'uat'], description: 'Select environment')
        choice(name: 'SUITE', choices: ['testng', 'purchase', 'errorValidationTest'], description: 'Select TestNG suite')
        choice(name: 'BROWSER', choices: ['chrome', 'edge'], description: 'Select browser')
    }
    //This gives dropdowns in Jenkins:

/*ENV     → qa / uat
SUITE   → testng / purchase / errorValidationTest
BROWSER → chrome / edge*/
//stages is where we define actual work.
//post runs after pipeline execution.
triggers {
        cron('H 2 * * *')
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Verify Java and Maven') {
            steps {
                sh '''
                    java -version
                    mvn -version
                '''
            }
        }

        stage('Run Automation Tests') {
            steps {
                sh '''
                    mvn clean test \
                    -DsuiteXmlFile=testsuites/${SUITE}.xml \
                    -Dbrowser=${BROWSER} \
                    -Denv=${ENV}
                '''
            }
        }

        stage('Publish Test Results') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }

        stage('Archive Reports') {
            steps {
                archiveArtifacts artifacts: 'test-output/**, reports/**, screenshots/**, logs/**, target/surefire-reports/**', allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed.'
        }

        success {
            echo 'Automation suite passed successfully.'
        }

        failure {
            echo 'Automation suite failed. Check console logs, reports, and screenshots.'
        }
    }
}