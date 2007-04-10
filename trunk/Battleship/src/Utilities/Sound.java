package Utilities;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.sound.sampled.AudioFormat;
import java.net.MalformedURLException;

/**
 * Utility class to play sound files.  Received from SeungJin Lim.
 * 
 * 
 * @author SeunqJin Lim
 *
 */
public class Sound {

	File soundFile;
	AudioInputStream sound;
	Clip soundClip;

	public Sound(String filename){
		// specify the sound to play
		this.soundFile = new File(filename);

		// (assuming the sound can be played by the audio system)
		try {
			this.sound = AudioSystem.getAudioInputStream(this.soundFile);
		} catch (UnsupportedAudioFileException e) {
			System.err.println( "Unsupported Audio File: " + filename );
			System.exit(1);
		} catch (IOException e) {
			System.err.println( "IO error: " + filename );
			System.exit(1);
		}

		// load the sound into memory (a Clip)
		DataLine.Info info = new DataLine.Info(Clip.class, this.sound.getFormat());
		try {
			this.soundClip = (Clip) AudioSystem.getLine(info);
		} catch (LineUnavailableException e) {
			System.err.println( "Audio Line Unavailable: " + filename );
			System.exit(1);
		}
		try {
			this.soundClip.open(this.sound);
		} catch (LineUnavailableException e) {
			System.err.println( "Audio Line Unavailable: " + filename );
			System.exit(1);
		} catch (IOException e) {
			System.err.println( "Audio IO Exception: " + filename );
			System.exit(1);
		}

	}

	public void playSound(){
		this.soundClip.start();
	}


	public static void playSound( String audiofile ) {
		try {
			// From file
			AudioInputStream stream = AudioSystem.getAudioInputStream(new File(audiofile));

			// From URL
			//stream = AudioSystem.getAudioInputStream(new URL("http://hostname/audiofile"));

			// At present, ALAW and ULAW encodings must be converted
			// to PCM_SIGNED before it can be played
			AudioFormat format = stream.getFormat();
			if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
				format = new AudioFormat(
						AudioFormat.Encoding.PCM_SIGNED,
						format.getSampleRate(),
						//format.getSampleSizeInBits()*2,
						format.getSampleSizeInBits(),
						format.getChannels(),
						//format.getFrameSize()*2,
						format.getFrameSize(),
						format.getFrameRate(),
						true);        // big endian
				stream = AudioSystem.getAudioInputStream(format, stream);
			}

			// Create the clip
			DataLine.Info info = new DataLine.Info(
					Clip.class, stream.getFormat(), ((int)stream.getFrameLength()*format.getFrameSize()));
			Clip clip = (Clip) AudioSystem.getLine(info);

			// This method does not return until the audio file is completely loaded
			clip.open(stream);

			// Start playing: Play once
			clip.start();

			// Play and loop forever
			//clip.loop(Clip.LOOP_CONTINUOUSLY);

			// Play and repeat for a certain number of times
			//int numberOfPlays = 3;
			//clip.loop(numberOfPlays-1);

		} catch (MalformedURLException e) {
			System.err.println("sound: MalformedURLException by "+audiofile);
		} catch (IOException e) {
			System.err.println("sound: IOException by "+audiofile);
		} catch (LineUnavailableException e) {
			System.err.println("sound: LineUnavailableException by "+audiofile);
		} catch (UnsupportedAudioFileException e) {
			System.err.println("sound: UnsupportedAudioFileException by "+audiofile);
		}
	}
}    