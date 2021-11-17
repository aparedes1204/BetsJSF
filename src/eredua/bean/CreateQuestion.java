package eredua.bean;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class CreateQuestion {
	
	BLFacade facadeBL;
	private Date eventDate;
	private List<Event> events;
	private Event selectedEvent;
	private String question;
	private float betMin;
	
	
	public CreateQuestion() {
		facadeBL = new BLFacadeImplementation(new DataAccess());
	}
	
	public BLFacade getFacadeBL() {
		return facadeBL;
	}
	
	public void setFacadeBL(BLFacade facadeBL) {
		this.facadeBL = facadeBL;
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
	
	public Event getSelectedEvent() {
		return selectedEvent;
	}
	
	public void setSelectedEvent(Event selectedEvent) {
		this.selectedEvent = selectedEvent;
	}
	
	public String getQuestion() {
		return this.question;
	}
	
	public void setQuestion(String q) {
		this.question=q;
	}
	
	public float getBetMin() {
		return this.betMin;
	}
	
	public void setBetMin(float bm) {
		this.betMin = bm;
	}
	
	public void onDateSelect(SelectEvent event) {
		this.selectedEvent = null;
		this.eventDate = (Date) event.getObject();
		this.events = facadeBL.getEvents(this.eventDate);
	}
	
	public void onEventSelect(SelectEvent event) {
		this.selectedEvent = (Event) event.getObject();
	}
	
	public String createQuestion() {
		if (eventDate == null || selectedEvent == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					 new FacesMessage("Select an event to create a question."));
			return "error";
		}
		
		if(this.question.length() == 0 || this.question == null){
			FacesContext.getCurrentInstance().addMessage(null,
					 new FacesMessage("Cant create an empty question."));
			return "error";
		}
		try {
		facadeBL.createQuestion(selectedEvent, question, betMin);
		return "ok";
		} catch (QuestionAlreadyExist e) {
			FacesContext.getCurrentInstance().addMessage(null,
					 new FacesMessage("The question already exists."));
			return "error";
		} catch (EventFinished e) {
			FacesContext.getCurrentInstance().addMessage(null,
					 new FacesMessage("The event has finished."));
			return "error";
		}
	}
}
