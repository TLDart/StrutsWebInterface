package webLogic.model;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.concurrent.CopyOnWriteArrayList;

import OnlineVoter.*;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuthService;
import uc.sd.apis.FacebookApi2;

public class HeyBean {
    final String svIP = "localhost";
    final String svName = "SV";
    final int svPort = 3200;
    RMIServerInterface rmiSv;
    String username; // username and password supplied by the user
    String password;
    ArrayList<String> userList = null;
    ArrayList<String> electionsList = null;
    TerminalInfo userInfo = null;
    String electionDataMessage = null;
    private int electionToVote = -1;

    private Token EMPTY_TOKEN = null;
    private String authorizationUrl;
    private String loginFacebookUrl;
    private OAuthService service;
    private OAuthService serviceLogin;
    private static final String PROTECTED_RESOURCE_URL = "https://graph.facebook.com/me";

    public HeyBean() {
        this.createServicesAndLinks();
        try {
            System.out.printf("//%s:%d/%s%n", this.svIP, this.svPort, this.svName);
            this.rmiSv = (RMIServerInterface) Naming
                    .lookup(String.format("//%s:%d/%s", this.svIP, this.svPort, this.svName));
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace(); // what happens *after* we reach this line?
        }
    }

    private void createServicesAndLinks(){
        String apiKey = "187811769882816";
        String apiSecret = "83c70e0ae4c30a49ce23da6bf464d90e";
        //link para associar a conta de facebook
        this.service =  new ServiceBuilder()
                .provider(FacebookApi2.class)
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .callback("http://localhost:8080/tp2_war_exploded/associateFacebook") // Do not change this.
                .scope("public_profile")
                .build();

        this.authorizationUrl = service.getAuthorizationUrl(EMPTY_TOKEN);

        //link para login com o facebook
        this.serviceLogin = new ServiceBuilder()
                .provider(FacebookApi2.class)
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .callback("http://localhost:8080/tp2_war_exploded/loginWithFacebook") // Do not change this.
                .scope("public_profile")
                .build();
        this.loginFacebookUrl = this.serviceLogin.getAuthorizationUrl(EMPTY_TOKEN);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getPersonList(String department, int type) {
        ArrayList<String> s = new ArrayList<>();
        try {
            ArrayList<Person> p = this.rmiSv.getListUsers(department, type);
            if (p.size() == 0) {
                s.add("There are no users for the current query");
            } else {
                for (Person pi : p) {
                    s.add(pi.toString());
                }
            }
            this.userList = s;
            return s;
        } catch (Exception e) {
            this.userList = null;
            return null;
        }
    }

    public ArrayList<String> getElectionList(String department, int type) {
        ArrayList<String> s = new ArrayList<>();
        try {
            ArrayList<Election> p = this.rmiSv.getListElections(department, type);
            if (p.size() == 0) {
                s.add("There are no users for the current query");
            } else {
                for (Election ei : p) {
                    s.add(ei.toString());
                }
            }
            this.electionsList = s;
            return s;
        } catch (Exception e) {
            this.electionsList = null;
            return null;
        }
    }

    public String registerUser(Person p) {
        try {

            this.rmiSv.registerUser(p);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went Wrong");
        }
        return "success";
    }

    public String createVotingList(long electionId, String name, int type, CopyOnWriteArrayList<String> members) {
        String response;
        try {
            response = this.rmiSv.createVotingList(electionId, name, type, members);
            if (response.equals(""))
                return "sucess";
            else {
                System.out.println(response);// dizer o que esta errado
                return "failure";
            }

        } catch (RemoteException e) {
            System.out.println("Something went Wrong");
            return "failure";
        }
    }

    public String createElection(Calendar stTime, Calendar endTime, String description, String title, String department, int type, CopyOnWriteArrayList<VotingListInfo> validDeps) {
        try {
            this.rmiSv.createElection(stTime, endTime, description, title, department, type,
                    validDeps);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went Wrong");
        }
        return "success";
    }

    public ArrayList<String> getPersonList() {
        return this.userList;
    }

    public ArrayList<String> getElectionList() {
        return this.electionsList;
    }

    public String updateTable(String name, long uid, int mode) {
        try {
            this.rmiSv.updateTables(name, uid, mode);
            return "success";
        } catch (RemoteException e) {
            e.printStackTrace();
            return "failure";
        }
    }

    public String updateElection(long uid, Calendar stTime, Calendar endTime, String description, String title, String department) {
        try {
            this.rmiSv.updateElection(uid, stTime, endTime, description, title, department);
            return "success";
        } catch (RemoteException e) {
            e.printStackTrace();
            return "failure";
        }

    }

    public String showFinishedElectionData(long uid) {
        try {
            this.electionDataMessage = this.rmiSv.finishedElectionData(uid);
            if (this.electionDataMessage.equals("")) {
                this.electionDataMessage = "Invalid election's uid or the election hasn't ended yet.";
            }
            System.out.println(this.electionDataMessage);
            return "success";
        } catch (RemoteException e) {
            e.printStackTrace();
            return "failure";
        }
    }

    public String getElectionDataMessage() {
        return this.electionDataMessage;
    }

    public String checkValidUser(int cc, String password) {
        try{
            this.userInfo = this.rmiSv.getPersonInfoWeb(cc, password);
            if(this.userInfo.getP() != null){
                if(this.userInfo.getP().getCcNr() != cc || !this.userInfo.getP().getPassword().equals(password)){
                    return "failure";
                }
                else{
                    return "user";
                }
            }
            else{
                return "failure";
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return "serverDown";
        }
    }
    public ArrayList<String> getValidElections(){
        ArrayList<String> list = new ArrayList<>();
        int i = 0;
        for(Election e: this.userInfo.getValidElections()){
            System.out.println( e.getTitle());
            list.add(i + " "  +e.getTitle());
            i++;
        }
        System.out.println(list);
        return list;
    }

    public String showElection(long uid){
        try {
            this.electionDataMessage = this.rmiSv.electionData(uid);
            if (this.electionDataMessage.equals("")) {
                this.electionDataMessage = "Invalid election's uid or the election hasn't ended yet.";
            }
            System.out.println(this.electionDataMessage);
            return "success";
        } catch (RemoteException e) {
            e.printStackTrace();
            return "failure";
        }
    }

    public String setElectionToVote(int option) {
        if(option < 0 || option > this.userInfo.getValidElections().size()){
            return "failure";
        }
        else{
            this.electionToVote = option;
        }
        return "success";
    }

    public long getElectionToVote() {
        return electionToVote;
    }

    public ArrayList<String> getLists() {
        ArrayList<String> list = new ArrayList<>();
        int i = 0;
        for (VotingList v : this.userInfo.getValidElections()
                .get(this.electionToVote).getLists()) {
            list.add(i + " " + v.getName());
            System.out.println(i + " " + v.getName());
            i++;
        }
        return list;
    }

    public String vote(int option){
        if(option < 0 || option > this.userInfo.getValidElections().get(this.electionToVote).getLists().size()){
            return "failure";
        }
        Election selected = this.userInfo.getValidElections().get(this.electionToVote);
        System.out.println("her");
        System.out.println(this.userInfo.getValidElections().get(this.electionToVote).getLists().get(option).getName());
        System.out.println(this.userInfo.getValidElections().get(this.electionToVote).getLists());
        System.out.println(option);
        System.out.println(this.userInfo.getValidElections().get(this.electionToVote).getUid());
        this.userInfo.setV(new Vote(this.userInfo.getValidElections().get(this.electionToVote).getUid(),"WEB",this.userInfo.getValidElections().get(this.electionToVote).getLists().get(option).getName() ,Calendar.getInstance()));
        System.out.println(this.userInfo.getV().getElectionUid());
        System.out.println(this.userInfo.getV().getListName());
        System.out.println(this.userInfo.getV().getVotingTable());
        try{
             this.rmiSv.processVote(this.userInfo);
             return "success";
        }
        catch (Exception e){
            e.printStackTrace();
            return "serverDown";
        }
    }

    public boolean getValidElectionsSize() {
        System.out.println(this.getValidElections().size() > 0);
        return this.getValidElections().size() > 0;
    }

    public OAuthService getService(){
        return this.service;
    }

    public OAuthService getServiceLogin(){
        return this.serviceLogin;
    }

    public String getAuthorizationUrl() {
        return authorizationUrl;
    }

    public String getLoginFacebookUrl(){
        return this.loginFacebookUrl;
    }

    public String getFacebookId(String code, int type){
        OAuthService service = null;
        //usar o service de login
        if (type == 0) service = this.serviceLogin;
        //usar o service de associacao de facebook
        else if(type == 1) service = this.service;
        Verifier verifier = new Verifier(code);
        Token accessToken = service.getAccessToken(EMPTY_TOKEN, verifier);
        OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL, service);
        service.signRequest(accessToken, request);
        Response response = request.send();
        String str = response.getBody();
        str = str.replace("\"", "").replace("{", "").replace("}", "");
        String [] pieces = str.split("[:,]");
        String facebookId = pieces[3];
        return facebookId;
    }

    public void saveFacebookId(String facebookId){
        try{
            int cc = Integer.parseInt(this.username);
            this.rmiSv.saveFacebookId(cc,  facebookId);
        }catch (RemoteException e){
            System.out.println("Erro a guardar o facebookID.");
        }catch (NumberFormatException e){
            System.out.println("Erro a dar parse da string do cc do user.");
        }
    }

    public boolean verifyFacebookLogin(String facebookId){
        try {
            this.userInfo = this.rmiSv.verifyFacebookLogin(facebookId);
            return this.userInfo != null;
        }catch(RemoteException e){
            //nao consegue conectar a base de dados, da erro no login
            return false;
        }
    }
}
