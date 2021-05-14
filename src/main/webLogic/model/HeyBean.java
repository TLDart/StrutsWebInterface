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

    public HeyBean() {
        try {
            System.out.printf("//%s:%d/%s%n", this.svIP, this.svPort, this.svName);
            this.rmiSv = (RMIServerInterface) Naming
                    .lookup(String.format("//%s:%d/%s", this.svIP, this.svPort, this.svName));
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace(); // what happens *after* we reach this line?
        }
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
                return "user";
            }
            else{
                return "failure";
            }
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("jkaslhdaksljh");
            return "serverDown";
        }
    }
    public ArrayList<String> getValidElections(){
        ArrayList<String> list = new ArrayList<>();
        for(Election e: this.userInfo.getValidElections()){
            System.out.println( e.getTitle());
            list.add(e.getUid() + " "  +e.getTitle());
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

}
