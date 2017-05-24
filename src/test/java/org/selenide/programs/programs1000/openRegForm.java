package org.selenide.programs.programs1000; 

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.SoftAssertionError;
import com.codeborne.selenide.impl.Navigator;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.navigator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.codeborne.selenide.Selectors.*;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.junit.AfterClass;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;

public class openRegForm { 
	public void setUp() {
		  //Configuration.timeout = 6000;
		}
	@Given("^an open browser with (.*)")
	public void an_open_browser_with_site(String link) throws Throwable {
		
	   Configuration.browser = "chrome";
	   Configuration.timeout = 6000;
	   //$("#welcome-message").shouldHave(text("Welcome, user!"));
	    
	    open(link);
	}
	@Given("^close browser")
	public void close_browser_with_site(String link) throws Throwable {
		System.out.println("Закрываю");
	    close();
	}
	@Given("^я создаю программу")
	public void create_program() throws Throwable {
		System.out.println("Создаю");
		for (int i=0; i<1000; i++){
		$(byTitle("Новый документ")).click();
		$(byId("name")).setValue("prNamew"+i);
		$(byId("chrono")).setValue("00:00:10:11");
		$(byValue("Добавить")).click();
		sleep(1500);
		$(byValue("Закрыть")).click();
		sleep(1500);
		}
	}

	@When("^I goto (.*)")
	public void i_goto(String arg1) throws Throwable {
		System.out.println("!bro= " +Configuration.browser);
		navigator.open(arg1);
		System.out.println("!bro= " +Configuration.browser);
	}

	@Then("Поле (.*) exists$")
	public void pole_exists(String fieldLabel) throws Throwable {

		findByRussianName(fieldLabel).should(exist);
		
	}
	@Then("Кнопка (.*) exists$")
	public void knopka_exists(String fieldLabel) throws Throwable {

		findByRussianName(fieldLabel).should(exist);
		
	}
	@Then("Поле (.*) отображается$")
	public void pole_otobrajaetsya(String fieldLabel) throws Throwable {

		findByRussianName(fieldLabel).should(exist);
		findByRussianName(fieldLabel).$(byXpath("../../..")).$(byClassName("button__text")).$(byXpath("div/div[@class='attach-doc__text']")).shouldHave(text(fieldLabel));
		
	}
	@Then("Текст (.*) отображается$")
	public void text_otobrajaetsya(String fieldLabel) throws Throwable {
		$(byText(fieldLabel)).shouldBe(visible);
		
	}
	@Then("Попап с текстом (.*) отображается$")
	public void popup_otobrajaetsya(String text) throws Throwable {
		$(byClassName("form-error-popup__content")).shouldHave(text(text));
				
	}
	@Then("Попап с текстом (.*) отсутствует$")
	public void popup_otsut(String text) throws Throwable {
		$(byClassName("form-error-popup__content")).shouldNotHave(text(text));
				
	}
	@Then("Кнопка (.*) отображается$")
	public void knopka_otobrajaetsya(String fieldLabel) throws Throwable {
		$(byText(fieldLabel)).shouldBe(visible);
		
	}
	@Then("^Поле (.*) редактируемое$")
	public void поле_редактируемое(String fieldLabel) throws Throwable {
		findByRussianName(fieldLabel).shouldBe(enabled);
	}
	@Then("^Поле (.*) имеет тип (.*)$")
	public void поле__имеет_тип(String fieldLabel, String fieldType) throws Throwable {
		String autocomplete = "";
		if (fieldType.contains("Текстовое поле")) {
			if (fieldType.contains("с подсказкой")) {
				autocomplete = "on";
			}
			else {
				autocomplete = "off";
			}
			fieldType = "input__control";
		
		
		}
		if (fieldType.equals("Дата")) {
			fieldType = "input__control";
		}
		if (fieldType.equals("Переключатель")) {
			fieldType = "radio__control";
		}
		if (fieldType.equals("Выпадающий список")) {
			fieldType = "select__button";
		}
	    // Write code here that turns the phrase above into concrete actions
		findByRussianName(fieldLabel).shouldHave(attribute("class", fieldType));
		/*if (!autocomplete.equals("")) {
			findByRussianName(fieldLabel).shouldHave(attribute("autocomplete", autocomplete));
			}*/
	}	
	@Then("^Поле (.*) имеет значение (.*)$")
	public void поле_имеет_значение(String fieldLabel, String fieldValue) throws Throwable {
		if (fieldValue.equals("пустое")) {
			fieldValue = "";
		}
		
	   findByRussianName(fieldLabel).shouldHave(value(fieldValue));
	}
	@Then("^Переключатель (.*) имеет значение (.*)$")
	public void переключатель_имеет_значение(String fieldLabel, String fieldValue) throws Throwable {
		if (fieldValue.equals("пустое")) {
			fieldValue = "";
		}
		/*if (fieldValue.equals("Нет")) {
			fieldValue = "false";
		}*/
	    // Write code here that turns the phrase above into concrete actions
		//System.out.println("!1val="+findByRussianName(fieldLabel).val());
		//System.out.println("!1checked="+findByRussianName(fieldLabel).getAttribute("checked"));
		String text1 = "";
		//Integer size1=$$(byAttribute("class", "menu-item__control")).size();
		Integer size1=2;
		for (int i=0; i<2; i++) {
			System.out.println("size="+size1);
			text1 = findAllByRussianName(fieldLabel).get(i).$(byXpath("../button/span")).text();
			System.out.println("text1="+text1+"i="+i);
			if (text1.equals(fieldValue)) {
				findAllByRussianName(fieldLabel).get(i).shouldBe(checked);
			}
		}
		//findAllByRussianName(fieldLabel).filter(text(fieldValue)).get(0).shouldHave(checked);
	}

	@Then("^Поле (.*) имеет длину (\\d+) символов$")
	public void поле_имеет_длину_символов(String fieldLabel, String maxlength) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		findByRussianName(fieldLabel).shouldHave(attribute("maxlength", maxlength));
	}

	@When("^я печатаю (.*) в поле (.*)$")
	public void i_type_to(String fieldText, String fieldLabel) throws Throwable {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.now();
		if (fieldText.equals("сегодня")) {
			
			System.out.println("сегодня="+dtf.format(localDate)); 
			fieldText = dtf.format(localDate);
		}
		else if (fieldText.equals("вчера")) {
			localDate.minusDays(1);
			System.out.println("вчера="+dtf.format(localDate.minusDays(1))); 
			fieldText = dtf.format(localDate.minusDays(1));
		}
		else if (fieldText.equals("завтра")) {
			localDate.plusDays(1);
			System.out.println("сегодня="+dtf.format(localDate.plusDays(1))); 
			fieldText = dtf.format(localDate.plusDays(1));
		}
		else if (fieldText.contains("сегодня-")) {
			if (fieldText.contains("лет")) {
				String numOfYears = fieldText.replace("сегодня-", "").replace("лет", "");

				localDate.minusYears(Integer.parseInt(numOfYears));
				System.out.println("сегодня-="+dtf.format(localDate.minusYears(Integer.parseInt(numOfYears)))); 
				fieldText = dtf.format(localDate.minusYears(Integer.parseInt(numOfYears)));
			}
			else if (fieldText.contains("дней")) {
				String numOfDays = fieldText.replace("сегодня-", "").replace("дней", "");

				localDate.minusDays(Integer.parseInt(numOfDays));
				System.out.println("сегодня-="+dtf.format(localDate.minusDays(Integer.parseInt(numOfDays)))); 
				fieldText = dtf.format(localDate.minusDays(Integer.parseInt(numOfDays)));
			}
		}
	    // Write code here that turns the phrase above into concrete actions
		
		if (findByRussianName(fieldLabel).exists()){
			findByRussianName(fieldLabel).click();
		findByRussianName(fieldLabel).setValue(fieldText);
		}//.pressTab();
		//findByRussianName(fieldLabel).pressTab();
		
	}
	
	@Then("^Поле (.*) содержит значения (.*)$")
	public void поле_содержит_значения(String fieldLabel, String fieldValueList) throws Throwable {
		findByRussianName(fieldLabel).$(byXpath("..")).click();
		List<String> list1 = $$(byAttribute("class", "menu-item__control")).texts();
		System.out.println("list1="+list1);
		for (int i=0; i<$$(byAttribute("class", "menu-item__control")).size(); i++) {
			System.out.println("pu"+i+$$(byAttribute("class", "menu-item__control")).get(i)+"exppu="+fieldValueList.split("; ")[i]);
			$$(byAttribute("class", "menu-item__control")).get(i).shouldHave(text(fieldValueList.split("; ")[i]));
		}
		//$$(byAttribute("class", "menu-item__control")).shouldHave(texts, fieldValueList.split(", "));
	}
	@Then("^Переключатель (.*) содержит значения (.*)$")
	public void переключатель_содержит_значения(String fieldLabel, String fieldValueList) throws Throwable {
		//findByRussianName(fieldLabel).click();
		//List<String> list1;
		//String text1 = "";
		for (int i=0;i<findAllByRussianName(fieldLabel).size();i++) {
		//text1 = findAllByRussianName(fieldLabel).get(i).$(byXpath("../button/span")).text();
		//list1.add(text1);
		findAllByRussianName(fieldLabel).get(i).$(byXpath("../button/span")).shouldHave(text(fieldValueList.split(",")[i]));
		
		}
		}
	

	@When("^click (.*)$")
	public void click(String buttonName) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		if (buttonName.equals("Далее")) {
			buttonName = "Продолжить";
		}
		if ($(byText(buttonName)).exists()){
	    $(byText(buttonName)).click();
		}
	}
	@When("^я нажимаю кнопку (.*)$")
	public void я_нажимаю_кнопку (String buttonName) throws Throwable {
		
	    findByRussianName(buttonName).click();
		
	}
	@Then("^Появляется ошибка (.*)$")
	public void появляется_ошибка (String errorText) throws Throwable {
		//System.out.println("ошибка="+$(byClassName("inline-error")).getText());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate localDate = LocalDate.now();
		if (errorText.contains("вчера")) {
			errorText = errorText.replace("вчера", dtf.format(localDate.minusDays(1)));
			System.out.println("errortext="+errorText);
		}
		if (errorText.contains("сегодня")) {
			errorText = errorText.replace("сегодня", dtf.format(localDate));
			System.out.println("errortext="+errorText);
		}
	    //$(byClassName("inline-error")).shouldHave(text(errorText));
		$(byText(errorText)).shouldBe(visible);
	}
	
	@Then("^Не появляется ошибка (.*)$")
	public void не_появляется_ошибка (String errorText) throws Throwable {
		//System.out.println("ошибка="+$(byClassName("inline-error")).getText());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate localDate = LocalDate.now();
		if (errorText.contains("вчера")) {
			errorText = errorText.replace("вчера", dtf.format(localDate.minusDays(1)));
			System.out.println("errortext="+errorText);
		}
		if (errorText.contains("сегодня")) {
			errorText = errorText.replace("сегодня", dtf.format(localDate));
			System.out.println("errortext="+errorText);
		}
	    //$(byClassName("inline-error")).shouldHave(text(errorText));
		$(byText(errorText)).shouldNot(exist);
	}
	
	@Then("^ошибки в календаре нет$")
	public void ошибки_в_календаре_нет () throws Throwable {
		//System.out.println("ошибка="+$(byClassName("inline-error")).getText());
		$(byClassName("inline-error")).shouldNot(exist);
		
	}
	
	@Given("^я выбираю (.*) в поле (.*)$")
	public void я_выбираю_в_поле(String fieldValue, String fieldLabel) throws Throwable {
		findByRussianName(fieldLabel).$(byXpath("..")).click();
		$(byText(fieldValue)).click();
	}

	@Given("^я выбираю (.*) в переключателе (.*)$")
	public void я_выбираю_в_переключателе(String fieldValue, String fieldLabel) throws Throwable {
	    $(byText(fieldValue)).click();
	}
	
	@When("^я нажимаю (.*) в поле (.*)$")
	public void я_нажимаю_Tab_в_поле_Номер(String fieldLabel, String buttonName) throws Throwable {
	    if (buttonName.equals("Tab")) {
		findByRussianName(fieldLabel).pressTab();
	    }
	    else if (buttonName.equals("Enter")) {
		findByRussianName(fieldLabel).pressEnter();
	    }
	}

	@Then("^появляется ошибка$")
	public void появляется_ошибка() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	@Then("^Поле (.*) отсутствует$")
	public void поле_отсутствует(String fieldLabel) throws Throwable {
	    findByRussianName(fieldLabel).shouldNot(exist);
	}
	@Then("^Кнопка (.*) отсутствует$")
	public void кнопка_отсутствует(String fieldLabel) throws Throwable {
	    findByRussianName(fieldLabel).shouldNot(exist);
	}
	
public SelenideElement findByRussianName (String russianName) {
	String name = "";
	String title = "";
	String id = "";
	String val1 = "";
	String type1 = "";
	SelenideElement findByRussianName;
	if (russianName.equals("Пакет услуг")) {
		name = "servicePackage";
			}
	if (russianName.equals("Удостоверение личности")) {
		name = "documentType";
			}
	else if (russianName.equals("Зарплатный клиент")) {
		name = "isPayroll";
	}
	else if (russianName.equals("Резидент РФ?")) {
		name = "isResident";
	}
	else if (russianName.equals("Фамилия")) {
		name = "lastName";
	}
	else if (russianName.equals("Имя")) {
		name = "firstName";
	}
	else if (russianName.equals("Отчество")) {
		name = "patronymic";
	}
	else if (russianName.equals("ФИО для эмбоссирования")) {
		name = "embossedName";
	}
	else if (russianName.equals("Кодовое слово")) {
		name = "codeWord";
	}
	else if (russianName.equals("Дата рождения")) {
		name = "birthDate";
	}
	else if (russianName.equals("Место рождения")) {
		name = "birthPlace";
	}
	else if (russianName.equals("Пол")) {
		name = "sex";
	}
	else if (russianName.equals("Номер")) {
		name = "number";
	}
	else if (russianName.equals("Кем выдан")) {
		name = "issuer";
	}
	else if (russianName.equals("Код подразделения")) {
		name = "divisionCode";
	}
	else if (russianName.equals("Дата выдачи")) {
		name = "issueDate";
	}
	else if (russianName.equals("Страна")) {
		name = "country";
	}
	else if (russianName.equals("Почтовый индекс")) {
		name = "postalCode";
	}
	else if (russianName.equals("Регион")) {
		name = "region";
	}
	else if (russianName.equals("Населенный пункт")) {
		name = "settlement";
	}
	else if (russianName.equals("Улица")) {
		name = "street";
	}
	else if (russianName.equals("Дом")) {
		name = "building";
	}
	else if (russianName.equals("Корпус")) {
		name = "block";
	}
	else if (russianName.equals("Квартира")) {
		name = "flat";
	}
	else if (russianName.equals("Паспорт гражданина РФ, Основная информация")) {
		name = "attachments.21$main";
	}
	else if (russianName.equals("Паспорт гражданина РФ, Страница регистрации")) {
		name = "attachments.21$registration";
	}
	else if (russianName.equals("Паспорт гражданина РФ, Страница с информацией о ранее выданном паспорте")) {
		name = "attachments.21$oldpassport";
	}
	else if (russianName.equals("Анкета-заявление на Присоединение к ДКБО")) {
		name = "attachments.ANKETA$main";
	}
	else if (russianName.equals("Удостоверение личности")) {
		name = "documentType";
	}
	else if (russianName.equals("Совпадает с адресом регистрации?")) {
		name = "sameAsRegistration";
	}
	else if (russianName.equals("Продолжить")) {
		name = "submit";
	}
	else if (russianName.equals("Далее")) {
		name = "submit";
	}
	else if (russianName.equals("Показать незаполненные поля")) {
		name = "showEmptyField";
	}
	else if (russianName.equals("Тип занятости")) {
		name = "type";
	}
	else if (russianName.equals("ИНН")) {
		name = "inn";
	}
	else if (russianName.equals("ИНН организации")) {
		name = "inn";
	}
	else if (russianName.equals("Название организации")) {
		name = "company";
	}
	else if (russianName.equals("Дата начала работы")) {
		name = "startDate";
	}
	else if (russianName.equals("Должность")) {
		name = "position";
	}
	else if (russianName.equals("Рабочий телефон")) {
		name = "workPhone";
	}
	else if (russianName.equals("Рабочая почта")) {
		name = "workEmail";
	}
	else if (russianName.equals("Мобильный телефон")) {
		name = "mobilePhone";
	}
	else if (russianName.equals("Телефон по месту регистрации")) {
		name = "registrationPhone";
	}
	else if (russianName.equals("Телефон по месту проживания")) {
		name = "actualPhone";
	}
	else if (russianName.equals("Электронная почта")) {
		name = "email";
	}
	else if (russianName.equals("Закончить регистрацию")) {
		name = "endRegistration";
	}
	else if (russianName.equals("Вернуться")) {
		name = "back";
	}
	else if (russianName.equals("Новый документ")) {
		title = "Новый документ";
	}
	else if (russianName.equals("Название")) {
		id = "name";
	}
	else if (russianName.equals("Хронометраж")) {
		id = "chrono";
	}
	else if (russianName.equals("Добавить")) {
		val1 = "Добавить";
	}
	else if (russianName.equals("Логин")) {
		name = "login";
	}
	else if (russianName.equals("Пароль")) {
		name = "password";
	}
	else if (russianName.equals("Отправить")) {
		type1 = "submit";
	}
	
	else {
		System.out.println("Поле не найдено по русскому названию");
	}
	if (!name.equals("")) {
	findByRussianName = $(byName(name));
	}
	else if (!title.equals("")) {
		findByRussianName = $(byTitle(title));
	}
	else if (!id.equals("")) {
		findByRussianName = $(byId(id));
	}
	else if (!val1.equals("")) {
		findByRussianName = $(byValue(val1));
	}
	else if(!type1.equals("")){
		findByRussianName = $(byAttribute("type", type1));	
	}
	else {
		findByRussianName = $(byName(name));	
	}
	
	return findByRussianName;
}
public ElementsCollection findAllByRussianName (String russianName) {
	String name = "";
	ElementsCollection findAllByRussianName;
	if (russianName.equals("Пакет услуг")) {
		name = "servicePackage";
			}
	if (russianName.equals("Удостоверение личности")) {
		name = "documentType";
			}
	else if (russianName.equals("Зарплатный клиент")) {
		name = "isPayroll";
	}
	else if (russianName.equals("Резидент РФ?")) {
		name = "isResident";
	}
	else if (russianName.equals("Фамилия")) {
		name = "lastName";
	}
	else if (russianName.equals("Имя")) {
		name = "firstName";
	}
	else if (russianName.equals("Отчество")) {
		name = "patronymic";
	}
	else if (russianName.equals("ФИО для эмбоссирования")) {
		name = "embossedName";
	}
	else if (russianName.equals("Кодовое слово")) {
		name = "codeWord";
	}
	else if (russianName.equals("Дата рождения")) {
		name = "birthDate";
	}
	else if (russianName.equals("Место рождения")) {
		name = "birthPlace";
	}
	else if (russianName.equals("Пол")) {
		name = "sex";
	}
	else if (russianName.equals("Номер")) {
		name = "number";
	}
	else if (russianName.equals("Кем выдан")) {
		name = "issuer";
	}
	else if (russianName.equals("Код подразделения")) {
		name = "divisionCode";
	}
	else if (russianName.equals("Дата выдачи")) {
		name = "issueDate";
	}
	else if (russianName.equals("Паспорт гражданина РФ, Основная информация")) {
		name = "21.main";
	}
	else if (russianName.equals("Паспорт гражданина РФ, Страница регистрации")) {
		name = "21.registration";
	}
	else if (russianName.equals("Паспорт гражданина РФ, Страница с информацией о ранее выданном паспорте")) {
		name = "21.oldpassport";
	}
	else if (russianName.equals("Анкета-заявление на Присоединение к ДКБО")) {
		name = "attachments.ANKETA$main";
	}
	else if (russianName.equals("Удостоверение личности")) {
		name = "documentType";
	}
	else if (russianName.equals("Совпадает с адресом регистрации?")) {
		name = "sameAsRegistration";
	}
	else if (russianName.equals("Продолжить")) {
		name = "submit";
	}
	else if (russianName.equals("Показать незаполненные поля")) {
		name = "showEmptyField";
	}
	else if (russianName.equals("Тип занятости")) {
		name = "type";
	}
	else if (russianName.equals("ИНН")) {
		name = "inn";
	}
	else if (russianName.equals("ИНН организации")) {
		name = "inn";
	}
	else if (russianName.equals("Название организации")) {
		name = "company";
	}
	else if (russianName.equals("Дата начала работы")) {
		name = "startDate";
	}
	else if (russianName.equals("Должность")) {
		name = "position";
	}
	else if (russianName.equals("Рабочий телефон")) {
		name = "workPhone";
	}
	else if (russianName.equals("Рабочая почта")) {
		name = "workEmail";
	}
	else if (russianName.equals("Мобильный телефон")) {
		name = "mobilePhone";
	}
	else if (russianName.equals("Электронная почта")) {
		name = "email";
	}
	else if (russianName.equals("Телефон по месту регистрации")) {
		name = "registrationPhone";
	}
	else if (russianName.equals("Телефон по месту проживания")) {
		name = "actualPhone";
	}
	else if (russianName.equals("Закончить регистрацию")) {
		name = "endRegistration";
	}
	else if (russianName.equals("Вернуться")) {
		name = "back";
	}
	else {
		System.out.println("Поле не найдено по русскому названию="+russianName);
	}
	findAllByRussianName = $$(byName(name));
	return findAllByRussianName;
}
	
	

}