package net.falesnik.minecraft.DynamicWhitelist;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

public class Utils {

	public static String readString(final InputStream is, final int bufferSize, final String encoding)
	{
	    final char[] buffer = new char[bufferSize];
	    final StringBuilder out = new StringBuilder();
	    try
	    {
	        final Reader in = new InputStreamReader(is, encoding);
	        try
	        {
	            for (;;)
	            {
	                int rsz = in.read(buffer, 0, buffer.length);
	                if (rsz < 0)
	                    break;
	                out.append(buffer, 0, rsz);
	            }
	        }
	        finally
	        {
	            in.close();
	        }
	    }
	    catch (UnsupportedEncodingException ex)
	    {
	    /* ... */
	    }
	    catch (IOException ex)
	    {
	      /* ... */
	    }
	    return out.toString();
	}
}
