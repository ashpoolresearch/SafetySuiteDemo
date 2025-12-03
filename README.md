### Assumptions
- the database design is very similar to one from my whiteboarding session. the java class setup is based on database instead of the overcooked one from the interview
- the id field is generated
- most fields are set by setters instead of constructors
- the addition of new users is somewhat outside the scope of the task so no in-api validation is implemented. there's a required tag in the fields for the front end but posting invalid users - via api may break it
- no auth / security for the demo
- front end was vibe-coded in 5 minutes and is very basic
- currently a bug that says an employee is failed to be deleted but upon refresh the delete goes through