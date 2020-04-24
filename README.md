<b>Technologies:</b><br/>
<br/>
<b>JDK</b>: ver. 1.8<br/>
<b>Maven</b>: ver. 4.0.0<br/>
<b>Spring Boot</b>: ver. 2.0.9<br/>
<b>JPA</b>: ver. 2.2.6<br/>
<b>Lombok</b><br/>
<b>JUnit</b><br/>
<b>H2 Database</b><br/>
<b>swagger2</b>: ver. 2.6.1<br/>
<b>swagger-ui</b>: ver. 2.6.1<br/>
<b>HATEOAS</b>: ver. 2.2.4<br/>
<b>RESTful API</b><br/>
<br/>
<b>H2 Database relation used: </b><br/>
@OneToOne<br/>
@ManyToOne<br/>
<br/>
<b>RESTful API</b><br/>
only POST method was used (because of security recomendations)<br/>
<br/>
<b>API:</b><br/>
POST /medus/getOfferedServices<br/>
POST /medus/createCustomer<br/>
POST /medus/deleteCustomer<br/>
POST /medus/getAccount<br/>
POST /medus/getAllCustomersInfo	 -- HATEOAS with Items self link<br/>
POST /medus/getCustomerAccounts<br/>
POST /medus/getCustomerInfo<br/>
POST /medus/getCustomerMsisdn	-- HATEOAS with Items self link<br/>
POST /medus/getCustomerServices	-- HATEOAS with Items self link<br/>
POST /medus/getOrderedService<br/>
POST /medus/getPhoneNumber<br/>
POST /medus/updateCustomerInfo<br/>
POST /medus/updateCustomerServices<br/>
<br/>
<b>Exception Handling:</b><br/>
ResourceNotFoundException<br/>
MethodArgumentNotValidException<br/>
<br/>
<b>Error Respond handling:</b><br/>
{<br/>
  "timestamp": "2020-04-23T16:12:27.577+0000",<br/>
  "message": "Customer with this Personal Code already exists: 0",<br/>
  "details": "uri=/medus/createCustomer"<br/>
}<br/>
<br/>
<b>User-friendly action confirmation Respond:</b><br/>
{<br/>
  "User was deleted": true<br/>
}<br/>
<br/>
<b>Request validations</b><br/>
<b>RESTful API Controller Integration Tests</b><br/>
<b>H2 Database with presetted schema and data</b><br/>
<b>HATEOAS with items link Respond</b><br/>
<br/>
<b>Database Logic</b>:<br/>
<br/>
·        Offered Service attributes: Id, Name, Type, Description. <br/>
·        Customer attributes: Id, Name, Surname, Company name, Company code, Personal code. One Customer can have multiple Accounts<br/>
·        Account attributes: Id, Name, Address. Account can have multiple MSISDNs (Phone numbers).<br/>
·        MSISDN attributes: Id, Active From, Active To. MSISDN can have multiple Ordered Services.<br/>
·        Ordered Service attributes: Id, ServiceId, Name, Active From, Active To. ServiceId = Service(Id)<br/>
<br/>
Project File structure schema: <b>medus-v0.1_File_Structure_Schema.jpg</b><br/>
<br/>
<b>Start the Application</b>:<br/>
<br/>
<b>Easy way:</b><br/>
1. Download file (only) <b>medus-v0.1.jar</b><br/>
2. Run Command Line (cmd) and go to the folder where you store <b>medus-v0.1.jar</b><br/>
3. In Command Line (cmd) write: <b>java -jar medus-v0.1.jar</b><br/>
4. Application will start (if JAVA is installed on you PC):<br/>
<br/>
<b>swagger-ui console</b>: http://localhost:8080/swagger-ui.html<br/>
<b>H2 Database console (no password)</b>: http://localhost:8080/h2/<br/>
<br/>
<br/>
<b>Long way:</b><br/>
1. Clone project from Gitghub.com: <b>git@github.com:AS2000/medusJava.git</b><br/>
2. Run Command Line (cmd) and go to the folder ~/<b>medusJava</b>/<br/>
3. Run: <b>mvn clean install</b><br/>
4. Got to the folder: /<b>target</b>/<br/>
5. <b>java -jar medus-v0.1.jar</b><br/>
<br/>
<b>swagger-ui console</b>: http://localhost:8080/swagger-ui.html<br/>
<b>H2 Database console (no password)</b>: http://localhost:8080/h2/<br/>
<br/>
