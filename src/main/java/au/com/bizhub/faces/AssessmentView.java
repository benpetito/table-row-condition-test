package au.com.bizhub.faces;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import org.skyve.CORE;
import org.skyve.impl.web.faces.beans.FacesView;
import org.skyve.persistence.DocumentQuery;

import modules.tableRowConditionTest.domain.Assessment;
import modules.tableRowConditionTest.domain.AssessmentList;

@Named
@RequestScoped
public class AssessmentView extends FacesView<AssessmentList> {

	private static final long serialVersionUID = 2066067326467487183L;

	private String console;
	private String city;
	private String city2;
	private List<String> cities;
	private String color;

	@PostConstruct
	public void init() {
		cities = new ArrayList<>();
		/*cities.add("Miami");
		cities.add("London");
		cities.add("Paris");
		cities.add("Istanbul");
		cities.add("Berlin");
		cities.add("Barcelona");
		cities.add("Rome");
		cities.add("Brasilia");
		cities.add("Amsterdam");*/

		DocumentQuery q = CORE.getPersistence().newDocumentQuery(Assessment.MODULE_NAME, Assessment.DOCUMENT_NAME);
		List<Assessment> assessments = q.beanResults();

		cities.addAll(assessments.stream()
				.map(a -> a.getDescription())
				.collect(Collectors.toList()));
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity2() {
		return city2;
	}

	public void setCity2(String city2) {
		this.city2 = city2;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<String> getCities() {
		return cities;
	}
}
