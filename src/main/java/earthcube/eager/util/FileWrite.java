/**
 * Copyright (C) 2011 Tom Narock and Eric Rozell
 *
 *     This software is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this software.  If not, see <http://www.gnu.org/licenses/>.
 */
package earthcube.eager.util;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for writing text to a file
 * @author Tom Narock
 */
public class FileWrite {
	
	/**
	 * Method to append a string to a file
	 * @param file full path and filename of file to write to
	 * @param line string to write to the file
	 * @param b boolean true = prepend date/time to line, false = don't prepend date/time
	 */
   public void append (String file, String line, boolean b) {
		  
		String dateStr = null;
		if (b) {
		  DateFormat dateFormat = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");
		  Date date = new java.util.Date ();
		  dateStr = dateFormat.format (date);
		} 
		try {
		  // append to file, create if doesn't exist 
		  FileWriter fstream = new FileWriter(file,true);
		  BufferedWriter out = new BufferedWriter(fstream);
		  if (b) out.write(dateStr + ", " + line + "\n");
		  if (!b) out.write(line + "\n");
		  //Close the output stream
		  out.close();
	    }catch (Exception e){ }
   }
	  
   /**
	 * Method to append a string to a file
	 * @param file full path and filename of file to write to
	 * @param line string to write to the file
	 */
   public void append (String file, String line){
     try{
       // append to file, create if doesn't exist 
       FileWriter fstream = new FileWriter(file,true);
       BufferedWriter out = new BufferedWriter(fstream);
       out.write(line + "\n");
       //Close the output stream
       out.close();
     }catch (Exception e){ }
   }
    
   /**
	 * Method to create a new file and write to it
	 * @param file full path and filename of file to write to
	 * @param line string to write to the file
	 */
   public void newFile (String file, String line){
	     try{
	       // create new file
	    	 if ((new File(file).exists()))
	    		System.err.println("File: " + file + " already exists.  Overwriting.");
	       FileWriter fstream = new FileWriter(file);
	       BufferedWriter out = new BufferedWriter(fstream);
	       out.write(line + "\n");
	       //Close the output stream
	       out.close();
	     }catch (Exception e){ }
   }
   
}