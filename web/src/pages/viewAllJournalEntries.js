import DailyRetroJournalClient from '../api/DailyRetroJournalClient';
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
      'fetchJournalEntriesToSummary'
    ];
    this.bindClassMethods(methodsToBind, this);
    this.dataStore = new DataStore();
    this.dataStore.addChangeListener(this.fetchJournalEntriesToSummary);
    this.header = new Header(this.dataStore);
  }

    async clientLoaded() {
        const identity = await this.client.getIdentity();
        const profile = await this.client.getProfile(identity.email);
        const journalEntries = await this.client.getAllJournalEntries();
        this.dataStore.set('email', identity.email);
        this.dataStore.set('profile', profile);
        this.fetchJournalEntriesToSummary();
      }

    mount() {
        // Get the journal entry summary container element
        const summaryContainer = document.getElementById('journal-entry-summary');

        // Fetch the journal entries
        this.fetchJournalEntriesToSummary();
      }

    async fetchJournalEntriesToSummary() {
        const journalEntries = await this.client.getAllJournalEntries();

      // Loop through the entries and create the HTML elements
        journalEntries.forEach((entry) => {
      // Create the container div for each entry
        const entryContainer = document.createElement('div');
        entryContainer.classList.add('journal-entry-button');

            // Create the date box
          const dateBox = document.createElement('div');
          dateBox.classList.add('date-box');
          dateBox.innerHTML = `<span class="month">${entry.month}</span>
                               <span class="day">${entry.day}</span>
                               <span class="year">${entry.year}</span>`;

          // Create the entry content
          const entryContent = document.createElement('div');
          entryContent.classList.add('entry-content');
          const previewContent = entry.content.substring(0, 20); // Get the first 20 characters
          entryContent.textContent = previewContent;

          // Append the date box and entry content to the entry container
          entryContainer.appendChild(dateBox);
          entryContainer.appendChild(entryContent);

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