[![CircleCI](https://circleci.com/gh/orestwojtowicz/Mail.svg?style=svg)](https://circleci.com/gh/orestwojtowicz/Mail)






# Java Mail Application

Desktop application implemented using JavaFX. In order to connect with gmail server, application is using IMAP and
to send messages SMTP. User can log in to his account and download all mails with all kind of MIME types and display
them in application window. Also, user is able to sent messages and add any king of attachments.

## Getting Started

To launch application, you just need at least Java 10 installed, because JFoenix library is configured with this kind of Java version.
[download Java 10](https://www.oracle.com/java/technologies/java-archive-javase10-downloads.html)
Application is not converted to .exe, so also IDE is mandatory. You can grab free IntelliJ trial there:
[download IDE]([download Java 10](https://www.oracle.com/java/technologies/java-archive-javase10-downloads.html))
You need to be aware that this is third party software, which is connecting to your inbox. By default, google is blocking this kind of
connections. You can turn off this setting there:
[allows third party apps](https://support.google.com/accounts/answer/3466521?hl=en)

### About Application

Application was build using gradle and designed with MVC pattern. To set connecting with gmail server I implemented EmailAccountBean.class.
This class holds account address and password. Also main properties from JavaMail API Properties, Session, Store.<br>
Properties use HashTable to store core mail connections variables.
Session is establishing connection with gmail. In this project I used Session.getInstance, because when new user is added I want to create 
new mail session, so each user has his own mail session.<br>
Store is sending my properties, and connecting to server. If connection is OK, LOGIN_STATE_SUCCEEDED = 3. This approach will help to test this application.<br> 
<br>

Application Threads
<br>
Each main feature of application is handled in different thread. We can include downloading attachments, creating new account, sending email, fetching folders,
fetching messages into folders, refreshing mail content every 10 seconds, rendering email content messages. 
<br>
This services can be found in src/main/java/com/damian/controller/services/
<br>
To manage threads in JavaFX application we can use
<br>
```
public abstract class Service<V> implements Worker<V>, EventTarget
```
Next step is to extends abstract class Service
```
public class AttachmentsHandleService extends Service<Void>
```
At the end we can just @Override Task
```
  @Override
    protected Task<Void> createTask() {
        return new Task<>(){
            @Override
            protected Void call() {

                for(MimeBodyPart mpb: message.getAttachmentsList()) {
                    try {
                        updateProgress(message.getAttachmentsList().indexOf(mpb),
                                  message.getAttachmentsList().size());
                        mpb.saveFile(LOCATION_OF_DOWNLOADS + mpb.getFileName());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

        };
    }
   ```
 
  This service is responsible for saving MIME files. All code which is under Task is going to be run in different Thread.
  So in this case, we have main application thread + this service thread.
  All other services use the same abstract class for multithreading.
    <br><br>
  Application content is managed by MainController, EmailDetailsController and ComposeMessageController. Is is a place, there all @FXML content is being loaded.
  Also new instances of services are created and started.
  <br>
  FXML templates, scenes and stylesheets are handled by ViewFactory.class. There is static reference to this class, which is triggered in Main.class to initialize whole application content.




## Built With

* [JavaFX](https://openjfx.io/) - allows Java creating desktop application
* [JFoenix](http://www.jfoenix.com/) - styling buttons, labels etc.
* [JavaMail](https://javaee.github.io/javamail/) - Provides necessary classes for connection and downloading messages
* [Gradle](https://maven.gradle.org/) - Dependency Management

## Features to add
- application needs to be tested
- refactor ViewFactory.class



## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details


