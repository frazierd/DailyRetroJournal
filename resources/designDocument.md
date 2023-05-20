# Design Document

## The Daily Retro Journal

## 1. Problem Statement

The absence of an efficient and organized system for tracking personal daily activities and reflections hinders individuals from effectively analyzing their progress and achieving their goals. There is a need for a web application that allows users to create and manage journal entries with optional hashtags, enabling them to easily retrieve and review entries based on specific dates or hashtags, thereby allowing the user to not only created the environment for self reflection, but also a way to query relevant data on their goals and progress.

The Daily Retro Journal, offers users a simple and intuitive interface to create, view, store and manage their journal entries. With support for hashtags and advanced filtering options, users can easily find entries related to specific topics or activities. In addition, integration with Amazon Cognito ensures that user data is private and secure. By using Daily Retro Journal, users can gain valuable insights into their daily routines and progress towards their goals.

## 2. Top Questions to Resolve in Review

## 3. Use Cases


U1. **Creating a New Journal Entry:**

- As a Daily Retro Journal user, I want to record a new journal entry with relevant functionality (Hashtags).
- User clicks '+' button to open a new journal template (top right corner).
- User enters entry (with the option to use template prompt *stretch goal)
- Entry is updated to the list of journal entries


U2. **Creating a Tag:**

- As a Daily Retro Journal user, I want to add tags to specific journal entries.
    - User adds tag to the 'tag' bar in the bottom of the editor on the journal entry.
    - after entry of single tag user presses 'save tag' button
    - user repeats steps for additional tags


U2. **Viewing a scrollable list of Journal Entries sorted by Date/Time:**

- As a Daily Retro Journal user, I want to see a record of journal entries at the left of the page, sorted by date with most recent at the top of the table.
    - User sees table of recently added journal entries, sorted by most recent date to the left of the page.
    - Each entry in the scrollable list is a button that can be clicked to display the full entry in the main editor pane of the page.


U3. **Viewing a scrollable list of Journal Entries sorted by  #Hashtags:**

- As a Daily Retro Journal user, I want to see a list of entries with a particular tag.
- User selects " view all tags " button in the bottom left of the editor on the journal entry.
- A list of tags is displayed in alphabetical order on the main editor pane of the page.
- once a tag is selected by user (each tag displayed is clickable) the list of journal entries containing that tag is displayed in the left pane of the page that previously contained all the recent journal entries
- the user can then select the entry by clicking it from the list displayed and the journal entry will be displayed in the main editor pane.


U4. **Editing/Updating Existing Journal Entries:**

- As a Daily Retro Journal user, I want to make changes to existing journal entries.
- User selects the journal entry to be edited from the list of journal entries on right pane
- User modifies content in the journal entry
- Entry is updated


U5. **Deleting Existing Journal Entries:**

- As a Daily Retro Journal user, I want to be able to delete existing journal entries.
- User selects "delete" button in the bottom left of the editor on the journal entry to be deleted and clicks delete option.
- pop up of successful delete is displayed
- the scrollable list of journal entries is updated to reflect the deleted entry.


STRETCH GOALS :

- suggested tags (from previous tags) in the tag page
- export a journal entry
- the ability for the editor to recognize # inside of the body of the entry.



## 4. Project Scope

### 4.1. In Scope

- Creating journal entries
- Optional attaching hashtags to journal entries
- Delete journal entries
- Editing journal entries
- Searching journal entries by tags

### 4.2. Out of Scope

- Suggested tags
- Exporting journal entries

# 5. Proposed Architecture Overview

The Minimum Viable Product (MVP) includes adding new Journal entries to a list of journal entries, as well as editing or deleting entries. Adding hashtags to individual entries, as well as sorting entries by hashtags.

Utilizes API Gateway and Lambda to create endpoints (`GetJournalEntries`, `PostJournalEntry`, `PutUpdateJournalEntry`, `DeleteJournalEntry`, `PostHashtagToEntry`, `GetHashtagsToEntry`)

1. `GetJournalEntries:`
- GET /entries/all{entryId}
  Retrieve a specific journal entry by its ID.
- Path parameter: entryId.
  Response: Journal entry details.

2. `PostJournalEntry:`
- POST /entries/create
- Request body: Title, content, date, hashtags.
- Response: Created entry details.

3. `PutUpdateJournalEntry:`
- PUT /entries/{entryId}
  Update an existing journal entry.
- Path parameter: entryId.
- Request body: Updated title, content, date, hashtags.
- Response: Journal entry details.

4. `DeleteJournalEntry:`
- DELETE /entries/{entryId}
  Delete a specific journal entry by its ID.
- Path parameter: entryId.
- Response: Success message.

5. `PostHashtagToEntry:`
- POST /entries/{entryId}/hashtags
  Add a hashtag to a specific journal entry.
- Path parameter: entryId (ID of the journal entry).
- Request body: Hashtag to be added.
- Response: Update entry details.

6. `GetHashtagsToEntry`
   -GET /hashtags
   Retrieve a list of previously used hashtags.
- Response: Success status code (e.g., 200 OK) response body containing an array of previously used hashtags.

The Journal entries and collections of hashtags are stored in DynamoDB.

The Daily Retro Journal provides a web interface for users to access and manage their journal entries and tagging functionality, utilizing Cognito to validate users based on the unique emailID

The main page will populate a list of previous journal entry summaries (title and date) as well as a button for a new entry.
It will also provide links to find and use recommended hashtags (stretch goal)




# 6. API

## 6.1. Public Models

add new entry, view entries, delete entry, add hashtag, (delete hashtag?)

```
// JournalEntry (model)
     String id;
     String title;
     String content;
     ZoneDateTime dateEntered;
     Array[String] hashtag;

```

```
// Hashtag (model)
     String hashtag; partition key
     ZoneDateTime createdAt; sort key

```

```
// CreateJournalEntryRequest (model)
     Sting title:
     String content;
     zoneDateTime date;
     Array[String] hashtag;
```
```
// JournalEntrySummary(model)
     String id;
     String title;
     zoneDateTime date;

```
## 6.2.```RetrieveAllJournalEntry:```

- Accepts a GET request to /entries/all
- Accepts a entryId and returns a specific journal entry.
    - If the journal entry for the specified entryId is not found then returns a JournalEntryForIDNotfound exception (400 exception).

## 6.3 ```CreateJournalEntry:```
- Accepts a POST request to /entries/create
- Accepts data to create a new journal entry with provided Title, Content, Date, and Hashtags
- Returns a newly generated journal entry.

## 6.4  ```UpdateJournalEntry:```
- Accepts a PUT request to /entries/{entryId}
- Accepts entryId to update the journal entry.
- If the journal entry for the specified entryId is not found then returns a JournalEntryForIDNotfound exception (400 exception).


## 6.5 ```DeleteJournalEntry:```
- Accepts a DELETE request to /entries/{entryId}
- Deletes a specific journal entry by its ID.
- Returns a success message confirming the deletion.

## 6.6 ```AddHashtagToEntry:```
- Accepts a POST request to /entries/{entryId}/hashtags
- Accepts a valid hashtag to be added to entry with valid entryId
- Response: Details of the updated journal entry with the added hashtag.
    - If the journal entry for the specified entryId is not found then returns a JournalEntryForIDNotfound exception (400 exception).

## 6.7 ```GetPreviouslyUsedHashtags```
- Accepts a GET request to /hashtags
- Retrieves an ArrayList of previously used hashtages

# 7. Tables

### 7.1. `UserTable`

```
userId // partition key, String
name // String
email // String
```

### 7.2. `JournalEntryTable`
```
entryId // partition key, String
title // sort key, String
hashtags // ArrayList<String>
content // String
date // (localDateTime)
```
### 7.3. `JournalEntryIndexTable`
```

entryId //  String
title // String
hashtags // partition key, ArrayList<String>
date // (localDateTime)

```
### 7.3 `HashtagsTable`
```
hashtag // partition key, String
entryId // list <String>
userId // String
```

# 8. Pages

![](wireframe.png)

