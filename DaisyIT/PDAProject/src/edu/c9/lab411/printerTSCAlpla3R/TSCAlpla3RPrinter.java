package edu.c9.lab411.printerTSCAlpla3R;
import android.app.Activity;
import android.os.Bundle;

import com.example.tscdll.TSCActivity;
public class TSCAlpla3RPrinter extends Activity{
	TSCActivity TscDll;
	String macBluetooth;
	int widthLabel,  heightLabel, speedPrint, density,  typeSensor, heightGap, blackMarkDistance;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		TscDll = new TSCActivity();
	}
	
	/**
	 * @param macBluetooth
	 * @param widthLabel
	 * @param heightLabel
	 * @param speedPrint
	 * @param densyti
	 * @param typeSensor
	 * @param heightGap
	 * @param blackMarkDistance
	 */
	public TSCAlpla3RPrinter(String macBluetooth,int widthLabel, int heightLabel, int speedPrint, int densyti, int typeSensor, int heightGap, int blackMarkDistance ){
		/**
		 * setup(a,b,c,d,e,f,g) 
		 * ----------new label----------
		 * -----------------------------
		 * 
		 * 
		 * 
		 * -----------------------------
		 * 1----------------------------
		 * 
		 * 2*******end label*************
		 * 3------new label--------------
		 * With special Gap
		 * from 1 to 2 is n-Gap
		 * form 2 to 3 is m-Gap 
		 * With normal Gap
		 * from 2 to 3 is m-Gap
		 * a, b string, sets up label width, height; unit: mm
		 * c: string, sets up print speed
		 * d: string, sets up print density
		 * e: string, sets up the sensor type to be used 0 -vertical sensor or 1-black mark sensor
		 * f: string, sets up vertical gap height of the gap/black mark; unit: mm
		 * g: string, sets up shift distance of the gap/black mark; unit:: mm; in the case of the average label, set this parameter to be 0.
		 *
		 */
		this.macBluetooth = macBluetooth;
		this.widthLabel = widthLabel;
		this.heightLabel = heightGap;
		this.speedPrint = speedPrint;
		this.density = densyti;
		this.typeSensor = typeSensor;
		this.heightGap = heightGap;
		this.blackMarkDistance = blackMarkDistance;	
	}
	/**
	 * 
	 */
	public void ConfigTSCAlpla3R(){
		TscDll.openport(macBluetooth);
		TscDll.setup(widthLabel, heightLabel, speedPrint, density, typeSensor, heightGap, blackMarkDistance);
		ClearBufferTSCAlpla3R();
	}
	/**
	 * 
	 */
	public void ClearBufferTSCAlpla3R()
	{
		TscDll.clearbuffer();
	}
	public void ClosePortTSCAlpla3R(){
		TscDll.closeport();
	}
	/**
	 * 
	 * @param command
	 */
	public void SendCommand(String command){
		TscDll.sendcommand(command);
	}
	/**
	 * 
	 * @param xDirection 
	 * @param yDirection
	 * @param codeType - auto convert code - ABC
	 * @param height - height bar code
	 * @param humanReadable - allow human read
	 * @param rotation - rotation bar code
	 * @param narrow - narrow bar code ex:|narrow|
	 * @param wide - width bar code
	 * @param codeNumber 
	 */
	public void PrintBarCode(int xDirection,int yDirection,String codeType, int height, int humanReadable, int rotation, int narrow, int wide, String codeNumber){
		TscDll.barcode(xDirection, yDirection, codeType, height, humanReadable, rotation, narrow, wide, codeNumber);
	}
	/**
	 * function print string
	 * @param xDirection
	 * @param yDirection
	 * @param sizeFont - font text
	 * @param rotation - rotation - text 
	 * @param xMagnificationRate 
	 * @param yMagnificationRate
	 * @param text
	 */
	public void PrintFont(int xDirection,int yDirection, String sizeFont, int rotation ,int xMagnificationRate, int yMagnificationRate, String text)
	{
		TscDll.printerfont(xDirection, yDirection, sizeFont, rotation, xMagnificationRate, yMagnificationRate, text);
	}
	/**
	 * 
	 * @param quantity - quantity print
	 * @param numberLabelCopy - number copy 
	 */
	public void PrintLable(int quantity, int numberLabelCopy){
		TscDll.printlabel(quantity, numberLabelCopy);
	}
	/**
	 * 
	 * @param xDirection
	 * @param yDirection
	 * @param fontHeight
	 * @param rotation
	 * @param fontStyle
	 * @param underline
	 * @param fontTypeFace
	 * @param text
	 */
	public void PrintWindownFont(int xDirection,int yDirection,int fontHeight, int rotation, int fontStyle, int underline, int fontTypeFace, String text){
		
	}
}
