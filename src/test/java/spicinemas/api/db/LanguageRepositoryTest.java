package spicinemas.api.db;

import org.jooq.DSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import spicinemas.SpiCinemasApplication;
import spicinemas.api.model.Language;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpiCinemasApplication.class)
@ActiveProfiles("test")
public class LanguageRepositoryTest {
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    DSLContext dslContext;

    @Test
    public void shouldGetAllLanguages(){
        List<Language> languages = languageRepository.getLanguages();
        assertTrue(languages.stream().anyMatch(language -> language.getName().equals("English")));
        assertTrue(languages.size() > 1);
    }
}
