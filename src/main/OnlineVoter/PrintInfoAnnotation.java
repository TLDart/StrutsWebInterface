package OnlineVoter;

import javax.websocket.*;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;
import java.rmi.server.UnicastRemoteObject;

@ServerEndpoint(value = "/rtd/{parameter}")
public class PrintInfoAnnotation extends UnicastRemoteObject implements PrintInfoAnnotationInterface{

    private static final AtomicInteger sequence = new AtomicInteger(1);
    private Session session;
    private static final Set<PrintInfoAnnotation> users = new CopyOnWriteArraySet<>();
    RMIServerInterface rmisv;
    private String svIP = "localhost";
    private int svPort = 3200;
    private String svName = "SV";

    private int mode;//se e um socket de uma pagina para listar os users ou de uma lista

    public PrintInfoAnnotation() throws RemoteException{}

    @OnOpen
    public void start(Session session, @PathParam("parameter") String param) {
        this.session = session;
        users.add(this);
        this.mode = Integer.parseInt(param);
        try{
            this.rmisv = (RMIServerInterface) Naming
                    .lookup(String.format("//%s:%d/%s", this.svIP, this.svPort, this.svName));
            this.rmisv.wbsSubscribe(this);
        }catch (Exception e){
            //erro
            System.out.println("Deu erro no RMIServer.");
        }
        System.out.printf("mode: %d\n", mode);
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
            sendMessage(this.rmisv.electionData(electionId), 0);
        }catch(RemoteException e){
            sendMessage("Error connecting to the database.", 0);
        }catch(NumberFormatException e){
            sendMessage("Check if the electionId given is correct.", 0);
        }
    }

    @OnError
    public void handleError(Throwable t) {
        t.printStackTrace();
    }

    private void sendMessage(String text, int mode) {
        // uses *this* object's session to call sendText()
            try {
                /*
                if (mode == 1) {
                    for (PrintInfoAnnotation wba : users) {
                        wba.session.getBasicRemote().sendText(text);
                    }
                }
                else if (mode == 0){
                    this.session.getBasicRemote().sendText(text);
                }
                 */
                this.session.getBasicRemote().sendText(text);
            } catch (IOException e) {
                // clean up once the WebSocket connection is closed}
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

    public void sendRealTimeData(String str, int type) throws RemoteException {
        //type = 0 -> mensagem com info dos users online
        //type = 1 -> mensagem com info dos votos
        if (type == this.mode) {
            this.sendMessage(str, 1);
        }
    }
}
