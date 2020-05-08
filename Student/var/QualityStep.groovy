pipeline {
    agent any
    parameters {  

		text(name: 'Release Notes', defaultValue: '', description: 'Enter some information about the Build,if required')
		
        booleanParam(name: 'Unit_Testing', defaultValue: true, description: '')

        choice(name: 'Size', choices: ['My Unit Test', 'Full Unit Test'], description: '')
		
		booleanParam(name: 'Code_Coverage', defaultValue: true, description: '')

        choice(name: 'Measure', choices: ['Incremental', 'Full Suite'], description: '')
		
		booleanParam(name: 'Code_Scan', defaultValue: true, description: '')

        choice(name: 'Extend', choices: ['Incremental', 'Full Suite'], description: '')
		
		booleanParam(name: 'Intergreted_Testing', defaultValue: true, description: '')

        //choice(name: 'Quantity', choices: ['NewTest', 'Selected', 'FullSuite'], description: '')
        choice(name: 'Quantity', choices: ['NewTest','FullRegressionSuite'], description: '')
		
		choice(name: 'Pipeline_Performance', choices: ['Yes', 'No'], description: '')

        
    }
    stages {
        stage('Unit Testing-New Code') {
		when {
			allOf {
                    expression { params.Unit_Testing == true }
					expression { params.Size == 'My Unit Test' }
					//expression { return 'MyUnitTest'}
					//expression { params.Size == /('P1'||'P2')/ }
					//expression { params.Size == 'P1' }
                }
            }
            steps {
			echo "Unit Testing: ${params.Size}"
			
            }

        }
		
		stage('Unit Testing-Full Code') {
		when {
			allOf {
                    expression { params.Unit_Testing == true }
					expression { params.Size == 'Full Unit Test' }
					expression { return 'Unit Suite'}
					//expression { params.Size == /('P1'||'P2')/ }
					//expression { params.Size == 'P1' }
                }
            }
            steps {
			echo "Unit Testing: ${params.Size}"
			
            }

        }
		
		stage('Code Coverage - New Code') {
		when {
                expression { params.Code_Coverage == true }
				expression { params.Measure == 'Incremental' }
            }
            steps {

                echo "Code Coverage: ${params.Measure}"
                
				}

            }
        
		
		stage('Code Coverage - Full Code') {
		when {
                expression { params.Code_Coverage == true }
				expression { params.Measure == 'Full Suite' }
            }
            steps {

                echo "Code Coverage: ${params.Measure}"
                }

            }
        
		
		stage('Code Scan') {
		when {
               expression { params.Code_Scan == true }
               
            }
            steps {

                echo "Code Coverage: ${params.Extend}"
				//bat label: '', script: 'mvn -f Student/pom.xml clean install -DCoveredRatio=1.0'

            }
			}
        
	stage('Progression Test') {
		when {
                expression { params.Intergreted_Testing == true }
				expression { params.Quantity == 'NewTest' }
            }
            steps {

                echo "Integrated Testing: ${params.Quantity}"
                
				
            }
        }
		
		stage('Regression Test') {
		when {
                expression { params.Intergreted_Testing == true }
				expression { params.Quantity == 'FullRegressionSuite' }
            }
            steps {

                echo "Integrated Testing: ${params.Quantity}"
                
				
            }
        }
		stage('Pipeline Performance') {
		when {
                // Only say hello if a "greeting" is requested
                expression { params.Pipeline_Performance == 'Yes' }
            }
            steps {

                echo "Code Coverage: ${params.Pipeline_Performance}"

            }
        }
    }
}
