package com.broscoding.flyingsheep.utils;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class UTF8Utils {
	
	public static boolean isUTF8(String s) {
		CharsetDecoder cs = Charset.forName("UTF-8").newDecoder();
		
		try {
			cs.decode(ByteBuffer.wrap(s.getBytes()));
			return false;
		}
		catch(CharacterCodingException e) {
			return true;
		}
	}
}