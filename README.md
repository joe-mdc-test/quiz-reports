# quiz-reports

This is a spark report on quiz-results.

To run the report use:

`sbt test`

This will generate a report "QuizReport" based on the sample json data provided, and check that it matches the given sample report.

The dummy json was created using this tool:
https://next.json-generator.com/VyCcCEkgU


The template has been provided as `jsonTemplate.json` incase you want to play around with it.

To run this you will require the latest sbt & spark. This can be installed using `brew` on mac or `apt-get` on linux.
