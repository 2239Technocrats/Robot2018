# Robot 2018
FIRST team 2239's robot code for 2018

# Controller Documentation
Buttons
--
`DriverStation.getStickButton(int controllerPort, byte button);`  
Returns a boolean, true if the controller on controllerPort has button pushed.  
**Button values**  
1 - A  
2 - B  
3 - X  
4 - Y  
5 - LB  
6 - RB  
7 - Back  
8 - Start  
9 - Left Thumbstick  
10 -  Right Thumbstick  
  
Axis (Joysticks, Triggers)
--
`DriverStation.getStickAxis(int controllerPort, int axis);`  
Returns a double between -1 and 1 for joysticks and 0 to 1 for triggers representing the current position of said axis. controllerPort is the port of the controller checked, and axis represents what axis to check.  
**Axis Values**  
0 - Left Stick X  
1 - Left Stick Y  
2 - Left Trigger  
3 - Right Trigger  
4 - Right Stick X  
5 - Right Stick Y
  
POV Positions (D-Pad)
-- 
`DriverStation.getStickPOV(int controllerPort, int position);`  
Retruns a boolean, true if the POV of controller on controllerPort is in position.  
**Position values**  
0 - Idle  
1 - Up  
2 - Up-Right  
3 - Right  
4 - Down-Right  
5 - Down  
6 - Down-Left  
7 - Left  
8 - Left-Up  
  
