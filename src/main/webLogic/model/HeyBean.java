package webLogic.model;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;
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

	public HeyBean() {
		try {
			System.out.printf("//%s:%d/%s%n", this.svIP, this.svPort, this.svName);
			this.rmiSv =(RMIServerInterface) Naming
					.lookup(String.format("//%s:%d/%s", this.svIP, this.svPort, this.svName));
		}
		catch(NotBoundException|MalformedURLException|RemoteException e) {
			e.printStackTrace(); // what happens *after* we reach this line?
		}
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<String> getPersonList(String department, int type){
		ArrayList<String> s = new ArrayList<>();
		try{
			ArrayList<Person> p= this.rmiSv.getListUsers(department, type);
			if(p.size() == 0){
				s.add("There are no users for the current query");
			}
			else{
				for(Person pi : p){
					s.add(pi.toString());
				}
			}
			this.userList = s;
			return s;
		}
		catch(Exception e){
			this.userList = null;
			return null;
		}
	}

	public ArrayList<String> getElectionList(String department, int type){
		ArrayList<String> s = new ArrayList<>();
		try{
			ArrayList<Election> p= this.rmiSv.getListElections(department, type);
			if(p.size() == 0){
				s.add("There are no users for the current query");
			}
			else{
				for(Election ei : p){
					s.add(ei.toString());
				}
			}
			this.electionsList = s;
			return s;
		}
		catch(Exception e){
			this.electionsList = null;
			return null;
		}
	}
	public String registerUser(Person p){
		try{

			this.rmiSv.registerUser(p);
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("Something went Wrong");
		}
		return "success";
	}
	public String createElection(Calendar stTime, Calendar endTime, String description, String title, String department, int type, CopyOnWriteArrayList<VotingListInfo> validDeps){
		try{
			this.rmiSv.createElection(stTime, endTime, description, title, department, type,
					validDeps);
		} catch (Exception e){
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
}
