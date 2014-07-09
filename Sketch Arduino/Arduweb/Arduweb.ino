#define ABUTTON 9
#define BBUTTON 8
#define CBUTTON 12
#define DBUTTON A1
#define STICKBUTTON A0
#define XAXIS A3
#define YAXIS A2

int change = 0;
int xAxisDiff = 0;
int yAxisDiff = 0;
int aButtonStatus = -1;
int bButtonStatus = -1;
int cButtonStatus = -1;
int dButtonStatus = -1;
int stickButtonStatus = -1;
int xAxisStatus = -1;
int yAxisStatus = -1;
int lastAButtonStatus = -1;
int lastBButtonStatus = -1;
int lastCButtonStatus = -1;
int lastDButtonStatus = -1;
int lastStickButtonStatus = -1;
int lastXAxisStatusStatus = -1;
int lastYAxisStatusStatus = -1;

void setup () {
	Serial.begin (9600);
	pinMode (ABUTTON, INPUT);
	pinMode (CBUTTON, INPUT);
	pinMode (BBUTTON, INPUT);
	pinMode (DBUTTON, INPUT);
	pinMode (STICKBUTTON, INPUT);
	pinMode (XAXIS, INPUT);
	pinMode (YAXIS, INPUT);
}

void loop () {
	// Read button status
	aButtonStatus = digitalRead(ABUTTON);
	bButtonStatus = digitalRead(BBUTTON);
	cButtonStatus = digitalRead(CBUTTON);
	dButtonStatus = digitalRead(DBUTTON);
	stickButtonStatus = digitalRead(STICKBUTTON);
	xAxisStatus = analogRead(XAXIS);
	yAxisStatus = analogRead(YAXIS);
	// Calculate axis difference (3+ to change)
	xAxisDiff = lastXAxisStatusStatus - xAxisStatus;
	yAxisDiff = lastYAxisStatusStatus - yAxisStatus;
	// Determine if a change occurred
	change = lastAButtonStatus != aButtonStatus;
	change|= lastBButtonStatus != bButtonStatus;
	change|= lastCButtonStatus != cButtonStatus;
	change|= lastDButtonStatus != dButtonStatus;
	change|= lastStickButtonStatus != stickButtonStatus;
	change|= (xAxisDiff >= 3) || (xAxisDiff <= -3);
	change|= (yAxisDiff >= 3) || (yAxisDiff <= -3);
	// Write the serial values if there was a change
	if(change) {
		Serial.print (aButtonStatus);
		Serial.print (";");
		Serial.print (bButtonStatus);
		Serial.print (";");
		Serial.print (cButtonStatus);
		Serial.print (";");
		Serial.print (dButtonStatus);
		Serial.print (";");
		Serial.print (stickButtonStatus);
		Serial.print (";");
		Serial.print (xAxisStatus);
		Serial.print (";");
		Serial.println (yAxisStatus);
	}
	// Update last buttons status
	lastAButtonStatus = aButtonStatus;
	lastBButtonStatus = bButtonStatus;
	lastCButtonStatus = cButtonStatus;
	lastDButtonStatus = dButtonStatus;
	lastStickButtonStatus = stickButtonStatus;
	lastXAxisStatusStatus = xAxisStatus;
	lastYAxisStatusStatus = yAxisStatus;
	delay (20);
}