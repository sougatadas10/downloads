Properties properties = new Properties()
File propertiesFile = new File('C:\\Jenkins\\workspace\\CastIronDeployment\\publishPar.properties')
propertiesFile.withInputStream {properties.load(it)}
def enableStart='enableStart'

if (properties."$enableStart"=="true"){
	def testStep = testRunner.testCase.getTestStepByName( "StartRequest" )
	log.info testStep.disabled
	if(testStep.disabled){
		testStep.disabled = false 
		}
	log.info testRunner.testCase.getTestStepByName( "StartRequest" ).disabled
}
else if (properties."$enableStart"=="false") {
	log.info "Orchestration will not be started"
	def testStep = testRunner.testCase.getTestStepByName( "StartRequest" )
	log.info testStep.disabled
	if(!testStep.disabled){
		testStep.disabled = true 
		}
	log.info testRunner.testCase.getTestStepByName( "StartRequest" ).disabled
	}
else
	log.info "Incorrect value for enableStart in properties file." 	

	
