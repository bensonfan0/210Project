# My Personal Program

## myTrainingpal

The *What*, *Who*, and *Why*:

-What will this application do? <br>
-Who will use it? <br>
-Why is this project of interest to me?

This application will create a personalized workout program tailored to customizable inputs which are user entered. <br>
Notable examples of personalization would include *time availability* , *strength or size orientated training*, and <br>
*specific muscle group focus*. First, time availability will address hours available per workout, and days per week <br>
available. In the final version, the app will use a calendar interface to show which days to work out and how long  <br>
each workout should last (possibly including rest periods and/or set duration). <br>
Secondly, programs will be based on pre-existing strength and conditioning programs such as GZCL, nSuns CAP3, <br>
Candito 6 week etc. While body-building specific programs will be built from volume programs such as KIZEN 4 <br>
week, PHUL, PHAT, etc. <br>
Lastly, any additional accessories will be chosen with intent to maximize growth of specific "lacking" muscle groups <br>
in each training mesocycle. <br>
<br>
The application will be targeted towards "beginner/intermediate" lifters who, by following a strict workout regiment, <br>
will be more conducive to strength and volume growth. Whereas "advanced" lifters may require greater personalization <br>
which will not be offered in the first edition of this application. <br>
<br>
I aim to help friends and colleagues achieve a healthy lifestyle by using realistic and science-based workout <br>
programs in an easy-to-use app. I am a strong believer that in bettering one's physical body, one will also help <br>
maintain a more balanced mental health. 


## User Stories
As a user, I want to be able to add training instructions to my calendar. <br>
As a user, I want to be able to control the length of my training sessions. <br>
As a user, I want to be able to control the days I train. <br>

As a user, I want to be able to choose between Strength or Hypertrophy focused programs <br>

As a user, I want to be able to select which muscle groups to focus on. <br>
As a user, I want to be able to document my lifts (either as kg and/or lbs). <br>
As a user, I want to be able to select which primary compound movement to focus on. <br>
As a user, I want to be able to see technique cues associated with each lift/exercise. <br>

As a user, I want to be able to save my personalizations <br>
As a user, I want to be able to reload my personalizations when I start the app <br>


## Instructions for Grader

Start app through main Class. <br>
Click "Initialize" to begin creating a new profile. <br>
At the moment only the "novel" option works. <br>
Click on any one of the body parts which you wish to focus on. <br>
Then select the days which you can train and press continue when ready. <br>
Click on the time which best suites your schedule. <br>
Currently, only the "strength" option works. <br>
Input your estimated maximum one rep maxes for each lift (should be integers) <br>

The final screen is the primary user interface screen. From here you can see your profile info, and through <br>
the setting options in the menuBar you can choose to Save, Exit, or Edit your profile info. <br>
When you save your profile and exit, you can reload from the initial login Screen. <br>

At the bottom is a button which you click when you achieve a new Personal Record on one of your lifts. <br>
!!WARNING!! <br>
The sound component can incite excessive adrenaline and the visual component can be very arousing. <br>



Side note: <br>
Currently the strength program is a linear strength program, so if you're curious feel free to give the <br>
a try, I ran a similar program for a few months and saw excellent results! <br>

Cheers!

## PHASE 4: task 2

Made MyStrengthProgram class more robust by using three exceptions. One handling the situation where the program encounters <br>
too many days in a week (ie 8 days a week), another where the desired body part chosen is not defined in the program, and <br>
lastly another exception where a user chooses to train all 7 days of the week.

The tests for checking exceptions are found in the "MyStrengthProgramTests".

## PHASE 4: task 3
Improving Cohesion: <br>
The class MyStrengthProgram used to deal with both choosing training days (based on user preference), AND creating the <br>
actual training days themselves (The instructions for each day). To improve cohesion, the class MyStrengthProgram now <br>
subclasses a superclass named TrainingDayBuilder. TrainingDayBuilder handles the actual instructions regarding what <br>
each day will have, whereas MyStrengthProgram will assign the appropriate training days together for the user's weekly availability. <br>

Improving Coupling: <br>
Originally each accessory movement had it's own class (leading to about 12-15 very similar classes). Now a single accessory class <br>
can be instantiated with a constructor of 4 fields to accommodate for each unique accessory. Now when the single accessory class is <br>
refactored there is no need to refactor all 12-15 other similar classes as well.
Inside MyStrengthProgram within the implementation of the methods produceChestArmsDay(), produceLegsDay(), produceBackDay(), <br>
and produceAbDay() was a String which would tell the user to not train 7 days in a row. In order to reduce coupling a <br>
method called mockUser() will handle the String itself while all other methods (which previously implemented the string <br>
in their own method body) will call mockUser() to adhere to the Single Responsibility Principle.
