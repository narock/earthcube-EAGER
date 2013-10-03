package earthcube.eager.util;

import java.io.File;
import java.util.ArrayList;

public class GetListOfFiles {
  
  ArrayList <String> files = new ArrayList <String> ();

  public void clear () { files.clear(); }
  
  public ArrayList <String> Process ( File aFile ) {
   
    if ( aFile.isFile() ) { files.add( aFile.toString() ); }
     
    if ( aFile.isDirectory() ) {
      File[] listOfFiles = aFile.listFiles();
      if ( listOfFiles != null ) {
        for (int i = 0; i < listOfFiles.length; i++) { Process(listOfFiles[i]); }
      } 
    }
    return files;
    
  }

}