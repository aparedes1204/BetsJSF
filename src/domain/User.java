package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	@Id 
	@GeneratedValue
	private Integer userId;
	private String username; 
	private String password;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Bet> bets=new ArrayList<Bet>();
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User() {
		super();
	}
	
	public List<Bet> getBets(){
		return this.bets;
	}

	public void setQuestions(List<Bet> bets) {
		this.bets = bets;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean doesBetExist(Question question) {
		for(Bet bet: bets) {
			if (bet.getQuestion().equals(question)) {
				return true;
			}
		}
		return false;
	}
	
	public void addBet(Question question, String answer, float bet) {
		Bet newbet = new Bet(this, answer, bet, question);
		bets.add(newbet);
	}

}
