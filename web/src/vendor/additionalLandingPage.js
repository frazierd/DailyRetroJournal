document.addEventListener('DOMContentLoaded', () => {
  // Auto-save functionality
  const journalEntry = document.getElementById('journal-entry');

  journalEntry.addEventListener('input', () => {
    const entryContent = journalEntry.value;
    const formattedContent = formatJournalEntry(entryContent);
    localStorage.setItem('journalEntry', formattedContent);
  });

  // Retrieve saved entry on page load
  const savedEntry = localStorage.getItem('journalEntry');
  if (savedEntry) {
    journalEntry.value = savedEntry;
  }

  function formatJournalEntry(entry) {
    const regex = /#(\w+)/g;
    // Replace hashtags with colored and clickable versions
    const formattedEntry = entry.replace(regex, (match) => {
      const hashtag =  match.substr(1); // remove the "#" character
      return `<span class="hashtag" onclick="handleHashtagClick(event)"><span class="hashtag-symbol">#</span>${hashtag}</span>`;
    });
    return formattedEntry;
  }

  // Helper Function to extract hashtags from a string
  function extractHashtags(text) {
    const regex = /#(\w+)/g;
    return text.match(regex);
  }

  // Add click event listener to journal entry buttons
  const journalEntryButtons = document.querySelectorAll('.journal-entry-button');

  journalEntryButtons.forEach((button) => {
    const contentElement = button.querySelector('.journal-entry-content');
    const contentText = contentElement.textContent;
    const hashtags = extractHashtags(contentText);

    // Replace hashtags with clickable links
    if (hashtags) {
      hashtags.forEach((hashtag) => {
        const hashtagWithSymbol = '#' + hashtag.substring(1); // Add back the "#" symbol
        const link = `<span class="hashtag">${hashtagWithSymbol}</span>`;
        contentElement.innerHTML = contentElement.innerHTML.replace(hashtag, link);
      });
    }
  });

  // Add click event listener to hashtags in the text editor
  const journalEntryEditor = document.getElementById('journal-entry');
  journalEntryEditor.addEventListener('click', (event) => {
    if (event.target.classList.contains('hashtag')) {
      // Handle the click event on the hashtag
      const clickedHashtag = event.target.textContent;
      // Do something with the clicked hashtag
      console.log(`Clicked hashtag: ${clickedHashtag}`);
    }
  });
});

// Make an HTTP request to your AWS endpoint
fetch('http://127.0.0.1:3000/entries/all')
  .then(response => response.json())
  .then(data => {
    // Process the data and generate the sidebar entries
    const sidebar = document.querySelector('.cell.medium-4.medium-cell-block-y');
    data.forEach(entry => {
      const button = document.createElement('button');
      button.classList.add('journal-entry-button');

      // Create the date box
      const dateBox = document.createElement('div');
      dateBox.classList.add('date-box');
      const month = document.createElement('div');
      month.classList.add('month');
      month.textContent = entry.month;
      const day = document.createElement('div');
      day.classList.add('day');
      day.textContent = entry.day;
      const year = document.createElement('div');
      year.classList.add('year');
      year.textContent = entry.year;
      dateBox.appendChild(month);
      dateBox.appendChild(day);
      dateBox.appendChild(year);

      // Create the entry content
      const entryContent = document.createElement('div');
      entryContent.classList.add('entry-content');
      entryContent.textContent = entry.content;

      // Append the date box and entry content to the button
      button.appendChild(dateBox);
      button.appendChild(entryContent);

      // Append the button to the sidebar
      sidebar.appendChild(button);
    });
  })
  .catch(error => {
    console.log('Error:', error);
  });