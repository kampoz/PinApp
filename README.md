## Pin App

The application is written in Kotlin.

The application allows you to generate six-digit pins and delete them.
The generated pin is validated.

Adding new pins is done by clicking the button with the "+" symbol. 
The pin is generated automatically and consists of 6 digits. No digit can be repeated more than 3 times.
The validator checks the correctness of the generated pin.
Deleting pins is done by clicking the trash icon on the item in the pins list.

The application has unit tests of the logic generating pins.

Pin data is stored in the Room database.

The application uses the HIlt library for dependency injection.