import org.custommonkey.xmlunit.*
import java.util.Random  
import java.security.MessageDigest
import java.nio.file.*


    def groovyUtils = new com.eviware.soapui.support.GroovyUtils(context)
    def projectPath = groovyUtils.projectPath
    log.info projectPath
    Properties properties = new Properties()
    File propertiesFile = new File('C:\\Jenkins\\workspace\\CastIronDeployment\\publishPar.properties')
    propertiesFile.withInputStream {properties.load(it)}
    
    def project = testRunner.testCase.testSuite.project
        log.info "Project: " + project.name
        def myTestSuite = testRunner.testCase.testSuite;
        log.info "TestSuite: " + myTestSuite.name

    def testCase = testRunner.testCase
    log.info "TestCase: " + testCase.name

    def testStepUploadDataAfterCheck = testCase.getTestStepByName("PublishSOAPRequest")
    def request= testStepUploadDataAfterCheck.testRequest
    log.info "TestStep: " + testStepUploadDataAfterCheck.name


    // clear existing attachments
    for( a in request.attachments ) {
        request.removeAttachment( a )
    }

    
    def propFileNamePath='FileNamePath'
    def propFileName='FileName'
    //FileNamePath
    //def fileNamePath = testCase.getTestStepAt(testRunner.testCase.getTestStepIndexByName("FileNameProperties")).getProperty("FileNamePath")
    def fileNamePath = properties."$propFileNamePath"
   	//log.info fileNamePath
    //log.info properties."$propFileNamePath"
    //FileName
    //def fileName = testCase.getTestStepAt(testRunner.testCase.getTestStepIndexByName("FileNameProperties")).getProperty("FileName")
    def fileName = properties."$propFileName"
    //log.info fileName

    // get file to attach
   // log.info "file to attach: " + fileNamePath.getValue()
   log.info "file to attach: " + fileNamePath
    //def file = new File(fileNamePath.getValue() )
    def file = new File(fileNamePath)
    if ( file == null) {
        log.error "bad filename"
    }   
    else 
    {
        // attach and set properties
        def attachment = request.attachFile( file, true )
        attachment.contentType = "application/octet-stream"
        //attachment.setPart(fileName.getValue())
        attachment.setPart(fileName)

        def holder2 = groovyUtils.getXmlHolder( "PublishSOAPRequest#Request" ) // Get Request body
        //holder2.setNodeValue( "//dep:publishProject/content","cid:"+fileName.getValue()); //Set "link" to attachment in request body
        holder2.setNodeValue( "//dep:publishProject/content","cid:"+fileName); //Set "link" to attachment in request body
        holder2.updateProperty() //and update
        //log.info fileName.getValue()
        log.info fileName
        log.info "file attached succesfully"
    }