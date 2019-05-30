# qCreator

qCreator is a Java program that generates questionnaires. It randomize questions and
connects them with filler questions.

## Requirements

You need Java to run this program.

## Versions

1.0 Initial release

## Usage

Format questions sentences in txt file (ANSI/Cp1252").
Format filler questions in txt.filler file (ANSI/Cp1252") that has the same name as the main question file.

Example: 
question_project.txt
question_project.txt.filler

To check right formatting, copy questions & filler here https://regex101.com/ and use regEx: 
"(?<pN>[0-9]{1,}) (?<sN>[0-9]{1,}) (?<vN>[0-9]{1,}) (?<sQ>[^!!!]{1,})!!! (?<qQ>.{1,})"

Drag and drop question_project.txt onto run.bat/run.sh
qCreator creates completely finished questionnaires in LaTex code (ISO-8859-1).

## Tex Template

Code between "!QUESTIONS!" will be used as question layout.
Always save the template in ANSI/Cp1252.

## Parameters

1. Question file path
2. Maximum amount of filler questions in a row (standard is set to 1)

If no .jar path is found the program exports "C:\"

## Question Formatting

Example: "1 1 1 Tom wants to paint a colorful canary. !!! This one has"

First number:	Represents project number. Set project number to 0 to mark filler question.
second number: 	Represents sentence number.
Third number: 	Represents sentence version.
Text until !!! represents setup line.
Text after !!! represents reply line.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
Copyright 2019 © Paul Eduard Koenig 
rezzer101@googlemail.com

