# Robot 2018
FIRST team 2239's robot code for 2018

# Documentation
Controller Required Imports
--  
`import edu.wpi.first.wpilibj.XboxController;` Initalizing and using Xbox Controllers  
`import edu.wpi.first.wpilibj.Joystick;` Initalizing and using Joysticks  

Initalizing a Controller
--  
`public XboxController controller = new XboxController(int controllerPort);`  
`public Joystick controller = new Joystick(int controllerPort);`  
Initalizes either a XboxController or a Joystick and gives it the port controllerPort.

Buttons
--
`controllerClass.getRawButton(int button);`  
Returns a boolean, true if button on controllerClass has button pushed.  
**Button values (XboxController)**  
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
**Button values (Joystick)**  
Buttons labeled on physical joystick correspondingly  
  
Axis (Joysticks, Triggers)
--
`controllerClass.getRawAxis(int which);`  
Returns a double between -1 and 1 for joysticks and 0 to 1 for triggers representing the current position of said axis. Which represents what axis to check.  
**Axis Values (XboxController)**  
0 - Left Stick X  
1 - Left Stick Y  
2 - Left Trigger  
3 - Right Trigger  
4 - Right Stick X  
5 - Right Stick Y  
**Axis Values (Joystick)**  
0 - X Axis (left/right)  
1 - Y Axis (up/down)  
2 - Z Rotation (twist)  
3 - Slider  
  
POV Positions (D-Pad)
-- 
`controllerClass.getPOV(int pov);`  
Retruns a boolean, true if the POV of controllerClass is in the position defined by int pov.  
**Position values (XboxController/Joystick)**  
0 - Idle  
1 - Up  
2 - Up-Right  
3 - Right  
4 - Down-Right  
5 - Down  
6 - Down-Left  
7 - Left  
8 - Left-Up  
  
Required Motor Imports
--  
`import com.ctre.phoenix.motorcontroll.can.WPI_TalonSRX` Motor controller's api  

Controlling Motors
--  
`WPI_TalonSRX.set(double powerLevel);`  
Sets the level of power provided to motor controller of typ WPI_TalonSRX to powerLevel.  
**1.0 >= powerLevel >= -1.0**
