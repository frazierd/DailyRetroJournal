import RetroJournalClient from '../api/retroJournalClient';
import PlaylistClient from '../api/musicPlaylistClient'; // had to bring in this file because it has the logic for cognito user to be signed in
import BindingClass from "../util/bindingClass";
import Header from '../components/header';
import DataStore from "../util/DataStore";

class SaveJournalEntry extends BindingClass {
    constructor() {
        super();
        const methodsToBind = [
          'clientLoaded',
          'mount',
          'saveEntry'
          ];
          this.bindClassMethods(methodsToBind, this);
          this.dataStore = new DataStore();
          this.header = new Header(this.dataStore);
          }

          async clientLoaded() {
              const identity = await this.client.getIdentity();
              if (identity == undefined) {
                  this.clientPlaylist.login();
                   }
              console.log(identity + "this is letting you know what is going on with your identity");
              const newJournalEntry = await this.client.saveJournalEntry();
              this.dataStore.set('newEntry', newJournalEntry);
              console.log(newJournalEntry + "this is letting you know what is going on with your new entry")
              this.dataStore.set('email', identity.email);
              }

          mount() {
            this.client = new RetroJournalClient();
            this.clientLoaded();
            c
          }

          async saveEntry(entry) {
          try {
          const result = await this.client.saveJournalEntry(entry);
                console.log('Journal entry saved:', result);
          }catch (err) {
          console.error('Error saving jounral entry:', err);

          }
        }

        const saveJournalEntry = new SaveJournalEntry();
        const saveButton = document.getElementById('save-button');
        saveButton.addEventListener('click', async () => {
          const journalEntryTextarea = document.getElementById('journal-entry');
          const entry = journalEntryTextarea.value.trim();

          if (entry.length > 0) {
             const currentDate = new Date().toISOString();
                const hashtag = 'your-hashtag'; // Replace with the desired hashtag
                await saveJournalEntry.createJournalEntry(entryContent, currentDate, hashtag);
                // Reload the journal entries sidebar
                await saveJournalEntry.loadJournalEntries();
              } else {
                console.log('Empty journal entry. Nothing to save.');
              }
            });
// Main method to run when the page contents have loaded
const main = async () => {
  const saveJournalEntry = new saveJournalEntry();
  saveJournalEntry.mount();
};

window.addEventListener('DOMContentLoaded', main);

