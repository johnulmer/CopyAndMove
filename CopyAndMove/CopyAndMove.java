package cmdCommands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;

public class CopyAndMove {
	
    public static void writeChars (byte[] bs, String outPath) throws Exception  {
        RandomAccessFile outputFile = new RandomAccessFile(outPath, "rw");
        outputFile.write(bs);
        outputFile.close();
    }
    
    public static byte[] readChars (String inPath) throws Exception {
		byte[] buf = new byte[100];
		byte[] data = null;
		int dataIdx = 0;
			RandomAccessFile f = new RandomAccessFile(inPath, "r");
			data = new byte[(int) f.length()];
			while (true) {
				int nBytes = f.read(buf);
				if (nBytes == -1) {
					break;
				}
				for (int i = 0; i < nBytes; i++) {
					data[dataIdx] = buf[i];
					dataIdx++;
				}
			}
			f.close();
			return data;
    }
    
    public static void moveChars (byte[] bs, String removeFilePath, String newFilePath) throws Exception  {
    	writeChars(bs, newFilePath);
		File f = new File(removeFilePath);
    	f.delete();
    }

	public static void main(String[] args) {
		if (args.length == 2) {
		try {
			//writeChars(readChars(args[0]), args[1]);
			moveChars(readChars(args[0]), args[0], args[1]);
		} catch (Exception ex) {
			System.out.println("file access: " + ex.toString());
		}
		} else {
			System.out.println("requires 2 args, not " + args.length);
		}
		}
	}

