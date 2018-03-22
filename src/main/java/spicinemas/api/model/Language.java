package spicinemas.api.model;

import lombok.EqualsAndHashCode;
import spicinemas.db.gen.tables.records.LanguageRecord;

@EqualsAndHashCode(exclude = {"id"})
public class Language {
    private Long id;
    private String name;

    public Language(String name) {
        this.name = name;
    }

    public Language(LanguageRecord languageRecord) {
        this.id = Long.valueOf(languageRecord.getId());
        this.name = languageRecord.getName();

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
