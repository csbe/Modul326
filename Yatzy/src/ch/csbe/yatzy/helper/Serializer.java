package ch.csbe.yatzy.helper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import ch.csbe.yatzy.model.Highscore;

public class Serializer {

	public static void save(Highscore score){
		ObjectOutputStream oos = null;
		FileOutputStream fout = null;
		try{
		    fout = new FileOutputStream("highscore.ser", false);
		    oos = new ObjectOutputStream(fout);
		    oos.writeObject(score);
		} catch (Exception ex) {
		    //ex.printStackTrace();
		} finally {
		    if(oos  != null){
		        try {
					oos.close();
				} catch (IOException e) {
					//e.printStackTrace();
				}
		    } 
		}
	}
	
	public static Highscore load(){
		ObjectInputStream objectinputstream = null;
		FileInputStream streamIn = null;
		Highscore readCase = null;
		try {
		    streamIn = new FileInputStream("highscore.ser");
		    objectinputstream = new ObjectInputStream(streamIn);
		    readCase = (Highscore) objectinputstream.readObject();
		    return readCase;
		} catch (Exception e) {
		    //e.printStackTrace();
		} finally {
		    if(objectinputstream != null){
		        try {
					objectinputstream .close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
		    } 
		}
		return null;
	}
	
}
