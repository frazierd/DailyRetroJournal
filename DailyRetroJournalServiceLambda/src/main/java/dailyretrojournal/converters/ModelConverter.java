package dailyretrojournal.converters;

import dailyretrojournal.dynamodb.models.JournalEntry;
import dailyretrojournal.models.JournalEntryModel;

import java.util.ArrayList;
import java.util.List;

public class ModelConverter {

    /**
     * Converts a provided {@link dailyretrojournal.dynamodb.models.JournalEntry} into a {@link dailyretrojournal.models.JournalEntryModel} representation.
     *
     * @param journalEntry the journalEntry to convert
     * @return the converted journalEntry
     */

    public JournalEntryModel toJournalEntryModel(JournalEntry journalEntry) {

        return JournalEntryModel.builder()
                .withId(journalEntry.getId())
                .withContent(journalEntry.getContent())
                .withDateEntered(journalEntry.getDateEntered())
                .withHashtag(journalEntry.getHashtag())
                .build();
    }
    /**
     * Converts a list of JournalEntries to a list of JounalEntryModels.
     *
     * @param journalEntries JournalEntry to convert to JournalEntryModels
     * @return The converted list of JournalEntryModels
     */

    public List<JournalEntryModel> toJournalEntryModelList(List<JournalEntry> journalEntries) {
        List<JournalEntryModel> journalEntryModels = new ArrayList<>();

        for (JournalEntry journalEntry : journalEntries) {
            journalEntryModels.add(toJournalEntryModel(journalEntry));
        }
        return journalEntryModels;
    }
}

