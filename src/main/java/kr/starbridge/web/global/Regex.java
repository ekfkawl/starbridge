package kr.starbridge.web.global;

public class Regex {
    /** 공백 제거 */
    public final static String VACUUM = "\\p{Z}";
    /** 배틀태그 양식 */
    public final static String BATTLE_TAG = "[0-9ㄱ-ㅎ가-힣A-Za-z]{1,32}\\#[0-9]{4,6}";
    /** MD5 해시 양식 */
    public final static String MD5_HASH = "^[a-zA-Z0-9]{32,32}$";
}
