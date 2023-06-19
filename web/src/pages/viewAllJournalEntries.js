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
      'readDataStoreAddJournalEntryToSummary'
    ];
    this.bindClassMethods(methodsToBind, this);
    this.dataStore = new DataStore();
    this.dataStore.addChangeListener(this.readDataStoreAddJournalEntryToSummary);
    this.header = new Header(this.dataStore);
  }

    async clientLoaded() {
        const identity = await this.client.getIdentity();
        if (identity == undefined) {
            this.clientPlaylist.login();
             }
        console.log(identity + "this is letting you know what is going on with your identity");
        const journalEntries = await this.client.getAllJournalEntries();
        this.dataStore.set('journal', journalEntries);
        console.log (journalEntries + "these are suppose to be your journal entries");
        this.dataStore.set('email', identity.email);
      }


    mount() {

        // Fetch the journal entries
        this.client = new RetroJournalClient();
        this.clientPlaylist = new PlaylistClient();
        this.clientLoaded();

      }

    async readDataStoreAddJournalEntryToSummary() { //made this a very descriptive name about how it is reading
        let journalEntries = this.dataStore.get('journal');
        // Get the journal entry summary container
        const summaryContainer = document.getElementById('journal-entry-summary');

      // Loop through the entries and create the HTML elements
        journalEntries.forEach((entryObj) => {
        console.log(JSON.stringify(entryObj) + " this is the entryObject LOOK FOR THE ALL CAPS");
//        const entry = entryObj.PutRequest.Item;

      // Create the container div for each entry
        const entryContainer = document.createElement('div');
        entryContainer.classList.add('journal-entry-button'); //eventually this will be the button that calls the GET journal entry but {entryID}

            // Create the date box
          const dateBox = document.createElement('div');
          dateBox.classList.add('date-box');
          console.log(dateBox + "this is the datebox created");

          //Get the date from the entry
          const epochTime = parseInt(entryObj.dateEntered, 10) *1000;

          //convert epoch to date object
         const entryDate= new Date(epochTime);
         console.log(entryDate + "this should be converting to a new date");

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
          console.log(month, day, year + "this should convert epoch to date");


          // Set the inner HTML of the date box
          dateBox.innerHTML = `<span class="month">${month}</span>
                               <span class="day">${day}</span>
                               <span class="year">${year}</span>`;

          // Create the entry content
          const entryContent = document.createElement('div');
          entryContent.classList.add('entry-content');
          const previewContent = entryObj.content.substring(0, 30); // Get the first 20 characters
          entryContent.textContent = previewContent;

          // Append the date box and entry content to the entry container
          entryContainer.appendChild(dateBox);
          entryContainer.appendChild(entryContent);

          console.log(entryContainer);

          // Append the entry container to the summary container
          summaryContainer.appendChild(entryContainer);
        });
    }
}
// Main method to run when the page contents have loaded
const main = async () => {
  const viewAllJournalEntries = new ViewAllJournalEntries();
  viewAllJournalEntries.mount();
};

window.addEventListener('DOMContentLoaded', main);