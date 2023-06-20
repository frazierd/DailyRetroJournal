package dailyretrojournal.dependency;


import dagger.Component;
import dailyretrojournal.activity.CreateNewJournalEntryActivity;
import dailyretrojournal.activity.DeleteJournalEntryActivity;
import dailyretrojournal.activity.GetAllJournalEntriesActivity;
import dailyretrojournal.activity.GetJournalEntryActivity;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    GetJournalEntryActivity provideGetJournalEntryActivity();

    GetAllJournalEntriesActivity provideGetAllJournalEntriesActivity();

    DeleteJournalEntryActivity provideDeleteJournalEntryActivity();

    CreateNewJournalEntryActivity provideCreateNewJournalEntryActivity();

    /**
     * Provides the relevant activity.
     * @return AddSongToPlaylistActivity
     */
//    AddSongToPlaylistActivity provideAddSongToPlaylistActivity();

    /**
     * Provides the relevant activity.
     * @return CreatePlaylistActivity
     */
//    CreatePlaylistActivity provideCreatePlaylistActivity();

    /**
     * Provides the relevant activity.
     * @return GetPlaylistActivity
     */
//    GetPlaylistActivity provideGetPlaylistActivity();

    /**
     * Provides the relevant activity.
     * @return GetPlaylistActivity
     */
//    SearchPlaylistsActivity provideSearchPlaylistsActivity();

    /**
     * Provides the relevant activity.
     * @return GetPlaylistSongsActivity
     */
//    GetPlaylistSongsActivity provideGetPlaylistSongsActivity();

    /**
     * Provides the relevant activity.
     * @return UpdatePlaylistActivity
     */
//    UpdatePlaylistActivity provideUpdatePlaylistActivity();

}