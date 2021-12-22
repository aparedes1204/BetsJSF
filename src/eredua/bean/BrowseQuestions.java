package eredua.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;

public class BrowseQuestions {

	BLFacade facadeBL;
	private Date eventDate;
	private List<Event> events;
	private Event selectedEvent;
	private List<Question> questions;
	private Question selectedQuestion;
	private String answer;
	private float bet;

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public float getBet() {
		return bet;
	}

	public void setBet(float bet) {
		this.bet = bet;
	}

	public Question getSelectedQuestion() {
		return selectedQuestion;
	}

	public void setSelectedQuestion(Question selectedQuestion) {
		this.selectedQuestion = selectedQuestion;
	}

	public BrowseQuestions() {
		facadeBL = FacadeBean.getBusinessLogic();
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Event getSelectedEvent() {
		return selectedEvent;
	}

	public void setSelectedEvent(Event ev) {
		this.selectedEvent = ev;
	}

	public void onDateSelect(SelectEvent event) {
		this.questions = null;
		this.eventDate = (Date) event.getObject();
		this.events = facadeBL.getEvents(this.eventDate);
	}
	
	public void onEventSelect(SelectEvent event) {
		System.out.println((Event) event.getObject());
		this.selectedEvent = (Event) event.getObject();
		this.questions = selectedEvent.getQuestions();
	}
	
	public void onQuestionSelect(SelectEvent event) {
		this.selectedQuestion = (Question) event.getObject();
	}
	
	public String makeBet(String username) {
		if(this.selectedQuestion == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					 new FacesMessage("Please pick a question"));
			return "error";
		}
		if(this.answer == "") {
			FacesContext.getCurrentInstance().addMessage(null,
					 new FacesMessage("Please write an answer"));
			return "error";
		}
		if(this.bet < this.selectedQuestion.getBetMinimum()) {
			FacesContext.getCurrentInstance().addMessage(null,
					 new FacesMessage("You have to bet more than the minimum bet"));
			return "error";
		} else {
			boolean valid = facadeBL.makeBet(selectedQuestion, answer, bet, username);
			if(valid) {
				FacesContext.getCurrentInstance().addMessage(null,
						 new FacesMessage("Bet successfuly created"));
				return "ok";
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						 new FacesMessage("You have already betted to this question"));
				return "error";
			}
		}
	}
	
	public void reset() {
		this.eventDate = null;
		this.events = null;
		this.questions = null;
		this.selectedEvent = null;
		this.answer = "";
		this.bet = 0;
		this.selectedQuestion = null;
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("Hasiera.xhtml");
		    FacesContext.getCurrentInstance().responseComplete();
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
