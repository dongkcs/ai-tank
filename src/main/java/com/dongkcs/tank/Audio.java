
package com.dongkcs.tank;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;

public class Audio extends Thread{

//	byte[] b = new byte[1024 * 1024 * 15];

	@Override
	public void run() {
		byte[] b = new byte[1024];
		int len = 0;
		try {
			sourceDataLine.open(audioFormat, 1024);
			sourceDataLine.start();
			while ((len = audioInputStream.read(b)) > 0) {
				sourceDataLine.write(b, 0, len);
			}
			audioInputStream.close();
			sourceDataLine.drain();
			sourceDataLine.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}



	private AudioFormat audioFormat = null;
	private SourceDataLine sourceDataLine = null;
	private DataLine.Info dataLine_info = null;

	private AudioInputStream audioInputStream = null;

	public Audio(String fileName) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(Audio.class.getClassLoader().getResourceAsStream(fileName));
			audioFormat = audioInputStream.getFormat();
			dataLine_info = new DataLine.Info(SourceDataLine.class, audioFormat);
			sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLine_info);
			//FloatControl volctrl=(FloatControl)sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
			//volctrl.setValue(-40);//

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	

	public void close() {
		try {
			audioInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//						audio/explode.wav
		// Audio a = new Audio("audio/explode.wav");
		Audio a = new Audio("audio/explode.wav");
		a.run();
	}

}
