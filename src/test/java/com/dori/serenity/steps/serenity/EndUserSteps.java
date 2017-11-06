package com.dori.serenity.steps.serenity;

import com.dori.serenity.pages.DictionaryPage;
import com.dori.service.RecordAudioService;
import com.dori.service.impl.RecordAudioServiceImpl;

import net.serenitybdd.junit.spring.integration.SpringIntegrationMethodRule;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.junit.spring.SpringIntegration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

import org.junit.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration("/appcontext.xml")
public class EndUserSteps {

    DictionaryPage dictionaryPage;

    @Step
    public void enters(String keyword) {
        dictionaryPage.enter_keywords(keyword);
    }

    @Step
    public void starts_search() {
        dictionaryPage.lookup_terms();
    }

    @Step
    public void should_see_definition(String definition) {
        assertThat(dictionaryPage.getDefinitions(), hasItem(containsString(definition)));
    }

    @Step
    public void is_the_home_page() {
        dictionaryPage.open();
    }

    @Step
    public void looks_for(String term) {
        enters(term);
        starts_search();
    }
    
	@Rule
    public SpringIntegrationMethodRule springIntegration = new SpringIntegrationMethodRule();

    @Autowired
    RecordAudioServiceImpl audioService;
    @Step
	public void is_the_home_audio() {
		audioService.recordAudioFile();
	}
}