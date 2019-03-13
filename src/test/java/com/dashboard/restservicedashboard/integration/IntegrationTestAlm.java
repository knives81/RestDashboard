package com.dashboard.restservicedashboard.integration;


import org.springframework.beans.factory.annotation.Value;

/*@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(
		  locations = {"classpath:application-int.properties"},
		  properties = { "key=value" })*/
public class IntegrationTestAlm {
	
	private static final String USERNAME = "mpinzi";
	private static final String PASSWORD = "pa33w0rd";
	
	@Value("${server.port}")
    private String port;
    
    /*@Test
    public void getMockChartData0() throws UnirestException {       	
    	given().auth().preemptive().basic(USERNAME, PASSWORD)
    	.when().get(getUrl()+"chartdatamock/0")
    	.then().body("labels",hasItems("Passed", "Failed", "Not Completed"));    	
    }  
    
    @Test
    public void getMockChartItemConf() throws UnirestException {       	
    	given().auth().preemptive().basic(USERNAME, PASSWORD)
    	.when().get(getUrl()+"chartitemconfmock")
    	.then()
    	.body("[0].chartType",equalTo("PIECHART"))
    	.body("[1].chartType",equalTo("LINECHART"));    	
    }   */
    
    private String getUrl() {
    	return "http://localhost:"+port+"/";
    }
}
