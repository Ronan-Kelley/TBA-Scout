# TBA-Scout
yet another scouting application for FIRST that pulls from TBA, but this time with desktop and command line use in mind. 

# CLI-Options
-key [TBA_Auth_Key] 

specifies the auth key to be used to access the blue alliance API. If you don't have one, create an account, go to your settings page, and generate one. This is no longer required for the GUI to launch, but ALL functionality for both the GUI and the command line implementation require a valid key to be input.
    
-cli

launch without graphics

-path [path]

specify a path on TheBlueAlliance to request data from, IE /status

-chart

attempt to create an image of a chart of a team's scores. Requires the use of -path with the simple matches path (/team/{team_key}/matches/{year}/simple). Default filename is image.png.

-o

if used with -chart, the name of the file that -chart outputs will be set to the text between this option and the next argument.
