import RetroJournalClient from '../api/retroJournalClient';
import PlaylistClient from '../api/musicPlaylistClient'; // had to bring in this file because it has the logic for cognito user to be signed in
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";


/**
 * Logic needed for the view journal entry summary section of the website.
 */

class ViewAllJournalEntries extends BindingClass {
  constructor() {
    super();
    const methodsToBind = [
      'clientLoaded',
      'mount',
      'readDataStoreAddJournalEntryToSummary',
      'viewJournal',
      'deleteEntry',
      'saveEntry'
    ];

    this.bindClassMethods(methodsToBind, this);
    this.dataStore = new DataStore();
    this.dataStore.addChangeListener(this.readDataStoreAddJournalEntryToSummary);
    this.header = new Header(this.dataStore);
  }

    async clientLoaded() {
    console.log("client loaded{}");
        const identity = await this.client.getIdentity();
        console.log("client loaded identity{}", identity);
        if (identity == undefined) {
        console.log("client playlist login{}");
            this.clientPlaylist.login();
             }
        console.log(identity + "this is letting you know what is going on with your identity");
        const journalEntries = await this.client.getAllJournalEntries();
        console.log("journalEntries: {}", journalEntries);
        this.dataStore.set('journals', journalEntries);
        this.dataStore.set('selectedJournalId', journalEntries[0].id);
//        console.log (journalEntries + "these are suppose to be your journal entries");
        this.dataStore.set('email', identity.email);
      }


    mount() {

        // Fetch the journal entries
        this.client = new RetroJournalClient();
        this.clientPlaylist = new PlaylistClient();
        this.clientLoaded();
        const deleteButton = document.getElementById('delete');
              deleteButton.addEventListener('click', this.deleteEntry);
        const saveButton = document.getElementById('save');
          saveButton.addEventListener('click', this.saveEntry);
       }

   async viewJournal(event) {
   event.preventDefault();
//   console.log(event.target.dataset.journalId + "this is where I am wanting to see the WHOLE journal");
    const journalId = event.target.dataset.journalId;
    const selectedJournalEntry= this.dataStore.get('journals').find(entry => entry.id === journalId);
    this.dataStore.set('selectedJournalId', journalId);
    if (selectedJournalEntry) {
        const journalEntryFullText = document.getElementById('journal-entry');
        journalEntryFullText.value = ""; // Clear the current text box
        journalEntryFullText.value = selectedJournalEntry.content;
        }
   }

    async readDataStoreAddJournalEntryToSummary() { //made this a very descriptive name about how it is reading
        let journalEntries = this.dataStore.get('journals');
        // Get the journal entry summary container
        const summaryContainer = document.getElementById('journal-entry-summary');

      // Loop through the entries and create the HTML elements
        journalEntries.forEach((entryObj) => {
//        console.log(JSON.stringify(entryObj) + " this is the entryObject LOOK FOR THE ALL CAPS");
//        const entry = entryObj.PutRequest.Item;

      // Create the container div for each entry
        const entryContainer = document.createElement('div');
        entryContainer.classList.add('journal-entry-button'); //eventually this will be the button that calls the GET journal entry but {entryID}

        entryContainer.dataset.journalId = entryObj.id;
        entryContainer.addEventListener('click', this.viewJournal);
            // Create the date box
          const dateBox = document.createElement('div');
          dateBox.classList.add('date-box');
//          console.log(dateBox + "this is the datebox created");

          //Get the date from the entry
          const epochTime = parseInt(entryObj.dateEntered, 10) *1000;

          //convert epoch to date object
         const entryDate= new Date(epochTime);
//         console.log(entryDate + "this should be converting to a new date");

         //made abreviated months to replace the epoch month number
         const monthAbbreviations = [
          "JAN",
          "FEB",
          "MAR",
          "APR",
          "MAY",
          "JUN",
          "JUL",
          "AUG",
          "SEP",
          "OCT",
          "NOV",
          "DEC"
        ];

          // Create the entry content
          const month = monthAbbreviations[entryDate.getMonth() + 1];
          const day = entryDate.getDate();
          const year = entryDate.getFullYear();
//          console.log(month, day, year + "this should convert epoch to date");


          // Set the inner HTML of the date box
          dateBox.innerHTML = `<span class="month">${month}</span>
                               <span class="day">${day}</span>
                               <span class="year">${year}</span>`;

          // Create the entry content
          const entryContent = document.createElement('div');
          entryContent.classList.add('entry-content');
          const maxPreviewLength = 30; // Maximum length of the preview content
          const previewContent = entryObj.content.length > maxPreviewLength
            ? entryObj.content.substring(0, maxPreviewLength) + "..." // Add "..." if the content exceeds the maximum length
            : entryObj.content;

          entryContent.textContent = previewContent;

          // Append the date box and entry content to the entry container
          entryContainer.appendChild(dateBox);
          entryContainer.appendChild(entryContent);

//          console.log(entryContainer);

          // Append the entry container to the summary container
          summaryContainer.appendChild(entryContainer);
        });
    }
    async deleteEntry() {
    console.log("deleteEntry");
    const selectedJournalId = this.dataStore.get('selectedJournalId');
    console.log(selectedJournalId);
    if (!selectedJournalId) {
        return; // if there is no selected Id then there is nothing to delete
        }

        try {
            await this.client.deleteEntry(selectedJournalId);
            const journalEntryFullText = document.getElementById('journal-entry');
            journalEntryFullText.value = ""; // this clears the text box and reloads the journal entry summary
            window.location.href = "/";
          } catch (error) {
          console.error('ERROR deleting journal entry:', error);
          }
    }

     async saveEntry() {
            const journalEntryContent = document.getElementById('journal-entry').value;
            const newEntry = {
                  content: journalEntryContent
                };
                try {
                //call API to save the entry
                await this.client.saveJournalEntry(newEntry);
                //clear the text field
                document.getElementById('journal-entry').value = "";
                //refresh the summary section
                await this.clientLoaded();
              } catch (error) {
                console.error('ERROR saving journal entry:', error);
              }
     }
}
// Main method to run when the page contents have loaded
const main = async () => {
  const viewAllJournalEntries = new ViewAllJournalEntries();
  viewAllJournalEntries.mount();
};

window.addEventListener('DOMContentLoaded', main);