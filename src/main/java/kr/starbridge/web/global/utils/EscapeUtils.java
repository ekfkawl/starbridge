package kr.starbridge.web.global.utils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EscapeUtils {
    public static String unescape(String escaped) {
        Pattern pattern = Pattern.compile("&#(\\d{1,5});");
        Matcher m = pattern.matcher(escaped);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            char c = (char) Integer.parseInt(m.group(1), 10);
            m.appendReplacement(sb, "" + c);
        }
        m.appendTail(sb);
        return sb.toString();
    }

    public static String escape(String csq) {
        Charset charset = Charset.forName("EUC-KR");
        CharsetEncoder encoder = charset.newEncoder();
        int size = csq.length() * 2 < 1024 ? 1024 : csq.length() * 2;
        ByteBuffer outBuffer = ByteBuffer.allocate(size);

        CharBuffer inputBuffer = CharBuffer.wrap(csq);

        for (CoderResult coderResult = encoder.encode(inputBuffer, outBuffer, true); coderResult
                .isError(); coderResult = encoder.encode(inputBuffer, outBuffer, true)) {
            if (coderResult.isUnmappable()) {
                CharSequence subSequence = inputBuffer.subSequence(0, coderResult.length());
                for (int i = 0; i < coderResult.length(); i++) {
                    outBuffer.put(charset.encode(numericCharacterReference(subSequence.charAt(i))));
                }

            } else {
                outBuffer.putChar('?');
            }

            inputBuffer.position(inputBuffer.position() + coderResult.length());
        }
        encoder.flush(outBuffer);
        outBuffer.flip();

        CharBuffer decode = charset.decode(outBuffer);
        return decode.toString();
    }

    private static String numericCharacterReference(char oneChar) {
        return "&#" + Integer.toString(oneChar) + ";";
    }
}
