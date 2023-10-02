## Vanisher

Code to illustrate how best to delete Discord messages by certain users in bulk. Please note that this project is not a perfect solution at all, and is written using Java & Maven, where it would preferably be Kotlin & Gradle.

It WILL miss occasional messages, so it's worth running an in-client search, eg `from:stephen in:general` to identify and remove any leftovers.

You can use this to help protect or renew your online identity without being bound to (tens of) thousands of messages in a server. 

This does not break Discord TOS as no self botting is involved, meaning the bot must have Manage Message permissions on the server it is invited to.

Please understand that this bot runs at the mercy of Discord, which is naturally slow the more messages it searches and the further back in time it goes. Also, the bot will get rate limited, but JDA works in such a way that it will reschedule those tasks, so very few should be missed. Discord does not really want users doing this, which is why it can be slow and painful.

### How to use

You can clone the repository and insert your own bot token to run it yourself. Feel free to make any changes you want, and if they are general improvements to the project then PRs would be welcome.

Once you've changed the user IDs and channels to search, then simply run the bot and it will do an immediate task as well as one every day (you can change this).

Note: You probably just want this repo to scrape the code and move to your own bot. That seems the best option.

### Warning
This repository is not actively maintained and feature requests will almost certainly be rejected. This comes "as-is". PRs will be reviewed.