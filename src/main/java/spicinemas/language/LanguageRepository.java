package spicinemas.language;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spicinemas.db.gen.tables.records.LanguageRecord;

import static spicinemas.db.gen.tables.Language.*;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class LanguageRepository {

    @Autowired
    private DSLContext dsl;

    public List<Language> getLanguages() {
        return dsl.select().from(LANGUAGE).fetchInto(LanguageRecord.class)
                .stream()
                .map(Language:: new)
                .collect(Collectors.toList());
    }
}
