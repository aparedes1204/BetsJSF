package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Bet {
	
	@Id 
	@GeneratedValue
	private Integer betId;
	private String option;
	private float bet;
	@OneToOne
	private User user;
	@OneToOne
	private Question question;
	
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Bet(User user, String option, float bet, Question question) {
		this.user = user;
		this.option = option;
		this.bet = bet;
		this.question = question;
	}
	
	public Bet() {
		super();
	}
	
	
	public Integer getBetId() {
		return betId;
	}
	public void setBetId(Integer betId) {
		this.betId = betId;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public float getBet() {
		return bet;
	}
	public void setBet(float bet) {
		this.bet = bet;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	} 

}
