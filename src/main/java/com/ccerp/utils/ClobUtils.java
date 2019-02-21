package com.ccerp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Clob;
import java.sql.SQLException;

public class ClobUtils {

	public static String clobToString(Clob clob) {

		String result="";
		try{
			BufferedReader in = new BufferedReader(clob.getCharacterStream());
			StringWriter out = new StringWriter();
			int c;
			while ((c = in.read()) != -1) {
				out.write(c);
			}
			result=out.toString();
		}catch(SQLException  ex){
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result ;
	}
}
