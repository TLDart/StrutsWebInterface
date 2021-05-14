package OnlineVoter;

import javax.websocket.*;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;
import java.rmi.server.UnicastRemoteObject;

@ServerEndpoint(value = "/rtd")
public class PrintInfoAnnotation extends UnicastRemoteObject implements PrintInfoAnnotationInterface{

    private static final AtomicInteger sequence = new AtomicInteger(1);
    private Session session;
    private static final Set<PrintInfoAnnotation> users = new CopyOnWriteArraySet<>();
    RMIServerInterface rmisv;
    private String svIP = "localhost";
    private int svPort = 3200;
    private String svName = "SV";

    public PrintInfoAnnotation() throws RemoteException{}

    @OnOpen
    public void start(Session session) {
        this.session = session;
        users.add(this);
        try{
            this.rmisv = (RMIServerInterface) Naming
                    .lookup(String.format("//%s:%d/%s", this.svIP, this.svPort, this.svName));
            this.rmisv.wbsSubscribe(this);
        }catch (Exception e){
            //erro
            System.out.println("Deu erro no RMIServer.");
            return;
        }
    }

    @OnClose
    public void end() {
        // clean up once the WebSocket connection is closed
        users.remove(this);

        try{
            this.rmisv.wbsDeSubscribe(this);
        }catch (Exception e){
            System.out.println("DEu erro a remover o websocket.");
        }
    }

    @OnMessage
    public void receiveMessage(String message) {
        //String upperCaseMessage = message.toUpperCase();
        //sendMessage(upperCaseMessage);
        try {
            long electionId = Long.parseLong(message);
            sendMessage(this.rmisv.electionData(electionId));
        }catch(RemoteException e){
            sendMessage("Error connecting to the database.");
        }catch(NumberFormatException e){
            sendMessage("Check if the electionId given is correct.");
        }
    }

    @OnError
    public void handleError(Throwable t) {
        t.printStackTrace();
    }

    private void sendMessage(String text) {
        // uses *this* object's session to call sendText()
        try {
            for (PrintInfoAnnotation wba : users){
                wba.session.getBasicRemote().sendText(text);
            }
            //this.session.getBasicRemote().sendText(text);
        } catch (IOException e) {
            // clean up once the WebSocket connection is closed
            try {
                this.session.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public Session getSession(){
        return this.session;
    }

    public void sendRealTimeData(String str) throws RemoteException {
        this.sendMessage(str);
    }
}
